package com.chooongg.formView.decoration

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMarginsRelative
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.chooongg.formView.FormView
import com.chooongg.formView.data.FormBoundary
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.part.AbstractFormPart

class FormItemDecoration : ItemDecoration() {

    @Suppress("LocalVariableName")
    @SuppressLint("SwitchIntDef")
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        if (parent !is FormView) return
        val holder = parent.getChildViewHolder(view) as? FormItemViewHolder ?: return
        if (holder.absoluteAdapterPosition == RecyclerView.NO_POSITION) return
        val adapter = parent.adapter ?: return
        val pair = adapter.data.getWrappedAdapterAndPosition(holder.absoluteAdapterPosition)
        if (pair.first !is AbstractFormPart<*>) return
        val item = adapter.getItem(holder.absoluteAdapterPosition) ?: return
        var _marginStart = 0
        var _marginTop = 0
        var _marginEnd = 0
        var _marginBottom = 0
        var _paddingStart = 0
        var _paddingTop = 0
        var _paddingEnd = 0
        var _paddingBottom = 0
        val start = holder.style.paddingInfo.start - holder.style.paddingInfo.startMedium
        val end = holder.style.paddingInfo.end - holder.style.paddingInfo.endMedium
        when (item.boundary.start) {
            FormBoundary.GLOBAL -> {
                _paddingStart =
                    if (item.fillEdgesPadding && holder.style.isFillHorizontalPadding()) start else 0
                _marginStart = if (holder.style.isFillHorizontalMargin()) 0 else -start
            }

            FormBoundary.MIDDLE -> {
                _paddingStart =
                    if (item.fillEdgesPadding && holder.style.isFillHorizontalPadding()) start else 0
                _marginStart = if (holder.style.config.isIndependentItem) {
                    (start + end) / item.columnCount * item.columnIndex
                } else holder.style.marginInfo.startMedium - if (holder.style.isFillHorizontalMargin()) 0 else start
            }
        }
        when (item.boundary.end) {
            FormBoundary.GLOBAL -> {
                _paddingEnd =
                    if (item.fillEdgesPadding && holder.style.isFillHorizontalPadding()) end else 0
                _marginEnd = if (holder.style.isFillHorizontalMargin()) 0 else -end
            }

            FormBoundary.MIDDLE -> {
                _paddingEnd =
                    if (item.fillEdgesPadding && holder.style.isFillHorizontalPadding()) end else 0
                _marginEnd = if (holder.style.config.isIndependentItem) {
                    (start + end) / item.columnCount * (item.columnCount - 1 - item.columnIndex)
                } else holder.style.marginInfo.endMedium - if (holder.style.isFillHorizontalMargin()) 0 else end
            }
        }
        val top = holder.style.paddingInfo.top - holder.style.paddingInfo.topMedium
        when (item.boundary.top) {
            FormBoundary.GLOBAL -> {
                _paddingTop =
                    if (item.fillEdgesPadding && holder.style.isFillVerticalPadding()) top else 0
                _marginTop = if (holder.style.isFillVerticalMargin()) 0 else -top
            }

            FormBoundary.MIDDLE -> {
                _paddingTop =
                    if (item.fillEdgesPadding && holder.style.isFillVerticalPadding()) top else 0
                _marginTop =
                    if (holder.style.isFillVerticalMargin()) holder.style.marginInfo.topMedium else 0
            }
        }
        val bottom = holder.style.paddingInfo.bottom - holder.style.paddingInfo.bottomMedium
        when (item.boundary.bottom) {
            FormBoundary.GLOBAL -> {
                _paddingBottom =
                    if (item.fillEdgesPadding && holder.style.isFillVerticalPadding()) bottom else 0
                _marginBottom = if (holder.style.isFillVerticalMargin()) 0 else -bottom
            }

            FormBoundary.MIDDLE -> {
                _paddingBottom =
                    if (item.fillEdgesPadding && holder.style.isFillVerticalPadding()) bottom else 0
                _marginBottom =
                    if (holder.style.isFillVerticalMargin()) holder.style.marginInfo.bottomMedium else 0
            }
        }
        holder.itemView.setPaddingRelative(_paddingStart, _paddingTop, _paddingEnd, _paddingBottom)
        holder.itemView.updateLayoutParams<MarginLayoutParams> {
            marginStart
            updateMarginsRelative(_marginStart, _marginTop, _marginEnd, _marginBottom)
            outRect.top = if (_marginTop < 0) _marginTop else 0
            outRect.bottom = if (_marginBottom < 0) _marginBottom else 0
        }
    }
}