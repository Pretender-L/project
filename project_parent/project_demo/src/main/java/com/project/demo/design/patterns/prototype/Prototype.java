package com.project.demo.design.patterns.prototype;

/**
 * 原型模式
 */
public class Prototype implements Cloneable{
    public Prototype() {
        System.out.println("原型创建成功");
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        System.out.println("原型复制成功");
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype();
        Object prototypeClone = prototype.clone();
        System.out.println(prototypeClone==prototype);
    }
}
