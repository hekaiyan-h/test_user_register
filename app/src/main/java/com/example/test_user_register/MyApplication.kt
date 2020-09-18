package com.example.test_user_register

import android.app.Application
import cn.bmob.v3.Bmob

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        // //第一：默认初始化
        Bmob.initialize(this, "b1bcedc1570ddc5855cab415b78924ba")
    }
}