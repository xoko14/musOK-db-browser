package com.musok.musokdbbrowser.api

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.session.Session
import org.junit.jupiter.api.Test

class APITests {
    @Test
    fun loginAndGetCurrentUser(){
        Session.token = Server.logIn("creator", "test1234")
        val user = Server.getCurrentUser()
        println(user.username)
        assert(user.username == "creator")
    }
}