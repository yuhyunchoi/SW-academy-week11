package com.nhnacademy.sangsudo.account;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Account {
    private String id;
    private String password;
    private String name;

    public Account(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account(id=" + id + ", password=" + password + ", name=" + name + ")";
    }

}
