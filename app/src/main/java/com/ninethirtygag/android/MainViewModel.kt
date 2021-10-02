package com.ninethirtygag.android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninethirtygag.android.data.MemeService
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val memeService by lazy { MemeService.create() }

    init {
        getMemes()
    }

    fun getMemes() {
        viewModelScope.launch {
            val memes = memeService.getMemes().data?.memes
        }
    }
}