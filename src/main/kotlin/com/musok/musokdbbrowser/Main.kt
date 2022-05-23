package com.musok.musokdbbrowser

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.ui.static.SettingsManager
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.util.*

class MainApp: Application(){
    override fun start(stage: Stage) {
        SettingsManager.load()

        if(!Server.isLoggedIn()){
            val loader = FXMLLoader()
            loader.location = this::class.java.getResource("/views/authenticate-view.fxml")
            loader.resources = ResourceBundle.getBundle("bundles/strings", Locale.getDefault())
            val root: Parent = loader.load()
            val stageLogIn = Stage()
            stageLogIn.icons.add(Image("/icon/icon.png"))
            stageLogIn.scene = Scene(root)
            stageLogIn.scene.stylesheets.removeAll()
            stageLogIn.scene.stylesheets.add("/theme/dark-theme.css")
            stageLogIn.title = "Login"
            stageLogIn.isResizable = false
            stageLogIn.showAndWait()
        }

        val fxmlLoader = FXMLLoader(this::class.java.getResource("/views/main-view.fxml"))
        fxmlLoader.resources = ResourceBundle.getBundle("bundles/strings", Locale.getDefault())
        val scene = Scene(fxmlLoader.load())
        scene.stylesheets.add("/theme/dark-theme.css")
        stage.icons.add(Image("/icon/icon.png"))
        stage.title = "MusOK Browser"
        stage.scene = scene
        stage.show()
    }

}

fun main() {
    Application.launch(MainApp::class.java)
}