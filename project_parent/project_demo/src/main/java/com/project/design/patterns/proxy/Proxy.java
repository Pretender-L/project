package com.project.design.patterns.proxy;

public class Proxy implements Subject{
   private RealSubject realSubject;

    @Override
    public void request() {
        afterRequest();
        if (realSubject==null){
            realSubject = new RealSubject();
        }
        realSubject.request();
        beforeRequest();
    }

    public void afterRequest() {
        System.out.println("前置通知");
    }

    public void beforeRequest() {
        System.out.println("后置通知");
    }
}
