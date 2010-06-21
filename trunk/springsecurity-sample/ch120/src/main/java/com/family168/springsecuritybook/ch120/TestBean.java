package com.family168.springsecuritybook.ch120;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;


public class TestBean implements InitializingBean, BeanNameAware {
    private String beanName;
    private Date initializeTime;

    public Date getInitializeTime() {
        return initializeTime;
    }

    public void setInitializeTime(Date initializeTime) {
        this.initializeTime = initializeTime;
    }

    public void afterPropertiesSet() throws Exception {
        setInitializeTime(new Date());
        System.out.println("bean [" + beanName + "] was initialized at " +
            initializeTime);
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
