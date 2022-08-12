package com.boykinchoi.star.ui.book

import androidx.lifecycle.MutableLiveData
import com.boykinchoi.baselibrary.base.vm.BaseViewModel
import com.boykinchoi.baselibrary.network.ext.dataConvert
import com.boykinchoi.baselibrary.network.ext.fetchLaunch
import com.boykinchoi.baselibrary.network.ext.fetchLaunchBase
import com.boykinchoi.star.api.RetrofitClient
import com.boykinchoi.star.app.ValueConfig
import com.boykinchoi.star.bean.HomeDataBean
import com.boykinchoi.star.bean.VersionBean
import com.boykinchoi.star.room.RoomMan
import com.boykinchoi.star.room.entity.DbBook
import com.boykinchoi.star.room.entity.DbUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * BookViewModel
 * Created by BoykinChoi
 * on 2022/6/`2
 **/
class BookViewModel : BaseViewModel() {
    var homeData: MutableLiveData<HomeDataBean2> = MutableLiveData()
    var versionData: MutableLiveData<VersionBean> = MutableLiveData()

    override fun getBaseData() {
        fetchLaunch(
            block = {
                application?.let { app ->
                    val bookDbList = withContext(Dispatchers.IO) {
                        RoomMan.getDB(app).bookData().getAll()
                    }
                    if (bookDbList.isNullOrEmpty()) {
                        RetrofitClient.testService.let {
                            if (ValueConfig.pToken.isEmpty()) {
                                ValueConfig.pToken =
                                    async {
                                        it.login("18302015102", "c66666666")
                                    }.await().dataConvert()?.token.toString()
                            }
                            val userInfo = async { it.homeUserInfo2() }.await().dataConvert()
                            val bookList = async { it.homeBookList2() }.await().dataConvert()
                            homeData.value = HomeDataBean2(userInfo, bookList)

                            //写到本地数据库
                            RoomMan.getDB(app).userData().insert(userInfo)
                            if (bookList?.isNotEmpty() == true) {
                                // *list.toTypedArray 将MutableList 转换成 vararg 参数
                                RoomMan.getDB(app).bookData().insert(*bookList.toTypedArray())
                            }
                        }
                    } else {
                        val dbUser = RoomMan.getDB(app).userData().findByName("科不投詹不传").apply {
                            studentName = "来自数据库的${studentName}及数据"
                        }
                        homeData.value = HomeDataBean2(dbUser, bookDbList)
                    }
                }
            },
            onError = {
                //loadState.value = LoadState.Fail(msg = it.message ?: "http exception")
            }
        )
    }

    fun checkVersion() {
        fetchLaunchBase {
            val versionBase = RetrofitClient.testService
                .checkVersion("vivo", "1.7.0", 1)
            versionData.value = versionBase.dataConvert()
        }
    }

}