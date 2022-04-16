package com.musok.musokdbbrowser.ui.components

import com.musok.musokdbbrowser.api.mappings.song.Song
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.SnapshotParameters
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.image.WritableImage
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import java.io.IOException
import java.util.*


class SongInfo(val song: Song): VBox(){
    @FXML lateinit var imgArt: ImageView
    @FXML lateinit var lbSongName: Label
    @FXML lateinit var lbSongAuthor: Label
    @FXML lateinit var lbArtworkAuthor: Label
    @FXML lateinit var lbDiffEasy: Label
    @FXML lateinit var lbEasyCharter: Label
    @FXML lateinit var lbDiffNormal: Label
    @FXML lateinit var lbNormalCharter: Label
    @FXML lateinit var lbDiffHard: Label
    @FXML lateinit var lbHardCharter: Label

    init{
        val loader = FXMLLoader(javaClass.getResource("/views/components/song-info.fxml"))
        loader.resources = ResourceBundle.getBundle("bundles/strings", Locale.getDefault())
        loader.setRoot(this)
        loader.setController(this)
        try {
            loader.load()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        imgArt.image = Image(song.artURL)
        val imgClip = Rectangle(
            imgArt.fitWidth, imgArt.fitHeight
        )
        imgClip.arcWidth = 3.0
        imgClip.arcHeight = 3.0
        imgArt.clip = imgClip
        val parameters = SnapshotParameters()
        parameters.fill = Color.TRANSPARENT
        val image: WritableImage = imgArt.snapshot(parameters, null)
        imgArt.clip = null
        imgArt.image = image

        lbSongName.text = song.songName
        lbSongAuthor.text = song.author
        lbArtworkAuthor.text = song.songArt[1]
        lbDiffEasy.text = song.easyDiff[0]
        lbDiffNormal.text = song.normalDiff[0]
        lbDiffHard.text = song.hardDiff[0]
        lbEasyCharter.text = song.easyDiff[2]
        lbNormalCharter.text = song.normalDiff[2]
        lbHardCharter.text = song.hardDiff[2]
    }
}