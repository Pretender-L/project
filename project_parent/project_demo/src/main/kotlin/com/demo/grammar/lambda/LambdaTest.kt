package com.demo.grammar.lambda

class LambdaTest {
}

fun main(args: Array<String>) {
    // 源代码
    /*fun test1() {
        println("无参数1")
    }*/
    // lambda代码(无参无返回值)
    val test1 = { println("test1:" + "无参数1") }
    test1()
    // 源代码
    /*fun test2(a : Int , b : Int) : Int{
        return a + b
    }*/
    // lambda
    val test2: (Int, Int) -> Int = { a, b -> a + b }
    println("test2:" + test2(1, 2))
    // 返回值自动推断
    val test3 = { a: Int, b: Int -> a + b }
    println("test3:" + test3(3, 5))
    // 源代码
    /*fun test(a: Int, b: Int): Int {
        return a + b
    }
    fun sum(num1: Int, num2: Int): Int {
        return num1 + num2
    }
    // 调用
    test(10, sum(3, 5))*/ // 结果为：18
    // lambda版(num1,num2可以省略)
    fun test4(a: Int, b: (num1: Int, num2: Int) -> Int): Int {
        return a + b.invoke(3, 5)
    }
    // 调用
    println("test4:" + test4(10) { num1, num2 -> num1 + num2 })// 结果为：18

    //调用test方法
    println("test" + test(1) { it > 5 })

    val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
    map.forEach { (key, value) ->
        println("$key \t $value")
    }
    //在使用Lambda表达式的时候，可以用下划线(_)表示未使用的参数，表示不处理这个参数。
    //不需要key的时候
    map.forEach { (_, value) ->
        println(value)
    }

    //匿名函数(返回类型自动推断)
    val test5 = fun(x: Int, y: Int) = x + y
    println("test5:"+test5(1,3))

    //在kotlin中，提供了指定的接受者对象调用Lambda表达式的功能。在函数字面值的函数体中，可以调用该接收者对象上的方法而无需任何额外的限定符。它类似于扩展函数，它允你在函数体内访问接收者对象的成员。
    val iop = fun Int.(other: Int): Int = this + other
    println("iop:" + 2.iop(3))

    //调用html方法
    html {       // 带接收者的 lambda 由此开始
        body()   // 调用该接收者对象的一个方法
    }

    //让函数返回一个函数，并携带状态值
    val demo = demo(2)
    println("demo:" + demo())

    //引用外部变量，并改变外部变量的值
    var num = 0
    val array = arrayOf(1, 3, 4, 5)
    array.filter { it > 3 }.forEach { num += it }
    println("num:$num")

}

fun test(num: Int, bool: (Int) -> Boolean): Int {
    return if (bool(num)) {
        num
    } else 0
}

class HTML {
    fun body() {}
}
fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()  // 创建接收者对象
    html.init()        // 将该接收者对象传给该 lambda
    return html
}

//所谓闭包，即是函数中包含函数，这里的函数我们可以包含(Lambda表达式，匿名函数，局部函数，对象表达式)。我们熟知，函数式编程是现在和未来良好的一种编程趋势。故而Kotlin也有这一个特性。
fun demo(num: Int): () -> Int {
    var a = 1
    return fun(): Int {
        return ++a
    }
}





