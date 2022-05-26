package com.musok.musokdbbrowser.api.mappings.song

import com.google.gson.annotations.SerializedName
import com.musok.musokdbbrowser.api.connection.Server

open class Song(
    @SerializedName("song_name") open val songName: String,
    open val author: String,
    open val music: String,
    @SerializedName("easy_diff") open val easyDiff: List<String>,
    @SerializedName("normal_diff") open val normalDiff: List<String>,
    @SerializedName("hard_diff") open val hardDiff: List<String>,
    @SerializedName("song_art") open val songArt: List<String>,
    open val uploader: Long,
    open val id: Long,
    @SerializedName("is_faved") open val isFaved: Boolean?
){
    open val artURL: String get() = "${Server.url}/songs/$id/jacket"
    open val audioURL: String get() = "${Server.url}/songs/$id/audio"
    open val easyChartURL: String get() = "${Server.url}/songs/$id/easy"
    open val normalChartURL: String get() = "${Server.url}/songs/$id/normal"
    open val hardChartURL: String get() = "${Server.url}/songs/$id/hard"
}
