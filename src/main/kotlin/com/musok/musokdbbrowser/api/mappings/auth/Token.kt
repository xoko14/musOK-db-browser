package com.musok.musokdbbrowser.api.mappings.auth

import com.google.gson.annotations.SerializedName

class Token(
    @SerializedName("access_token")
    val content: String
) {
    fun getHeaderValue(): String{
        return "Bearer $content"
    }
}
