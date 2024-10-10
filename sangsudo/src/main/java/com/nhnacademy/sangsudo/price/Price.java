package com.nhnacademy.sangsudo.price;

public class Price {
    private String city;
    private String sector;
    private int unitPrice;

    public Price(String city, String sector, int unitPrice) {
        this.city = city;
        this.sector = sector;
        this.unitPrice = unitPrice;
    }

    public String getCity() {
        return city;
    }

    public String getSector() {
        return sector;
    }

    public int getUnitPrice() {
        return unitPrice;
    }
}
