package com.example.questionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.example.questionary.data.model.User

class Login : AppCompatActivity() {

    var username: TextView? = null
    var password: TextView? = null
    var btnLog: Button? = null
    var user: User? = null
    var question: Question? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnLog = findViewById(R.id.logInBtn)

    }

    private fun login() {
        val p = listOf(
            Pair("username", username?.text),
            Pair("password", password?.text),

            )

        val bodyJson = """
              { "username" : "${username?.text}",
                "password" : "${password?.text}"
              }
            """

        Fuel.post("https://jungtest.herokuapp.com/login")
            .header(
                mapOf(
                    "Content-Type" to "application/json",
                    "Authorization" to "Bearer $"
                )
            )
            .body(bodyJson)
            .response() { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        User.token = response.headers["Authorization"].first()
                        User.username = username?.text.toString()
                        startActivity(Intent(this, MainActivity::class.java))
                        btnLog?.text = "logout"
                        println(User.token)
                    }
                }
            }

        //getProfile()
    }

    fun getProfile() {
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
                        btnLog?.text = "logout"
                        startActivity(Intent(this, MainActivity::class.java))
                        User.token = response.headers["Authorization"].first()
                        println(User.token)
                    }
                }
            }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.logInBtn -> {
                login()
            }
            R.id.createAccBtn -> {
                startActivity(Intent(this, RegistrationActivity::class.java))
            }
        }
    }
}