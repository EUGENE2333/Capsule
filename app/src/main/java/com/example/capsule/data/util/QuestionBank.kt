package com.example.capsule.data.util

import com.example.capsule.R
import com.example.capsule.data.QuizQuestion

object QuestionBank {


    val quizQuestions:List<QuizQuestion> = listOf(
        QuizQuestion(
            R.string.question_australia,
            listOf("Option A","Option B", "Option C", "Option D"),
            "Option A"
        ),
    QuizQuestion(
        R.string.question_oceans,
        listOf("Option A","Option B", "Option C", "Option D"),
        "Option D"
    ),
    QuizQuestion(
        R.string.question_mideast,
        listOf("Option A","Option B", "Option C", "Option D"),
        "Option A"
    ),
    QuizQuestion(
        R.string.question_africa,
        listOf("Option A","Option B", "Option C", "Option D"),
        "Option C"
    ),
    QuizQuestion(
        R.string.question_americas,
        listOf("Option T","Option B", "Option C", "Option D"),
        "Option T"
    ),
    QuizQuestion(
        R.string.question_asia,
        listOf("Option A","Option B", "Option F", "Option D"),
        "Option F"
    ),
    )

}
