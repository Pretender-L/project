package com.project.design.patterns.singleton;

/**
 * 懒汉式单例
 */
public class LazySingleton {
    private static LazySingleton singleton;

    private LazySingleton() {
    }

    public synchronized LazySingleton getSingleton() {
        if (singleton == null) {
            return new LazySingleton();
        }
        return singleton;
    }
}
