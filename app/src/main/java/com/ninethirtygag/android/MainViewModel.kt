package com.ninethirtygag.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninethirtygag.android.data.MemeService
import com.ninethirtygag.android.data.models.Meme
import com.ninethirtygag.android.utils.asLiveData
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val memeService by lazy { MemeService.create() }

    private val _memes = MutableLiveData<List<Meme>>()

    val memes get() = _memes.asLiveData

    fun getMemes() {
        viewModelScope.launch {
            val memes = memeService.getMemes().data?.memes ?: return@launch
            _memes.postValue(memes)
        }
    }
}