package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.mappings.user.User
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.fxml.Initializable
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.stage.Stage
import java.net.URL
import java.util.*

class UserController: Initializable {
    @FXML private lateinit var taUserInfo: TextArea
    private lateinit var user: User
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        user = Server.getCurrentUser()

        println(user)

        taUserInfo.text="$user\n${Server.getCurrentUserUploaded()}"
    }
}