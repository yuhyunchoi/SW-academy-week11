package com.nhnacademy.sangsudo.dataparser;

import com.nhnacademy.sangsudo.account.Account;
import com.nhnacademy.sangsudo.formatter.OutputFormatter;
import com.nhnacademy.sangsudo.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Profile("csv")
@Component
public class CsvDataParser implements DataParser
{
    @Value("${file.account.path}")
    private final String accountFilePath = "D:\\소프트웨어 아카데미\\11주차\\sangsudo\\src\\main\\resources\\files\\account.csv";
    @Value("${file.tariff.path}")
    private final String tariffFilePath = "D:\\소프트웨어 아카데미\\11주차\\sangsudo\\src\\main\\resources\\files\\Tariff.csv";

    private final OutputFormatter outputFormatter;

    @Autowired
    public CsvDataParser(OutputFormatter outputFormatter) {
        this.outputFormatter = outputFormatter;
    }

    @Override
    public List<Account> accounts(){
        List<Account> accounts = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(accountFilePath))){
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                Account account = new Account(data[0], data[1], data[2]);
                accounts.add(account);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return accounts;
    }

    @Override
    public List<String> sectors(String city){
        List<String> sectors = getTariffData().stream()
                .filter(tariff -> tariff[1].trim().equals(city.trim()))
                .map(tariff -> tariff[2])  // 업종을 추출
                .distinct()
                .collect(Collectors.toList());
        return sectors;
    }

    @Override
    public List<String> cities(){
        return getTariffData().stream()
                .map(tariff -> tariff[1])//지자체명
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public Price price(String city, String sector){
        List<String[]> tariffData = getTariffData().stream()
                .filter(tariff -> tariff[1].trim().equals(city.trim()) && tariff[2].trim().equals(sector.trim()))  // 도시와 업종 필터링
                .collect(Collectors.toList());

        if (tariffData.isEmpty()) {
            System.out.println("No matching data found for city: " + city + " and sector: " + sector);  // 디버깅 출력
            return null;
        }

        String[] tariff = tariffData.get(0);
        String municipality = tariff[1];
        String industry = tariff[2];
        int unitPrice = Integer.parseInt(tariff[3]);

        return new Price(municipality, industry, unitPrice);
    }

    private List<String[]> getTariffData(){
        List<String[]> tariffData = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(tariffFilePath))){
            String line;
            br.readLine();

            while((line = br.readLine()) != null){
                String[] data = line.split(",");
                tariffData.add(data);
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return tariffData;
    }


}

