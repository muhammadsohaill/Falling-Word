package com.babbel.fallingwords.data

import com.babbel.fallingwords.AppLauncher
import com.babbel.fallingwords.R
import com.babbel.fallingwords.data.jsonmodel.Word
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.reflect.Type


class WordsRepository {
    fun getAllWords(): List<Word> {
        var result: List<Word> = listOf()
        runBlocking {
            launch(Dispatchers.IO) {
                val type: Type = object : TypeToken<List<Word?>?>() {}.type
                result = Gson().fromJson(readFile(R.raw.word_data), type)
            }
        }
        return result
    }

    private fun readFile(id: Int): String {
        var text = ""
        AppLauncher.context?.let { ctx ->
            text = ctx.resources.openRawResource(id)
                .bufferedReader().use { it.readText() }
        }
        return text
    }
}