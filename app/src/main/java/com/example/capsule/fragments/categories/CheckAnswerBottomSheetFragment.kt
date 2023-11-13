package com.example.capsule.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.capsule.R
import com.example.capsule.databinding.FragmentCheckAnswerBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.example.capsule.viewmodel.MainViewModel

class CheckAnswerBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentCheckAnswerBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckAnswerBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get arguments passed to the fragment
        val args = arguments
        val isAnswerCorrect = args?.getBoolean("isAnswerCorrect") ?: false
        val correctAnswer = args?.getString("correctAnswer") ?: ""

        // Set the scoreTextView based on whether the answer is correct or not
        if (isAnswerCorrect) {
            binding.scoreTextView.text = "Correct!"
            binding.scoreTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        } else {
            binding.scoreTextView.text = "Wrong!"
            binding.scoreTextView.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.red
                )
            )
        }

        // Set the correct answer text
        binding.answerTextView.text = "Correct Answer: $correctAnswer"
    }
}


