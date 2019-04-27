package com.watkins.lisa.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import javax.inject.Inject

class HomeViewModel @Inject constructor(): ViewModel() {
    val crazyNumber = MutableLiveData<Int>()

    init {
        crazyNumber.value = 5000000
    }
}