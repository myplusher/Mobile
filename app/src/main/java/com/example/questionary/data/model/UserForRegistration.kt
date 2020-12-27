package com.example.questionary.data.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class UserForRegistration (
    var login: String,
    var password: String,
    var sex: Boolean,
    var date: String,
    //var birthday: String
) {
    fun UserForRegistration(login: String, password: String, date: String, sex: Boolean) {
        this.login = login
        this.password = password
        this.sex = sex
        this.date = date
        //this.birthday = birthday
    }

    class Deserializer : ResponseDeserializable<UserForRegistration> {
        override fun deserialize(content: String): UserForRegistration =
            Gson().fromJson(content, UserForRegistration::class.java)
    }
}