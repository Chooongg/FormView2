package com.chooongg.formView.item

import com.chooongg.formView.delegation.FormMenuImpl
import com.chooongg.formView.delegation.IFormMenu
import com.chooongg.formView.itemProvider.AbstractFormItemProvider
import com.chooongg.formView.itemProvider.FormTextProvider
import com.chooongg.formView.part.AbstractFormPart
import kotlin.reflect.KClass

class FormText(
    name: Any?, field: String?, content: Any?
) : BaseForm<Any>(name, field, content), IFormMenu by FormMenuImpl() {

    override fun getProvider(part: AbstractFormPart<*>): KClass<out AbstractFormItemProvider> {
        return FormTextProvider::class
    }
}