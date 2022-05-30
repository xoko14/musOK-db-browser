package com.musok.musokdbbrowser.ui.alerts

import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.stage.Stage
import java.util.*

class YesNoAlert(
        alertName: String,
        alertDesc: String
): Stage() {
    @FXML lateinit var lbAlertTitle: Label
    @FXML lateinit var lbAlertDesc: Label
    var result: Boolean? = null

    init {
        val loader = FXMLLoader()
        loader.location = this::class.java.getResource("/views/alerts/yesno-alert.fxml")
        loader.resources = ResourceBundle.getBundle("bundles/strings", Locale.getDefault())
        loader.setController(this)
        val root: Parent = loader.load()
        this.scene = Scene(root)
        this.scene.stylesheets.removeAll()
        this.scene.stylesheets.add("/theme/dark-theme.css")
        this.title = alertName
        lbAlertTitle.text = alertName
        lbAlertDesc.text = alertDesc
    }

    @FXML fun onOkAction() {
        result=true
        this.close()
    }

    @FXML fun onCancelAction() {
        result=false
        this.close()
    }
}