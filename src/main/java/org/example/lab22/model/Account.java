package org.example.lab22.model;

import java.time.LocalDate;

public class Account {
    private int id;
    private String username;
    private String password;
    private boolean status;
    private LocalDate createDate;

    public Account() {
    }

    public Account(int id, String username, String password, boolean  status, LocalDate createDate) {
        this.id = id;
        this.username = username;
        this.status = status;
        this.password = password;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return status;
    }
}
