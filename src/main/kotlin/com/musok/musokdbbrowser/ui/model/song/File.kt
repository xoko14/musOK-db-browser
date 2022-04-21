package com.musok.musokdbbrowser.ui.model.song

import org.simpleframework.xml.Attribute

abstract class File {
    @field:Attribute
    var file: String? = null
}