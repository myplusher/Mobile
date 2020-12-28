package com.example.questionary.data.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class UserForRegistration (
    var username: String,
    var password: String,
    var sex: Boolean,
    var dateOfBirth: String,
    //var birthday: String
) {
    fun UserForRegistration(username: String, password: String, dateOfBirth: String, sex: Boolean) {
        this.username = username
        this.password = password
        this.sex = sex
        this.dateOfBirth = dateOfBirth
        //this.birthday = birthday
    }

    class Deserializer : ResponseDeserializable<UserForRegistration> {
        override fun deserialize(content: String): UserForRegistration =
            Gson().fromJson(content, UserForRegistration::class.java)
    }
}