package com.vorh.spring.bpp.example;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by vorh on 7/1/18.
 */
public class PropertyFileApplicationContext extends GenericApplicationContext {

    public PropertyFileApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int i = reader.loadBeanDefinitions(fileName);
        System.out.println("found " + i+" beans");
        refresh();
    }

    public static void main(String[] args) {
        PropertyFileApplicationContext context= new PropertyFileApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuoter();
    }
}
