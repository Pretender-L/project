package com.project.aop;

public class HelloServiceImpl implements HelloService{
    @Override
    public void hello(String mes) {
        System.out.println(mes);
    }
}
