package com.boykinchoi.star.bean

import android.content.Context
import com.boykinchoi.baselibrary.base.BaseApplication
import com.boykinchoi.star.app.Constants


/**
 * Created by BoykinChoi
 * on 2021/1/29
 */
 data class UserBean(val token: String) {
    companion object {
        fun saveToken(token: String?) {
            val context = BaseApplication.instance
            val sp = context?.getSharedPreferences(
                context.getPackageName() + "_preferences",
                Context.MODE_PRIVATE
            )
            val editor = sp?.edit()
            editor?.putString(Constants.USER_TOKEN, token)
            editor?.commit()
        }

    }

}

