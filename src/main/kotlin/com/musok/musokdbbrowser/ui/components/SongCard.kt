package com.musok.musokdbbrowser.ui.components

import com.musok.musokdbbrowser.api.mappings.song.Song
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox
import java.io.IOException


class SongCard(val song: Song): VBox() {
    @FXML lateinit var img: ImageView
    @FXML lateinit var lbName: Label
    @FXML lateinit var lbAuthor: Label

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
        lbName.text = song.songName
        lbAuthor.text = song.author
    }
}