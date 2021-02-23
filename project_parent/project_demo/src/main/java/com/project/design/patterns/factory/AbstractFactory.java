package com.project.design.patterns.factory;

/**
 * 抽象工厂
 * 抽象工厂模式是工厂方法模式的升级版本，工厂方法模式只生产一个等级的产品，而抽象工厂模式可生产多个等级的产品。
 */
public class AbstractFactory {
    public static void main(String[] args) {
        AbstractFactory abstractFactory = new AbstractFactory();
        abstractFactory.test();
    }

    public void test() {
        SGFarm sgFarm = new SGFarm();
        sgFarm.newAnimal();
        sgFarm.newFruitage();
    }

    interface Animal {
        void show();
    }

    class Horse implements Animal {
        @Override
        public void show() {
            System.out.println("我是马儿");
        }
    }

    interface Fruitage {
        void show();
    }

    class Apple implements Fruitage {
        @Override
        public void show() {
            System.out.println("我是牛儿");
        }
    }

    //抽象工厂：农场类
    interface Farm {
        Animal newAnimal();

        Fruitage newFruitage();
    }

    //具体工厂：韶关农场类
    class SGFarm implements Farm {
        @Override
        public Animal newAnimal() {
            System.out.println("新马出生");
            return new Horse();
        }

        @Override
        public Fruitage newFruitage() {
            System.out.println("新苹果出生");
            return new Apple();
        }
    }
}
