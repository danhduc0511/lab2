package org.example.lab22.service;

import org.example.lab22.enity.Account;
import org.example.lab22.repository.AccountReponsitory;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountReponsitory accountReponsitory;

    public List<Account> getAllAccounts(Integer pageSize,Integer pageNo,String sortBy,String sortDir ) throws SQLException {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        return accountReponsitory.findAll(pageable).getContent();
    }
    public Optional<Account> getAccountById(Long id) throws SQLException {
        return accountReponsitory.findById(id);
    }
    public Account addAccount(Account account) throws SQLException {
        account.setId(null);
        account.setCreateDate(LocalDateTime.now());
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        return accountReponsitory.save(account);
    }
    public Account updateAccount(Long  id, Account account) throws SQLException {
        account.setId(id);
        account.setPassword(BCrypt.hashpw(account.getPassword(), BCrypt.gensalt()));
        account.setCreateDate(LocalDateTime.now());
        return accountReponsitory.save(account);
    }
    public int deleteAccount(Long id) throws SQLException {
         accountReponsitory.deleteById(id);
         return 1;

    }
    public Optional<Account> getAccountByUsername(String username) throws SQLException {
         return accountReponsitory.findByUsername(username);
    }

}
