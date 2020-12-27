package com.example.questionary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
//import im.dacer.androidcharts.LineView
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_result.*
import java.util.ArrayList
//import com.example.questionary.data.model.Statistic
//import com.example.questionary.data.model.UserForRegistration


class ProfileActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    var login: String? = null;

    //var user: UserForRegistration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

//        startTest?.setOnClickListener {
//            startActivity(Intent(this, BeforeTestActivity::class.java))
//        }

        prefs =
            getSharedPreferences("settings", Context.MODE_PRIVATE)
        editor = prefs.edit()

        login = prefs.getString("login", "")

        //this.getUser()

        //this.getStatistic()
    }

//    private fun getUser() {
//        Fuel.get("http://10.0.2.2:8080/api/users/$login")
//            .responseObject(UserForRegistration.Deserializer()) { request, response, result ->
//                when (result) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        println(ex)
//                    }
//                    is Result.Success -> {
//                        user = result.component1()
//                        username?.text = this.user?.login
//                        email?.text = this.user?.email
//                        sex?.text = this.user?.sex
//                        birthday?.text = this.user?.birthday?.split("T")?.get(0)
//                        println(user)
//                    }
//                }
//            }
//    }
//
//    private fun getStatistic() {
//        Fuel.get("http://10.0.2.2:8080/api/answer-of-questions/statistic/$login")
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
//
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