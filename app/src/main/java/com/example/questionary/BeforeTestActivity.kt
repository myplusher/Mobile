package com.example.questionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_before_test.*

class BeforeTestActivity : AppCompatActivity() {

    var info: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_before_test)
        info = findViewById(R.id.infoText)

        bntNo?.setOnClickListener {
            // TODO
            startActivity(Intent(this, MainActivity::class.java))
        }

        btnYes?.setOnClickListener {
            startActivity(Intent(this, Questions::class.java))
        }
//
//        profile?.setOnClickListener {
//            // TODO повесить проверку если юзер авторизирован
//            startActivity(Intent(this, ProfileActivity::class.java))
//        }

    }
}