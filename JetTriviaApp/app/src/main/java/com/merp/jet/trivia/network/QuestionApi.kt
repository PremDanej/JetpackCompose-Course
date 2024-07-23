package com.merp.jet.trivia.network

import com.merp.jet.trivia.model.Question
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {
    @GET("world.json")
    suspend fun getAllQuestions() : Question
}