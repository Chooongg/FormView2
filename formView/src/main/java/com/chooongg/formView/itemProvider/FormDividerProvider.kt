package com.chooongg.formView.itemProvider

import android.view.View
import android.view.ViewGroup
import com.chooongg.formView.holder.FormItemViewHolder
import com.chooongg.formView.item.AbstractFormItem
import com.chooongg.formView.style.AbstractFormStyle

class FormDividerProvider : AbstractFormItemProvider() {

    override fun onCreateViewHolder(style: AbstractFormStyle, parent: ViewGroup): View {
        return View(parent.context)
    }

    override fun onBindViewHolder(
        holder: FormItemViewHolder, item: AbstractFormItem<*>, enabled: Boolean
    ) {
    }
}