package com.acsl.jakartacovidwatch.data.datasource.remote.api

import com.acsl.jakartacovidwatch.data.datasource.model.VulnerableDistrictResponse
import retrofit2.Call
import retrofit2.http.GET

interface HttpRequestService {

    @GET("/vulnerable/districts")
    fun getVulnerableDistricts() : Call<VulnerableDistrictResponse>



}