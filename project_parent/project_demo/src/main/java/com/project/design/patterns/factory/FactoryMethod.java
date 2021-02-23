package com.project.design.patterns.factory;

/**
 * 工厂方法
 */
public class FactoryMethod {

    /**
     * 抽象工厂,强制实现抽象方法获得机器,成员方法获得机器后开始制造
     */
    public static void main(String[] args) {
        //非静态内部类只能在非静态方法里创建
        //SteamedBunFactory mSteamedBunFactory = new SteamedBunFactory();
        new FactoryMethod().factoryMethod();
    }

    public void factoryMethod() {
        SteamedBunFactory steamedBunFactory = new SteamedBunFactory();
        steamedBunFactory.process("面粉");//我把面粉加工成了馒头
        NoodleFactory noodleFactory = new NoodleFactory();
        noodleFactory.process("面粉");
    }

    /**
     * 首先无论是做馒头还是挂面，他们都有一个加工方法，可以抽象出来
     * Machine：机器
     */
    public interface MachineApi {
        //process：加工      material:材料
        public void process(String material);

    }

    /**
     * 馒头机器
     * steamed bun : 馒头
     */
    public class SteamedBreadMachine implements MachineApi {
        @Override
        public void process(String material) {
            System.out.println("我把" + material + "加工成了馒头");
        }
    }

    /**
     * 面条机器
     * noodle : 面条N
     */
    public class NoodleMachine implements MachineApi {
        @Override
        public void process(String material) {
            System.out.println("我把" + material + "加工成了面条");
        }
    }

    public abstract class Factory {
        /**
         * 让子类（具体工厂）来实例化具体对象（机器）
         */
        public abstract MachineApi getMachine();

        /**
         * 加工材料
         */
        public void process(String material) {
            MachineApi machine = getMachine();
            machine.process(material);
        }

    }

    //馒头工厂
    public class SteamedBunFactory extends Factory {
        //馒头工厂，只需要提供馒头机器就行
        @Override
        public MachineApi getMachine() {
            return new SteamedBreadMachine();
        }
    }

    //面条工厂
    public class NoodleFactory extends Factory {
        //面条工厂，只需要提供面条机器就行
        @Override
        public MachineApi getMachine() {
            return new NoodleMachine();
        }
    }

}
