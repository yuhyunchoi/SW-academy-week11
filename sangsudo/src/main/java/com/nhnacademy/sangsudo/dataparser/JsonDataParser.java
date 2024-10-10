package com.nhnacademy.sangsudo.dataparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.sangsudo.account.Account;
import com.nhnacademy.sangsudo.formatter.OutputFormatter;
import com.nhnacademy.sangsudo.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Profile("json")
@Component
public class JsonDataParser implements DataParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${file.account.path}")
    private final String accountFilePath = "D:\\소프트웨어 아카데미\\11주차\\sangsudo\\src\\main\\resources\\files\\account.json";
    @Value("${file.tariff.path}")
    private final String tariffFilePath = "D:\\소프트웨어 아카데미\\11주차\\sangsudo\\src\\main\\resources\\files\\Tariff.json";

    private final OutputFormatter outputFormatter;

    @Autowired
    public JsonDataParser(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    @Override
    public List<Account> accounts(){
        try{
            List<Map<String, String>> accountsData = objectMapper.readValue(new File(accountFilePath), new TypeReference<List<Map<String, String>>>() {});
            return accountsData.stream()
                    .map(data -> new Account(data.get("id"), data.get("password"), data.get("name")))
                    .collect(Collectors.toList());
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> sectors(String city){
        return getTariffData().stream()
                .filter(tariff -> tariff.get("지자체명").equals(city))
                .map(tariif -> tariif.get("업종"))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> cities(){
        return getTariffData().stream()
                .map(tariff -> tariff.get("지자체명"))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Price price(String city, String sector) {
        List<Map<String, String>> tariffData = getTariffData().stream()
                .filter(tariff -> tariff.get("지자체명").equals(city) && tariff.get("업종").equals(sector))
                .collect(Collectors.toList());

        if (tariffData.isEmpty()) {
            return null; // 또는 적절한 예외 처리
        }

        // 첫 번째 일치하는 데이터만 사용
        Map<String, String> tariff = tariffData.get(0);

        String municipality = tariff.get("지자체명");
        String industry = tariff.get("업종");
        int unitPrice = Integer.parseInt(tariff.get("구간 금액"));
        // Price 객체를 반환
        return new Price(municipality, industry, unitPrice);
    }

    private List<Map<String, String>> getTariffData(){
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, String>> tariffData;

        try {
            tariffData = objectMapper.readValue(new File(tariffFilePath), new TypeReference<List<Map<String, String>>>(){});
        } catch (Exception e) {
            throw new RuntimeException("Error reading tariff data from JSON file", e);
        }

        return tariffData;
    }

}
