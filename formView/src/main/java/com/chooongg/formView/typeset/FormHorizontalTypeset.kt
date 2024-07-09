package com.chooongg.formView.typeset

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat
import com.chooongg.formView.FormManager
import com.chooongg.formView.FormUtils
import com.chooongg.formView.R
import com.chooongg.formView.enum.FormEmsMode
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.BaseForm
import com.chooongg.formView.style.AbstractFormStyle
import com.chooongg.formView.widget.FormMenuView
import com.google.android.material.button.MaterialButton

class FormHorizontalTypeset : AbstractFormTypeset() {

    override var emsMode: FormEmsMode = FormEmsMode(FormEmsMode.FIXED)

    override fun onCreateTypeset(style: AbstractFormStyle, parent: ViewGroup): ViewGroup {
        val layout = LinearLayoutCompat(parent.context).apply {
            id = R.id.formLayoutView
            orientation = LinearLayoutCompat.HORIZONTAL
        }
        layout.addView(MaterialButton(layout.context).apply {
            id = R.id.formNameView
            setTextAppearance(formTextAppearance(R.attr.formTextAppearanceName))
            insetTop = 0
            insetBottom = 0
            isClickable = false
            gravity = Gravity.NO_GRAVITY
            background = null
            minWidth = 0
            minHeight = 0
            minimumWidth = 0
            minimumHeight = 0
            iconSize = FormUtils.getFontRealHeight(this)
            iconPadding = (style.paddingInfo.startMedium + style.paddingInfo.endMedium) / 2
            setPaddingRelative(
                style.paddingInfo.startMedium,
                style.paddingInfo.topMedium,
                style.paddingInfo.endMedium,
                style.paddingInfo.bottomMedium
            )
        }, LinearLayoutCompat.LayoutParams(-2, -2))
        layout.addView(FormMenuView(layout.context, style).apply {
            id = R.id.formMenuView
        }, LinearLayoutCompat.LayoutParams(-2, -2))
        return layout
    }

    override fun onBindTypeset(holder: FormItemViewHolder, item: BaseForm<*>) {
        with(holder.getView<MaterialButton>(R.id.formNameView)) {
            iconGravity = item.iconGravity ?: nameIconGravity ?: holder.style.config.nameIconGravity
            val nameIcon = FormManager.parseIcon(context, item.icon)
            if (nameIcon != null) {
                icon = nameIcon
                iconTint = item.iconTint?.invoke(context) ?: textColors
            } else icon = null
            text = holder.style.config.nameFormatter.format(context, item)
            configNameView(holder, this)
        }
        configMenuView(holder, item, holder.getView(R.id.formMenuView))
    }

    override fun configTypesetAddChildView(layoutView: ViewGroup, childView: View) {
        layoutView.addView(childView, 1, LinearLayoutCompat.LayoutParams(0, -2).apply {
            weight = 1f
        })
    }
}