package com.boykinchoi.star.ui.home

import com.boykinchoi.star.R
import com.boykinchoi.star.bean.HistoryTodayBean
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 历史上的今天列表适配器
 * Created by BoykinChoi
 * on 2021/5/12
 **/

class HistoryTodayAdapter :
    BaseQuickAdapter<HistoryTodayBean, BaseViewHolder>(R.layout.item_history_today) {
    override fun convert(holder: BaseViewHolder, item: HistoryTodayBean) {

        //可用with（非内联函数） 简化
        with(item) {
            holder.setText(R.id.tv_date, date)
            holder.setText(R.id.tv_title, title)

        }
    }
}