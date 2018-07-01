package com.vorh.spring.bpp.api;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Created by vorh on 7/1/18.
 */
@Component
public class APIBeanDefinitionPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {



        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String name : beanDefinitionNames) {

            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String className = beanDefinition.getBeanClassName();
            if (className==null)continue;
            try {
                Class<?> beanClass = Class.forName(className);

                if (beanClass.isAnnotationPresent(EnableAPI.class)) {
                    System.out.println("API EXIST");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
