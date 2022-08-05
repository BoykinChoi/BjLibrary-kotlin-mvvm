package com.boykinchoi.star.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in  2022/8/4 17:09
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(ActivityLifecycleObserver())
    }
}