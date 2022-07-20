package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nubmergame.R
import com.example.nubmergame.databinding.EndGameLayoutBinding
import com.example.nubmergame.domain.GameResult
import java.lang.RuntimeException

class EndGameFragment:Fragment() {
    private lateinit var gameResultImage:ImageView
    private lateinit var gameResultTextView: TextView

    private val arguments by navArgs<EndGameFragmentArgs>()
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





    private fun initViews() {
        gameResultImage = endGameBinding.gameResultImageView
        gameResultTextView = endGameBinding.gameResultTextView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }


        })
        endGameBinding.restartGameButton.setOnClickListener(View.OnClickListener {
            retryGame()
        })
        if(arguments.result.isWinner){
            gameResultImage.setBackgroundResource(R.drawable.ic_baseline_sentiment_very_satisfied_24)
        }
        else{
            gameResultImage.setBackgroundResource(R.drawable.ic_round_sentiment_dissatisfied_24)
        }
        setText()
    }

    private fun setText() {
        if(arguments.result.isWinner){
            gameResultTextView.text = "Правильных ответов ${arguments.result.countOfRightAnswers} " +
                    "из ${arguments.result.countOfQuestions} вы победили"

        }
        else{
            gameResultTextView.text = "Правильных ответов ${arguments.result.countOfRightAnswers} " +
                    "из ${arguments.result.countOfQuestions} вы лошара"

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _endGameBinding = null

    }

    private fun retryGame(){
        findNavController().popBackStack()

    }

}