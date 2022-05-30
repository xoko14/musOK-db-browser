package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.ui.components.LocalSongCard
import com.musok.musokdbbrowser.ui.components.SongCard
import com.musok.musokdbbrowser.ui.model.song.LocalSong
import com.musok.musokdbbrowser.ui.model.song.SongXml
import com.musok.musokdbbrowser.ui.static.SettingsManager
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.layout.FlowPane
import javafx.scene.layout.VBox
import org.controlsfx.control.Notifications
import org.simpleframework.xml.core.Persister
import java.io.File
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import kotlin.collections.ArrayList


class LocalBrowseController: Initializable {
    @FXML lateinit var rootPane: FlowPane
    private var songs: MutableList<LocalSong> = ArrayList()

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        populateView()
    }

    @FXML
    fun update(){
        populateView()
    }

    private fun populateView(){
        rootPane.children.clear()
        songs.clear()
        try {
            Files.walk(Paths.get(SettingsManager.settings?.chartsLocation ?: "")).use { walkStream ->
                walkStream.filter { p: Path ->
                    p.toFile().isFile
                }.forEach { f: Path ->
                    if (f.toString().endsWith("song.xml")) {
                        try {
                            val song = Persister().read(SongXml::class.java, f.toFile()).toSong(f.toString().replace("song.xml", ""))
                            if(File(f.parent.toString(), "upload_info.json").exists()) song.fromServer = true
                            songs.add(song)
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

            songCard.setOnDelete{
                val index = File(song.artURL).parentFile
                if(index.exists()){
                    val entries: Array<String> = index.list() as Array<String>
                    for (s in entries) {
                        val currentFile= File(index.path, s)
                        currentFile.delete()
                    }
                    index.delete()
                }
                rootPane.children.remove(songCard)
            }

            rootPane.children.add(songCard)
        }
    }

}