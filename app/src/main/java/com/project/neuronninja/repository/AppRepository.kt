package com.project.neuronninja.repository

import android.util.Log
import com.project.neuronninja.data.ApiResponse
import com.project.neuronninja.model.Question
import com.project.neuronninja.network.QuestionApi
import javax.inject.Inject

class AppRepository @Inject constructor(private val api: QuestionApi) {
    private val response = ApiResponse<Question>()

    suspend fun getAllQuestions(): ApiResponse<Question> {
        try {
            response.isLoading = true
            response.data = api.getAllQuestions()
            if (response.data.toString().isNotEmpty()) response.isLoading = false
        } catch (ex: Exception) {
            response.exception = ex
            Log.d("EXC", "getAllQuestions: ${ex.localizedMessage}")
        }
        return response
    }
}