package com.example.nubmergame.domain

import androidx.room.Entity

@Entity
data class Question(val sum:Int,val visibleNumber:Int,val options:List<Int>)
