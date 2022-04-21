package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.mappings.song.Song
import com.musok.musokdbbrowser.ui.components.SongCard
import com.musok.musokdbbrowser.ui.model.song.SongXml
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.FlowPane
import org.simpleframework.xml.core.Persister
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer
import java.util.function.Predicate
import kotlin.collections.ArrayList


class LocalBrowseController: Initializable {
    @FXML lateinit var rootPane: FlowPane
    private var songs: MutableList<Song> = ArrayList()

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        try {
            Files.walk(Paths.get("A:\\Games\\MusOK\\charts")).use { walkStream ->
                walkStream.filter { p: Path ->
                    p.toFile().isFile
                }.forEach { f: Path ->
                    if (f.toString().endsWith("song.xml")) {
                        try {
                            songs.add(Persister().read(SongXml::class.java, f.toFile()).toSong(f.toString().replace("song.xml", "")))
                        }
                        catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        }
        catch (e: Exception){
            println("local chart folder not found")
            e.printStackTrace()
            return
        }

        for (song in songs){
            val songCard = SongCard(song)

            songCard.setOnDownload{
                println("Downloading ${songCard.song.songName}...")
            }

            rootPane.children.add(songCard)
        }
    }

}