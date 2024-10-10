package com.example.springcore.chef;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class ChefBeanConfig {

    @Profile("eng")
    @Bean(name = "englishChefBean")
    public ChefBean chefBeanEng(){
        return new ChefBeanEng();
    }

    @Profile("!eng")
    @Bean(name = "koreanChefBean")
    public ChefBean chefBeanKor(){
        return new ChefBeanKor();
    }

}
