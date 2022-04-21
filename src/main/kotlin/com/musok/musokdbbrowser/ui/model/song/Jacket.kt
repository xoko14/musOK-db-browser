package com.musok.musokdbbrowser.ui.model.song

import org.simpleframework.xml.Attribute

class Jacket : File() {
    @field:Attribute
    var artist: String? = null
}