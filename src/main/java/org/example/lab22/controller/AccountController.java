package org.example.lab22.controller;

import org.example.lab22.model.Account;
import org.example.lab22.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() throws SQLException {

        if (accountService.getAllAccounts().isEmpty()) {

            return new ResponseEntity<List<Account>>(Collections.emptyList(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Account>>(accountService.getAllAccounts(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Integer> addAccount(@RequestBody Account account) throws SQLException {
        if(accountService.addAccount(account)>0){
            return ResponseEntity.status(HttpStatus.CREATED).body(account.getId());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Integer> updateAccount(@PathVariable("id") int id, @RequestBody Account account) throws SQLException {
        int rows = accountService.updateAccount(id,account);
        if(rows>0){
            return ResponseEntity.status(HttpStatus.OK).body(rows);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") int id) throws SQLException {
        Account account = accountService.getAccountById(id);
        if(account!=null){
            return ResponseEntity.status(HttpStatus.OK).body(account);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteAccount(@PathVariable("id") int id) throws SQLException {
        int rows = accountService.deleteAccount(id);
        if(rows>0){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rows);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(-1);
    }

}
