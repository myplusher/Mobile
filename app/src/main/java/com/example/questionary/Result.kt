package com.example.questionary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    var anxiety: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

//        profileBtn?.setOnClickListener {
//            // TODO повесить проверку если юзер авторизирован
//            startActivity(Intent(this, ProfileActivity::class.java))
//        }

        exitBtn?.setOnClickListener {
            finishAffinity()
        }

//        againBtn?.setOnClickListener {
//            startActivity(Intent(this, BeforeTestActivity::class.java))
//        }

        //prefs =
        //    getSharedPreferences("settings", Context.MODE_PRIVATE)
        //editor = prefs.edit()

        //anxiety = prefs.getString("anxiety", "")?.toInt()!!

        //this.setResponse(anxiety)
    }

//    private fun setResponse(anxiety: Int) {
//        var result: TextView? = null
//        result = findViewById(R.id.result)
//
//        when (anxiety) {
//            0,1,2 -> {
//                result?.text = "Anxiety score: " + anxiety.toString() + " out of 12. \n You have a state of minor anxiety. You're doing great! Keep moving in this direction and don't forget to rest"
//            }
//            3,4,5 -> {
//                result?.text = "Anxiety score: " + anxiety.toString() + " out of 12. \n You have a state of emotional tension. Get more rest and everything will get better."
//            }
//            6,7,8 -> {
//                result?.text = "Anxiety score: " + anxiety.toString() + " out of 12. \n You have a state of disadaptation. Try to relax more and not strain yourself so much."
//            }
//            9,10,12 -> {
//                result?.text = "Anxiety score: " + anxiety.toString() + " out of 12. \n You have a state of psychological and physiological stress. We recommend you contact a specialist."
//            }
//        }
//    }
}