package com.acsl.jakartacovidwatch.data

import com.acsl.jakartacovidwatch.data.datasource.model.VulnerableDistrictItem
import com.acsl.jakartacovidwatch.data.datasource.model.VulnerableDistrictResponse
import com.acsl.jakartacovidwatch.data.datasource.remote.api.ApiService
import com.acsl.jakartacovidwatch.util.MSG_ERROR
import com.acsl.jakartacovidwatch.util.MSG_SUCCESS
import com.acsl.jakartacovidwatch.util.ResponseStatus
import com.acsl.jakartacovidwatch.util.Status
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

typealias VulnerableDistrictCallback = (data: List<VulnerableDistrictItem>?, callback: ResponseStatus?) -> Unit

class AppRepository(private val remote: ApiService) {

    fun getVulnerableDistricts(callback: VulnerableDistrictCallback) {
        remote.getVulnerableDistricts.enqueue(object : Callback<VulnerableDistrictResponse> {
            override fun onResponse(
                call: Call<VulnerableDistrictResponse>,
                response: Response<VulnerableDistrictResponse>
            ) {
                val vulnerableDistricts: List<VulnerableDistrictItem>? =
                    response.body()?.vulnerableDistricts
                val data: ArrayList<VulnerableDistrictItem> = arrayListOf()
                if (response.isSuccessful && vulnerableDistricts != null) {
                    data.addAll(vulnerableDistricts)
                    val isSuccess = data.size > 0
                    if (isSuccess) {
                        callback.invoke(
                            data, ResponseStatus(
                                MSG_SUCCESS,
                                Status.SUCCESS
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<VulnerableDistrictResponse>, t: Throwable) {
                t.printStackTrace()
                callback.invoke(
                    null, ResponseStatus(
                        t.message ?: MSG_ERROR,
                        Status.FAILED
                    )
                )
            }

        })
    }

    companion object {
        fun getInstance(remote: ApiService) = AppRepository(remote)

    }


}