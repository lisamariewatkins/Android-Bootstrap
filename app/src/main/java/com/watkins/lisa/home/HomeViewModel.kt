package com.watkins.lisa.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(): ViewModel() {
    val crazyNumber = MutableLiveData<Int>()

    init {
        crazyNumber.value = 50000000
    }
}