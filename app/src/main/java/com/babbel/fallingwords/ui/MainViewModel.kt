package com.babbel.fallingwords.ui

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.babbel.fallingwords.data.gameseassion.Session
import com.babbel.fallingwords.data.jsonmodel.Word
import com.babbel.fallingwords.domain.WordUseCase
import com.babbel.fallingwords.gamemanager.GameManager
import com.babbel.fallingwords.gamemanager.GameManagerImpl
import com.babbel.fallingwords.util.Constant
import com.babbel.fallingwords.util.WORD_FALLING_DURATION

class MainViewModel(
    val gameManager: GameManager = GameManagerImpl,
    val useCase: WordUseCase = WordUseCase()
): ViewModel() {

    private var timer: CountDownTimer? = null
    private var _activeWord = MutableLiveData<Word?>()
    val activeWord: LiveData<Word?> = _activeWord
    private var _liveTimer = MutableLiveData<Long>()
    val liveTimer: LiveData<Long?> = _liveTimer
    private var _userSelectedAnswer = MutableLiveData<Constant>()
    val userSelectedAnswer: LiveData<Constant?> = _userSelectedAnswer
    private var _session = MutableLiveData<Session>()
    val session: LiveData<Session?> = _session
    private var _isGameOver = MutableLiveData<Boolean>()
    val isGameOver: LiveData<Boolean?> = _isGameOver

    fun startGame() {
        if (gameManager.getGameSession() == null) {
            val words = useCase.getAllWords()
            gameManager.startGame(words)
        }
    }

    fun restartGame() {
        val words = useCase.getAllWords()
        gameManager.startGame(words)
    }

    fun showNewWord() {
        if (!gameManager.isGameOver()) {
            _activeWord.value = gameManager.getNewWord()
        } else {
            gameManager.endGame()
            _isGameOver.value = true
        }
    }

    fun startTimer() {
        timer = object : CountDownTimer((WORD_FALLING_DURATION).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _liveTimer.value = (millisUntilFinished / 1000)
            }
            override fun onFinish() {
                gameManager.getGameSession()?.attempted =
                    gameManager.getGameSession()!!.attempted.plus(1)
                _userSelectedAnswer.value = Constant.UNATTEMPTED
            }
        }.start()
    }

    fun stopTimer() {
        timer?.cancel()
    }

    fun onCorrectButtonClicked() {
        gameManager.getGameSession()?.attempted = gameManager.getGameSession()!!.attempted.plus(1)
        if (activeWord.value?.text_spa == activeWord.value?.translatedWord) {
            gameManager.getGameSession()?.correct = gameManager.getGameSession()!!.correct.plus(1)
            _userSelectedAnswer.value = Constant.CORRECT
        } else {
            _userSelectedAnswer.value = Constant.WRONG
        }
    }

    fun onWrongButtonClicked() {
        gameManager.getGameSession()?.attempted = gameManager.getGameSession()!!.attempted.plus(1)
        if (activeWord.value?.text_spa != activeWord.value?.translatedWord) {
            gameManager.getGameSession()?.correct = gameManager.getGameSession()!!.correct.plus(1)
            _userSelectedAnswer.value = Constant.CORRECT
        } else {
            _userSelectedAnswer.value = Constant.WRONG
        }
    }

    fun getSession() {
        _session.value = gameManager.getGameSession()
    }

}