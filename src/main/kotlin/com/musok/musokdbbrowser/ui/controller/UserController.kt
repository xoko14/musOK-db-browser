package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.mappings.user.User
import com.musok.musokdbbrowser.ui.alerts.YesNoAlert
import com.musok.musokdbbrowser.ui.static.SettingsManager
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
import kotlin.system.exitProcess

class UserController: Initializable {
    private lateinit var user: User
    @FXML private lateinit var userNameText: Label

    private var res: ResourceBundle = SettingsManager.getResources()

    override fun initialize(p0: URL?, p1: ResourceBundle?) {
        user = Server.getCurrentUser()
        userNameText.text = user.username
    }

    @FXML
    fun deleteAccount() {
        val alert = YesNoAlert(res.getString("deleteConfirmation"), res.getString("deleteAndClose"))
        alert.showAndWait()
        if(alert.result == true){
            Server.deleteUser()
            exitProcess(0)
        }
    }
}