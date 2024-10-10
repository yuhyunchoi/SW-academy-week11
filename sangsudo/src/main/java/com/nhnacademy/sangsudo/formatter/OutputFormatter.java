package com.nhnacademy.sangsudo.formatter;

import com.nhnacademy.sangsudo.price.Price;

public interface OutputFormatter {
    String format(Price price, int usage);
}
