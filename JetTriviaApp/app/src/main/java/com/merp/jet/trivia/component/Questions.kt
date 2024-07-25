package com.merp.jet.trivia.component

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import com.merp.jet.trivia.screens.QuestionsViewModel


@Composable
fun Questions(viewModel: QuestionsViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    } else {
        questions?.forEach { questionItem ->
            Log.d("SIZE ", "Questions: ${questionItem.answer}")
        }
    }
}
