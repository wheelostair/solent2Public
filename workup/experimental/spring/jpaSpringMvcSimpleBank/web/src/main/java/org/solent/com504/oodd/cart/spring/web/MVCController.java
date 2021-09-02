package org.solent.com504.oodd.cart.spring.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.oodd.bank.model.dto.BankAccount;
import org.solent.com504.oodd.bank.model.dto.BankTransaction;
import org.solent.com504.oodd.bank.model.dto.CreditCard;
import org.solent.com504.oodd.bank.model.dto.User;
import org.solent.com504.oodd.bank.model.service.BankService;
import org.solent.com504.oodd.bank.service.BankServiceImpl;
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

    private static final Logger LOG = LogManager.getLogger(MVCController.class);

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
    public String home(@RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "sortCode", required = false) String sortCode, // used to select account from bankaccounts page
            @RequestParam(name = "accountNo", required = false) String accountNo, // used to select account from bankaccounts page
            @RequestParam(name = "fromSortCode", required = false) String fromSortCode,
            @RequestParam(name = "fromAccountNo", required = false) String fromAccountNo,
            @RequestParam(name = "toSortCode", required = false) String toSortCode,
            @RequestParam(name = "toAccountNo", required = false) String toAccountNo,
            @RequestParam(name = "amount", required = false) String amountStr,
            Model model,
            HttpSession session) {

        // used to set tab selected
        model.addAttribute("selectedPage", "home");
        String message = "";
        String errorMessage = "";

        // persist to and from values in session so that persist between page pulls
        if (fromSortCode == null || fromSortCode.trim().isEmpty()) {
            fromSortCode = (session.getAttribute("fromSortCode") == null) ? "" : (String) session.getAttribute("fromSortCode");
        }
        session.setAttribute("fromSortCode", fromSortCode);

        if (toSortCode == null || toSortCode.trim().isEmpty()) {
            toSortCode = (session.getAttribute("toSortCode") == null) ? "" : (String) session.getAttribute("toSortCode");
        }
        session.setAttribute("toSortCode", toSortCode);

        if (fromAccountNo == null || fromAccountNo.trim().isEmpty()) {
            fromAccountNo = (session.getAttribute("fromAccountNo") == null) ? "" : (String) session.getAttribute("fromAccountNo");
        }
        session.setAttribute("fromAccountNo", fromAccountNo);

        if (toAccountNo == null || toAccountNo.trim().isEmpty()) {
            toAccountNo = (session.getAttribute("toAccountNo") == null) ? "" : (String) session.getAttribute("toAccountNo");
        }
        session.setAttribute("toAccountNo", toAccountNo);

        Double amount = 0.0;
        BankTransaction bankTransactionResult = null;
        try {
            amount = (amountStr == null) ? 0.0 : Double.valueOf(amountStr);
        } catch (NumberFormatException ex) {
            errorMessage = "invalid amont value: " + amountStr;
        }

        if (action == null) {
            // do nothing but show page
        } else if ("selectFromAccount".equals(action)) {
            fromSortCode = sortCode;
            session.setAttribute("fromSortCode", fromSortCode);
            fromAccountNo = accountNo;
            session.setAttribute("fromAccountNo", fromAccountNo);
        } else if ("selectToAccount".equals(action)) {
            toSortCode = sortCode;
            session.setAttribute("toSortCode", fromSortCode);
            toAccountNo = accountNo;
            session.setAttribute("toAccountNo", fromAccountNo);
        } else if ("transferMoneyBetweenAccounts".equals(action)) {

            BankAccount fromAccount = bankAccountRepository.findBankAccountByNumber(fromSortCode, fromAccountNo);
            boolean error = false;
            if (fromAccount == null) {
                errorMessage = "unknown FROM bank account: " + fromSortCode + " " + fromAccountNo;
                error = true;
            }
            BankAccount toAccount = bankAccountRepository.findBankAccountByNumber(toSortCode, toAccountNo);
            if (toAccount == null) {
                errorMessage = errorMessage + " unknown TO bank account: " + toSortCode + " " + toAccountNo;
                error = true;
            }
            if (!error) {
                bankTransactionResult = bankService.transferMoney(fromAccount, toAccount, amount);
                model.addAttribute("bankTransactionResult", bankTransactionResult);
            }

        } else {
            message = "unknown action=" + action;
        }

        // populate model with values
        model.addAttribute("fromSortCode", fromSortCode);
        model.addAttribute("fromAccountNo", fromAccountNo);
        model.addAttribute("toSortCode", toSortCode);
        model.addAttribute("toAccountNo", toAccountNo);
        model.addAttribute("amount", amount);

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
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "secondName", required = false) String secondName,
            @RequestParam(name = "address", required = false) String address,
            @RequestParam(name = "accountactive", required = false) String accountactive,
            @RequestParam(name = "supportedIssuerBank", required = false) String supportedIssuerBank,
            @RequestParam(name = "balance", required = false) String balanceStr,
            Model model) {
        // used to set tab selected
        model.addAttribute("selectedPage", "bankaccounts");
        String message = "";
        String errorMessage = "";

        Double balance = 0.0;
        try {
            balance = (balanceStr == null) ? 0.0 : Double.valueOf(balanceStr);
        } catch (NumberFormatException ex) {
            errorMessage = "invalid balance value: " + balanceStr;
        }

        // todo replace with log
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxx bankaccountview called: action=" + action
                + " sortCode=" + sortCode
                + " accountNo=" + accountNo
                + " firstName=" + firstName
                + " secondName=" + secondName
                + " address=" + address
                + " accountactive=" + accountactive
                + " supportedIssuerBank=" + supportedIssuerBank
                + "balanceStr=" + balanceStr);

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
            bankAccount.setBalance(balance);
            User owner = new User();
            owner.setAddress(address);
            owner.setFirstName(firstName);
            owner.setSecondName(secondName);
            bankAccount.setOwner(owner);
            if (accountactive != null) {
                bankAccount.setActive(true);
            } else {
                bankAccount.setActive(false);
            }
            CreditCard cc = bankAccount.getCreditcard();
            cc.setName(firstName + " " + secondName);
            bankAccount.setCreditcard(cc);

            bankAccount = bankAccountRepository.save(bankAccount);
            message = "updated account: sort code: " + bankAccount.getSortcode() + " account no: " + bankAccount.getAccountNo();

        } else if ("create".equals(action)) {
            User user = new User();
            user.setFirstName(firstName);
            user.setAddress(address);
            user.setSecondName(secondName);
            bankAccount = bankService.createBankAccount(user, supportedIssuerBank);

            message = "fill in user details for new account: sort code: " + bankAccount.getSortcode() + " account no: " + bankAccount.getAccountNo();
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
