package com.nhnacademy.sangsudo.formatter;

import com.nhnacademy.sangsudo.price.Price;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("csv")
@Component
public class CsvOutputFormatter implements OutputFormatter {

    @Override
    public String format(Price price, int usage) {
        return String.format("CSV - City: %s, Sector: %s, Unit Price: %d, Total Bill: %d",
                price.getCity(), price.getSector(), price.getUnitPrice(), price.getUnitPrice() * usage);
    }
}
