package com.boykinchoi.baselibrary.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by BoykinChoi
 * on 2021/1/30
 **/
abstract class BaseViewModel : ViewModel() {
    val loadState = MutableLiveData<LoadState>()
    abstract fun getBaseData()
}