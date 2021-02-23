package com.project.pattern.singleton;

/**
 * 懒汉式单例
 * @author wjl
 */
public class LazySingleton {

    private static LazySingleton lazySingleton = null;

    private LazySingleton() {
    }

    /**
     * 如果编写的是多线程程序，则不要删除上例代码中的关键字 volatile 和 synchronized，否则将存在线程非安全的问题。
     * 如果不删除这两个关键字就能保证线程安全，但是每次访问时都要同步，会影响性能，且消耗更多的资源，这是懒汉式单例的缺点。
     * @return
     */
    public static synchronized LazySingleton  getLazySingleton() {
        if (lazySingleton == null) {
            lazySingleton = new LazySingleton();
        }else {
            System.out.println("LazySingleton已有对象,无法创建!");
        }
        return lazySingleton;
    }
}
