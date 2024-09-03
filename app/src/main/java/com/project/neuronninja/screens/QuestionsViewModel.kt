package com.project.neuronninja.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.neuronninja.data.ApiResponse
import com.project.neuronninja.model.Question
import com.project.neuronninja.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionsViewModel @Inject constructor(private val repository: AppRepository): ViewModel() {
    private val _questions = mutableStateOf(ApiResponse<Question>())
    val questions = _questions

    init {
        viewModelScope.launch {
            _questions.value.isLoading = true
            _questions.value = repository.getAllQuestions()
            if (_questions.value.data.toString().isNotEmpty())
                _questions.value.isLoading = false
        }
    }
}