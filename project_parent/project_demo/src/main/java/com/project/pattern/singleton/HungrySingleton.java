package com.project.pattern.singleton;

/**
 * 饿汉式单例
 * @author wjl
 */
public class HungrySingleton {
    private static final HungrySingleton HUNGRYSINGLETON = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getHungrySingleton() {
        return HUNGRYSINGLETON;
    }

}
