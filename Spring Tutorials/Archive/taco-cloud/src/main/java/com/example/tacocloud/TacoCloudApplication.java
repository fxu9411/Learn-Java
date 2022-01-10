package com.example.tacocloud;

import com.example.tacocloud.Domain.Ingredient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TacoCloudApplication {

    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }


}
