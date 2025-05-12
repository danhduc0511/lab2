package org.example.lab22.repository;

import org.example.lab22.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepositoryImplement implements AccountReponsitory{
    @Autowired
    Connection connection;

    private String addAccountJdbc="insert into accounts values(?,?,?,?,?)";
    private String getAllAccountJdbc="select * from accounts";
    private String getAccountByIdJdbc="select * from accounts where id=?";
    private String updateAccountByIdJdbc="update accounts set username = ?,password=?,status=?,createdDate=?  where id=?";
    private String deleteAccountByIdJdbc="delete from accounts where id=?";

    @Override
    public int  addAccount(Account account) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(addAccountJdbc);){
            ps.setInt(1, account.getId());
            ps.setString(2, account.getUsername());
            ps.setString(3, account.getPassword());
            ps.setBoolean(4,account.isStatus());
            ps.setDate(5, Date.valueOf(account.getCreateDate()));
            int rows = ps.executeUpdate();
            return rows;
        }
    }

    @Override
    public List<Account> getAllAccounts() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(getAllAccountJdbc)) {
            ResultSet rs = ps.executeQuery();
            List<Account> accounts = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                boolean status = rs.getBoolean(4);
                LocalDate createDate = rs.getDate(5).toLocalDate();
                Account account =new Account(id,username,password,status,createDate);
                accounts.add(account);
            }
            return accounts;
        }
    }

    @Override
    public Account getAccountById(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(getAccountByIdJdbc)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account account = new Account();
                account.setId(rs.getInt(1));
                account.setUsername(rs.getString(2));
                account.setPassword(rs.getString(3));
                account.setStatus(rs.getBoolean(4));
                account.setCreateDate(rs.getDate(5).toLocalDate());
                return account;
            }
            return null;

        }

    }

    @Override
    public int  updateAccount(int id, Account account) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(updateAccountByIdJdbc)) {
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setBoolean(3, account.isStatus());
            ps.setDate(4, Date.valueOf(account.getCreateDate()));
            ps.setInt(5, id);
            int rows = ps.executeUpdate();
            return rows;


        }

    }

    @Override
    public int  deleteAccount(int id) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(deleteAccountByIdJdbc)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows;

        }

    }
}
