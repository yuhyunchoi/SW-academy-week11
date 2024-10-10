package com.nhnacademy.sangsudo.formatter;

import com.nhnacademy.sangsudo.price.Price;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;


@Profile("kor")
@Component
public class KoreanOutputFormatter implements OutputFormatter {

    @Override
    public String format(Price price, int usage) {
        int billTotal = price.getUnitPrice() * usage;
        return String.format("지자체명: %s, 업종: %s, 구간금액(원): %d, 총금액(원): %d",
                price.getCity(), price.getSector(), price.getUnitPrice(), billTotal);
    }
}
