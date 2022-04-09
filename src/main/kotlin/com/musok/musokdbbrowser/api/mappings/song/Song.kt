package com.musok.musokdbbrowser.api.mappings.song

import com.google.gson.annotations.SerializedName
import com.musok.musokdbbrowser.api.connection.Server

data class Song(
    @SerializedName("song_name") val songName: String,
    val author: String,
    val music: String,
    @SerializedName("easy_diff") val easyDiff: List<String>,
    @SerializedName("normal_diff") val normalDiff: List<String>,
    @SerializedName("hard_diff") val hardDiff: List<String>,
    @SerializedName("song_art") val songArt: List<String>,
    val uploader: Long,
    val id: Long
){
    val artURL: String get() = "${Server.url}/songs/$id/jacket"
    val audioURL: String get() = "${Server.url}/songs/$id/audio"
    val easyChartURL: String get() = "${Server.url}/songs/$id/easy"
    val normalChartURL: String get() = "${Server.url}/songs/$id/normal"
    val hardChartURL: String get() = "${Server.url}/songs/$id/hard"
}
