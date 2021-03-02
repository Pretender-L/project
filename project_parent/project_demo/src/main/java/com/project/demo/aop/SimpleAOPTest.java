package com.project.demo.aop;

public class SimpleAOPTest {
    public static void main(String[] args) {
        // 1.创建一个MethodInvocation,实现类,代理内容
        MethodInvocation methodInvocation = () -> System.out.println("前置通知内容");
        //被代理对象
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        // 2.创建一个Advice,前置通知对象
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, methodInvocation);
        // 3.为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl,beforeAdvice);
        helloServiceImplProxy.hello("被代理内容");

        System.out.println("--------------------------------------------------");

        methodInvocation = ()-> System.out.println("后置通知内容");
        AfterAdvice afterAdvice = new AfterAdvice(helloServiceImpl, methodInvocation);
        helloServiceImplProxy = (HelloService) SimpleAOP.getProxy(helloServiceImpl, afterAdvice);
        helloServiceImplProxy.hello("被代理内容");
    }
}
