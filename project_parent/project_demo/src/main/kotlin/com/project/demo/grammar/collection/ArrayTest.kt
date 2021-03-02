package com.project.demo.grammar.collection

class ArrayTest {

}

fun main() {
    //kotlin的数组不限制类型
    val array = arrayOf("a", 2, 3, 5, "b")
    println(array[0])
    println(array.component5())
    array.reverse()
    array.forEach { println(it) }
}