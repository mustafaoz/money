package uk.co.incircity.money.controller;

import uk.co.incircity.money.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.incircity.money.service.MoneyService;

import java.util.List;


@RestController
public class MoneyController {

    @Autowired
    MoneyService moneyService;

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name) {
        return "Hello " + name + "!";
    }

    @RequestMapping("/accounts")
    public List<Account> getAccountList() {
        return moneyService.getAccountList();
    }
}
