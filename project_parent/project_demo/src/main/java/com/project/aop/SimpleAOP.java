package com.project.aop;

import java.lang.reflect.Proxy;

public class SimpleAOP {
    //获得代理对象(实现被代理接口)
    public static Object getProxy(Object bean,Advice advice) {
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(),
                bean.getClass().getInterfaces(), advice);
    }
}
