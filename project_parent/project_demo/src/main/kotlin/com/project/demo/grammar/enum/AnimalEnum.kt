package com.project.demo.grammar.enum

enum class AnimalEnum {
    dog, cat,monky,panda,fish
}

fun main(args:Array<String>){
    println(AnimalEnum.cat.name)
}