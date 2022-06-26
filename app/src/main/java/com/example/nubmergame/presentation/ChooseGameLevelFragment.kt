package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nubmergame.R
import com.example.nubmergame.databinding.ChooseGameLevelFragmentLayoutBinding
import java.lang.RuntimeException

class ChooseGameLevelFragment: Fragment() {
    private var _chooseGameBinding:ChooseGameLevelFragmentLayoutBinding? = null
    private val chooseGameBinding:ChooseGameLevelFragmentLayoutBinding
    get() = _chooseGameBinding?:throw RuntimeException("binding=null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _chooseGameBinding = ChooseGameLevelFragmentLayoutBinding.inflate(inflater,container,false)
        return chooseGameBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _chooseGameBinding = null
    }
}