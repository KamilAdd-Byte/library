package com.homemanagment.homemanagment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class HomeManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeManagmentApplication.class, args);

    }
    @Bean
    Validator validator(){
        return new LocalValidatorFactoryBean();
    }
}
