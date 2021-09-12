package com.acsl.jakartacovidwatch.data.datasource.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class ApiService {

    private val retrofit = Retrofit.Builder()
        .baseUrl(jakartaCovidWatchBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(HttpRequestService::class.java);

    val getVulnerableDistricts = service.getVulnerableDistricts()


    companion object {
        @Volatile
        private var instance : ApiService? = null
        private const val jakartaCovidWatchBaseUrl = "https://quiet-cove-60893.herokuapp.com/api/"
        fun getInstance() : ApiService =
            instance ?: synchronized(this) {
                instance ?: ApiService()
            }
    }
}