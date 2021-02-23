package com.project.grammar.collection

class MapTest {
}

fun main() {

    val map1 = mapOf("key1" to 2, "key2" to 3)
    val map2 = mapOf<Int, String>(1 to "value1", 2 to "value2")
    val mutableMap = mutableMapOf("key1" to 2, "key1" to 3)
    val hashMap = hashMapOf("key1" to 2, "key1" to 3) // 同Java中的HashMap
    map2.forEach { (key, value) -> println("$key \t $value") }

    //集合类型的协变
    val listPerson:List<Any>
    val listStudent:List<Student> = listOf(Student("张三",12,"一班"),Student("王五",20,"二班"))
    listPerson=listStudent
    listPerson.forEach { println(it.toString()) }

    val mutableListPerson:MutableList<Person>
    val listStudent2:List<Student> = listOf(Student("张五",20,"三年级二班"),Student("王三",21,"三年级一班"))
    mutableListPerson = listStudent2.toMutableList()
    mutableListPerson.forEach { println(it.toString()) }

    //集合转数组
    val mutableListInt = mutableListOf<Int>(1, 3, 5, 7, 9)
    val intArray = mutableListInt.toIntArray()
    intArray.forEach { print("$it ") }
}


open class Person(val name: String, val age: Int) {
    override fun toString(): String {
        return "Person(name='$name', age=$age)"
    }
}

class Student(name: String, age : Int, cls : String) : Person(name, age)
