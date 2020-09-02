package com.dsm.dms.dmsviewlibrary.button

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.dsm.dms.dmsviewlibrary.R
import android.animation.ValueAnimator
import android.animation.ArgbEvaluator
import android.graphics.drawable.Animatable
import android.os.Handler
import android.view.Gravity
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView


interface DmsProgressButtonFunc {

    var originText: String
    var isComeback: Boolean

    val progressDrawable: CircularProgressDrawable

    val animatedDrawable: Drawable
    val errorColor: Int

    val callback: Drawable.Callback

    fun onError(errorMessage: String)

    fun onLoad(loadingText: String)

    fun onSuccess(successMessage: String)

    fun changeBackgroundColorAnimation(colorFromId: Int, colorToId: Int)

}
