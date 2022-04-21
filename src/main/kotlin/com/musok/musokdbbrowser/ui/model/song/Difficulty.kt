package com.musok.musokdbbrowser.ui.model.song

import org.simpleframework.xml.Attribute

class Difficulty : File() {
    @field:Attribute
    var charter: String? = null
    @field:Attribute
    var difficulty: String? = null
}