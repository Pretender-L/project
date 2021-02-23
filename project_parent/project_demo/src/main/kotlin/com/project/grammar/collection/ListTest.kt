package com.project.grammar.collection

class ListTest {
}

fun main() {
    //不可变集合
    val list = listOf<String>("1", "2")
    list.forEach { println(it) }

    println("----------------------")

    //可变集合
    val mutableListOf1 = mutableListOf<String>("1", "3", "5")
    mutableListOf1.add("7")
    mutableListOf1.forEach { println(it) }

    println("----------------------")

    //不加泛型随意存放
    val mutableListOf2 = mutableListOf(1,"2","3")
    //第二种遍历方式
    for (item in mutableListOf2) {
        println(item)
    }

    //set集合方法同list,特性去重

}