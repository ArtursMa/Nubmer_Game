package com.example.nubmergame.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nubmergame.R
import com.example.nubmergame.data.NumberGameApplication
import com.example.nubmergame.databinding.GameLayoutBinding
import com.example.nubmergame.domain.GameResult
import com.example.nubmergame.domain.GameSettings
import com.example.nubmergame.domain.Level
import java.lang.RuntimeException



class GameFragment:Fragment() {
    private val arguments by navArgs<GameFragmentArgs>()



    val gameViewModel:GameViewModel by viewModels {
        GameViewModel.GameViewModelProviderFactory((requireActivity().application as NumberGameApplication).repository,arguments.level)
    }
    private lateinit var timerTextView:TextView
    private lateinit var startButton:Button
    private lateinit var sumTextView: TextView
    private lateinit var visibleNumber:TextView
    private lateinit var answerTextView: TextView
    private lateinit var percentOfRightAnswers:SeekBar
    private lateinit var firstOptionTextView: TextView
    private lateinit var secondOptionTextView: TextView
    private lateinit var thirdOptionTextView: TextView
    private lateinit var fourthOptionTextView: TextView
    private lateinit var fifthOptionTextView: TextView
    private lateinit var sixthOptionTextView: TextView






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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        startButton.setOnClickListener(View.OnClickListener {

            startGame()
        })

    }



    private fun startGame() {
       startButton.visibility = View.GONE
        gameViewModel.startGame()
        gameViewModel.timerLiveData.observe(viewLifecycleOwner, Observer {
            timerTextView.text = it
        })
        gameViewModel.gameQuestion.observe(viewLifecycleOwner, Observer {
            sumTextView.text = it.sum.toString()
            visibleNumber.text =it.visibleNumber.toString()
            answerTextView.text = "?"
            firstOptionTextView.text = it.options[0].toString()
            secondOptionTextView.text = it.options[1].toString()
            thirdOptionTextView.text = it.options[2].toString()
            fourthOptionTextView.text =it.options[3].toString()
            fifthOptionTextView.text = it.options[4].toString()
            sixthOptionTextView.text = it.options[5].toString()


        })
        firstOptionTextView.setOnClickListener(View.OnClickListener {
            val answer = fifthOptionTextView.text.toString().toInt()
            gameViewModel.chooseAnswer(answer)

        })
        secondOptionTextView.setOnClickListener(View.OnClickListener {
            val answer = fifthOptionTextView.text.toString().toInt()
            gameViewModel.chooseAnswer(answer)

        })
        thirdOptionTextView.setOnClickListener(View.OnClickListener {
            val answer = fifthOptionTextView.text.toString().toInt()
            gameViewModel.chooseAnswer(answer)

        })
        fourthOptionTextView.setOnClickListener(View.OnClickListener {
            val answer = fifthOptionTextView.text.toString().toInt()
            gameViewModel.chooseAnswer(answer)

        })
        fifthOptionTextView.setOnClickListener(View.OnClickListener {
            val answer = fifthOptionTextView.text.toString().toInt()
            gameViewModel.chooseAnswer(answer)

        })
        sixthOptionTextView.setOnClickListener(View.OnClickListener {
            val answer = sixthOptionTextView.text.toString().toInt()
            gameViewModel.chooseAnswer(answer)


        })
        gameViewModel.percentOfRightAnswers.observe(viewLifecycleOwner, Observer { it ->
            percentOfRightAnswers.progress = it



        gameViewModel.gameIsFinished.observe(viewLifecycleOwner){
            if(it){
                gameViewModel.gameResult.value?.let { it1 -> finishGame(it1) }

            }
        }











    })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _gameFragmentBinding =null
        gameViewModel.countDownTimer.cancel()

    }


    private fun finishGame(result: GameResult){


        if(findNavController().currentDestination?.id==R.id.gameFragment){
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToEndGameFragment(result))

        }


    }
    private fun initViews(){
        timerTextView = gameFragmentBinding.timerTextView
        startButton = gameFragmentBinding.startGameButton
        sumTextView = gameFragmentBinding.sumTextView
        visibleNumber = gameFragmentBinding.visibleNumberTextView
        answerTextView = gameFragmentBinding.answerNumberTextView
        percentOfRightAnswers = gameFragmentBinding.percentOfCorrectAnswers
        percentOfRightAnswers.isEnabled= false

        firstOptionTextView = gameFragmentBinding.firstOptionTextView
        secondOptionTextView = gameFragmentBinding.secondOptionTextView
        thirdOptionTextView = gameFragmentBinding.thirdOptionTextView
        fourthOptionTextView = gameFragmentBinding.fourthOptionTextView
        fifthOptionTextView = gameFragmentBinding.fifthOptionTextView
        sixthOptionTextView = gameFragmentBinding.sixthOptionTextView

    }

}