package com.vorh.spring.bpp.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;

/**
 * Created by vorh on 6/30/18.
 */
public class PostProxyInvokerContextListener implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private ConfigurableListableBeanFactory factory;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ApplicationContext context = contextRefreshedEvent.getApplicationContext();

        String[] names = context.getBeanDefinitionNames();

        for (String name : names) {
            BeanDefinition beanDefinition = factory.getBeanDefinition(name);
            try {
                Class<?> originalClass = Class.forName(beanDefinition.getBeanClassName());
                Method[] methods = originalClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(PostProxy.class)) {
                        Object bean = context.getBean(name);
                        Method currentMethod = bean.getClass().getMethod(method.getName(), method.getParameterTypes());
                        currentMethod.invoke(bean);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
