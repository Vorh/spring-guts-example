package com.vorh.spring.bpp.api;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

/**
 * Created by vorh on 7/1/18.
 */
@Component
public class APIConditional implements Condition {


    private ClassPathScanningCandidateComponentProvider scanner =
            new ClassPathScanningCandidateComponentProvider(false);

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

        scanner.addIncludeFilter(new AnnotationTypeFilter(EnableAPI.class));

        for (BeanDefinition beanDefinition : scanner.findCandidateComponents(EnableAPI.class.getPackage().getName())){

            try {
                Class<?> beanClass = Class.forName(beanDefinition.getBeanClassName());
                EnableAPI annotation = beanClass.getAnnotation(EnableAPI.class);
                EnableAPI.Type type = annotation.type();
                System.out.println(type);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }


        return false;
    }
}
