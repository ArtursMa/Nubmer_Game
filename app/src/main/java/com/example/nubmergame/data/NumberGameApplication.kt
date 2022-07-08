package com.example.nubmergame.data

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class NumberGameApplication:Application() {
    val applicationScope:CoroutineScope = CoroutineScope(SupervisorJob())
    val repository by lazy { GameRepositoryImpl() }
}