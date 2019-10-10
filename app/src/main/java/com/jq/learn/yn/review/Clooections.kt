package com.jq.learn.yn.review

//Array 数组
fun demoArray() {
    val intArr = intArrayOf(1, 2, 3, 4)
    val floatArr = floatArrayOf(1.0f, 2.55f)

    val seecondItem = intArr[1]

    for (num in intArr) {    //遍历
        println(num)
    }

    for ((i, num) in intArr.withIndex()) { //索引和对应的元素
        println("element $i: $num")
    }


    //val samples = Array<Sample>(6,{Sample("1")})   //6个元素，后面是元素内容，例子里都是Sample("1")
    //lamba  表达
    val samples = Array(6) {
        Sample("$it")    //创建了0到6的元素
    }

    //kotlin 的类型推断
    val arr1 = arrayOf(1, 2, 3, 4)
    val samples1 = arrayOf<Sample>(Sample("1"), Sample("2"))

    val reversed = intArr.reversed()        //倒序方法，返回一个与指定list相反顺序的list

    intArr.reverse()                        //倒叙方法

}

data class Sample(val name: String)

// Mutable 如果是一个Mutable的集合，可以增删改，没有的话，是只读的

//List
fun demoList() {
    val list = listOf(1, 2, 3, 4)

    //kotlin特点，可以用下标找到元素
    list[2]

    //kotlin集合类所共有的遍历方法
    list.forEach {
        print("$it")//it指的就是每一个元素
    }

//    val list1 = List<Int>(7) { it }  //同样可以这么定义
//    list1.add     //没有add方法
//    list1[2] = 8  //自动初始化的集合，没有Mutable修饰，无法添加，修改

    //需要Mutable修饰
    val list2 = MutableList(7) { it }
    list2[2] = 8
    list2.add(3, 9)//第4个位置插入9


    //  filter 提取满足某个条件的元素
    val evenNumbers = list.filter {
        //it 代表集合内的每一个元素
        it % 2 == 0
    }//{2,4}

    //  map 对集合进行操作
    val doubledList = list.map { it * 2 }       //{2,4,6,8}

    //reduce 累加
    //val reducedValue = list.reduce(sum) //1+2+3+4  没找到方法
    val reducedValue = list.reduce { acc, i -> acc + i }

    //search
    val v = list.first { it == 2 }

    //slice
    val s = list.slice(1..3) //那下标1到3的，参数只能是个范围

    //链式操作，找出比1大的元素，取部分，double，做和操作
    val result = list.filter { it > 1 }
            .slice(1..2)
            .map { it * 2 }
//                .reduce(sum)

}


//set 元素唯一，不重复
//一般用来放东西，所有一般用mutableSet

fun demoSet() {
    val set = mutableSetOf<Int>()

    set.add(1)
    set.add(2)
    set.add(3)
    set.add(1)          //与第一个重复，被忽略

    print(set.size)     //3  元素重复的会被忽略

    //常用方法
    set.contains(5)     //查看set有没有特点的元素，false
}

//Map
//HashMap 键值对

fun demoMap() {

    val map: HashMap<Int, String> = hashMapOf(
            1 to "Hello",
            2 to "Kotlin"
    )

    //返回一个list，每个parms 就是 key = value的形式
    val entries = map.entries     //1=Hello,2=Kotlin

    map.keys //{1, 2}
    map.values//{"Hello","Kotlin"}
}









