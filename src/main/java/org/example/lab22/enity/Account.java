package org.example.lab22.enity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table( name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name ="password",nullable = false)
    private String password;
    @Column(name = "status")
    private boolean status;
    @Column(name = "creatDate")
    private LocalDateTime createDate;

    public Account() {
    }

    public Account(Long id, String username, String password, boolean  status, LocalDateTime createDate) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.password = password;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }
}
