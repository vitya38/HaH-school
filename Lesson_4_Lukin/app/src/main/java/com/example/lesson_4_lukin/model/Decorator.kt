package com.example.lesson_4_lukin.model

import android.graphics.Rect
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Decorator(
    val left: Int = 0,
    val top: Int = 0,
    val right: Int = 0,
    val bottom: Int = 0,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        val adapter = parent.adapter
        val viewType = adapter?.getItemViewType(position)
        if (viewType == 1) {
            if (position % 2 == 0) {
                outRect.left = left
                outRect.right = right
                outRect.top = top
            } else {
                outRect.right = right
                outRect.top = top
            }
        } else {
            outRect.left = left
            outRect.right = right
            outRect.top = top
        }
    }
}