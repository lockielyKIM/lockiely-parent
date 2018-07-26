package org.lockiely.utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Spring;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.Assert;

public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static ConfigurableBeanFactory beanFactory;

    private Map<Object, Object> beanCache = Collections.synchronizedMap(new HashMap<>());

    public static ApplicationContext applicationContextHolder(){
        assertApplicationContext();
        return applicationContext;
    }

    public static ConfigurableBeanFactory beanFactoryHolder(){
        assertApplicationContext();
        return beanFactory;
    }

    public Object getBean(String beanName){
        return getBean(beanName, null);
    }

    public Object getBean(String beanName, Object... args){
        if(beanCache.containsKey(beanName)){
            return beanCache.get(beanName);
        }
        return beanFactoryHolder().getBean(beanName, args);
    }

    public <T> T getBean(Class<T> classType){
        return getBean(classType, "");
    }

    public <T> T getBean(Class<T> classType, String name){
        if(beanCache.containsKey(classType)){
            return (T) beanCache.get(classType);
        }
        return beanFactoryHolder().getBean(name, classType);
    }

    public <T> T getBean(Class<T> classType, Object... args){
        if(beanCache.containsKey(classType)){
            return (T) beanCache.get(classType);
        }
        return beanFactoryHolder().getBean(classType, args);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
        if(applicationContext instanceof ConfigurableApplicationContext){
            SpringContextHolder.beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
        }
    }

    private static void assertApplicationContext(){
        Assert.notNull(SpringContextHolder.applicationContext, "applicationContextHolder is not put to spring ioc");
    }

}
