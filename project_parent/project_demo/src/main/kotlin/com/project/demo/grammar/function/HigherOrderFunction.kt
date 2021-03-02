package com.project.demo.grammar.function

import com.project.demo.pojo.Admin

fun main(args: Array<String>) {
    //把字符串中的每一个字符转换为Int的值，用于累加，最后返回累加的值
    val testStr = "a"
    val sum = testStr.sumBy { it.toInt() }
    println("sum:$sum")

    //传入不同表达式不同实现
    println("test:" + test(2, 6) { num1: Int, num2: Int -> num1 + num2 })
    println("test:" + test(9, 2) { num1: Int, num2: Int -> num1 - num2 })

    //TODO("抛出异常函数,抛出后程序不继续向下执行")

    runTest()

    //当一个业务逻辑都需要执行同一段代码而根据不同的条件去判断得到不同结果的时候。可以用到run函数
    val index = 0
    val num = run {
        when (index) {
            0 -> "java"
            1 -> "kotlin"
            else -> "?"
        }.length
    }
    println("num:${num}")

    //T.run是返回了执行的结果
    val str1: String = "kotlin"
    val length = str1.run {
        length
    } //可以用执行的结果继续操作

    //run实际开发用法
    val admin = Admin()
    admin.run {
        this.id = "1"
        this.loginName = "run"
    }
    println("admin:${admin.id},${admin.loginName}")

    val str2: String = "kotlin"
    //with(作用同run函数,参数为null时run函数可读性更强)
    with(str2) {
        println("first:${first()}")
        println("length:$length")
    }

    //T,apply执行完了返回了自身对象。而T.run是返回了执行的结果
    admin.apply {
        this.id = "2" //this可以省略
        loginName = "apply"
    }.loginName //可以继续用对象操作
    println("admin:${admin.id},${admin.loginName}")

    //also作用同apply(必须用it指向调用者(不可省略),apply用this指向调用者(可以省略))
    admin.also {
        it.id = "3"
        it.loginName = "also"
    }
    println("admin:${admin.id},${admin.loginName}")

    //let操作符的作用：当时用符号?.验证的时候忽略掉null
    //返回操作结果(结果已经不是原对象)
    "kotlin".let {
        println("原字符串：$it")         // kotlin
        it.reversed()
    }.let {
        println("反转字符串后的值：$it")     // niltok
        it.plus("-java")
    }.let {
        println("新的字符串：$it")          // niltok-java
    }

    //返回原对象,字符串每次改变后都是新的对象,更改失效
    "kotlin".also {
        println("原字符串：$it")     // kotlin
        it.reversed()
    }.also {
        println("反转字符串后的值：$it")     // kotlin
        it.plus("-java")
    }.also {
        println("新的字符串：$it")        // kotlin
    }

    //返回原对象,字符串每次改变后都是新的对象,更改失效
    "kotlin".apply {
        println("原字符串：$this")     // kotlin
        this.reversed()
    }.apply {
        println("反转字符串后的值：$this")     // kotlin
        this.plus("-java")
    }.apply {
        println("新的字符串：$this")        // kotlin
    }


}

//用关键字 inline 标记函数，该函数就是一个内联函数
//编译器在编译的时候，会自动把内联函数 sum() 方法体内的代码，替换到调用该方法的地方(减少方法调用带来的性能损耗,把代码放入调用该方法的方法中)
//将函数用作函数参数的情况的高阶函数
inline fun CharSequence.sumBy(selector: (Char) -> Int): Int {
    var sum: Int = 0
    for (element in this) {
        sum += selector(element)
    }
    return sum
}

//自定义高阶函数
private fun test(num1: Int, num2: Int, result: (Int, Int) -> Int): Int {
    return result(num1, num2)
}

private fun runTest() {
    val str: String = "88"
    //独立代码块
    run {
        val str = "3"
        println("run中字符串为:$str")
    }
    println("runTest:$str")
}



