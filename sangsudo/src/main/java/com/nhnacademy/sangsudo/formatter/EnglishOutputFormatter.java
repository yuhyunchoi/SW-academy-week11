package com.nhnacademy.sangsudo.formatter;

import com.nhnacademy.sangsudo.price.Price;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("en")
@Component
public class EnglishOutputFormatter implements OutputFormatter {
    @Override
    public String format(Price price, int usage) {
        int billTotal = price.getUnitPrice() * usage;
        return String.format("city: %s, sector: %s, unit price(won): %d, bill total(won): %d",
                price.getCity(), price.getSector(), price.getUnitPrice(), billTotal);
    }
}
