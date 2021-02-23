package com.demo.grammar.`class`

/**
 * 多个构造器
 */
class ConstructorTest constructor(var num: Int, var age: Int) {
    //次构造器需要初始化主构造器
    //初始化方法赋值后次构造器初始化失败
    //this指主构造器
    constructor(year: Int) : this(year, 2) {
        println("year:" + year)
    }

    //比次构造器先执行
    init {
        //age初始化方法没赋值会被次构造器赋值
        age=3
        println("age被初始化方法赋值为:${age}")
    }
}

//如果类主构造函数的所有参数都具有默认值，编译器将生成一个额外的无参数构造函数，它将使用默认值
class ConstructorDemo constructor(num1: Int = 10 , num2: Int = 20){
    init {
        println("num1 = $num1\t num2 = $num2")
    }

    constructor(num1 : Int = 1, num2 : Int = 2, num3 : Int = 3) : this(num1 , num2){
        println("num1 = $num1\t num2 = $num2 \t num3 = $num3")
    }
}

fun main(args: Array<String>) {
    //创建一个类的实例，需要调用类的构造函数，就像它是一个常规函数一样
    val testClass = ConstructorTest(1)
    println("num:" + testClass.num)
    println("age:" + testClass.age)
    println("-------------")

    var test = ConstructorDemo()
    var test1 = ConstructorDemo(1,2)
    var test2 = ConstructorDemo(4,5,6)
}

