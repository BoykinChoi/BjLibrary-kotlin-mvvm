package com.boykinchoi.star.ui.home

import androidx.lifecycle.MutableLiveData
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.network.ext.dataConvert
import com.boykinchoi.baselibrary.network.ext.fetchLaunch
import com.boykinchoi.baselibrary.network.ext.fetchLaunchBase
import com.boykinchoi.star.api.RetrofitClient
import com.boykinchoi.star.app.ValueConfig
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
        fetchLaunch(
            block = {
                RetrofitClient.testService.let {
                    if (ValueConfig.pToken.isEmpty()) {
                        ValueConfig.pToken =
                            async {
                                it.login("18302015102", "c66666666")
                            }.await().dataConvert()?.token.toString()
                    }
                    val userInfo = async { it.homeUserInfo() }.await().dataConvert()
                    val bookList = async { it.homeBookList() }.await().dataConvert()
                    homeData.value = HomeDataBean(userInfo, bookList)
                }

            },
            onError = {
                //loadState.value = LoadState.Fail(msg = it.message ?: "http exception")
            }
        )
    }

    fun checkVersion() {
        fetchLaunchBase(block = {
            val versionBase = RetrofitClient.testService
                .checkVersion("vivo", "1.7.0", 1)
            versionData.value = versionBase.dataConvert()
        })
    }


}