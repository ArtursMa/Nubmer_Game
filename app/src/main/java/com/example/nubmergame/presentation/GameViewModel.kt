package com.example.nubmergame.presentation
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nubmergame.domain.*
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import java.util.concurrent.CountDownLatch

class GameViewModel(val repository: GameRepository,val level: Level): ViewModel() {


    private var _gameSettings = MutableLiveData<GameSettings>()
    val gameSettings:LiveData<GameSettings>
    get() = _gameSettings

    private var _gameQuestion = MutableLiveData<Question>()
    val gameQuestion:LiveData<Question>
    get() = _gameQuestion

    private var _percentOfRightAnswers = MutableLiveData<Int>()
    val percentOfRightAnswers:LiveData<Int>
    get() = _percentOfRightAnswers

    private var _enoughPercentOfRightAnswers = MutableLiveData<Boolean>()
    val enoughPercentOfRightAnswers:LiveData<Boolean>
        get() = _enoughPercentOfRightAnswers

    private var _enoughRightAnswers = MutableLiveData<Boolean>()
    val enoughRightAnswers:LiveData<Boolean>
    get()=_enoughRightAnswers

    private var _gameResult = MutableLiveData<GameResult>()
    val gameResult:LiveData<GameResult>
    get() = _gameResult



    var timerInSeconds = 0
    var timeInMin = 0


    var amountOfRightAnswers = 0
    var amountOfAnswers = 0
    var textTimer = "00:00"
    private var _timeLiveData = MutableLiveData<String>()
    val timerLiveData:LiveData<String>
    get() = _timeLiveData

    private var _gameIsFinished = MutableLiveData<Boolean>()
    val gameIsFinished:LiveData<Boolean>
    get()= _gameIsFinished






    fun getSettings() = viewModelScope.launch {
        _gameSettings.value = repository.getSettings(level)
    }

    fun getQuestion() = viewModelScope.launch {
        val maxSumValue = _gameSettings.value?.maxSumValue
        val countOfOption = NUMBER_OF_OPTIONS
        _gameQuestion.value = maxSumValue?.let { repository.getQuestions(it,countOfOption) }


    }
    fun startGame(){
        getSettings()
        getQuestion()
        startTimer(gameSettings.value?.gameTimeInSec)




    }
    fun chooseAnswer(answer:Int){
        var correctAnswer = _gameQuestion.value?.sum!! - _gameQuestion.value?.visibleNumber!!
        if(correctAnswer == answer){
            amountOfRightAnswers++
        }
        amountOfAnswers++
        _percentOfRightAnswers.value = ((amountOfRightAnswers.toDouble()/amountOfAnswers.toDouble())*100).toInt()
        _enoughRightAnswers.value = amountOfRightAnswers>= _gameSettings.value?.minCorrectAnswer!!
        _enoughPercentOfRightAnswers.value = percentOfRightAnswers.value!! >= gameSettings.value?.minCorrectAnswerPercent!!

        if(timeInMin+timerInSeconds>0){
            getQuestion()

        }





    }

    private fun startTimer(timeInSec:Int?) {
        var timeInMills = (timeInSec?.times(1000)?.toLong())
        var countDownTimer = (object :CountDownTimer(timeInMills!!,1000){
            override fun onTick(p0: Long) {
                timeInMin = ((p0/60)/1000).toInt()
                timerInSeconds = ((p0/1000)%60).toInt()
                textTimer = String.format("%02d:%02d",timeInMin,timerInSeconds)
                _timeLiveData.value = textTimer

            }

            override fun onFinish() {
                finishGame()

            }


        }).start()



    }

    private fun finishGame() {
        var isWinner = _enoughPercentOfRightAnswers.value == true && _enoughRightAnswers.value == true
         _gameResult.value = GameResult(isWinner,amountOfRightAnswers,amountOfAnswers,gameSettings.value!!)
        _gameIsFinished.value = true
    }

    companion object{
        private const val NUMBER_OF_OPTIONS = 6
    }
class GameViewModelProviderFactory(private val repository: GameRepository,private val level: Level):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java)){
@Suppress("UNCHECKED)CAST")
return GameViewModel(repository,level) as T
        }
throw IllegalArgumentException("Unknown class view model")
    }

}

}