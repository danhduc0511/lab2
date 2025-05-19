package org.example.lab22.repository;

import org.example.lab22.enity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AccountReponsitory extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

}
