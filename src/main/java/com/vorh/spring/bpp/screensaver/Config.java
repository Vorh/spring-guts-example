package com.vorh.spring.bpp.screensaver;

import org.springframework.context.annotation.*;

import java.awt.*;
import java.util.Random;

/**
 * Created by vorh on 7/1/18.
 */
@Configuration
@ComponentScan("com.vorh.spring.bpp.screensaver")
public class Config {

    @Bean
    @Scope("periodical")
    public Color color(){
        Random random =new Random();
        return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    }

    @Bean
    public ColorFrame colorFrame(){
        return new ColorFrame() {
            @Override
            protected Color getColor() {
                return color();
            }
        };
    }

    public static void main(String[] args) throws InterruptedException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        while (true){
            ColorFrame bean = context.getBean(ColorFrame.class);
            System.out.println(bean.getColor());
            System.out.println(bean);
            bean.showOnRandomPlace();
            Thread.sleep(1000);
        }
    }
}
