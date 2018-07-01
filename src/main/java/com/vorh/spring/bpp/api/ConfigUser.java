package com.vorh.spring.bpp.api;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by vorh on 7/1/18.
 */
@Configuration
@ConditionalOnProperty(name = "claimService.api.user.enabled")
public class ConfigUser {


    @Bean
    public Service service(){
        return new Service() {
            @Override
            public void someDo() {
                System.out.println("User");
            }
        };
    }
}
