package com.babbel.fallingwords.gamemanager

import com.babbel.fallingwords.data.gameseassion.Session
import com.babbel.fallingwords.data.jsonmodel.Word

interface GameManager {

    fun getGameSession(): Session?

    fun startGame(questions: List<Word>)

    fun endGame()

    fun getNewWord(): Word?

    fun markWordUsed(word: Word)

    fun isGameOver(): Boolean

}