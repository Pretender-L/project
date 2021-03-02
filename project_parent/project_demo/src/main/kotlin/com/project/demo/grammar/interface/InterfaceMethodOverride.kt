package com.project.demo.grammar.`interface`

/**
 * 接口不带结构体的函数可以省略大括号，且不用强制重写带结构体的函数就可以直接调用
 * @author wjl
 */
interface InterfaceMethodOverride {
    fun test(){
        println("我是接口方法")
    }
}

class DemoImpl : InterfaceMethodOverride{
    /*override fun test() {
        println("我是子类重写方法")
        super.test()
    }*/
}

fun main(args:Array<String>){
    val demoImpl = DemoImpl()
    demoImpl.test()
}