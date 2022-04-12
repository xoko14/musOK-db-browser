package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.mappings.user.User
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class UserView: Initializable {
    @FXML private lateinit var usernamePlaceholder: Label
    private lateinit var user: User
    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        user = Server.getCurrentUser()
        usernamePlaceholder.text = user.username
    }
}