package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nubmergame.R
import com.example.nubmergame.databinding.ChooseGameLevelFragmentLayoutBinding
import com.example.nubmergame.domain.Level
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(chooseGameBinding){
            testTextView.setOnClickListener(View.OnClickListener {
                launchFragment(Level.TEST)


            })
            easyTextView.setOnClickListener(View.OnClickListener {
                launchFragment(Level.EASY)

            })
            mediumTextView.setOnClickListener(View.OnClickListener {
                launchFragment(Level.MEDIUM)

            })
            hardTextView.setOnClickListener(View.OnClickListener {
                launchFragment(Level.HARD)

            })

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _chooseGameBinding = null
    }
    fun launchFragment(level: Level){

        findNavController().navigate(ChooseGameLevelFragmentDirections.actionChooseGameLevelFragmentToGameFragment(level))



    }

}