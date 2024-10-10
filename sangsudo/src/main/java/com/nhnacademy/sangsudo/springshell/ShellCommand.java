package com.nhnacademy.sangsudo.springshell;

import com.nhnacademy.sangsudo.account.Account;
import com.nhnacademy.sangsudo.service.AuthenticationService;
import com.nhnacademy.sangsudo.dataparser.DataParser;
import com.nhnacademy.sangsudo.formatter.OutputFormatter;
import com.nhnacademy.sangsudo.price.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Objects;

@ShellComponent
@Profile({"csv", "json"})
public class ShellCommand {

    private final DataParser dataParser;
    private final OutputFormatter outputFormatter;
    private final AuthenticationService authenticationService;

    @Autowired
    public ShellCommand(DataParser dataParser, OutputFormatter outputFormatter, AuthenticationService authenticationService) {
        this.dataParser = dataParser;
        this.outputFormatter = outputFormatter;
        this.authenticationService = authenticationService;
    }

    @ShellMethod
    public String city(){
        List<String> cities = dataParser.cities();
        return String.join(", ", cities);
    }

    @ShellMethod
    public String sector(String city){
        List<String> sectors = dataParser.sectors(city);
        return String.join(", ", sectors);
    }

    @ShellMethod
    public String price(String city, String sector, @ShellOption(defaultValue = "1") int usage){
        Price price = dataParser.price(city, sector);
        if(price == null){
            return "No price information available for the given city and sector";
        }
        return outputFormatter.format(price, usage);
    }

    @ShellMethod
    public String login(String id, String password) {
        List<Account> accounts = dataParser.accounts();
        return authenticationService.login(id, password, accounts);
    }

    @ShellMethod
    public String billTotal(String city, String sector, int usage) {
        Price price = dataParser.price(city, sector);
        if (price == null) {
            return "No price information available for the given city: " + city + " and sector: " + sector;
        }
        return outputFormatter.format(price, usage);
    }



    @ShellMethod
    public String logout(){
        return authenticationService.logout();
    }

    @ShellMethod
    public String currentUser() {
        Account loggedInUser = authenticationService.getLoggedInUser();
        if (Objects.nonNull(loggedInUser)) {
            return "Account(id=" + loggedInUser.getId() + ", password=" + loggedInUser.getPassword() + ", name=" + loggedInUser.getName() + ")";
        }else{
            return "";
        }
    }

}
