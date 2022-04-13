package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.mappings.user.User
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import java.net.URL
import java.util.*

class UserView: Initializable {
    @FXML private lateinit var usernamePlaceholder: Label
    private lateinit var user: User
    override fun initialize(p0: URL?, p1: ResourceBundle?) {

        if(!Server.isLoggedIn()){
            val loader = FXMLLoader()
            loader.location = this::class.java.getResource("/views/hello-view.fxml")
            val root: Parent = loader.load()
            val stage = Stage()
            stage.scene = Scene(root)
            stage.scene.stylesheets.add("/theme/dark-theme.css")
            stage.title = "Login"
            stage.showAndWait()
        }


        user = Server.getCurrentUser()

        println(user)

        usernamePlaceholder.text = user.username
    }
}