package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.nubmergame.R
import com.example.nubmergame.databinding.EndGameLayoutBinding
import com.example.nubmergame.domain.GameResult
import java.lang.RuntimeException

class EndGameFragment:Fragment() {
    private lateinit var gameResult:GameResult
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
           gameResult = it
       }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                retryGame()
            }


        })
        endGameBinding.restartGameButton.setOnClickListener(View.OnClickListener {
            retryGame()
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _endGameBinding = null

    }
    companion object{
        private const val KEY_GAME_RESULT = "game_result"
        fun getInstance(result:GameResult):EndGameFragment{
           return EndGameFragment().apply {
               arguments = Bundle().apply {
                   putParcelable(KEY_GAME_RESULT,result)
               }
           }

        }
    }
    private fun retryGame(){
        requireActivity().supportFragmentManager.
        popBackStack(GameFragment.GAME_FRAGMENT,FragmentManager.POP_BACK_STACK_INCLUSIVE)

    }

}