package com.musok.musokdbbrowser.ui.components

import com.musok.musokdbbrowser.api.mappings.song.Song
import com.musok.musokdbbrowser.ui.model.song.Difficulty
import com.musok.musokdbbrowser.ui.model.song.Jacket
import com.musok.musokdbbrowser.ui.model.song.Music
import com.musok.musokdbbrowser.ui.model.song.SongXml
import com.musok.musokdbbrowser.ui.static.SettingsManager
import com.musok.musokdbbrowser.ui.util.SVGPaths
import javafx.application.Platform
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
import kong.unirest.Unirest
import org.controlsfx.control.Notifications
import org.simpleframework.xml.core.Persister
import java.io.File
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
    @FXML lateinit var svgDownload: SVGPath

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
                        try {
                            clip.open(AudioSystem.getAudioInputStream(URL(song.audioURL)))
                        }
                        catch (e: Exception){
                            e.printStackTrace()
                            svgPlayback.content = SVGPaths.ERROR
                            playbackStatus = PlaybackStatus.STOPPED
                            return@thread
                        }

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

        btnDownload.setOnAction {
            val thisaction = btnDownload.onAction
            svgDownload.content = SVGPaths.LOADING
            btnDownload.setOnAction {  }
            thread(start = true) {
                val folderName = "${song.id}_${song.songName.replace(' ', '-')}"
                val chartsLocation = SettingsManager.settings?.chartsLocation?: "./charts"
                val chartFolder = File(chartsLocation, folderName)
                if (!chartFolder.exists()) chartFolder.mkdir()

                fun downloadChartFile(source:String, target:String){
                    Unirest.get(source)
                        .downloadMonitor { b, fileName, bytesWritten, totalBytes ->
                            println("Downloading $fileName -> ${totalBytes - bytesWritten}")
                        }
                        .asFile(File(chartFolder, target).absolutePath)
                }

                downloadChartFile(song.audioURL, "song.wav")
                downloadChartFile(song.artURL, "jacket.png")
                downloadChartFile(song.easyChartURL, "song_e.chart")
                downloadChartFile(song.normalChartURL, "song_n.chart")
                downloadChartFile(song.hardChartURL, "song_h.chart")

                var songinfo = SongXml()
                songinfo.title = song.songName
                songinfo.artist = song.author
                songinfo.easy = run {
                    val diff = Difficulty()
                    diff.file = "song_e.chart"
                    diff.charter = song.easyDiff[2]
                    diff.difficulty = song.easyDiff[0]
                    diff
                }
                songinfo.normal = run {
                    val diff = Difficulty()
                    diff.file = "song_n.chart"
                    diff.charter = song.normalDiff[2]
                    diff.difficulty = song.normalDiff[0]
                    diff
                }
                songinfo.hard = run {
                    val diff = Difficulty()
                    diff.file = "song_h.chart"
                    diff.charter = song.hardDiff[2]
                    diff.difficulty = song.hardDiff[0]
                    diff
                }
                songinfo.music = run {
                    val music = Music()
                    music.file="song.wav"
                    music
                }
                songinfo.jacket = run {
                    val jacket = Jacket()
                    jacket.file = "jacket.png"
                    jacket.artist = song.songArt[1]
                    jacket
                }
                Persister().write(songinfo, File(chartFolder.absolutePath, "song.xml"))

                Platform.runLater {
                    btnDownload.onAction = thisaction
                    svgDownload.content = SVGPaths.DOWNLOAD
                    Notifications.create()
                        .title("Download completed")
                        .text("${song.songName} downloaded!")
                        .showInformation()
                }
            }
        }
    }
}