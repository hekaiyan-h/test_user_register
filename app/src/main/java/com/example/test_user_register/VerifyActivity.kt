package com.example.test_user_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.Bmob
import kotlinx.android.synthetic.main.activity_verify.*

class VerifyActivity : AppCompatActivity() {
    //懒加载保存数据
    private val verifyViews:Array<TextView> by lazy {
        arrayOf(mv1, mv2, mv3, mv4, mv5, mv6)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        //获取数据
        intent.getStringExtra("phone").also {
            mPhone.text = it
        }

        //监听文本框内容改变事件
        mOrigin.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                //将输入的内容拆分到每一个TextView中
                for ((i:Int, item:Char) in s?.withIndex()!!){
                    //获取i对应的哪个TextView
                    verifyViews[i].text = item.toString()
                }

                //如果位数少于6位 后面的TextView不显示
                for (i in s.length..5){
                    verifyViews[i].text = ""
                }

                //判断输入的验证码是不是6个
                if (s.length == 6){
                    //发起验证请求
                    BmobUtil.verifySMSCode(mPhone.text.toString(), s.toString()){
                        if (it == BmobUtil.SUCCESS){
                            //跳转主页
                            startActivity(Intent(this@VerifyActivity, HomeActivity::class.java))
                        }else{
                            Toast.makeText(this@VerifyActivity,"验证失败,验证码自动清空", Toast.LENGTH_LONG).show()
                            mOrigin.text.clear()
                        }
                    }
                }
            }

        })
    }

    override fun onResume() {
        super.onResume()
        //调用requestSMSCode方法 使用lambda表达式
        BmobUtil.requestSMSCode(mPhone.text.toString()){
            if (it == BmobUtil.SUCCESS){
                //弹出Toast
                Toast.makeText(this,"发送验证码成功", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "发送验证码失败", Toast.LENGTH_LONG).show()
            }
        }
    }
}