package com.example.nubmergame.domain

import androidx.room.Entity
import java.io.Serializable

@Entity
data class GameResult(val isWinner:Boolean,val countOfRightAnswers:Int,
val countOfQuestions:Int,val gameSettings: GameSettings):Serializable
