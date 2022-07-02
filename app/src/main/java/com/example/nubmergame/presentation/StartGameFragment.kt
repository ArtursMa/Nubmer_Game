package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nubmergame.R
import com.example.nubmergame.databinding.StartGameFragmentLayoutBinding
import java.lang.RuntimeException

class StartGameFragment:Fragment() {
    private var _startGameBinding:StartGameFragmentLayoutBinding?=null
    private val startGameBinding:StartGameFragmentLayoutBinding
    get() = _startGameBinding?:throw RuntimeException("binding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _startGameBinding = StartGameFragmentLayoutBinding.inflate(inflater,container,false)
        startGameBinding.startGameButton.setOnClickListener(View.OnClickListener {
            launchGame()
        })
        return startGameBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _startGameBinding = null
    }
    fun launchGame(){
        requireActivity().supportFragmentManager.beginTransaction()
            .addToBackStack(ChooseGameLevelFragment.CHOOSE_GAME_LEVEL_NAME).replace(R.id.fragmentContainer,ChooseGameLevelFragment.getInstance()).commit()
    }
}