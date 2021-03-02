package com.project.demo.design.patterns.adapter;

public class AdapterTest {
    public static void main(String[] args) {
        //类适配器
        Target target = new ClassAdapter();
        target.request();

        //对象适配器
        ObjectAdapter objectAdapter = new ObjectAdapter(new Adaptee());
        objectAdapter.request();
    }
}

//目标接口
interface Target {
    void request();
}

//适配者接口
class Adaptee {
    public void specificRequest() {
        System.out.println("适配者中的业务代码被调用！");
    }
}

class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        specificRequest();
        System.out.println("目标接口方法");
    }
}

//对象适配器类
class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        adaptee.specificRequest();
    }
}



