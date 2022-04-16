package com.musok.musokdbbrowser.ui.components

import com.musok.musokdbbrowser.api.mappings.song.Song
import com.musok.musokdbbrowser.ui.util.SVGPaths
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.SnapshotParameters
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.SVGPath
import javafx.stage.Stage
import java.io.IOException
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.concurrent.thread


class SongCard(val song: Song): VBox() {
    @FXML lateinit var img: ImageView
    @FXML lateinit var lbName: Label
    @FXML lateinit var lbAuthor: Label
    @FXML lateinit var btnInfo: Button
    @FXML lateinit var btnDownload: Button
    @FXML lateinit var btnPlayback: Button
    @FXML lateinit var svgPlayback: SVGPath

    private var playbackStatus: PlaybackStatus = PlaybackStatus.STOPPED
    private lateinit var clip: Clip

    init {
        val loader = FXMLLoader(javaClass.getResource("/views/components/song-card.fxml"))
        loader.setRoot(this)
        loader.setController(this)
        try {
            loader.load()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        img.image = Image(song.artURL)
        val imgClip = Rectangle(
            img.fitWidth, img.fitHeight
        )
        imgClip.arcWidth = 6.0
        imgClip.arcHeight = 6.0
        img.clip = imgClip
        val parameters = SnapshotParameters()
        parameters.fill = Color.TRANSPARENT
        val image: WritableImage = img.snapshot(parameters, null)
        img.clip = null
        img.image = image


        lbName.text = song.songName
        lbAuthor.text = song.author

        btnPlayback.setOnAction {
            when(playbackStatus){
                PlaybackStatus.STOPPED -> {
                    playbackStatus = PlaybackStatus.LOADING
                    svgPlayback.content = SVGPaths.LOADING
                    thread(start = true){
                        clip = AudioSystem.getClip()
                        clip.open(AudioSystem.getAudioInputStream(URL(song.audioURL)))
                        clip.start()
                        playbackStatus = PlaybackStatus.PLAYING
                        svgPlayback.content = SVGPaths.PAUSE
                    }
                }
                PlaybackStatus.PLAYING -> {
                    clip.stop()
                    playbackStatus = PlaybackStatus.STOPPED
                    svgPlayback.content = SVGPaths.PLAY
                }
                PlaybackStatus.LOADING -> {}
            }
        }

        btnInfo.setOnAction{
            val stage = Stage()
            stage.title = song.songName
            stage.scene = Scene(SongInfo(song))
            stage.scene.stylesheets.add("/theme/dark-theme.css")
            stage.isResizable = false
            stage.show()
        }
    }

    fun setOnDownload(action: EventHandler<ActionEvent>){
        btnDownload.onAction = action
    }
}