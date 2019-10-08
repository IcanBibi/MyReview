package com.jq.learn.yn.review

open class Human (val age: Int,val gender: Boolean){      //被继承需要open修饰

     private val i: Int = 200  //派生类无法继承 privite 修饰的
     protected val i2: Int = 200 //派生类可以继承 protected 修饰,但在派生类外部无法使用

    val mouth = 1
    fun speak() {

    }

    val legs = 2
    open fun walk() {
        print("I am walking!")
    }
}

class Woman(myAge: Int) : Human(myAge,true) {  // 继承 ： 冒号跟上基类的构造函数
    val earRings = 2

    fun makeUp() {
        i2              //可以调用父类的 protected 修饰 ，不能调用 privite 修饰
    }

    override fun walk(){        //重载override，父类需要open修饰
        print("walking slowly")
    }
}



class Man(myAge: Int): Human(myAge,false){
    override fun walk() {
        print("walking quickly")
    }
}

fun demoInheritance() {
    var human: Human

    human = Woman(25)  //无法使用protected 修饰符
    human.walk()     // walking slowly

    human = Man(25)
    human.walk()      //walking quickly
}


fun runningMan(man: Man){
    print("I am a man of age ${man.age},${man.walk()}")
}

fun Man.run(){
    print("$age,${walk()}")
}


