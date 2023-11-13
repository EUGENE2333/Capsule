package com.example.capsule.data

import androidx.annotation.StringRes

data class QuizQuestion(
    @StringRes  val questionText: Int,
    val answers: List<String>,
    val correctAnswer: String
)



