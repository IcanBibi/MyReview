package com.jq.learn.yn.review

class MyClass( val v: Int, var s: String){

    constructor(tmp: Int) : this(tmp,"")
    constructor() : this(500,"")

     fun myFun() {
        println("Hello $v")
    }

    companion object {
        const val commonV  = 2000
        fun commonFun(){}
    }

}

fun useMyClass(){
    val instance = MyClass(300)
    instance.myFun()

    print(instance.v)

    MyClass.commonV
    MyClass.commonFun()
}