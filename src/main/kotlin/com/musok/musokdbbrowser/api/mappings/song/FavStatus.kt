package com.musok.musokdbbrowser.api.mappings.song

import com.google.gson.annotations.SerializedName

enum class FavStatus {
    @SerializedName("faved") FAVED,
    @SerializedName("unfaved") UNFAVED,
    @SerializedName("error") ERROR
}