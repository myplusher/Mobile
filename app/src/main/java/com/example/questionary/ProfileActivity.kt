package com.example.questionary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.questionary.data.model.User
import com.example.questionary.data.model.UserForRegistration
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
//import im.dacer.androidcharts.LineView
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_result.*
import java.util.ArrayList

//import com.example.questionary.data.model.Statistic
//import com.example.questionary.data.model.UserForRegistration


class ProfileActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var username: TextView? = null
    var sex: TextView? = null
    var birthday: TextView? = null
    var user: UserForRegistration? = null

    //var user: UserForRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        username = findViewById(R.id.username)
        sex = findViewById(R.id.sex)
        birthday = findViewById(R.id.birthday)
        username?.text = User.username


//        startTest?.setOnClickListener {
//            startActivity(Intent(this, BeforeTestActivity::class.java))
//        }

        prefs =
            getSharedPreferences("settings", Context.MODE_PRIVATE)
        editor = prefs.edit()

        //login = prefs.getString("login", "")

        //this.getUser()

        //this.getStatistic()

        Fuel.get("https://jungtest.herokuapp.com/user/myprofile")
            .header(
                mapOf(
                    "Content-Type" to "application/json",
                    "Authorization" to "Bearer ${User.token}"
                )
            )
            .responseObject(User.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        user = result.component1()
                        User.username = user?.username.toString()
                        if (user?.sex == true) {
                            User.sex = "male"
                        } else {
                            User.sex = "female"
                        }
                        User.dateOfBirth = user?.dateOfBirth.toString()
                        username?.text = User.username
                        sex?.text = User.sex
                        birthday?.text = User.dateOfBirth
                        User.token = response.headers["Authorization"].first()
                        println(User.token)
                    }
                }
            }
    }

    private fun getUser() {
        Fuel.get("https://jungtest.herokuapp.com/user/$username")
            .responseObject(User.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
//                        user = result.component1()
//                        username?.text = this.user?.login
//                        email?.text = this.user?.email
//                        sex?.text = this.user?.sex
//                        birthday?.text = this.user?.birthday?.split("T")?.get(0)
//                        println(user)
                    }
                }
            }
    }

//    private fun getStatistic() {
//        Fuel.get("https://jungtest.herokuapp.com/user/stat/{sex}/{minAge}/{maxAge}")
//            .responseObject(Statistic.Deserializer()) { request, response, result ->
//                when (result) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        println(ex)
//                    }
//                    is Result.Success -> {
//                        this.drawGraf(result.component1())
//                        println(user)
//                    }
//                }
//            }
//    }

//    private fun drawGraf( statistics: Array<Statistic>?) {
//        lineView.setDrawDotLine(false) //optional
//
//        lineView.setShowPopup(LineView.SHOW_POPUPS_All) //optional
//
//        val dateList: ArrayList<String> = ArrayList()
//        val anxietyList: ArrayList<Int> = ArrayList()
//
//        val dataList: ArrayList<ArrayList<Int>> = ArrayList()
//
//        if (statistics != null) {
//            for (item in statistics) {
//                dateList.add(item.date.split("T")[0])
//                anxietyList.add(item.anxiety)
//            }
//            dataList.add(anxietyList)
//        }
//
//        lineView.setBottomTextList(dateList)
//        lineView.setColorArray(intArrayOf(Color.BLUE))
//        lineView.setDataList(dataList) //or lineView.setFloatDataList(floatDataLists)
//    }
}