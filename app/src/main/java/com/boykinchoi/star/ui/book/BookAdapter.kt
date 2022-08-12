package com.boykinchoi.star.ui.book

import android.widget.ImageView
import com.boykinchoi.star.R
import com.boykinchoi.star.bean.BookBean
import com.boykinchoi.star.util.ImageUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Created by BoykinChoi
 * on 2020/11/27
 **/
class BookAdapter : BaseQuickAdapter<BookBean, BaseViewHolder>(R.layout.item_book) {
    override fun convert(holder: BaseViewHolder, item: BookBean) {
//        holder.setText(R.id.tv_title, item.bookChineseName)
//        holder.setGone(R.id.iv_lock, item.status == 1)
//        val headerView = holder.getView(R.id.iv_img) as ImageView
//        ImageUtil.load(headerView,item.hdImgUrl)

        //可用with（非内联函数） 简化
        with(item) {
            holder.setText(R.id.tv_title, bookChineseName)
            holder.setGone(R.id.iv_lock, status == 1)
            val headerView = holder.getView(R.id.iv_img) as ImageView
            ImageUtil.load(headerView, hdImgUrl)
        }
        //这里item不可以null所以没必要用run
//        item?.run {
//            holder.setText(R.id.tv_title, bookChineseName)
//            holder.setGone(R.id.iv_lock, status == 1)
//            val headerView = holder.getView(R.id.iv_img) as ImageView
//            ImageUtil.load(headerView, hdImgUrl)
//        }
    }
}