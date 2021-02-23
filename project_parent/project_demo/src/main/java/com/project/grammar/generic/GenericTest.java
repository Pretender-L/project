package com.project.grammar.generic;

//类泛型指定了类中存放什么类型的数据
public class GenericTest<T> {

    //成员变量类型和类泛型一致
    private T key;

    public GenericTest(T key) {
        this.key = key;
    }

    public static void main(String[] args) {
        //指定泛型的类型
        GenericTest<String> stringGenericTest = new GenericTest<>("1");

        //不指定泛型可以传入任意类型实参
        GenericTest booleanGenericTest = new GenericTest(false);

        stringGenericTest.genericTest(booleanGenericTest);
    }

    //只用object类中方法时并且接收泛型不确定的时候可以用"?"表示不固定泛型,"?"是类型实参,不是类型形参,可以看做为Number等所有类的父类
    public void genericTest(GenericTest<?> obj){
        System.out.println(obj);
    }
}




