package com.example.lesson_6_lukin.secondScreen

import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decorator(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        val adapter = parent.adapter
        val viewType = adapter?.getItemViewType(position)
        if (viewType == 1) {
            outRect.bottom = bottom
        }
        outRect.left = left
        outRect.right = right
        outRect.top = top
    }
}