package com.ninethirtygag.android

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninethirtygag.android.data.MemeService
import com.ninethirtygag.android.data.models.Meme
import com.ninethirtygag.android.utils.Resource
import com.ninethirtygag.android.utils.asLiveData
import com.ninethirtygag.android.utils.pagination.PaginationRecord
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val memesPaginationRecord = PaginationRecord()

    private val memeService by lazy { MemeService.create() }

    private val _memes = MutableLiveData<Resource<List<Meme>?>>()
    val memes get() = _memes.asLiveData

    fun loadMoreMemes() {
        viewModelScope.launch {
            if (memesPaginationRecord.endOfPage) return@launch
            if (_memes.value is Resource.Loading) return@launch
            val data = _memes.value?.data ?: listOf()
            _memes.postValue(Resource.Loading(data))
            try {
                val memes = memeService.getMemes(data.size, memesPaginationRecord.pageSize)
                _memes.postValue(Resource.Success(data + memes))
                if (memes.size < memesPaginationRecord.pageSize) {
                    memesPaginationRecord.endOfPage = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _memes.postValue(Resource.Error(Throwable(e), data))
            }
        }
    }

}