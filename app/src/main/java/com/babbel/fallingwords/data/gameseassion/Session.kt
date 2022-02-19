package com.babbel.fallingwords.data.gameseassion

import com.babbel.fallingwords.data.jsonmodel.Word
// This data class holds the necessary game attributes for game sessions
data class Session(
    var WordsPerSession: Int = 0,
    var attempted: Int = 0,
    var correct: Int = 0,
    val availableWords: List<Word>? = null
)