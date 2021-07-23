package com.example.cleanarchitecture.widget

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.R

class SpacesItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private var space: Int = context.resources.getDimensionPixelSize(R.dimen.dp_8)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val lastPosition = parent.adapter?.itemCount?.minus(1)

        if (position == 0) {
            outRect.top = space
            outRect.bottom = space / 2
        } else if (position == lastPosition) {
            outRect.top = space / 2
            outRect.bottom = space
        } else {
            outRect.top = space / 2
            outRect.bottom = space / 2
        }
    }
}
