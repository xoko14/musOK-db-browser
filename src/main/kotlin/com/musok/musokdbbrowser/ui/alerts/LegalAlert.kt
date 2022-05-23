package com.musok.musokdbbrowser.ui.alerts

import com.musok.musokdbbrowser.api.connection.Server
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.control.TextArea
import javafx.stage.Stage
import java.util.*

class LegalAlert(): Stage() {
    @FXML
    lateinit var textbox: TextArea
    private var accepted: Boolean? = null

    init {
        val loader = FXMLLoader()
        loader.location = this::class.java.getResource("/views/alerts/legal-alert.fxml")
        loader.resources = ResourceBundle.getBundle("bundles/strings", Locale.getDefault())
        loader.setController(this)
        val root: Parent = loader.load()
        this.scene = Scene(root)
        this.scene.stylesheets.removeAll()
        this.scene.stylesheets.add("/theme/dark-theme.css")

        textbox.text = Server.getLegal().text
    }

    fun ask(): Boolean?{
        this.showAndWait()
        return accepted
    }

    @FXML
    fun onOkAction() {
        this.close()
        accepted = true
    }

    @FXML
    fun onCancelAction(){
        this.close()
        accepted = false
    }

}