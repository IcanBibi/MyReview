package com.jq.learn.yn.review

data class User(    //data修饰，数据类，系统会自动有某些方法
        val name: String,
        val age: Int)

fun demoDataClass() {
    val user = User("somedody", 25)
    val user2 = user.copy(name = "another")         //copy， data的免费方法

    val (name,age) = user
    //等同于下面，用于大量数据循环
//    var name = user.name
//    var age = user.age

    print(user.toString()) //
}


sealed class Event(message: String)

data class Event1(val data: Int): Event("1")

class Event2: Event("2")

fun demoSealed(){
    var evt: Event = Event2()

    when(evt) {
         is Event1 -> {}
         is Event2 -> {}
    }
}