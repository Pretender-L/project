package com.project.aop;

public class SimpleAOPTest {
    public static void main(String[] args) {
        // 1.创建一个MethodInvocation,实现类,代理内容
        MethodInvocation logTask = () -> System.out.println("log task start");
        //被代理对象
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        // 2.创建一个Advice,前置通知对象
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);
        // 3.为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);
        helloServiceImplProxy.hello("12");
    }
}
