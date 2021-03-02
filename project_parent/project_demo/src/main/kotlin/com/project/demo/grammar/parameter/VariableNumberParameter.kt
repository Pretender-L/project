package com.project.demo.grammar.parameter

/**
 * 可变数量参数
 * @author wjl
 */
class VariableNumberParameter {
    fun test(vararg string: String) {
        for (s in string) {
            println(s)
        }
    }
}

fun main(args: Array<String>) {
    val variableNumber = VariableNumberParameter()
    variableNumber.test("a", "b", "c")
    println("===========")

    val arrayOf = arrayOf("1", "2", "3")
    //伸展操作符( * )
    variableNumber.test(*arrayOf)
}