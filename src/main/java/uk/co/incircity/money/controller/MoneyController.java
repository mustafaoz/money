package uk.co.incircity.money.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.incircity.money.model.Account;
import uk.co.incircity.money.model.MoneyTransfer;
import uk.co.incircity.money.service.MoneyService;

import java.util.List;


@RestController
public class MoneyController {

    @Autowired
    MoneyService moneyService;

    @PostMapping(path = "/transfer", consumes = "application/json")
    public ResponseEntity transferMoney(@RequestBody MoneyTransfer moneyTransfer) {

        try {
            moneyService.transferMoney(moneyTransfer);
        }
        catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Transfer has been done successfully");
    }

    @GetMapping(path = "/accounts", produces = "application/json")
    public ResponseEntity<List<Account>> getAccounts() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(moneyService.getAccountList());
    }
}
