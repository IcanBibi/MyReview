package com.jq.learn.yn.review

import android.util.Log
val TAG = "KOTLIN"
fun demo1(){
    var str: String = "Hello"
    var s = StringBuffer(str).indexOf("H")
    Log.d(TAG,s.toString())
}
