package org.example.lab22.controller;

import org.example.lab22.enity.Account;
import org.example.lab22.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts(
            @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize ,
            @RequestParam(value = "pageNo",defaultValue = "0")Integer pageNo,
            @RequestParam(value = "sortBy",defaultValue = "id")String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc")String sortDir
    ) throws SQLException {
        List<Account> accounts = accountService.getAllAccounts(pageSize,pageNo,sortBy,sortDir);

        if (accounts.isEmpty()) {

            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(accounts);
    }
    @PostMapping
    public ResponseEntity<Account> addAccount(@RequestBody Account account) throws SQLException {
        if(accountService.addAccount(account)!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(account);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Account) null);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable("id") Long id, @RequestBody Account account) throws SQLException {
        Account account1 = accountService.updateAccount(id,account);
        if(account1!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(account);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body((Account) null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable("id") Long id) throws SQLException {
        return accountService.getAccountById(id).map(account -> ResponseEntity.status(HttpStatus.OK).body(account)).
                orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body((Account) null));


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteAccount(@PathVariable("id") Long id) throws SQLException {
        int rows = accountService.deleteAccount(id);
        if(rows>0){
            return ResponseEntity.status(HttpStatus.OK).body(rows);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
    }
    @GetMapping("/username/{username}")
    public ResponseEntity<Account> getAccountByUsername(@PathVariable("username") String username) throws SQLException {
        return accountService.getAccountByUsername(username).map(account -> ResponseEntity.ok(account)).
                orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body((Account) null));
    }


}
