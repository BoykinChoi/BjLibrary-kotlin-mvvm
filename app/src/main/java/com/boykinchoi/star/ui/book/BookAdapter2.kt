package com.boykinchoi.star.ui.book

import android.widget.ImageView
import com.boykinchoi.star.R
import com.boykinchoi.star.room.entity.DbBook
import com.boykinchoi.star.util.ImageUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Created by BoykinChoi
 * on 2022/8/12
 **/
class BookAdapter2 : BaseQuickAdapter<DbBook, BaseViewHolder>(R.layout.item_book) {
    override fun convert(holder: BaseViewHolder, item: DbBook) {
        //可用with（非内联函数） 简化
        with(item) {
            holder.setText(R.id.tv_title, bookChineseName)
            holder.setGone(R.id.iv_lock, status == 1)
            val headerView = holder.getView(R.id.iv_img) as ImageView
            ImageUtil.load(headerView, hdImgUrl)
        }
    }
}