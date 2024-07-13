package com.chooongg.formView.style

class FormNoneStyle : AbstractFormStyle() {

    @Deprecated("Disable")
    override var isIndependentItem: Boolean = false

    override fun isDecorateNoneItem(): Boolean = false
    override fun isFillVerticalMargin() = false
    override fun isFillHorizontalMargin() = false
    override fun isFillVerticalPadding(): Boolean = false
    override fun isFillHorizontalPadding(): Boolean = false
}