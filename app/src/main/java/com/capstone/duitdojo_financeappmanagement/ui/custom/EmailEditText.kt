package com.capstone.duitdojo_financeappmanagement.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.util.TypedValue
import androidx.appcompat.R.*
import com.capstone.duitdojo_financeappmanagement.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class EmailEditText : TextInputEditText {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        hint = context.getString(R.string.email)
        error = null
        textError()
    }

    private fun textError() {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attr.colorError, typedValue, true)

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val textInputLayout = parent.parent as TextInputLayout
                if (!isValidEmail(p0.toString())) {
                    textInputLayout.error = context.getString(R.string.email_eror_text)
                    textInputLayout.errorIconDrawable = null
                    val errorColor = ColorStateList.valueOf(typedValue.data)
                    textInputLayout.boxStrokeErrorColor = errorColor
                } else {
                    textInputLayout.error = null
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}