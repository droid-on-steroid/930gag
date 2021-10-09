package com.ninethirtygag.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninethirtygag.android.data.MemeRepository
import com.ninethirtygag.android.data.models.Meme
import com.ninethirtygag.android.data.remote.MemeService
import com.ninethirtygag.android.utils.Resource
import com.ninethirtygag.android.utils.asLiveData
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val memeService by lazy { MemeRepository(MemeService.create()) }

    private val _memes = MutableLiveData<Resource<List<Meme>>>()
    val memes get() = _memes.asLiveData


    fun getMemes() {
        viewModelScope.launch {
            _memes.postValue(Resource.Loading())
            val memes = memeService.getMemes()
            _memes.postValue(memes)
        }
    }
}