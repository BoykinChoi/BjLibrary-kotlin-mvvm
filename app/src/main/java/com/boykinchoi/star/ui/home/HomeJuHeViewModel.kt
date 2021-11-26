package com.boykinchoi.star.ui.home

import androidx.lifecycle.MutableLiveData
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.base.LoadState
import com.boykinchoi.baselibrary.network.ext.juHeDataConvert
import com.boykinchoi.baselibrary.network.ext.launch
import com.boykinchoi.star.api.RetrofitClient
import com.boykinchoi.star.bean.HistoryTodayBean
import kotlinx.coroutines.async
import me.jessyan.autosize.utils.LogUtils

/**
 * 首页ViewModel2 使用聚合数据
 * Created by BoykinChoi
 * on 2021/5/12
 **/

class HomeJuHeViewModel : BaseViewModel() {
    val historyTodayData by lazy { MutableLiveData<MutableList<HistoryTodayBean>>() }

    override fun getBaseData() {
        //not to do sth
    }

    fun getHistoryToadyData(date: String) {
        launch(
            block = {
                val resultData = async {
                    RetrofitClient.testService
                        .historyToday(date, "421c0804d1c95d80ba0843c958ac8fab")
                }
                val result = resultData.await().juHeDataConvert()
                if (result.isNullOrEmpty()) {
                    loadState.value = LoadState.EmptyData()
                }
                historyTodayData.value = result

            },
            onError = {
                //如有需要，具体请求具体处理请求异常
                LogUtils.e("errorMessage=${it.message}")
            }
        )
    }
}