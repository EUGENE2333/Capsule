package com.example.capsule.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capsule.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.capsule.databinding.FragmentResultBottomSheetBinding
import com.example.capsule.data.util.QuestionBank

class ResultBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentResultBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Update the score in the bottom sheet
        val userScore = arguments?.getInt(USER_SCORE_KEY, 0) ?: 0
        val scoreMessage = getString(R.string.score_message, userScore, QuestionBank.quizQuestions.size)

        binding.scoreTextView.text = scoreMessage
    }

    companion object {
        const val USER_SCORE_KEY = "user_score"

        fun newInstance(userScore: Int): ResultBottomSheetFragment {
            val fragment = ResultBottomSheetFragment()
            val bundle = Bundle().apply {
                putInt(USER_SCORE_KEY, userScore)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}
