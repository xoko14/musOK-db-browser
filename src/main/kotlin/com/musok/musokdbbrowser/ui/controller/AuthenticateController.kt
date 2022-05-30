package com.musok.musokdbbrowser.ui.controller

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.exceptions.IncorrectLoginException
import com.musok.musokdbbrowser.api.exceptions.UnknownException
import com.musok.musokdbbrowser.api.exceptions.UserAlreadyRegisteredException
import com.musok.musokdbbrowser.ui.alerts.InfoAlert
import com.musok.musokdbbrowser.ui.alerts.LegalAlert
import com.musok.musokdbbrowser.ui.static.SettingsManager
import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.mindrot.jbcrypt.BCrypt
import java.util.ResourceBundle


class AuthenticateController {
    @FXML private lateinit var tfURL: TextField
    @FXML private lateinit var tfURLsu: TextField
    @FXML private lateinit var tfUser: TextField
    @FXML private lateinit var tfUserSu: TextField
    @FXML private lateinit var pfPassword: PasswordField
    @FXML private lateinit var pfPassSu: PasswordField
    @FXML private lateinit var pfPassConfSu: PasswordField

    private val res: ResourceBundle  = SettingsManager.getResources()

    @FXML
    fun onConnectAction(){
        Server.url = tfURL.text.trim()
        try {
            Server.logIn(tfUser.text, pfPassword.text)
        }
        catch (e: IncorrectLoginException){
            InfoAlert(
                alertName = "Incorrect login",
                alertDesc = "Username / password does not exist."
            ).showAndWait()
            return
        }
        catch (e: UnknownException){
            println("something went wrong")
            return
        }

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
        if(tfURLsu.text.isNotBlank() && tfUserSu.text.isNotBlank() && pfPassSu.text.isNotBlank() && pfPassConfSu.text.isNotBlank()){
            if(pfPassSu.text.trim() == pfPassConfSu.text.trim()) {
                Server.url = tfURLsu.text.trim()
                val legalAlert = LegalAlert()
                val accepted = legalAlert.ask()
                if (accepted == true) {
                    try {
                        val user = Server.createUser(
                            username = tfUserSu.text.trim(),
                            hashedPwd = BCrypt.hashpw(pfPassSu.text.trim(), BCrypt.gensalt(10))
                        )
                    } catch (e: UserAlreadyRegisteredException) {
                        InfoAlert(
                            alertName = "User already registered",
                            alertDesc = "User with name \n${tfUserSu.text}\n already exists."
                        ).showAndWait()
                        return
                    } catch (e: UnknownException) {
                        println("something went wrong")
                        return
                    }

                    InfoAlert(
                        alertName = "Sign up successful",
                        alertDesc = "User \n${tfUserSu.text}\n created successfully."
                    ).showAndWait()
                }

            } else {
                InfoAlert(
                    alertName = "Passwords don't match",
                    alertDesc = "Please check that the passwords match."
                ).showAndWait()
            }
        } else {
            InfoAlert(
                alertName = "Info cannot be empty",
                alertDesc = "Please fill all the textboxes."
            ).showAndWait()
        }
    }


}