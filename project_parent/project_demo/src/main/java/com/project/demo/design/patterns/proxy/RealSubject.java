package com.project.demo.design.patterns.proxy;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("真实主题");
    }
}
