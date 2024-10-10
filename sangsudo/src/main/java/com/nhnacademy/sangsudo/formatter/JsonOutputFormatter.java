package com.nhnacademy.sangsudo.formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.sangsudo.price.Price;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Profile("json")
@Component
public class JsonOutputFormatter implements OutputFormatter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String format(Price price, int usage) {
        int billTotal = price.getUnitPrice() * usage;
        Map<String, Object> output = new HashMap<>();
        output.put("city", price.getCity());
        output.put("sector", price.getSector());
        output.put("unitPrice", price.getUnitPrice());
        output.put("billTotal", billTotal);

        try {
            return objectMapper.writeValueAsString(output);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Error formatting to JSON";
        }
    }
}
