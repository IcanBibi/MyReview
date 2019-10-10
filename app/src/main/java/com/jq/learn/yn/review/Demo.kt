package com.jq.learn.yn.review

fun <T, R> T.let(block: (T) -> R): R = block(this) //R

fun <T, R> T.run(block: T.() -> R): R = block() //R

 fun <T> T.apply(block: T.() -> Unit): T {
     block(); return this
 }// T

 fun <T> T.also(block: (T) -> Unit): T {
     block(this); return this
 }// T


data class GoodResult(val value: String){
    fun method1(){}

    fun method2(){
        print(value)
    }
}

fun convertGoodResult(good: GoodResult): String = "BEST!!"

object Api{
    fun getResult(): GoodResult? {
        return if (System.currentTimeMillis() < 100000)
            GoodResult("good") else null
    }
}

fun demo(){
    Api.getResult()?.method1()

    val resultLet = Api.getResult()?.let {
        print(it.value)
        convertGoodResult(it)
    }//BEST

    val resultRun = Api.getResult()?.run {
        method1()
        method2()
        convertGoodResult(this)
    }//BEST

    Api.getResult()?.apply {
        method1()
        method2()
    }

    Api.getResult()?.also {
        convertGoodResult(it)
    }

    with(Api.getResult()!!){
        convertGoodResult(this)
    }
}