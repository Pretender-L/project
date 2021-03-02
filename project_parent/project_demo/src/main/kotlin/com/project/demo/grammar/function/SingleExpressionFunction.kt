package com.project.demo.grammar.function

class SingleExpressionFunction {
}

/**
 * 单表达式函数：即函数具备返回值的时候，可以省略花括号并且在=赋值符号之后指定代码体，而函数的返回值是有编辑器自动推断的。
 */
fun main(args: Array<String>) {
    // 无参数的情况
    fun test1() = 2                     // 自动推断为：返回类型为Int

    // 有参数的情况
    fun test2(num : Int) = num * 2      // 自动推断为：返回类型为Int

    // 或者
    fun test3(x : Float, y : Int = 2) = x * y  // 和默认参数一起使用，返回值为Float型
    println(test1())
    println(test2(2))
    println(test3(2f))
}