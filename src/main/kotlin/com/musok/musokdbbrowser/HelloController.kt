package com.musok.musokdbbrowser

import com.musok.musokdbbrowser.api.connection.Server
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField


class HelloController {
    @FXML
    private lateinit var tfURL: TextField
    @FXML
    private lateinit var tfUser: TextField
    @FXML
    private lateinit var pfPassword: PasswordField

    @FXML
    fun onConnectAction(){
        Server.url = tfURL.text.trim()
        Server.logIn(tfUser.text, pfPassword.text)

        val user = Server.getCurrentUser()
        println(user.username)
        val alert = Alert(AlertType.INFORMATION)
        val dialogPane = alert.dialogPane
        dialogPane.stylesheets.add(
            this::class.java.getResource("/theme/dark-theme.css")?.toExternalForm()
        )
        alert.title = "Log in correct"
        alert.headerText = "Welcome ${user.username}"
        alert.contentText = "User id: ${user.id}"
        alert.showAndWait()
    }


}