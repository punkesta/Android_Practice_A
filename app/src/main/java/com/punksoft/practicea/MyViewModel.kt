package com.punksoft.practicea

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyViewModel : ViewModel() {
    private val _input = MutableStateFlow("")

    val input = _input.asStateFlow()

    fun setInput(input: String) {
        _input.value = input
    }
}