package com.example.questionary

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.google.android.material.button.MaterialButton
import com.google.gson.Gson
//import com.example.questionary.data.model.AnswerOfQuestion
//import com.example.questionary.data.model.Question
//import com.example.questionary.data.model.TypeOfQuestion
//import com.example.questionary.ui.login.LoginActivity






class Questions : AppCompatActivity() {

    var next: Button? = null
    var cancel: Button? = null
    var number: TextView? = null
    var question: TextView? = null
    var questions: Question? = null
    var count: Int = 1
    var even: Button? = null
    var odd: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        next = findViewById(R.id.btnNext)
        cancel = findViewById(R.id.btnCancel)
        number = findViewById(R.id.textNumber)
        question = findViewById(R.id.textQuestion)
        even = findViewById(R.id.btnEven)
        odd = findViewById(R.id.btnOdd)
        getQuestions()
    }

    private fun getQuestions() {
        Fuel.get("https://jungtest.herokuapp.com/question/" + count)
            .responseObject(Question.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        questions = result.component1()
                        if (questions != null) {
                            z()
                        }
                        println(questions)
                    }
                }
            }
    }

    fun z() {
        number?.setText("Question " + questions?.id)
        question?.setText(questions?.text)
        even?.setText(questions?.answers?.get(0)?.text)
        odd?.setText(questions?.answers?.get(1)?.text)

    }

    private fun sendAnswer(id: Int) {
        val p = listOf(
            Pair("username", id.toString()),
        )
        Fuel.post("https://jungtest.herokuapp.com/selanswer/sav", parameters = p)
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

    fun OnClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                count++
                if (count <= 19){
                    getQuestions()
                } else if (count == 20) {
                    next?.setText("complete")
                    getQuestions()
                } else {
                    startActivity(Intent(this, com.example.questionary.Result::class.java))
                }
            }

            R.id.btnCancel -> {
                if (count == 1) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    count--
                    getQuestions()
                }
            }
            R.id.btnEven -> {
                println(count*2)
                sendAnswer(count*2)
            }
            R.id.btnOdd -> {
                println((count*2)-1)
                sendAnswer((count*2)-1)
            }
        }
    }

}

data class Question(
    var id: String,
    var text: String,
    var answers: Array<Answer>,
) {
    class Deserializer : ResponseDeserializable<Question> {
        override fun deserialize(content: String): Question =
            Gson().fromJson(content, Question::class.java)

    }
}

data class Answer(
    var id: String,
    var text: String
)