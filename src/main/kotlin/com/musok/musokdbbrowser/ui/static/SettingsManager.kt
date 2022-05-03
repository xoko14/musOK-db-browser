package com.musok.musokdbbrowser.ui.static

import com.google.gson.Gson
import com.musok.musokdbbrowser.ui.model.settings.Settings
import java.io.FileReader

object SettingsManager {
    var settings: Settings? = null
        private set

    fun load(){
        settings = Gson().fromJson(FileReader("./settings.json"), Settings::class.java)
    }

    fun save(settings: Settings){

    }
}