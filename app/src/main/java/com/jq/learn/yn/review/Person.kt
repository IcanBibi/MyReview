package com.jq.learn.yn.review

class Person(
        var firstName: String,
        var lastName: String) {

    var fullName: String

        get() = "$firstName $lastName"
        set(value) {
            val  names = value.split(" ")
            firstName = names[0]
            lastName = names[1]
        }
}

fun demoNames(){
    val p = Person("zhang","san")


    p.fullName = "li si"
    print(p.firstName)  //li
    print(p.lastName)   //si

}
