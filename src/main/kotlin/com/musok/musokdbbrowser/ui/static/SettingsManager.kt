package com.musok.musokdbbrowser.ui.static

import com.google.gson.Gson
import com.musok.musokdbbrowser.ui.model.settings.Settings
import java.io.File
import java.io.FileReader
import java.io.FileWriter

object SettingsManager {
    var settings: Settings? = null
        private set

    fun load(){
        if(!File("./settings.json").exists()) {
            val fileWriter = FileWriter("./settings.json")
            fileWriter.write(Gson().toJson(Settings("")))
            fileWriter.close()
        }
        settings = Gson().fromJson(FileReader("./settings.json"), Settings::class.java)
    }

    fun save(){
        val fileWriter = FileWriter("./settings.json")
        fileWriter.write(Gson().toJson(settings))
        fileWriter.close()
    }
}