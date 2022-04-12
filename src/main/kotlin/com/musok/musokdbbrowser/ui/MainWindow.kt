package com.musok.musokdbbrowser.ui

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainWindow: Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(this::class.java.getResource("/views/main-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Log In"
        stage.scene = scene
        stage.show()
    }
}