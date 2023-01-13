package com.epam.entity;

import com.epam.entity.Enum.Role;
import com.epam.entity.Enum.Status;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    private String email;
    private  String password;
    private Role role;
    private Status status;
    private BigDecimal balance;
    private UserDetails userDetails;
    private List<Subscription> subscriptions;

    public User() {
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }



    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(email,password,role,status,balance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return Objects.equals(email, user.email) &&
                Objects.equals(role, user.role) &&
                Objects.equals(status, user.status) &&
                Objects.equals(password, user.password) &&
                Objects.equals(balance, user.balance);
    }
}
