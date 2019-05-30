package com.instacart.android.challenges

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class DividerDecoration(private val color: Int, private val height: Int, var rect: Rect) : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val drawable = ColorDrawable(color)
        val childCount = parent.childCount
        for (i in 0 until childCount - 1) {
            val view = parent.getChildAt(i)
            val params = view.layoutParams as RecyclerView.LayoutParams
            val left = params.leftMargin + rect.left
            val top = view.bottom + params.bottomMargin
            val bottom = top + height
            drawable.setBounds(left, top, view.right, bottom)
            drawable.draw(c)
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val adapterPosition = parent.getChildAdapterPosition(view)
        if (adapterPosition < 0) {
            return
        }
        outRect.bottom += height
    }
}