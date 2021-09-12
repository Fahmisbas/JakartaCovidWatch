package com.acsl.jakartacovidwatch

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acsl.jakartacovidwatch.data.AppRepository
import com.acsl.jakartacovidwatch.di.Injection
import com.acsl.jakartacovidwatch.ui.districts.VulnerableDistrictViewModel

class ViewModelFactory(private val repository: AppRepository) : ViewModelProvider.NewInstanceFactory() {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(VulnerableDistrictViewModel::class.java) -> {
                VulnerableDistrictViewModel(repository) as T
            } else -> throw Throwable("Unknown ViewModel class : " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance : ViewModelFactory? = null

        fun getInstance() : ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository())
            }
    }

}