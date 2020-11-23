package com.eduardo.pokedex.response

import android.graphics.Bitmap

data class Pokemon(
    val id: Int,
    val url: String,
    val name: String,
    val type: String,
    val chain: Int
)