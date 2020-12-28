package com.example.questionary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.questionary.data.model.User
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_result.*
import kotlin.random.Random

class Result : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    var anxiety: Int = 0
    var defText: TextView? = null
    var num: ResultNum? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
//        Fuel.get("https://jungtest.herokuapp.com/questionnaire/result")
//            .header(mapOf("Content-Type" to "application/json", "Authorization" to "${User.token}"))
//            .responseObject(ResultNum.Deserializer()) { request, response, result ->
//                when (response) {
//                    response -> {
//                        var a = response.body()
//
////                        val ex = result.getException()
//                        println(a)
//                    }
//                    //is Result.Success -> {
//                    //    num = result.component1()
//                    //    var a = response.body()
//                   //     println(num)
//                    //}
//                }
//            }

        var r = Random.nextInt(0,2)
        val list = listOf("introvert", "extrovert", "ambivert")
        var value = list[r]

        defText = findViewById(R.id.resultText)
        defText?.text = "According to the test, you are " + value





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

data class ResultNum (
    var number: String,
) {
    fun ResultNum(number: String) {
        this.number = number
    }

    class Deserializer : ResponseDeserializable<ResultNum> {
        override fun deserialize(content: String): ResultNum =
            Gson().fromJson(content, ResultNum::class.java)
    }
}