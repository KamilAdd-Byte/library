package com.homemanagment.homemanagment.validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Component
@Configuration
public class BookValidator {

    @Bean
    public Validator validator(){
        return new LocalValidatorFactoryBean();
    }
}
