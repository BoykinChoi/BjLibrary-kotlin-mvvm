package com.boykinchoi.read.ui.home

import android.widget.ImageView
import com.boykinchoi.read.R
import com.boykinchoi.read.bean.BookBean
import com.boykinchoi.read.util.ImageUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Created by BoykinChoi
 * on 2020/11/27
 **/
class BookAdapter : BaseQuickAdapter<BookBean, BaseViewHolder>(R.layout.item_book) {
    override fun convert(holder: BaseViewHolder, item: BookBean) {
        holder.setText(R.id.tv_title, item.bookChineseName)
        holder.setGone(R.id.iv_lock, item.status == 1)
        val headerView = holder.getView(R.id.iv_img) as ImageView
        ImageUtil.load(headerView,item.hdImgUrl)
    }
}