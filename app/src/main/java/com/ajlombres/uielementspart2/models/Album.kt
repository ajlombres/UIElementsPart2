package com.ajlombres.uielementspart2.models

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Album (var id: Int = 0, var albumTitle: String, var releaseDate : String ) {
    override fun toString(): String {
        return "$albumTitle"
    }
}
