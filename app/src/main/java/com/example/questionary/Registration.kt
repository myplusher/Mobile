package com.example.questionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//import com.example.questionary.data.model.UserForRegistration
//import com.example.questionary.ui.login.Logging


class Registration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

//        createAccBtnBtn?.setOnClickListener {
//            if (login.text!!.isEmpty()) textInputLogin.setError(resources.getString(R.string.emptyField))
//            if (email.text!!.isEmpty()) textInputEmail.setError(resources.getString(R.string.emptyField))
//            if (password.text!!.isEmpty()) textInputPassword.setError(resources.getString(R.string.emptyField))
//            if (passwordCorfirm.text!!.isEmpty()) textInputPasswordCorfirm.setError(resources.getString(R.string.emptyField))
//
//
//            if (!password.text!!.isEmpty() &&
//                !login.text!!.isEmpty() &&
//                !email.text!!.isEmpty() &&
//                !passwordCorfirm.text!!.isEmpty()) {
//                textInputPassword.setError(null)
//                textInputLogin.setError(null)
//                textInputPasswordCorfirm.setError(null)
//                textInputEmail.setError(null)
//                this.registration()
//            }
//
//        }

    }

//    fun registration() {
//        val user: UserForRegistration? = UserForRegistration(
//            login?.text.toString(),
//            password?.text.toString(),
//            // TODO ДАТАПИКЕР
//            email?.text.toString(),
//            if (maleBtn?.isChecked!!) "MAN" else "WOMAN",
//            birthday?.text.toString()
//        )
//        Fuel.post("http://10.0.2.2:8080/api/register")
//            .header(mapOf("Content-Type" to "application/json"))
//            .body(Gson().toJson(user))
//            .response() { request, response, result ->
//                when(result ) {
//                    is Result.Failure -> {
//                        val ex = result.getException()
//                        println(ex)
//                    }
//                    is Result.Success -> {
//                        startActivity(Intent(this, LoginActivity::class.java))
//                    }
//                }
//            }
//
//    }
}