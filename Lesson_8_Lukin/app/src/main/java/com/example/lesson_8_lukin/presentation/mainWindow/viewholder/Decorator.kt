package com.example.lesson_8_lukin.presentation.mainWindow.viewholder

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decorator(
    private val left: Int,
    private val top: Int,
    private val right: Int,
    private val bottom: Int,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val count = parent.adapter?.itemCount
        if (position == RecyclerView.NO_POSITION) {
            return
        }
        if (position % 2 == 0) {
            outRect.left = left
            outRect.right = right
            outRect.top = top
        } else {
            outRect.right = right
            outRect.top = top
        }
        if (count != null) {
            if (count % 2 == 0) {
                if (position in count - 1..count) {
                    outRect.bottom = bottom
                }
            } else {
                if (position == count - 1) {
                    outRect.bottom = bottom
                }
            }
        }
    }
}