package com.project.neuronninja.data

import com.project.neuronninja.model.QuestionItem

fun getDummyQuestions(): List<QuestionItem> = listOf(

    QuestionItem(
        question = "What is TWO ZERO TWO FOUR?",
        category = "brain-teasers",
        answer = "None of these",
        choices = listOf(
            "2024",
            "0044",
            "2044",
            "0024",
            "None of these"
        )
    ),

    QuestionItem(
        question = "Which of these is true about the sleep of zebras?",
        category = "brain-teasers",
        answer = "They sleep standing up.",
        choices = listOf(
            "All of these",
            "They sleep standing up.",
            "They would fall asleep every 5 to 6 hours.",
            "They need more than 12 hours of sleep a day."
        )
    )
)