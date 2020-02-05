package com.gyy.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Account implements Serializable {
    private String username;
    private String password;
    private Integer money;

    private List<User> ll;
    private Map<String,User> mm;
//    private User user;

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

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

/*    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

  /*  @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", user=" + user +
                '}';
    }*/

    public List<User> getLl() {
        return ll;
    }

    public void setLl(List<User> ll) {
        this.ll = ll;
    }

    public Map<String, User> getMm() {
        return mm;
    }

    public void setMm(Map<String, User> mm) {
        this.mm = mm;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", ll=" + ll +
                ", mm=" + mm +
                '}';
    }
}
