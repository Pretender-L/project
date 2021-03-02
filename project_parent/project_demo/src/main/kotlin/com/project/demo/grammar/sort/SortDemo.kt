package com.project.demo.grammar.sort

import com.project.demo.pojo.People

class SortDemo {
}

fun main(args: Array<String>) {
    val peopleList = mutableListOf<People>()
    for (i in 0..10) {
        val people = People()
        people.age = (0..10).random()
        people.sort = (0..10).random()
        peopleList.add(people)
    }

    //升序排序
    //peopleList.sortBy { it.age }

    //降序排序
    //peopleList.sortByDescending { it.age }

    //多个排序条件
    //peopleList.sortWith(compareBy({ it.age }, { it.sort }))

    //比较器对象排序
    val comparator: Comparator<People> = Comparator() { o1, o2 ->
        if (o1.age == o2.age) {
            o1.sort.compareTo(o2.sort)
        } else {
            o1.age - o2.age
        }
    }
    peopleList.sortWith(comparator)

    for (people in peopleList) {
        println("${people.age}---${people.sort}")
    }
}