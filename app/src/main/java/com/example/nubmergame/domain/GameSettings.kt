package com.example.nubmergame.domain

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class GameSettings(val maxSumValue:Int,val minCorrectAnswer:Int,
 val minCorrectAnswerPercent:Int,val gameTimeInSec:Int):Parcelable



