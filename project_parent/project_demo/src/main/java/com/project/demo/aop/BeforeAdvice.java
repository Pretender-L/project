package com.project.common.aop;

import java.lang.reflect.Method;

//前置通知
public class BeforeAdvice implements Advice{
    //被代理对象
    private Object bean;
    //代理内容接口(对象)
    private MethodInvocation methodInvocation;

    public BeforeAdvice(Object bean, MethodInvocation methodInvocation) {
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    /**
     * 逻辑:实现被代理对象接口,接口中调用invoke方法,invoke方法调用代理对象方法,在利用反射调用被代理对象的方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        //反射调用
        return method.invoke(bean,args);
    }
}
