package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.ui.components.SongCard
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.FlowPane
import java.net.URL
import java.util.*

class BrowseController: Initializable {
    @FXML lateinit var rootPane: FlowPane

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        val songs = Server.getAllSongs()
        for (song in songs){
            val songCard = SongCard(song)

            songCard.setOnMouseClicked {
                println("Song selected: $song")
            }

            rootPane.children.add(songCard)
        }
    }

}