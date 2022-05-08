package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.ui.static.SettingsManager
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
        cbLanguage.items.add(rb?.getString("en-US"))
        cbLanguage.items.add(rb?.getString("es-ES"))
        cbLanguage.items.add(rb?.getString("gl-ES"))
        try {
            cbLanguage.value = rb?.getString(Locale.getDefault().toLanguageTag())
        }
        catch (_: MissingResourceException){
            println(Locale.getDefault().toLanguageTag())
            cbLanguage.value = rb?.getString("en-US")
        }
        tfFolderPath.text = SettingsManager.settings?.chartsLocation
    }

    @FXML
    fun chooseFolder(){
        val directoryChooser = DirectoryChooser()
        val selectedDirectory: File = directoryChooser.showDialog(tfFolderPath.scene.window)
        tfFolderPath.text = selectedDirectory.absolutePath
    }

    @FXML
    fun saveSettings(){
        SettingsManager.settings?.chartsLocation = tfFolderPath.text
        SettingsManager.save()
    }

}