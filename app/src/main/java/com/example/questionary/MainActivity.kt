package com.example.questionary

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.questionary.data.model.User
import kotlinx.android.synthetic.main.activity_main.*

//import com.example.questionary.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)

    var questions: Array<Question>? = null

    // var answersOfQuestions: Array<AnswerOfQuestion>? = null
    var currentQuestion: Question? = null
    //  var currentAnswerOfQuestion: AnswerOfQuestion? = null
    // var textQuestion:TextView? = null
    var loginbtn: Button? = null
    var mainText: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginbtn = findViewById(R.id.logInBtn)
        mainText = findViewById(R.id.mainText)

        if (User.token == "") {
            loginbtn?.text = "log in"
            mainText?.text = "Are you not with us yet?"
        } else {
            mainText?.text = "Hello, " + User.username
            loginbtn?.text = "log out"
        }

        contBtn?.setOnClickListener {
            startActivity(Intent(this, Questions::class.java))
        }
        logInBtn?.setOnClickListener {
            if (User.token == "") {
                startActivity(Intent(this, Login::class.java))
            } else {
                User.token = ""
                loginbtn?.text = "log in"

                finish()
                startActivity(intent)
            }
        }
        btnProfile?.setOnClickListener {
            if (User.token == ""){
                startActivity(Intent(this, Login::class.java))
            } else {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }

    }

}
