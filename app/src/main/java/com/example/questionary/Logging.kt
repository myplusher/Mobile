package com.example.questionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import com.google.gson.Gson

class Logging : AppCompatActivity() {

    var username: TextView? = null
    var password: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logging)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

    }

    private fun login() {
        val p = listOf(
            Pair("username", username?.text),
            Pair("password", password?.text),

        )

        Fuel.post("https://jungtest.herokuapp.com/user/reg", parameters = p)
            .header(mapOf("Content-Type" to "application/json"))
            .response() { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        startActivity(Intent(this, Logging::class.java))
                    }
                }
            }
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.logInBtn -> {
               login()
            }
        }
    }
}