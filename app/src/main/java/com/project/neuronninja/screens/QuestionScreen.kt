package com.project.neuronninja.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.neuronninja.components.DashedLine
import com.project.neuronninja.components.ProgressBar
import com.project.neuronninja.components.QuestionDisplay
import com.project.neuronninja.components.QuestionTracker
import com.project.neuronninja.data.getDummyQuestions
import com.project.neuronninja.model.QuestionItem
import com.project.neuronninja.util.AppColors

@Preview
@Composable
fun QuestionScreen(
    scaffoldPadding: PaddingValues = PaddingValues(),
    questions: List<QuestionItem> = getDummyQuestions(),
    questionIndex: Int = 0,
    onNext: () -> Unit = {}
) {
    var correctAnswer by remember(questionIndex) {
        mutableStateOf(false)
    }
    val screenScrollState = rememberScrollState()
    Surface(modifier = Modifier
        .fillMaxSize()
        .padding(scaffoldPadding),
        color = AppColors.paleYellow) {
        Column(modifier = Modifier
            .padding(12.dp)
            .verticalScroll(state = screenScrollState)) {
            ProgressBar(modifier = Modifier.fillMaxWidth(),
                fraction = questionIndex.toFloat() / questions.size)
            QuestionTracker(modifier = Modifier.padding(16.dp),
                currentQuestion = questionIndex + 1,
                totalQuestions = questions.size)
            DashedLine(modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
                color = AppColors.darkOrange,
                thickness = 2.dp)
            QuestionDisplay(modifier = Modifier.fillMaxWidth(),
                question = questions[questionIndex],
                onUpdateChoice = { correctAnswer = it })
            Button(onClick = onNext,
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                enabled = correctAnswer,
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.darkPurple,
                    contentColor = Color.White,
                    disabledContainerColor = AppColors.lightPurple,
                    disabledContentColor = AppColors.mediumGray
                )) {
                Text(text = "Next")
            }
        }
    }
}
