package com.icuxika.scaffold.registrar;

import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.Proxy;

public class SimpleFeignClientFactoryBean implements FactoryBean<Object> {

    private final Class<?> clazz;
    private final String baseUrl;

    public SimpleFeignClientFactoryBean(Class<?> clazz, String baseUrl) {
        this.clazz = clazz;
        this.baseUrl = baseUrl;
    }

    @Override
    public Object getObject() throws Exception {
        return Proxy.newProxyInstance(this.clazz.getClassLoader(), new Class[]{this.clazz}, new SimpleFeignClientProxy(baseUrl));
    }

    @Override
    public Class<?> getObjectType() {
        return this.clazz;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
