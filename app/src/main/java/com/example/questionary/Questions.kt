package com.example.questionary

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.questionary.data.model.User
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.result.Result
import com.google.gson.Gson

//import com.example.questionary.data.model.AnswerOfQuestion
//import com.example.questionary.data.model.Question
//import com.example.questionary.data.model.TypeOfQuestion
//import com.example.questionary.ui.login.LoginActivity


class Questions : AppCompatActivity() {

    var next: Button? = null
    var cancel: Button? = null
    var number: TextView? = null
    var questionlabel: TextView? = null
    var question: Question? = null
    var index: Int = 0
    var even: Button? = null
    var odd: Button? = null
    var access: Array<Boolean> = arrayOf(
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true,
        true
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        next = findViewById(R.id.btnNext)
        cancel = findViewById(R.id.btnCancel)
        number = findViewById(R.id.textNumber)
        questionlabel = findViewById(R.id.textQuestion)
        even = findViewById(R.id.btnEven)
        odd = findViewById(R.id.btnOdd)


    }

    override fun onStart() {
        super.onStart()
        getQuestions()
    }

    private fun getQuestions() {
        Fuel.get("https://jungtest.herokuapp.com/question/" + index)
            .responseObject(Question.Deserializer()) { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        question = result.component1()
                        question?.let { z(it) }

                        println(question)
                    }
                }
            }
    }

    fun z(question: Question) {
        var answ = question?.answers!!
        number?.text = "Question " + question?.id
        questionlabel?.text = question?.text
        even!!.text = answ[0].text
        odd!!.text = answ[1].text

    }

    fun setUnClick(v: View) {
        if (!access[index-1]) {
            even?.isClickable = false
            odd?.isClickable = false
        } else {
            even?.isClickable = true
            odd?.isClickable = true
        }
    }


    private fun sendAnswer(id: Int) {
        val p = listOf(
            Pair("id", id.toString()),
        )

        val bodyJson = """
              { 
                "id" : "$id",
              }
            """

        Fuel.post("https://jungtest.herokuapp.com/selanswer/sav")
            .header(mapOf("Content-Type" to "application/json", "Authorization" to "${User.token}"))
            .body(bodyJson)
            .response() { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                        println(ex)
                    }
                    is Result.Success -> {
                        startActivity(Intent(this, Login::class.java))
                    }
                }
            }
    }

    fun OnClick(v: View) {
        when (v.id) {
            R.id.btnNext -> {
                index++
                if (index == 0) {
                    setUnClick(v)
                    next?.setText("yes")
                } else if (index <= 19) {
                    setUnClick(v)
                    next?.setText("next")
                    getQuestions()
                } else if (index == 20) {
                    setUnClick(v)
                    next?.setText("complete")
                    getQuestions()
                } else {
                    startActivity(Intent(this, com.example.questionary.Result::class.java))
                }
            }

            R.id.btnCancel -> {
                if (index <= 1) {
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    index--
                    setUnClick(v)
                    getQuestions()
                }
            }
            R.id.btnEven -> {
                access[index-1] = false
                setUnClick(v)
                println(index * 2)
                setUnClick(v)
                //sendAnswer(index * 2)
            }
            R.id.btnOdd -> {
                access[index-1] = false
                setUnClick(v)
                println((index * 2) - 1)
                setUnClick(v)
                //sendAnswer((index * 2) - 1)
            }
        }
    }

}

data class Question(
    var id: String,
    var text: String,
    var answers: List<Answer>,
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