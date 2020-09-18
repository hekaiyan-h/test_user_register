package com.example.test_user_register

import android.util.Log
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import cn.bmob.v3.listener.UpdateListener
import com.example.test_user_register.BmobUtil.verifySMSCode

object BmobUtil {
    const val SUCCESS = 0
    const val FAILURE = 1

    //先服务器发送请求--发送验证码  ->  发送成功 发送失败
    fun requestSMSCode(phone: String, callback: (Int) -> Unit){
        BmobSMS.requestSMSCode(phone, "", object : QueryListener<Int>() {
            override fun done(p0: Int?, p1: BmobException?) {
                if (p1 == null){
                    //验证码发送成功
                    callback(SUCCESS)
                }else{
                    //验证码发送失败
                    //打印错误信息
                    Log.v("aaa","错误码：${p1.errorCode}  message:${p1.message}")
                    callback(FAILURE)
                }
            }

        })
    }

    //需要验证用户输入的验证码 -> 验证成功 验证失败
    fun verifySMSCode(phone: String, code: String, callBack: (Int) -> Unit) {
        BmobSMS.verifySmsCode(phone, code, object : UpdateListener(){
            override fun done(p0: BmobException?) {
                if (p0 == null){
                    //验证成功
                    callBack(SUCCESS)
                }else{
                    //验证失败
                    Log.v("aaa","错误码:${p0.errorCode}  message:${p0.message}")
                    callBack(FAILURE)
                }
            }

        })
    }
}