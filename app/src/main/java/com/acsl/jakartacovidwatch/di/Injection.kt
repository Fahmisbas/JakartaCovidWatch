package com.acsl.jakartacovidwatch.di

import android.content.Context
import com.acsl.jakartacovidwatch.data.AppRepository
import com.acsl.jakartacovidwatch.data.datasource.remote.api.ApiService

object Injection {
    fun provideRepository() : AppRepository {
        val remoteDataSource = ApiService.getInstance()
        return AppRepository.getInstance(remoteDataSource)
    }
}