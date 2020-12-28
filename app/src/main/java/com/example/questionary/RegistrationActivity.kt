package com.example.questionary

import android.app.DatePickerDialog
import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView
import com.example.questionary.data.model.UserForRegistration
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result
import java.text.SimpleDateFormat
import java.util.*

class RegistrationActivity : AppCompatActivity() {

    var sigIn: TextView? = null
    var login: TextView? = null
    var password: TextView? = null
    var date: TextView? = null
    var man: RadioButton? = null
    var woman: RadioButton? = null
    lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        // sigIn?.setOnClickListener {
        //     startActivity(Intent(this, Logging::class.java))
        // }
        login = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextTextPassword)
        man = findViewById(R.id.maleBtn)
        woman = findViewById(R.id.womanBtn)
        date = findViewById(R.id.editTextDate)
        date?.text = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())

        var cal = Calendar.getInstance()

        dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "yyyy-MM-dd" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                this.date?.text = sdf.format(cal.time)
            }
    }
        fun registration(view: View) {
            val user: UserForRegistration? = UserForRegistration(
                login?.text.toString(),
                password?.text.toString(),
                man?.isChecked!!,
                date?.text.toString()
            )

            val bodyJson = """
              { "username" : "${login?.text}",
                "password" : "${password?.text}",
                "sex" : "${man?.isChecked!!}",
                "dateOfBirth" : "${date?.text}"
              }
            """

            Fuel.post("https://jungtest.herokuapp.com/user/reg")
                .header(mapOf("Content-Type" to "application/json"))
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


    fun onClick(v: View) {
        when (v.id) {
            R.id.buttonLog -> {
                startActivity(Intent(this, Login::class.java))
            }
            R.id.btnRegister -> {
                registration(v)
            }
        }
    }

}