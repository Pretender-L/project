package com.demo.pattern.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        LazySingleton lazySingleton = LazySingleton.getLazySingleton();
        LazySingleton lazySingleton1 = LazySingleton.getLazySingleton();
        System.out.println("懒汉测试结果:"+(lazySingleton==lazySingleton1));

        HungrySingleton hungrySingleton = HungrySingleton.getHungrySingleton();
        HungrySingleton hungrySingleton1 = HungrySingleton.getHungrySingleton();
        System.out.println("饿汉式测试结果:"+(hungrySingleton==hungrySingleton1));
    }
}
