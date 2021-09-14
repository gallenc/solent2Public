/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.spring.web;

import java.util.Date;
import java.util.Map;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.BankTransactionStatus;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.TransactionReplyMessage;
import org.solent.com504.oodd.bank.model.dto.TransactionRequestMessage;
import org.solent.com504.oodd.bank.model.service.BankService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author cgallen
 */
@RestController
@RequestMapping("/rest/")
public class ReSTController {

    private static final Logger LOG = LogManager.getLogger(ReSTController.class);

    @Autowired
    private BankService bankService;

    @RequestMapping(value = "/test", method = {RequestMethod.POST, RequestMethod.GET},
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> testRequest(@RequestBody(required = false) TransactionRequestMessage transactionRequestMessage,
            @RequestHeader Map<String, String> headers) {
        TransactionReplyMessage replyMessage = new TransactionReplyMessage();

        replyMessage.setMessage("test message received");

        return ResponseEntity.status(HttpStatus.OK).body(replyMessage);
    }

    /*
    TransactionReplyMessage transactionRequest(TransactionRequestMessage transactionRequestMessage);
     */
    @Transactional
    @RequestMapping(value = "/transactionRequest", method = {RequestMethod.POST},
            consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> transactionRequest(@RequestBody(required = true) TransactionRequestMessage transactionRequestMessage,
            @RequestHeader Map<String, String> headers) {

        Double amount = transactionRequestMessage.getAmount();
        CreditCard fromCard = transactionRequestMessage.getFromCard();
        CreditCard toCard = transactionRequestMessage.getToCard();

        String authString = headers.get("authorization");

        BankTransaction result;

        TransactionReplyMessage replyMessage = new TransactionReplyMessage();

        if (authString == null) {
            LOG.debug("message received without basic auth :" + transactionRequestMessage);
            result = bankService.transferMoney(fromCard, toCard, amount);
        } else {
            //decode auth string and use
            String[] userPass = BasicAuthCoder.decode(authString);
            String toUserName = userPass[0];
            String toUserPassword = userPass[1];
            LOG.debug("message received with basic auth toUserName: " + toUserName
                    + " request: " + transactionRequestMessage);
            try {
                result = bankService.transferMoneyAuth(fromCard, toCard, amount, toUserName, toUserPassword);
            } catch (SecurityException ex) {
                replyMessage.setCode(HttpStatus.UNAUTHORIZED.value());
                replyMessage.setMessage(ex.getMessage());
                LOG.error("security exception toUserName" + toUserName + " " + ex.getMessage());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(replyMessage);
            }
        }

        replyMessage.setFromCardNo(fromCard.getCardnumber());
        replyMessage.setToCardNo(toCard.getCardnumber());
        replyMessage.setTransactionDate(result.getTransactionDate());
        replyMessage.setTransactionId(result.getTransactionId());
        replyMessage.setMessage(result.getMessage());
        replyMessage.setStatus(result.getStatus());
        replyMessage.setAmount(result.getAmount());

        // differnt reply code depending on result
        if (BankTransactionStatus.SUCCESS == result.getStatus()) {
            replyMessage.setCode(HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body(replyMessage);
        } else {
            replyMessage.setCode(HttpStatus.BAD_REQUEST.value());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(replyMessage);
        }

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<TransactionReplyMessage> handleGenericException(Exception ex, WebRequest request) {
        LOG.error("genericExceptionHandlerCalled for exception: ", ex);
        TransactionReplyMessage errorMessage = new TransactionReplyMessage();
        errorMessage.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorMessage.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
