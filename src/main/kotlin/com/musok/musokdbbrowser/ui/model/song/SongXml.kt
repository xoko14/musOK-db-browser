package com.musok.musokdbbrowser.ui.model.song

import com.musok.musokdbbrowser.api.mappings.song.Song
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "chart", strict = false)
class SongXml {
    @field:Element(name = "title")
    var title: String? = null

    @field:Element(name = "artist")
    var artist: String? = null

    @field:Element(name = "easy", type = Difficulty::class)
    var easyDiff: Difficulty? = null
    @field:Element(name = "normal", type = Difficulty::class)
    var normalDiff: Difficulty? = null
    @field:Element(name = "hard", type = Difficulty::class)
    var hardDiff: Difficulty? = null

    @field:Element(name = "music")
    var music: Music? = null
    @field:Element(name = "jacket")
    var jacket: Jacket? = null

    abstract class File {
        @field:Attribute(name = "file")
        var file: String? = null
    }

    class Difficulty : File() {
        @field:Attribute(name = "charter")
        var charter: String? = null
        @field:Attribute(name = "difficulty")
        var difficulty: String? = null
    }

    class Music : File()

    class Jacket : File() {
        @field:Attribute(name = "artist")
        var artist: String? = null
    }

    fun toSong(): Song {
        return LocalSong(
            songName = this.title ?: "null",
            author = this.artist ?: "null",
            music = music?.file ?: "null",
            easyDiff = listOf(
                this.easyDiff?.difficulty ?: "null",
                this.easyDiff?.file ?: "null",
                this.easyDiff?.charter ?: "null"
            ),
            normalDiff = listOf(
                this.normalDiff?.difficulty ?: "null",
                this.normalDiff?.file ?: "null",
                this.normalDiff?.charter ?: "null"
            ),
            hardDiff = listOf(
                this.hardDiff?.difficulty ?: "null",
                this.hardDiff?.file ?: "null",
                this.hardDiff?.charter ?: "null"
            ),
            songArt = listOf(jacket?.file ?: "null", jacket?.artist ?: "null"),
            uploader = -1,
            id = -1,
            artURL = jacket?.file ?: "null",
            audioURL = music?.file ?: "null",
            easyChartURL = this.easyDiff?.file ?: "null",
            normalChartURL = this.normalDiff?.file ?: "null",
            hardChartURL = this.hardDiff?.file ?: "null",
        )
    }
}