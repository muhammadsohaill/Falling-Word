package com.babbel.fallingwords.domain

import com.babbel.fallingwords.data.WordsRepository
import com.babbel.fallingwords.data.jsonmodel.Word

class WordUseCase {

    private val repository by lazy { WordsRepository() }

    fun getAllWords(): List<Word> {
        return repository.getAllWords()
    }

}