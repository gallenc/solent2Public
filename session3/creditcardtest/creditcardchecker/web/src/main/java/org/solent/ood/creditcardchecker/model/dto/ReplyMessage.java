/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.creditcardchecker.model.dto;

import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;

/**
 *
 * @author cgallen
 */
public class ReplyMessage {
    
    String message;
    
    int code;
    
    CardValidationResult cardValidationResult;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }



    public CardValidationResult getCardValidationResult() {
        return cardValidationResult;
    }

    public void setCardValidationResult(CardValidationResult cardValidationResult) {
        this.cardValidationResult = cardValidationResult;
    }

    @Override
    public String toString() {
        return "ReplyMessage{" + "message=" + message + ", code=" + code + ", cardValidationResult=" + cardValidationResult + '}';
    }
    
    
    
}
