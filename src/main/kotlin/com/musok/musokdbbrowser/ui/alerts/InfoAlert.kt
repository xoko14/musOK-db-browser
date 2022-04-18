package com.musok.musokdbbrowser.ui.alerts

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import java.util.*

class InfoAlert(
        alertName: String,
        alertDesc: String
): Stage() {
    @FXML lateinit var lbAlertTitle: Label
    @FXML lateinit var lbAlertDesc: Label

    init {
        val loader = FXMLLoader()
        loader.location = this::class.java.getResource("/views/authenticate-view.fxml")
        loader.resources = ResourceBundle.getBundle("bundles/strings", Locale.getDefault())
        val root: Parent = loader.load()
        this.scene = Scene(root)
        this.scene.stylesheets.removeAll()
        this.scene.stylesheets.add("/theme/dark-theme.css")
        this.title = alertName
    }
}