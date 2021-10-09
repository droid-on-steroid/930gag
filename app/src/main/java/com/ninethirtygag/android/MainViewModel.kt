package com.ninethirtygag.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninethirtygag.android.data.MemeService
import com.ninethirtygag.android.data.models.Meme
import com.ninethirtygag.android.utils.Resource
import com.ninethirtygag.android.utils.asLiveData
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val memeService by lazy { MemeService.create() }

    private val _memes = MutableLiveData<Resource<List<Meme>?>>()
    val memes get() = _memes.asLiveData


    fun getMemes() {
        viewModelScope.launch {
            _memes.postValue(Resource.Loading())
            try {
                val memes = memeService.getMemes()
                _memes.postValue(Resource.Success(memes))
            } catch (e: Exception) {
                e.printStackTrace()
                _memes.postValue(Resource.Error(Throwable(e)))
            }
        }
    }
}