package com.example.test_user_register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.SpannableStringBuilder
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    //自动分割
    private var shouldAutoSplit = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //手机号输入框文本改变的监听
        mPhoneEditText.addTextChangedListener(object:LoginTextWatcher(){
            override fun afterTextChanged(s: Editable?) {
                //设置按钮是否可以点击，如果手机号码长度为13，enable=true；否则为false
                mRegister.isEnabled = (s.toString().length == 13)

                //判断是在删除还是输入，如果是在删除，后面不需要再执行
                if (!shouldAutoSplit) return

                s.toString().length.also {
                    if (it == 3 || it == 8){
                        //需要添加空格
                        s?.append(' ')
                    }
                }
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //count==1时在输入数字  count==0时在删除数字
                shouldAutoSplit = (count == 1)
            }
        })

        //注册按钮的监听
        mRegister.setOnClickListener{
            Intent().apply {
                //跳转方向
                setClass(this@MainActivity, VerifyActivity::class.java)
                //配置跳转携带的数据
                putExtra("phone",getPhoneNumber(mPhoneEditText.text))
                //启动
                startActivity(this)
            }
        }
    }

    //创建一个类实现TextWatcher接口
    open class LoginTextWatcher: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }

        override fun afterTextChanged(s: Editable?) {

        }

    }

    //将格式化的内容转化为正常数据
    private fun getPhoneNumber(editable: Editable):String{
        //创建一个新的对象 用于操作editable对象中的内容
        SpannableStringBuilder(editable.toString()).also {
            it.delete(3,4)
            it.delete(7,8)
            return it.toString()
        }
    }
}