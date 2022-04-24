package com.musok.musokdbbrowser.ui.controller

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ChoiceBox
import javafx.scene.control.TextField
import java.net.URL
import java.util.*

class SettingsController: Initializable {
    @FXML lateinit var tfFolderPath: TextField
    @FXML lateinit var cbLanguage: ChoiceBox<Any>

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        Locale.getDefault()
    }


}