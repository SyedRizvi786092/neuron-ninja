package com.project.neuronninja.network

import com.project.neuronninja.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {

    @GET("brain-teasers.json")
    suspend fun getAllQuestions(): Question
}