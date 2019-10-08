package com.jq.learn.yn.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

class KotlinActivity : AppCompatActivity() {

    val TAG = "KotlinActivity123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)

        demo1()
        demo2()
        demo3()
        demo4("zhang",1)


        read(byteArrayOf(1,2,3,4),2)
        vars(1)
        vars(1,2,3)
        vars(1, 2, 3, 4)


        demoArray()
    }

    fun vars(vararg v: Int) {
        for (vt in v) {

        }

        fun sum(a: Int, b: Int) = a + b
    }

    fun demoKt(){
        val arr = arrayOf(1,2,3,4,5,6)
        print(arr.filter({ it % 2 ==0 })) 	//

    }

    private fun demo4(s: String, second: Any): Boolean {

        return s == "Wang"
    }

    private fun read(b: ByteArray, off: Int = 0, len: Int = b.size) {

    }
    private fun getV8() = 4
    private fun demo3() {
        var result = when (getV8()) {
            1 -> "1"
            else -> {"0"}
        }
        Log.d(TAG, result)


        var list = listOf(1,3,5,7,9)
        val withIndex = list.withIndex()
        for ((i, v) in list.withIndex()) {

            Log.d(TAG, "i>>>>>>$i")
            Log.d(TAG, "v>>>" + v)
        }


        val mylist = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        for (i in mylist.indices) {
            Log.d(TAG, ">>>>>>>$i>>>>${mylist[i]}")

        }

    }

    private fun demo1() {
        var str: String = "Hello"
        val s = StringBuilder(str).indexOf("H").toString()
        Log.d(TAG, s)

        val s1 = "我"
        val s2 = "你"
        val s3 = "$s1 是$s2，${1 + 2}"
        Log.d(TAG, s3)


        val list = listOf(1, 3, 5, 7)
    }

    private fun demo2() {
        val answer = 3 + 2
        if (answer == 5) {
            Log.d(TAG, "good")
        }

        var isTrue: Boolean = true
        var result = 10
        result = if (isTrue) 11 else 12

        Log.d(TAG, "结果是" + result)


    }

    fun demoArray() {
        val intArr = intArrayOf(1,2,3,4)

        val reversed = intArr.reversed()//4，3,2,1
        for ((i, num) in reversed.withIndex()) {
            Log.d(TAG, "reversed>>>>>>第${i}个是${num}")
        }

        intArr.reverse()
        Log.d(TAG, "intArr[0]结果是${intArr[0]}")


        val map: HashMap<Int,String> = hashMapOf(
                1 to "Hello",
                2 to "Kotlin"
        )

        val entries = map.entries     //返回一个list，每个parms 就是
        for (i in entries) {
            Log.d(TAG, "entries结果是${i}")
        }

    }



}
