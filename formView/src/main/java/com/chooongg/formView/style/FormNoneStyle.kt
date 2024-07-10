package com.chooongg.formView.style

import com.chooongg.formView.config.AbstractFormConfig
import com.chooongg.formView.config.FormNoneConfig

class FormNoneStyle(config: AbstractFormConfig = FormNoneConfig()) :
    AbstractFormStyle(config) {
    override fun isDecorateNoneItem(): Boolean = false
    override fun isAlignmentToEdge(): Boolean = true
}