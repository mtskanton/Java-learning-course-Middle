package app.example.controller;

import app.example.exception.TransferException;
import app.example.form.TransferForm;
import app.example.model.Account;
import app.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Контроллер Account
 */
@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public String getAccounts(Model model) {
        List<Account> list = this.accountService.getAccounts();
        model.addAttribute("accounts", list);
        model.addAttribute("transferForm", new TransferForm());
        return "accounts";
    }

    @PostMapping("/transfer")
    public String getAccountsForTransfer(Model model, TransferForm transferForm) {
        try {
            this.accountService.transfer(
                    transferForm.getFromId(),
                    transferForm.getToId(),
                    transferForm.getSum()
            );
        } catch (TransferException ate) {
            model.addAttribute("error", ate.getMessage());
            this.getAccounts(model); //данные об ошибке присоединяем к прочим данным модели из метода getAccounts.
            return "accounts";
        }
        return "redirect:/accounts";
    }
}
