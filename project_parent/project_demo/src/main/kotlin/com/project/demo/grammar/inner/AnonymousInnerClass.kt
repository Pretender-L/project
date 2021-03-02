package com.project.demo.grammar.inner

/**
 * 匿名内部类方法
 * @author wjl
 */
class AnonymousInnerClass {
    lateinit private var onClickListener: OnClickListener

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    fun testOnItemClick(){
        onClickListener.onItemClick("匿名类部类方法")
    }
}

interface OnClickListener {
    fun onItemClick(string: String)
}

fun main(args:Array<String>){
    val anonymousInnerClass = AnonymousInnerClass()
    anonymousInnerClass.setOnClickListener(object :OnClickListener{
        override fun onItemClick(string: String) {
            println(string)
        }
    })
    anonymousInnerClass.testOnItemClick()
}