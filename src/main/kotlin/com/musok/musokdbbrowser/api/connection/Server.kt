package com.musok.musokdbbrowser.api.connection

import com.google.gson.Gson
import com.musok.musokdbbrowser.api.mappings.auth.Token
import com.musok.musokdbbrowser.api.mappings.user.User
import com.musok.musokdbbrowser.session.Session
import kong.unirest.Unirest

object Server {
    var url: String? = null

    fun logIn(user: String, password: String): Token {
        val response = Unirest.post("http://unnamed-chart-server.com:8000/token")
            .header("accept", "application/json")
            .field("username", user)
            .field("password", password)
            .asJson()
            .body.toString()

        return Gson().fromJson(response, Token::class.java)
    }

    fun getCurrentUser(): User {
        val response = Unirest.get("http://unnamed-chart-server.com:8000/users/me")
            .header("Authorization", Session.token?.getHeaderValue())
            .header("accept", "application/json")
            .asJson()
            .body.toString()
        return Gson().fromJson(response, User::class.java)
    }
}