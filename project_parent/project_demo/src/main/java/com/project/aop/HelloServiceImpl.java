package com.project.aop;

public class HelloServiceImpl implements HelloService{
    @Override
    public void hello(String message) {
        System.out.println(message);
    }
}
