package com.example.test_user_register

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        cym.setOnClickListener{
            val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, cym, "meishi1")
            startActivity(Intent(this, DetailedActivity::class.java), options.toBundle())
        }

        xlx.setOnClickListener{
            val options: ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(this, xlx, "meishi2")
            startActivity(Intent(this, DetailedActivity2::class.java), options.toBundle())
        }
    }
}