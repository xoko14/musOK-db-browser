package com.musok.musokdbbrowser.ui.controller

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import javafx.stage.DirectoryChooser
import java.io.File
import java.net.URL
import java.util.*


class SettingsController: Initializable {
    @FXML lateinit var tfFolderPath: TextField
    @FXML lateinit var cbLanguage: ChoiceBox<Any>

    override fun initialize(p0: URL?, rb: ResourceBundle?) {
        Locale.getDefault()
        cbLanguage.items.add(rb?.getString("en_US"))
        cbLanguage.items.add(rb?.getString("es_ES"))
        cbLanguage.items.add(rb?.getString("gl_ES"))
        try {
            cbLanguage.value = 1
        }
        catch (_: MissingResourceException){
            println(Locale.getDefault())
            cbLanguage.value = rb?.getString("en_US")
        }
    }

    @FXML
    fun chooseFolder(){
        val directoryChooser = DirectoryChooser()
        val selectedDirectory: File = directoryChooser.showDialog(tfFolderPath.scene.window)
        tfFolderPath.text = selectedDirectory.absolutePath
    }

    @FXML
    fun saveSettings(){

    }

}