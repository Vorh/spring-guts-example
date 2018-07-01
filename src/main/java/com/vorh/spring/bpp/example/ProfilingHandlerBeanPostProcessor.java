package com.vorh.spring.bpp.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vorh on 6/30/18.
 */
public class ProfilingHandlerBeanPostProcessor implements BeanPostProcessor{

    private Map<String,Class> map = new HashMap<>();
    private ProfilingController profilingController = new ProfilingController();

    public ProfilingHandlerBeanPostProcessor() throws Exception {
        MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();

        platformMBeanServer.registerMBean(profilingController,new ObjectName("profiling","name","controller"));
    }

    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {

        Class<?> beanClass = o.getClass();
        if (beanClass.isAnnotationPresent(Profiling.class)){
            map.put(s, beanClass);
        }
        return o;
    }

    public Object postProcessAfterInitialization(final Object bean, String s) throws BeansException {

        Class beanClass = map.get(s);
        if (beanClass!=null){
                return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
                        if (profilingController.isEnabled()){
                            long before = System.nanoTime();
                            System.out.println("Profiling ...");

                            Object retVal = method.invoke(bean, objects);

                            long after = System.nanoTime();
                            System.out.println(after-before);
                            System.out.println("Profiling finish ...");
                            return retVal;
                        }else {
                            return method.invoke(bean,objects);
                        }
                    }
                });
        }
        return bean;
    }
}
