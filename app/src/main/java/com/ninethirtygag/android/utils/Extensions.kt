package com.ninethirtygag.android.utils

import androidx.lifecycle.LiveData

val <T> LiveData<T>.asLiveData: LiveData<T> get() = this