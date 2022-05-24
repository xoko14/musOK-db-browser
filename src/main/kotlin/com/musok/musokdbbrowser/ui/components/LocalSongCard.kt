package com.musok.musokdbbrowser.ui.components

import com.google.gson.Gson
import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.exceptions.IncorrectMediaTypeException
import com.musok.musokdbbrowser.ui.model.song.LocalSong
import com.musok.musokdbbrowser.ui.model.song.upload.UploadInfo
import com.musok.musokdbbrowser.ui.util.SVGPaths
import javafx.application.Platform
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
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import javafx.scene.shape.SVGPath
import javafx.stage.Stage
import org.controlsfx.control.Notifications
import java.io.File
import java.io.IOException
import java.net.URL
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import kotlin.concurrent.thread


class LocalSongCard(val song: LocalSong): VBox() {
    @FXML lateinit var img: ImageView
    @FXML lateinit var lbName: Label
    @FXML lateinit var lbAuthor: Label
    @FXML lateinit var btnInfo: Button
    @FXML lateinit var btnDelete: Button
    @FXML lateinit var btnPlayback: Button
    @FXML lateinit var svgPlayback: SVGPath
    @FXML lateinit var buttonContainer: HBox

    private var uploadStatus: UploadStatus = UploadStatus.NOT_STARTED
    private lateinit var clip: Clip

    init {
        val loader = FXMLLoader(javaClass.getResource("/views/components/localsong-card.fxml"))
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

        if(song.fromServer) buttonContainer.children.remove(btnPlayback)
        else btnPlayback.setOnAction {
            when(uploadStatus){
                UploadStatus.NOT_STARTED -> {
                    uploadStatus = UploadStatus.UPLOADING
                    svgPlayback.content = SVGPaths.LOADING
                    thread(start = true){
                        val audio = File(song.audioURL)
                        val art = File(song.artURL)
                        val easy = File(song.easyChartURL)
                        val normal = File(song.normalChartURL)
                        val hard = File(song.hardChartURL)
                        val songInfo = File(audio.parent, "song.xml")

                        try {
                            val song = Server.createSong(songInfo, art, audio, easy, normal, hard)
                            val uploadInfo = UploadInfo(
                                Server.url as String,
                                song.id,
                                song.uploader
                            )
                            File(audio.parent, "upload_info.json").writeText(Gson().toJson(uploadInfo))
                            Platform.runLater {
                                uploadStatus = UploadStatus.NOT_STARTED
                                svgPlayback.content = SVGPaths.UPLOAD
                                buttonContainer.children.remove(btnPlayback)
                                Notifications.create()
                                    .title("Upload complete")
                                    .text("${song.songName} uploaded!")
                                    .showInformation()
                            }
                        }
                        catch (e: Exception){
                            e.printStackTrace()
                            Platform.runLater {
                                uploadStatus = UploadStatus.NOT_STARTED
                                svgPlayback.content = SVGPaths.UPLOAD
                                Notifications.create()
                                    .title("Upload failed")
                                    .text("${song.songName} failed to upload")
                                    .showInformation()
                            }
                        }
                    }
                }
                else -> {}
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
        btnDelete.onAction = action
    }
}