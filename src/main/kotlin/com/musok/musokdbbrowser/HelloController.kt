package com.musok.musokdbbrowser

import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import kong.unirest.Unirest
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.lang.IllegalArgumentException

class HelloController {
    @FXML
    private lateinit var welcomeText: Label
    @FXML
    private lateinit var imgView: ImageView

    @FXML
    private fun onHelloButtonClick() {
        welcomeText.text = "Welcome to JavaFX Application!"

        try{
            imgView.image = Image(FileInputStream("./img.png"))
        } catch(e: IllegalArgumentException) {
            Unirest.get("http://unnamed-chart-server.com:8000/songs/1/jacket")
                .downloadMonitor { s, s2, l, l2 ->
                    if(l2 == l) imgView.image = Image(FileInputStream("./img.png"))
                }
                .asFile("./img.png")
                .body
        }

    }
}