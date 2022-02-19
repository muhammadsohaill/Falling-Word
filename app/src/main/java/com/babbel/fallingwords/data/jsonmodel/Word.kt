package com.babbel.fallingwords.data.jsonmodel


//Word Data Class
data class Word(
    var text_eng: String,
    var text_spa: String,
    var isShown: Boolean = false,
    var translatedWord: String? = null
)