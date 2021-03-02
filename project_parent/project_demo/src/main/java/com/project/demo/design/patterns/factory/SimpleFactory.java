package com.project.demo.design.patterns.factory;

/**
 * 简单工厂模式
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Product productA = SimpleFactoryTest.makeProduct(0);
        if (productA != null) {
            productA.show();
        }
        Product productB = SimpleFactoryTest.makeProduct(1);
        if (productB != null) {
            productB.show();
        }
    }

    //产品接口
    public interface Product {
        void show();
    }

    //具体产品：ProductA
    static class ConcreteProduct1 implements Product {
        public void show() {
            System.out.println("具体产品1显示...");
        }
    }

    //具体产品：ProductB
    static class ConcreteProduct2 implements Product {
        @Override
        public void show() {
            System.out.println("具体产品2显示...");
        }
    }

    //常量类
    final class Const {
        static final int PRODUCT_A = 0;
        static final int PRODUCT_B = 1;
    }

    static class SimpleFactoryTest {
        public static Product makeProduct(int kind) {
            switch (kind) {
                case Const.PRODUCT_A:
                    return new ConcreteProduct1();
                case Const.PRODUCT_B:
                    return new ConcreteProduct2();
            }
            return null;
        }
    }
}
