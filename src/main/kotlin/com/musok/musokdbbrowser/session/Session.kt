package com.musok.musokdbbrowser.session

import com.musok.musokdbbrowser.api.mappings.auth.Token
import com.musok.musokdbbrowser.api.mappings.user.User

object Session{
    var token: Token? = null
    var user: User? = null
}