package com.boykinchoi.star.app

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
//import me.jessyan.autosize.utils.LogUtils

/**
 * @Author: 蔡佰健
 * @Description:
 * - 在 App 启动时，系统会在 App 的主进程中自动实例化你声明的这个 ContentProvider，
 * - 并调用它的 onCreate 方法，执行时机比 Application#onCreate 还靠前，可以做一些初始化的工作
 * @Date:Create：in  2022/7/14 17:52
 *
 */
class MyInitProvider : ContentProvider() {
    override fun onCreate(): Boolean {
//        LogUtils.d("fucking InitProvider.hahaha")
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? = null

    override fun getType(uri: Uri): String? = null

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int = 0

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int = 0
}