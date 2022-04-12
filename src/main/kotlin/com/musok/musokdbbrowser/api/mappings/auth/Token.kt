package com.musok.musokdbbrowser.api.mappings.auth

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("access_token")
    val content: String
) {
    fun getHeaderValue(): String{
        return "Bearer $content"
    }
}
