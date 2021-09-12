package com.acsl.jakartacovidwatch.ui.districts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acsl.jakartacovidwatch.data.AppRepository
import com.acsl.jakartacovidwatch.data.datasource.model.VulnerableDistrictItem
import com.acsl.jakartacovidwatch.util.ResponseStatus
import com.acsl.jakartacovidwatch.util.Status

class VulnerableDistrictViewModel(private val repository: AppRepository) : ViewModel() {

    private val _isSuccess = MutableLiveData<ResponseStatus>()
    val isSuccess : LiveData<ResponseStatus> = _isSuccess

    fun getVulnerableDistricts(): LiveData<List<VulnerableDistrictItem>>{
        val item =  MutableLiveData<List<VulnerableDistrictItem>>()
        repository.getVulnerableDistricts { data, callback ->
            if (callback?.status != Status.SUCCESS) {
                _isSuccess.value = ResponseStatus(callback?.message, callback?.status)
            }
            item.value = data
        }
        return item
    }
}