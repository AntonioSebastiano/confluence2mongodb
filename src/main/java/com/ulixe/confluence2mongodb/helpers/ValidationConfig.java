package com.ulixe.confluence2mongodb.helpers;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig implements DisposableBean {

    private ValidatorFactory validatorFactory;

    @Bean
    public Validator validator() {
        validatorFactory = Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ParameterMessageInterpolator())
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Override
    public void destroy() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }
}