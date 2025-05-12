package org.example.lab22.repository;

import org.example.lab22.model.Account;

import java.sql.SQLException;
import java.util.List;

public interface AccountReponsitory {
    public int addAccount(Account account) throws SQLException;
    public List<Account> getAllAccounts() throws SQLException;
    public Account getAccountById(int id) throws SQLException;
    public int  updateAccount(int id,Account account) throws SQLException;
    public int  deleteAccount(int id) throws SQLException;
}
