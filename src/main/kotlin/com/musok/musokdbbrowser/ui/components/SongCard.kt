package com.musok.musokdbbrowser.ui.components

import javafx.fxml.FXMLLoader
import javafx.scene.layout.VBox
import java.io.IOException





class SongCard: VBox() {
    init {
        val loader = FXMLLoader(javaClass.getResource("/components/song-card.fxml"))
        loader.setRoot(this)
        loader.setController(this)
        try {
            loader.load()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}