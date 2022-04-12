package com.musok.musokdbbrowser

import com.musok.musokdbbrowser.api.connection.Server
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage


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

        val loader = FXMLLoader()
        loader.location = this::class.java.getResource("/views/main-view.fxml")
        val root: Parent = loader.load()
        val stage = Stage()
        stage.scene = Scene(root)
        stage.title = "MusOK Browser"
        stage.show()
    }


}