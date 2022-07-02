package com.example.nubmergame.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.nubmergame.R
import com.example.nubmergame.databinding.GameLayoutBinding
import com.example.nubmergame.domain.GameResult
import com.example.nubmergame.domain.GameSettings
import com.example.nubmergame.domain.Level
import java.lang.RuntimeException


class GameFragment:Fragment() {
    private lateinit var level:Level
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parsArgs()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameFragmentBinding.firstOptionTextView.setOnClickListener(View.OnClickListener {
            finishGame(GameResult(false,0,10, GameSettings(10,
                5,
                10,10)))

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _gameFragmentBinding =null
    }
    companion object{
        public const val GAME_FRAGMENT = "game_fragment"
        private const val KEY_LEVEL = "level"
        fun getInstance(gameLevel: Level):GameFragment{
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL,gameLevel)
                }

            }

        }
    }
    private fun parsArgs(){
        level = requireArguments().getParcelable<Level>(KEY_LEVEL) as Level
    }
    private fun finishGame(result: GameResult){
        requireActivity().supportFragmentManager.beginTransaction().addToBackStack(null)
            .replace(R.id.fragmentContainer,EndGameFragment.getInstance(result)).commit()

    }
}