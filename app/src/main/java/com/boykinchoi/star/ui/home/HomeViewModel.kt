package com.boykinchoi.star.ui.home

import androidx.lifecycle.MutableLiveData
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.star.api.RetrofitClient
import com.boykinchoi.star.api.dataConvert
import com.boykinchoi.star.api.launch
import com.boykinchoi.star.api.launchBase
import com.boykinchoi.star.bean.HomeDataBean
import com.boykinchoi.star.bean.VersionBean
import kotlinx.coroutines.async

/**
 * 首页ViewModel
 * Created by BoykinChoi
 * on 2021/1/29
 **/
class HomeViewModel : BaseViewModel() {
    var homeData: MutableLiveData<HomeDataBean> = MutableLiveData()
    var versionData: MutableLiveData<VersionBean> = MutableLiveData()

    override fun getBaseData() {
        launch(
                {
                    val userInfoBase = async { RetrofitClient.testService.homeUserInfo() }
                    val bookListBase = async { RetrofitClient.testService.homeBookList() }
                    val homeDataBean = HomeDataBean(userInfoBase.await().dataConvert(),
                            bookListBase.await().dataConvert())
                    homeData.value = homeDataBean
                },
                {
                    //loadState.value = LoadState.Fail(it.message ?: "http exception")
                }
        )
    }

    fun checkVersion() {
        launchBase(true, {
            val versionBase = RetrofitClient.testService
                    .checkVersion("vivo", "1.7.0", 1)
            versionData.value = versionBase.dataConvert()
        }, {
            //not to do sth
        })
    }


}