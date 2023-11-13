package com.example.capsule.fragments.categories

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.capsule.R
import com.example.capsule.databinding.FragmentQuizBinding
import com.example.capsule.data.util.QuestionBank.quizQuestions
import com.example.capsule.viewmodel.MainViewModel

class QuizFragment: Fragment(){

    private lateinit var binding: FragmentQuizBinding
    private val viewModel by viewModels<MainViewModel>()
    private var lastClickedButton: Button? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.resetQuiz()
        displayQuestionAndAnswers()
        binding.nextButton.setOnClickListener {
            viewModel.apply {
                if(currentQuestionIndex < quizQuestions.size -1){
                    currentQuestionIndex++
                    displayQuestionAndAnswers()
                }else{
                    // Show the result bottom sheet
                    val resultBottomSheetFragment =
                        ResultBottomSheetFragment.newInstance(viewModel.userScore)
                    resultBottomSheetFragment.show(parentFragmentManager, resultBottomSheetFragment.tag)
                  stopTimer()

                }
                binding.checkAnswerButton.isEnabled = false
            }

        }

        binding.checkAnswerButton.setOnClickListener {

            // Show the CheckAnswerBottomSheetFragment
            val checkAnswerBottomSheetFragment = CheckAnswerBottomSheetFragment()
            checkAnswerBottomSheetFragment.arguments = bundleOf(
                "isAnswerCorrect" to viewModel.isAnswerCorrect,
                "correctAnswer" to viewModel.question.correctAnswer
            )
            checkAnswerBottomSheetFragment.show(parentFragmentManager, checkAnswerBottomSheetFragment.tag)
        }

    }

    private fun displayQuestionAndAnswers() {
        // Update the question text
        val question = quizQuestions[viewModel.currentQuestionIndex]
        val questionProgressText = getString(R.string.question_progress, viewModel.currentQuestionIndex + 1, quizQuestions.size)

            binding.questionTextView.text = getString(question.questionText)
            binding.progress.text = questionProgressText



            // Set up buttons with answer options
            val answerButtons = listOf(
                binding.answer1Button,
                binding.answer2Button,
                binding.answer3Button,
                binding.answer4Button
            )

            //text on answerButtons
            answerButtons.forEachIndexed { buttonIndex, button ->
                button.text = question.answers[buttonIndex]
                button.setBackgroundColor(Color.YELLOW)
                lastClickedButton = null

                button.setOnClickListener {
                    viewModel.checkAnswer(button.text.toString())
                    viewModel.score(button.text.toString())
                    button.setBackgroundColor(Color.CYAN)
                    binding.checkAnswerButton.isEnabled = true

                    // Reset the color of the last clicked button to default
                    lastClickedButton?.setBackgroundColor(Color.YELLOW)


                    // Update the last clicked button
                    lastClickedButton = button
                }
            }
    }
}
