package com.boykinchoi.baselibrary.base.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boykinchoi.baselibrary.base.LoadState

/**
 * ViewModel基类
 * 使用ViewModel的时候，不能将任何含有Context引用的对象传入ViewModel，因为这可能会导致内存泄露。
 * 但如果你希望在ViewModel中使用Context怎么办呢？我们可以使用AndroidViewModel类，它继承自ViewModel，
 * 并且接收Application作为Context
 * Created by BoykinChoi
 * on 2021/1/30
 **/
abstract class BaseViewModel : ViewModel() {

    // 全局Application，提供获取资源的能力（不使用官方提供的AndroidViewModel是因为扩展能力差）
    var application: Application? = null

    // 页面状态
    val loadState by lazy { MutableLiveData<LoadState>() }

    // 加载框状态
    val showLoading by lazy { MutableLiveData<Boolean>() }

    open fun getBaseData() {

    }

}