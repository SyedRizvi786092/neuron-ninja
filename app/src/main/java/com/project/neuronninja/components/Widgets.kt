package com.project.neuronninja.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.neuronninja.data.getDummyQuestions
import com.project.neuronninja.model.QuestionItem
import com.project.neuronninja.util.AppColors

@Composable
fun QuestionTracker(
    modifier: Modifier = Modifier,
    currentQuestion: Int,
    totalQuestions: Int) {
    Text(text = buildAnnotatedString {
        withStyle(style = SpanStyle(fontSize = 28.sp)) {
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                append("Question ")
            }
            withStyle(style = SpanStyle(fontWeight = FontWeight.Light)) {
                append("$currentQuestion/")
            }
        }
        withStyle(style = SpanStyle(fontSize = 14.sp)) {
            append("$totalQuestions")
        }
    },
        modifier = modifier,
        color = AppColors.orange
    )
}

@Composable
fun DashedLine(
    modifier: Modifier = Modifier,
    thickness: Dp = 1.dp,
    color: Color = MaterialTheme.colorScheme.onSurface) {
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f))
    Canvas(modifier = modifier
        .fillMaxWidth()
        .height(thickness)) {
        drawLine(color = color,
            start = Offset.Zero,
            end = Offset(size.width, 0f),
            strokeWidth = thickness.toPx(),
            pathEffect = pathEffect)
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFF9C4)
@Composable
fun QuestionDisplay(
    modifier: Modifier = Modifier,
    question: QuestionItem = getDummyQuestions()[1],
    onUpdateChoice: (Boolean) -> Unit = {}
) {
    val correctChoice = question.choices.indexOf(question.answer)
    var selectedChoice by remember(question) {
        mutableStateOf<Int?>(null)
    }
    val selectChoice: (Int) -> Unit = { index ->
        selectedChoice = index
        if (selectedChoice == correctChoice) onUpdateChoice(true)
        else onUpdateChoice(false)
    }
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = question.question,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp),
            color = AppColors.darkPurple,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        question.choices.forEachIndexed { index, choice ->
            Row(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(0.9f)
                .heightIn(min = 60.dp)
                .border(
                    width = 4.dp,
                    brush = Brush.verticalGradient(listOf(AppColors.green, AppColors.teal)),
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp))
                .clickable { selectChoice(index) },
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedChoice == index,
                    onClick = { selectChoice(index) },
                    modifier = Modifier.padding(start = 8.dp),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = if (selectedChoice == correctChoice) AppColors.green
                            else AppColors.darkRed,
                        unselectedColor = AppColors.navyBlue)
                )
                Text(text = choice,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    color = if (index == selectedChoice && selectedChoice == correctChoice) AppColors.green
                        else if (index == selectedChoice) AppColors.darkRed
                        else AppColors.navyBlue,
                    fontSize = 14.sp)
            }
        }
    }
}

/*
Row inside of a Surface wasn't working as the Row was filling the entire space due to
fillMaxWidth() being specified in the parent composable (Surface).
 */
@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
    fraction: Float = 0.3f
) {
    Row(modifier = modifier
        .padding(4.dp)
        .height(40.dp)
        .border(
            width = 4.dp,
            color = AppColors.mediumGray,
            shape = RoundedCornerShape(percent = 100)
        )
        .clip(RoundedCornerShape(percent = 100))) {
        Surface(modifier = Modifier
            .fillMaxHeight().fillMaxWidth(fraction)
            .background(Brush.horizontalGradient(listOf(AppColors.teal, AppColors.green))),
            color = Color.Transparent) {}
    }
}
