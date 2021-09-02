package org.solent.com504.oodd.cart.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.bank.model.service.BankService;
import org.solent.com504.oodd.dao.impl.BankAccountRepository;
import org.solent.com504.oodd.dao.impl.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MVCController {

    @Autowired
    private BankService bankService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    // this redirects calls to the root of our application to index.html
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String viewCart(@RequestParam(name = "action", required = false) String action,
            Model model,
            HttpSession session) {

        // used to set tab selected
        model.addAttribute("selectedPage", "home");
        String message = "";
        String errorMessage = "";

        if (action == null) {
            // do nothing but show page
        } else if ("xxx".equals(action)) {

        } else {
            message = "unknown action=" + action;
        }

        // populate model with values
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "home";
    }

    @RequestMapping(value = "/bankaccounts", method = {RequestMethod.GET, RequestMethod.POST})
    public String bankAccounts(@RequestParam(name = "action", required = false) String action,
            Model model) {
        // used to set tab selected
        model.addAttribute("selectedPage", "bankaccounts");

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        model.addAttribute("bankAccounts", bankAccounts);

        return "bankaccounts";
    }

    @RequestMapping(value = "/bankaccountview", method = {RequestMethod.GET, RequestMethod.POST})
    public String bankAccountView(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "sortCode", required = false) String sortCode,
            @RequestParam(name = "accountNo", required = false) String accountNo,
            @RequestParam(name = "firstName ", required = false) String firstName,
            @RequestParam(name = "secondName", required = false) String secondName,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "supportedIssuerBank", required = false) String supportedIssuerBank,
            Model model) {
        // used to set tab selected
        model.addAttribute("selectedPage", "bankaccounts");
        String message = "";
        String errorMessage = "";
        // list all
        // add bank account
        // deactivate / activate account
        BankAccount bankAccount = new BankAccount();

        if ("view".equals(action)) {
            // do nothing but show page
            bankAccount = bankAccountRepository.findBankAccountByNumber(sortCode, accountNo);
            if (bankAccount == null) {
                throw new IllegalArgumentException("unknown bank account: " + sortCode + " " + accountNo);
            }
        } else if ("update".equals(action)) {
            bankAccount = bankAccountRepository.findBankAccountByNumber(sortCode, accountNo);
            if (bankAccount == null) {
                throw new IllegalArgumentException("unknown bank account: " + sortCode + " " + accountNo);
            }
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX bankAccount.getOwner()"+bankAccount.getOwner());
            bankAccount.getOwner().setAddress(address);
            bankAccount.getOwner().setFirstName(firstName);
            bankAccount.getOwner().setSecondName(secondName);
            
            bankAccount = bankAccountRepository.save(bankAccount);
            
        } else if ("create".equals(action)) {
           User user = new User();
           user.setFirstName(firstName);
           user.setAddress(address);
           user.setSecondName(secondName);
           bankAccount = bankService.createBankAccount(user, supportedIssuerBank);

           message = "fill in user details for new account: sort code: "+bankAccount.getSortcode()+" account no: "+bankAccount.getAccountNo()+ "owner: "+bankAccount.getOwner();
        } else {
            throw new IllegalArgumentException("unknown action=" + action);
        }

        model.addAttribute("bankAccount", bankAccount);
        // populate model with values
        model.addAttribute("message", message);
        model.addAttribute("errorMessage", errorMessage);

        return "bankaccountview";
    }

    @RequestMapping(value = "/banktransactions", method = {RequestMethod.GET, RequestMethod.POST})
    public String bankTransactions(Model model) {
        // used to set tab selected
        List<BankTransaction> bankTransactions = bankTransactionRepository.findAll();
        model.addAttribute("bankTransactions", bankTransactions);

        model.addAttribute("selectedPage", "banktransactions");
        return "banktransactions";
    }

    @RequestMapping(value = "/contact", method = {RequestMethod.GET, RequestMethod.POST})
    public String contactCart(Model model) {
        // used to set tab selected
        model.addAttribute("selectedPage", "contact");
        return "contact";
    }

    @RequestMapping(value = "/about", method = {RequestMethod.GET, RequestMethod.POST})
    public String abount(Model model) {
        // used to set tab selected
        model.addAttribute("selectedPage", "about");
        return "about";
    }


    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for user
    }

}
