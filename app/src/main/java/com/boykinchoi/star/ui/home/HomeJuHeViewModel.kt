package com.boykinchoi.star.ui.home

import androidx.lifecycle.MutableLiveData
import com.boykinchoi.baselibrary.base.BaseViewModel
import com.boykinchoi.baselibrary.base.LoadState
import com.boykinchoi.star.api.RetrofitClient
import com.boykinchoi.star.api.juHeDataConvert
import com.boykinchoi.star.api.launch
import com.boykinchoi.star.bean.HistoryTodayBean
import kotlinx.coroutines.async

/**
 * 首页ViewModel2 使用聚合数据
 * Created by BoykinChoi
 * on 2021/5/12
 **/

class HomeJuHeViewModel : BaseViewModel() {
    var historyTodayData: MutableLiveData<MutableList<HistoryTodayBean>> = MutableLiveData()

    override fun getBaseData() {
        //not to do sth
    }

    fun getHistoryToadyData(date: String) {
        launch(
            {
                val resultData = async {
                    RetrofitClient.testService
                        .historyToday(date, "421c0804d1c95d80ba0843c958ac8fab")
                }
                historyTodayData.value = resultData.await().juHeDataConvert()

            },
            {
                loadState.value = LoadState.Fail(it.message ?: "http exception")
            }
        )
    }
}