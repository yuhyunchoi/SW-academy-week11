package com.nhnacademy.sangsudo.service;

import com.nhnacademy.sangsudo.dataparser.DataParser;
import com.nhnacademy.sangsudo.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceService {

    private final DataParser dataParser;

    @Autowired
    public PriceService(DataParser dataParser) {
        this.dataParser = dataParser;
    }

    public List<String> sectors(String city){
        return dataParser.sectors(city);
    }

    public Price price(String city, String sector) {
        Price price = dataParser.price(city, sector);
        if (price == null) {
            throw new RuntimeException("No matching data found for city: " + city + " and sector: " + sector);
        }
        return price;
    }
    public List<String> cities(){
        return dataParser.cities();
    }

}
