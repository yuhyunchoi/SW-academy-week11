package com.nhnacademy.sangsudo.service;


import com.nhnacademy.sangsudo.account.Account;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Getter
@Service
public class AuthenticationService {

    private Account loggedInUser;

    public String login(String id, String password, List<Account> accounts) {
        for (Account account : accounts) {
            if (account.getId().trim().equals(id.trim()) && account.getPassword().trim().equals(password.trim())) {
                loggedInUser = account;
                return account.toString();
            }
        }
        return "id or password not correct";
    }


    public String logout(){
        if(Objects.nonNull(loggedInUser)){
            String name = loggedInUser.getName();
            loggedInUser = null;
            return "Good Bye";
        }else{
            return "No user in currently logged in";
        }
    }

    public Account getLoggedInUser(){
        return loggedInUser;
    }

}
