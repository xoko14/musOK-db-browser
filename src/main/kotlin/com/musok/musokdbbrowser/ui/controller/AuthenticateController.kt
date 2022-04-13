package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage


class AuthenticateController {
    @FXML private lateinit var tfURL: TextField
    @FXML private lateinit var tfURLsu: TextField
    @FXML private lateinit var tfUser: TextField
    @FXML private lateinit var tfUserSu: TextField
    @FXML private lateinit var pfPassword: PasswordField
    @FXML private lateinit var pfPassSu: PasswordField
    @FXML private lateinit var pfPassConfSu: PasswordField

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

        val stage = tfURL.scene.window as Stage
        stage.close()
    }

    @FXML
    fun createAccount(){
        println("create account")
    }


}