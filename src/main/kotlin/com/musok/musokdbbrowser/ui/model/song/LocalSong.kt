package com.musok.musokdbbrowser.ui.model.song

import com.google.gson.annotations.SerializedName
import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.mappings.song.Song

class LocalSong(
    override val songName: String,
    override val author: String,
    override val music: String,
    override val easyDiff: List<String>,
    override val normalDiff: List<String>,
    override val hardDiff: List<String>,
    override val songArt: List<String>,
    override val uploader: Long,
    override val id: Long,
    override val artURL: String,
    override val audioURL: String,
    override val easyChartURL: String,
    override val normalChartURL: String,
    override val hardChartURL: String,
    override val isFaved: Boolean?
): Song(songName, author, music, easyDiff, normalDiff, hardDiff, songArt, uploader, id, isFaved){
    var fromServer: Boolean = false
}