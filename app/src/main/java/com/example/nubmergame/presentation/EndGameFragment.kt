package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nubmergame.R
import com.example.nubmergame.databinding.EndGameLayoutBinding
import java.lang.RuntimeException

class EndGameFragment:Fragment() {
    private var _endGameBinding:EndGameLayoutBinding?= null
    private val endGameBinding:EndGameLayoutBinding
    get() = _endGameBinding?:throw RuntimeException("binding == null")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _endGameBinding = EndGameLayoutBinding.inflate(inflater,container,false)
        return endGameBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _endGameBinding = null
    }
}