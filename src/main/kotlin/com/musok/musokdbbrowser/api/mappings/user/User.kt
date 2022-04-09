package com.musok.musokdbbrowser.api.mappings.user

import com.google.gson.annotations.SerializedName
import com.musok.musokdbbrowser.api.mappings.song.Song

data class User(
    val username: String,
    val id: Long
)
