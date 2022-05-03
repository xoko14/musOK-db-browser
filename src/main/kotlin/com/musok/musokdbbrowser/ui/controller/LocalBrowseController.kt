package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.mappings.song.Song
import com.musok.musokdbbrowser.ui.components.LocalSongCard
import com.musok.musokdbbrowser.ui.model.song.SongXml
import com.musok.musokdbbrowser.ui.static.SettingsManager
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.FlowPane
import org.controlsfx.control.Notifications
import org.simpleframework.xml.core.Persister
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList


class LocalBrowseController: Initializable {
    @FXML lateinit var rootPane: FlowPane
    private var songs: MutableList<Song> = ArrayList()

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        try {
            Files.walk(Paths.get(SettingsManager.settings?.chartsLocation ?: "")).use { walkStream ->
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
            val songCard = LocalSongCard(song)

            songCard.setOnDownload{
                Notifications.create()
                    .title("test")
                    .text("test text")
                    .show()
                println("Downloading ${songCard.song.songName}...")
            }

            rootPane.children.add(songCard)
        }
    }

}