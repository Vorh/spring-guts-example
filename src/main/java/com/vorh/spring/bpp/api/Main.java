package com.vorh.spring.bpp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by vorh on 7/1/18.
 */
@SpringBootApplication
@EnableAPI(type = EnableAPI.Type.SERVICE)
public class Main {


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);

        Service bean = run.getBean(Service.class);
        bean.someDo();
    }
}
