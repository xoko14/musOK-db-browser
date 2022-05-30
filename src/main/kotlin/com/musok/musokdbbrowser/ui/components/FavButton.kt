package com.musok.musokdbbrowser.ui.components

import com.musok.musokdbbrowser.ui.util.SVGPaths
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.control.Button
import javafx.scene.shape.SVGPath
import java.io.IOException
import java.util.concurrent.Callable

class FavButton: Button() {
    @FXML lateinit var svg: SVGPath
    private var isFaved: Boolean = false
    private var favedAction: (() -> Unit)? = null
    private var unfavedAction: (() -> Unit)? = null

    init {
        val loader = FXMLLoader(javaClass.getResource("/views/components/fav-button.fxml"))
        loader.setRoot(this)
        loader.setController(this)
        try {
            loader.load()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        this.setOnAction {
            when(isFaved){
                true -> {
                    unfavedAction?.invoke()
                    setFaved(false)
                }

                false -> {
                    favedAction?.invoke()
                    setFaved(true)
                }
            }
        }
    }

    fun setFaved(state: Boolean){
        isFaved = state
        when(isFaved){
            true -> {
                svg.content = SVGPaths.FAVED
            }

            false -> {
                svg.content = SVGPaths.UNFAVED
            }
        }
    }

    fun setOnFaved(action: () -> Unit){
        favedAction = action
    }

    fun setOnUnfaved(action: () -> Unit){
        unfavedAction = action
    }
}