package com.example.questionary

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

//import com.example.questionary.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)

    var questions: Array<Question>? = null

    // var answersOfQuestions: Array<AnswerOfQuestion>? = null
    var currentQuestion: Question? = null
    //  var currentAnswerOfQuestion: AnswerOfQuestion? = null
    // var textQuestion:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contBtn?.setOnClickListener {
            startActivity(Intent(this, Questions::class.java))
        }
        logInBtn?.setOnClickListener {
            startActivity(Intent(this, Logging::class.java))
        }
        createAccBtn?.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

    }

}
