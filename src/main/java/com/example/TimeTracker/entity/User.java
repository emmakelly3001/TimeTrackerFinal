package com.example.TimeTracker.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {


    @Id
    @Column(name="user_id", length = 45)
    @GeneratedValue
    private int userId;

    @Column(name="user_name", length = 255)
    private String userName;

    @Column(name="email", length = 255)
    private String email;

    @Column(name="password", length = 255)
    private String password;

    public User(int userId, String userName, String email, String password) {
        this.password = password;
        this.email = email;
        this.userName = userName;
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


}
