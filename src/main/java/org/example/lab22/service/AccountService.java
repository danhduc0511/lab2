package org.example.lab22.service;

import org.example.lab22.model.Account;
import org.example.lab22.repository.AccountReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountReponsitory accountReponsitory;
    public List<Account> getAllAccounts() throws SQLException {
        return accountReponsitory.getAllAccounts();
    }
    public Account getAccountById(int id) throws SQLException {
        return accountReponsitory.getAccountById(id);
    }
    public int addAccount(Account account) throws SQLException {
        return accountReponsitory.addAccount(account);
    }
    public int updateAccount(int id,Account account) throws SQLException {
        return accountReponsitory.updateAccount(id, account);
    }
    public int deleteAccount(int id) throws SQLException {
        return accountReponsitory.deleteAccount(id);
    }

}
