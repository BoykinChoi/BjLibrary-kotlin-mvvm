package com.boykinchoi.baselibrary.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/7/21 10:12
 */
inline fun <reified T> Context.start() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T> Activity.startForResult(requestCode: Int) {
    startActivityForResult(Intent(this, T::class.java), requestCode)
}
