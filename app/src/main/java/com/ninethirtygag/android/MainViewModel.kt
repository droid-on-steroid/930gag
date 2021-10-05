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

    private val _error = MutableLiveData<String?>()

    val error get() = _error.asLiveData

    private val _loading = MutableLiveData<Boolean>()

    val loading get() = _loading.asLiveData

    fun getMemes() {
        viewModelScope.launch {
            _loading.postValue(true)
            try {
                val memes = memeService.getMemes().data?.memes
                if (memes == null) {
                    _error.postValue("Something went wrong")
                    return@launch
                } else {
                    _error.postValue(null)
                    _memes.postValue(memes)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _error.postValue(e.message)
            } finally {
                _loading.postValue(false)
            }
        }
    }
}