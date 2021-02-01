package com.boykinchoi.read.ui.home

import androidx.lifecycle.MutableLiveData
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.base.LoadState
import com.boykinchoi.baselibrary.network.ApiException
import com.boykinchoi.baselibrary.network.StatusCode
import com.boykinchoi.read.api.RetrofitClient
import com.boykinchoi.read.api.launch
import com.boykinchoi.read.bean.HomeDataBean
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel

/**
 * 首页ViewModel
 * Created by BoykinChoi
 * on 2021/1/29
 **/
class HomeViewModel : BaseViewModel() {
    var homeData: MutableLiveData<HomeDataBean> = MutableLiveData()
    override fun getBaseData() {
        launch(
                {
                    loadState.value = LoadState.Loading()
                    val userInfoBase = async { RetrofitClient.testService.homeUserInfo() }
                    val bookListBase = async { RetrofitClient.testService.homeBookList() }
                    if (userInfoBase.await().code != StatusCode.SUCCESS
                            || bookListBase.await().code != StatusCode.SUCCESS) {
                        loadState.value = LoadState.Fail(userInfoBase.await().message!!)
                        cancel()
                    }
                    val homeDataBean = HomeDataBean(userInfoBase.await().data, bookListBase.await().data)
                    homeData.value = homeDataBean
                    loadState.value = LoadState.Success()
                },
                {
                    loadState.value = LoadState.Fail(it.message ?: "request exception")
                }
        )
    }

}