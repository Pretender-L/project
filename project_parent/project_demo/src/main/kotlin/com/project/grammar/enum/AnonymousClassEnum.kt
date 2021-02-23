package com.demo.grammar.enum

/**
 * 枚举常量的匿名类
 * 要实现枚举常量的匿名类，则必须提供一个抽象方法（必须重写的方法）。且该方法定义在枚举类内部。而且必须在枚举变量的后面。
 * 枚举变量之间使用逗号（,）分割开。但是最后一个枚举变量必须使用分号结束。不然定义不了抽象方法。
 * 在上面已经说过，每一个枚举常量就是一个对象。
 */
enum class AnonymousClassEnum {
    apple {
        override fun println() {
            println("我是枚举常量apple")
        }
    },
    banana {
        override fun println() {
            println("我是枚举常量banana")
        }
    };

    abstract fun println();
}

fun main(args: Array<String>) {
    AnonymousClassEnum.apple.println()
    println("----------------")

    println(enumValues<AnonymousClassEnum>().joinToString { it.name })
    println(enumValueOf<AnonymousClassEnum>("apple"))
    println("----------------")

    println(AnonymousClassEnum.valueOf("banana"))
    println(AnonymousClassEnum.values()[0])
    println(AnonymousClassEnum.values()[1])

}