package com.musok.musokdbbrowser

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class MainApp: Application(){
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(this::class.java.getResource("/views/main-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        scene.stylesheets.add("/theme/dark-theme.css")
        stage.title = "MusOK Browser"
        stage.scene = scene
        stage.show()
    }

}

fun main() {
    Application.launch(MainApp::class.java)
}