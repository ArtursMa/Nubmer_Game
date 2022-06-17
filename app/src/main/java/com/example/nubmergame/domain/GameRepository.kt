package com.example.nubmergame.domain

interface GameRepository {
    fun getQuestions(maxSumValue:Int,countOfOptions:Int):Question
    fun getSettings(level: Level):GameSettings
}