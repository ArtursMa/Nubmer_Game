package com.example.nubmergame.domain

import androidx.room.Entity

@Entity
data class GameSettings(val maxSumValue:Int,val minCorrectAnswer:Int,
 val minCorrectAnswerPercent:Int,val gameTimeInSec:Int)



