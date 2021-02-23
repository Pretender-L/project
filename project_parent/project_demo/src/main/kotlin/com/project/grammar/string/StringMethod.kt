package com.project.grammar.string

class StringMethod {
}

fun main(args: Array<String>) {
    val string: String = "testStr"
    val nullString: String = ""

    println("first:" + string.first())
    //Exception in thread "main" java.util.NoSuchElementException: Char sequence is empty.
    /*println(nullString.first())*/
    println("firstOrNull:" + nullString.firstOrNull())
    println("first:" + string.first { it == 'e' })
    //null
    println("firstOrNull:" + string.firstOrNull { it == 's' })
    println("last:" + string.last())
    println("lastOrNull:" + string.lastOrNull { it == 'r' })
    println("------------")

    /*
    indexOf() : 查找某一个元素或字符串在原字符串中第一次出现的下标。
    indexLastOf() : 查找某一个元素或字符串在原字符串中最后一次出现的下标。
     */
    println("indexOfFirst:" + string.indexOfFirst { it == 't' })
    println("indexOfLast+" + string.indexOfLast { it == 't' })
    println("indexOf:" + string.indexOf('t', 0))
    println("indexOf:" + string.indexOf("Str", 0))
    println("lastIndexOf:" + string.lastIndexOf('r'))
    println("lastIndexOf:" + string.lastIndexOf("test"))

    val str = "Kotlin is a very good programming language"
    //当只有开始下标时，结束下标为length-1
    println("substring:${str.substring(10)}")
    //左闭右开
    println("substring:" + str.substring(0, 15))//Kotlin is a ver
    //IntRange范围参数是闭区间
    println("substring:" + str.substring(IntRange(0, 15)))//Kotlin is a very

    //字符串替换全部符合条件的
    val strTest: String = "StringTestString"
    println("strTest:" + strTest.replace("String", "str"))

    //字符串替换第一个符合条件的
    val stringTest: String = "StringTestString"
    println("replaceFirst:" + stringTest.replaceFirst('S', 's'))
    println("replaceFirst:" + stringTest.replaceFirst("String", "Java"))

    //作用：截取满足条件的第一个字符或字符串后面的字符串，包含满足条件字符或字符串自身，并在其前面加上新的字符串。
    val replaceBefore: String = "StringTest"
    println("replaceBefore:" + replaceBefore.replaceBefore("Test", "string"))

    //作用：截取满足条件的最后一个字符或字符串后面的字符串，包含满足条件字符或字符串自身，并在其前面加上新的字符串。
    val replaceBeforeLast: String = "StringTest"
    println("replaceBeforeLast:" + replaceBefore.replaceBeforeLast("Test", "string"))

    //作用：截取满足条件的第一个字符或字符串前面的字符串，包含满足条件字符或字符串自身，并在其后面加上新的字符串。
    val replaceAfter: String = "replaceAfter"
    println("replaceAfter:" + replaceAfter.replaceAfter("replace", "Replace"))

    //作用：截取满足条件的最后一个字符或字符串前面的字符串，包含满足条件字符或字符串自身，并在其后面加上新的字符串。
    val replaceAfterLast: String = "replaceAfter"
    println("replaceAfterLast:" + replaceAfter.replaceAfterLast("replace", "Replace"))

    //多个字符串切割条件
    val splitString = "a b c d e f g h 2+3+4+5"
    val list = splitString.split(' ', '+')
    for (str in list) {
        print("$str \t")
    }
    println()

    val countStr: String = "banana"
    println("重复字符个数:" + countStr.count { it == 'a' })

    /**
     *  验证字符串
     *  isEmpty() : 其源码是判断其length是等于0，若等于0则返回true,反之返回false。不能直接用于可空的字符串
     *  isNotEmpty() : 其源码是判断其length是否大于0，若大于0则返回true,反之返回false。不能直接用于可空的字符串
     *  isNullOrEmpty() : 其源码是判断该字符串是否为null或者其length是否等于0。
     *  isBlank() : 其源码是判断其length是否等于0,或者判断其包含的空格数是否等于当前的length。不能直接用于可空的字符串
     *  isNotBlank() : 其源码是对isBlank()函数取反。不能直接用于可空的字符串
     *  isNotOrBlank() : 其源码判断该字符串是否为null。或者调用isBlank()函数
     */

    //字符串连接
    val oldStr = "kotlin"
    println("plus:" + oldStr.plus(" very good"))

    //字符串翻转
    val reversed = "kotlin"
    println("字符串反转：${reversed.reversed()}")

    //判断字符串的起始与结尾
    val startsWith = "startsWith"
    println("startsWith:" + startsWith.startsWith("st"))
    println("endsWith:" + startsWith.endsWith("th"))
}