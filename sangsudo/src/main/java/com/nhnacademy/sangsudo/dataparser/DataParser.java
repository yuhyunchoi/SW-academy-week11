package com.nhnacademy.sangsudo.dataparser;

import com.nhnacademy.sangsudo.account.Account;
import com.nhnacademy.sangsudo.price.Price;

import java.util.List;

public interface DataParser {

    Price price(String city, String sector);

    List<Account> accounts();

    List<String> sectors(String city);

    List<String> cities();
}
