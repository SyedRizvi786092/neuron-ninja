package com.project.neuronninja

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.project.neuronninja.screens.QuestionScreen
import com.project.neuronninja.screens.QuestionsViewModel
import com.project.neuronninja.ui.theme.NeuronNinjaTheme
import com.project.neuronninja.util.AppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NeuronNinjaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuizApp(scaffoldPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun QuizApp(scaffoldPadding: PaddingValues, viewModel: QuestionsViewModel = hiltViewModel()) {
    val questions = viewModel.questions.value
    var questionIndex by remember {
        mutableIntStateOf(0)
    }
    if (questions.isLoading == true) {
        Surface(modifier = Modifier.padding(scaffoldPadding),
            color = AppColors.paleYellow) {
            Column(modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "LOADING...", color = AppColors.darkOrange)
            }
        }
    }
    else if (questions.isLoading == false) {
        if (questions.data != null)
            QuestionScreen(
                scaffoldPadding = scaffoldPadding,
                questions = questions.data!!.toList(),
                questionIndex = questionIndex,
                onNext = { questionIndex++ }
            )
    }
}
