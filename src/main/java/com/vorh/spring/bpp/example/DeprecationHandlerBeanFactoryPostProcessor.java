package com.vorh.spring.bpp.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by vorh on 7/1/18.
 */
public class DeprecationHandlerBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
            String beanClassName = beanDefinition.getBeanClassName();

            try {
                Class<?> beanClass = Class.forName(beanClassName);
                DeprecatedClass deprecatedClass = beanClass.getAnnotation(DeprecatedClass.class);

                if (deprecatedClass != null) {
                    beanDefinition.setBeanClassName(deprecatedClass.newImpl().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
