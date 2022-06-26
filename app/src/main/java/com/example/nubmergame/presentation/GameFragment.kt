package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nubmergame.R
import com.example.nubmergame.databinding.GameLayoutBinding
import java.lang.RuntimeException

class GameFragment:Fragment() {
    private var _gameFragmentBinding:GameLayoutBinding? = null
    private val gameFragmentBinding:GameLayoutBinding
    get()= _gameFragmentBinding?:throw RuntimeException("binding== null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _gameFragmentBinding = GameLayoutBinding.inflate(inflater,container,false)
        return gameFragmentBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _gameFragmentBinding =null
    }
}