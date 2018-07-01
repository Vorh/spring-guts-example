package com.vorh.spring.bpp.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by vorh on 6/30/18.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        context.getBean(Quoter.class).sayQuoter();
    }
}
