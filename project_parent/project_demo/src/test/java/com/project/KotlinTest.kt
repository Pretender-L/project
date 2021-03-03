package com.project

import org.junit.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class KotlinTest {
    /**
     * 常量
     * const val NUM_A = 6
     */

    /**
     * 包含任意字符的字符串 由三重引号（""" .... """）表示
     * """ fun main(args: Array<String>){
     *         println("我是三重引号引用的字符串，我可以包含任意字符")
     *     } """
     */

    /**
     * 字符串模板
     * 使用字符串模板的符号为（$）。在$符号后面加上变量名或大括号中的表达式
     */
    @Test
    fun strTest() {
        val text1: String = "我来了！"
        var text2: String = "$text1 kotlin"
        var text3: String = "$text2 ${text1.length} 哈哈！！！！"
        println(text1)
        println(text2)
        println(text3)
    }

    /**
     * 后期初始化:
     * 使用lateinit关键字
     * 必须是可读且可写的变量，即用var声明的变量
     * 不能声明于可空变量。
     * 不能声明于基本数据类型变量。例：Int、Float、Double等，注意：String类型是可以的。
     * 声明后，在使用该变量前必须赋值，不然会抛出UninitializedPropertyAccessException异常。
     */
    @Test
    fun lateInitTest() {
        // 声明组件
        lateinit var redisTemplate: RedisTemplate<String, Any>
        //lateinit var a : Int // 会报错。因为不能用于基本数据类型。
    }

    /**
     * 延迟初始化属性:指当程序在第一次使用到这个变量（属性）的时候在初始化。
     */
    @Test
    fun lazyTest() {
        val mStr: String by lazy {
            "我是延迟初始化字符串变量"
        }
        println(mStr)
    }

    /**
     * if(类似三元运算符)
     */
    @Test
    fun ifTest() {
        var string: String = ""
        val str: String = if (string == "1") "1" else "null"
        println("---------------$str")
    }

    /**
     * if(块结构)
     */
    @Test
    fun ifDemo(){
        var numA: Int = 2
        var numC: Int = if (numA > 2){
            numA++
            numA = 10
            println("numA > 2 => true")
            numA
        }else if (numA == 2){
            numA++
            numA = 20
            println("numA == 2 => true")
            numA
        }else{
            numA++
            numA = 30
            println("numA < 2 => true")
            numA
        }
        // 根据上面的代码可以看出，每一个if分支里面都是一个代码块，并且返回了一个值。根据条件numC的值应该为20
        println("numC => $numC")
    }

    /**
     *  let操作符的作用：当时用符号?.验证的时候忽略掉null
     */
    @Test
    fun letDemo() {
        val arrTest: Array<Int?> = arrayOf(1, 2, null, 3, null, 5, 6, null)
        // 传统写法
        for (index in arrTest) {
            if (index == null) {
                continue
            }
            println("index = $index")
        }
        // let写法
        for (index in arrTest) {
            index?.let { println("index => $it") }
        }
    }

    /**
     * 其实这里是指as操作符，表示类型转换，如果不能正常转换的情况下使用as?操作符。
     * 当使用as操作符的使用不能正常的转换的情况下会抛出类型转换（ClassCastException）异常，而使用as?操作符则会返回null,但是不会抛出异常
     */
    @Test
    fun asDemo() {
        // 会抛出ClassCastException异常
        val num1: Int? = "Koltin" as Int
        println("nun1 = $num1")
    }

    /**
     *  ?: 这个操作符表示在判断一个可空类型时，会返回一个我们自己设定好的默认值.
     */
    @Test
    fun demo01() {
        val testStr: String? = null
        var length = 0
        // 例： 当testStr不为空时，输出其长度，反之输出-1
        // 传统写法
        length = if (testStr != null) testStr.length else -1
        // ?: 写法
        length = testStr?.length ?: -1
        println(length)
    }

    /**
     *  !! 这个操作符表示在判断一个可空类型时，会显示的抛出空引用异常（NullPointException）.
     */
    @Test
    fun demo02() {
        //val testStr: String? = null
        val testStr: String? = "null"
        println(testStr!!.length)
    }
}