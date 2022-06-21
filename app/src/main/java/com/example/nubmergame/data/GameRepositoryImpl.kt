package com.example.nubmergame.data

import com.example.nubmergame.domain.GameRepository
import com.example.nubmergame.domain.GameSettings
import com.example.nubmergame.domain.Level
import com.example.nubmergame.domain.Question
import kotlin.random.Random

object GameRepositoryImpl:GameRepository {
    private const val MIN_SUM = 2

    override fun getQuestions(maxSumValue: Int, countOfOptions: Int): Question {
        val sum = Random.nextInt(MIN_SUM,maxSumValue+1)
        val visibleNumber = Random.nextInt(MIN_SUM,maxSumValue)
        val correctAnswer = sum - visibleNumber
        val listOfQuestion = mutableListOf<Int>()

        for(i in 0 until countOfOptions){
            listOfQuestion.add(i, Random.nextInt(MIN_SUM,maxSumValue*3))

        }
        listOfQuestion[listOfQuestion.size-1] = correctAnswer

        return Question(sum,visibleNumber,listOfQuestion)



    }

    override fun getSettings(level: Level): GameSettings {
        return when(level){
            Level.TEST->GameSettings(
                20,
                4,
                50,
                8

            )
            Level.EASY-> GameSettings(20,4,60,10)
            Level.MEDIUM-> GameSettings(20,4,60,10)
            Level.HARD->GameSettings(20,4,60,10)
        }
    }
}