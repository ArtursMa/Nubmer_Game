package com.example.nubmergame.domain

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.io.Serializable
@Parcelize
@Entity
data class GameResult(val isWinner:Boolean,val countOfRightAnswers:Int,
val countOfQuestions:Int,val gameSettings: GameSettings):Parcelable
