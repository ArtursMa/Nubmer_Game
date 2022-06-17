package com.example.nubmergame.domain.usecases

import com.example.nubmergame.domain.GameRepository
import com.example.nubmergame.domain.Question

class GenerateQuestionUseCase(val repository: GameRepository) {
    operator fun invoke(maxSumValue:Int):Question{
        return repository.getQuestions(maxSumValue, COUNT_OF_OPTIONS)


    }
    private companion object{
        private const val COUNT_OF_OPTIONS = 6
    }

}