package com.example.capsule.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.capsule.data.QuizQuestion
import com.example.capsule.data.util.QuestionBank

class MainViewModel: ViewModel() {

  //timer
    private var timer: CountDownTimer? = null
   private val _timeRemaining = MutableLiveData<Long>()
    val timeRemaining: LiveData<Long> = _timeRemaining

    //  duration in milliseconds
    private val quizDuration = 600000 // 10 minutes


  // Quiz
    var currentQuestionIndex = 0
    var userScore = 0
    var isAnswerCorrect = false
    val question: QuizQuestion
        get() = QuestionBank.quizQuestions[currentQuestionIndex]



    init {
        startTimer()
    }

    //Quiz
    fun checkAnswer(selectedAnswer: String) {
        // Check if the selected answer is correct
        isAnswerCorrect = selectedAnswer == question.correctAnswer
    }

    fun score(selectedAnswer: String){
        if(selectedAnswer == question.correctAnswer)
            userScore++
        return
    }

    fun resetQuiz(){
        currentQuestionIndex = 0
        userScore = 0
    }


    //Timer

    fun startTimer() {
        timer = object : CountDownTimer(quizDuration.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _timeRemaining.value = millisUntilFinished / 1000
            }

            override fun onFinish() {
                // Timer finished, handle accordingly (e.g., show results)
                // For now, let's reset the timer value to 0
                _timeRemaining.value = 0
            }
        }.start()
    }

    fun stopTimer() {
        timer?.cancel()
    }

    fun resetTimer() {
        timer?.cancel()
        // Set the timer back to the initial duration
        _timeRemaining.value = (quizDuration / 1000).toLong()
    }

}
