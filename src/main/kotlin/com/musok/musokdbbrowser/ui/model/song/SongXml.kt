package com.musok.musokdbbrowser.ui.model.song

import com.musok.musokdbbrowser.api.mappings.song.Song
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "chart")
class SongXml {
    @field:Element
    var title: String? = null

    @field:Element
    var artist: String? = null

    @field:Element
    var easy: Difficulty? = null
    @field:Element
    var normal: Difficulty? = null
    @field:Element
    var hard: Difficulty? = null

    @field:Element
    var music: Music? = null
    @field:Element
    var jacket: Jacket? = null

    fun toSong(path: String): LocalSong {
        return LocalSong(
            songName = this.title ?: "null",
            author = this.artist ?: "null",
            music = music?.file ?: "null",
            easyDiff = listOf(
                this.easy?.difficulty ?: "null",
                this.easy?.file ?: "null",
                this.easy?.charter ?: "null"
            ),
            normalDiff = listOf(
                this.normal?.difficulty ?: "null",
                this.normal?.file ?: "null",
                this.normal?.charter ?: "null"
            ),
            hardDiff = listOf(
                this.hard?.difficulty ?: "null",
                this.hard?.file ?: "null",
                this.hard?.charter ?: "null"
            ),
            songArt = listOf(jacket?.file ?: "null", jacket?.artist ?: "null"),
            uploader = -1,
            id = -1,
            artURL = (path + jacket?.file),
            audioURL = (path + music?.file),
            easyChartURL = (path + this.easy?.file),
            normalChartURL = (path + this.normal?.file),
            hardChartURL = (path + this.hard?.file),
        )
    }
}