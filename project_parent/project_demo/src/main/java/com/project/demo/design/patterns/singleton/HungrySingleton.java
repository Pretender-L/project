package com.project.demo.design.patterns.singleton;

/**
 * 恶汉式单例
 */
public class HungrySingleton {
    /**
     * 静态变量和静态代码块加载顺序取决于代码出现顺序
     * 在编译阶段，jvm发现某个变量被final修饰，即认定这个是常量，且这个常量在编译时就能确认其值。
     * 则把这个常量放到调用这个常量的方法的所在类的常量池中。本质上，并没有引用这个常量所在的类，因此并不会触发常量所在类的初始化
     */
    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }
}
