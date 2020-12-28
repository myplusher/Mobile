package com.example.questionary.data.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

class User {
    companion object {
        var token = ""
        var username = ""
        var sex = ""
        var dateOfBirth = ""
    }

    class Deserializer : ResponseDeserializable<UserForRegistration> {
        override fun deserialize(content: String): UserForRegistration =
            Gson().fromJson(content, UserForRegistration::class.java)
    }
}