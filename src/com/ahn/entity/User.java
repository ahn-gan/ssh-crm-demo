package com.ahn.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer uid;
    private String username;
    private String password;

    //一个用户对应有多个访问记录
    private Set<Visit> uvisitSet=new HashSet<Visit>();

    public Set<Visit> getUvisitSet() {
        return uvisitSet;
    }

    public void setUvisitSet(Set<Visit> uvisitSet) {
        this.uvisitSet = uvisitSet;
    }

    public User() {
    }

    public User(Integer uid, String username, String password) {
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
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

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
