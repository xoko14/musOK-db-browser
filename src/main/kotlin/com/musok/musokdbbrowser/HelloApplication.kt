package com.musok.musokdbbrowser

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage

class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val fxmlLoader = FXMLLoader(this::class.java.getResource("/views/hello-view.fxml"))
        val scene = Scene(fxmlLoader.load())
        stage.title = "Log In"
        stage.scene = scene
        stage.show()
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}