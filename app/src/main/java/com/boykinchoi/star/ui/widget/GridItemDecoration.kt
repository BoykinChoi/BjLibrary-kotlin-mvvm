package com.boykinchoi.star.ui.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * GridLayout item之间平均相等间距
 * Created by BoykinChoi
 * on 2020/11/27
 **/
class GridItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // item position
        val column = position % spanCount // item column

        if (includeEdge) {
            // spacing - column * ((1f / spanCount) * spacing)
            outRect.left =
                (spacing - column.toFloat() * spacing.toFloat() * 1f / spanCount).toInt()
            // (column + 1) * ((1f / spanCount) * spacing)
            outRect.right = ((column + 1).toFloat() * spacing.toFloat() * 1f / spanCount).toInt()

            if (position < spanCount) { // top edge
                outRect.top = spacing
            }
            outRect.bottom = spacing // item bottom
        } else {
            // column * ((1f / spanCount) * spacing)
            // outRect.left = column * mSpacing / mSpanCount;
            outRect.left = (column.toFloat() * spacing.toFloat() * 1f / spanCount).toInt()
            // spacing - (column + 1) * ((1f / spanCount) * spacing)
            // outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount;
            outRect.right =
                (spacing - (column + 1).toFloat() * 1f * spacing.toFloat() / spanCount).toInt()
            if (position >= spanCount) {
                outRect.top = spacing // item top
            }
        }
    }
}