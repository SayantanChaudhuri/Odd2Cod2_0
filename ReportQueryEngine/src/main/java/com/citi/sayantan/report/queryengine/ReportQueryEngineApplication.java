package com.citi.sayantan.report.queryengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ReportQueryEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportQueryEngineApplication.class, args);
    }

}
