package com.geekbrains.tests.repository

import com.geekbrains.tests.presenter.RepositoryContract
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryProvider : IRepositoryProvider {
    private const val BASE_URL = "https://api.github.com"

    override fun getRepository(): RepositoryContract =
        GitHubRepository(createRetrofit().create(GitHubApi::class.java))

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}