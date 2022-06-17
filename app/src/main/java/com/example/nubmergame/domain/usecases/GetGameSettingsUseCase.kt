package com.example.nubmergame.domain.usecases

import com.example.nubmergame.domain.GameRepository
import com.example.nubmergame.domain.GameSettings
import com.example.nubmergame.domain.Level

class GetGameSettingsUseCase(val repository: GameRepository) {
    operator fun invoke(level: Level):GameSettings{
      return  repository.getSettings(level)

    }
}