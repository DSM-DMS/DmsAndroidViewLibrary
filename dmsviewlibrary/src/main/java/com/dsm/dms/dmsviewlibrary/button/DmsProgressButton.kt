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
import androidx.appcompat.widget.AppCompatTextView


class DmsProgressButton(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
): AppCompatTextView(context, attrs, defStyleAttr) {

    var originText: String = ""

    val progressDrawable = CircularProgressDrawable(context).apply {
        setStyle(CircularProgressDrawable.LARGE)
        setColorSchemeColors(Color.WHITE)

        val size = (centerRadius + strokeWidth).toInt()
        setBounds(0, 0, size * 2, size)
    }

    val animatedDrawable = ContextCompat.getDrawable(context, R.drawable.animated_check)!!.apply {
        val size = resources.getDimensionPixelSize(R.dimen.check_size)
        setBounds(0, 0, size, size)
    }

    val errorColor = ContextCompat.getColor(context, R.color.error_red)

    private val callback = object : Drawable.Callback {
        override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        }
        override fun invalidateDrawable(who: Drawable) {
            invalidate()
        }
        override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        }
    }

    init {
        setPadding(30, 40, 30, 40)
        setBackgroundColor(ContextCompat.getColor(context, R.color.main_900))
        setTextColor(ContextCompat.getColor(context, R.color.black_100))
        gravity = Gravity.CENTER
        originText = text.toString()
    }

    fun onError(errorMessage: String) {
        progressDrawable.stop()

        isClickable = true
        isFocusable = true

        changeBackgroundColorAnimation(R.color.main_900, errorColor)
        setTextColor(ContextCompat.getColor(context, R.color.black_100))
        text = errorMessage

        Handler().postDelayed(
            {
                changeBackgroundColorAnimation(errorColor, R.color.main_900)
                text = originText
        }, 4000)
    }

    fun onLoad(loadingText: String) {
        setBackgroundColor(ContextCompat.getColor(context, R.color.main_900))

        isClickable = false
        isFocusable = false

        val drawableSpan = DmsDrawableSpan(progressDrawable, true, 20, 20)
        val spannableString = SpannableString("$loadingText ").apply {
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        text = spannableString

        progressDrawable.callback = callback
        progressDrawable.start()
    }

    fun onSuccess(successMessage: String) {
        progressDrawable.stop()

        isClickable = true
        isFocusable = true

        val drawableSpan = DmsDrawableSpan(animatedDrawable, true, 20, 20)
        val spannableString = SpannableString("$successMessage ").apply {
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        text = spannableString

        animatedDrawable.callback = callback
        if (animatedDrawable is Animatable) {
            animatedDrawable.start()
        }

        Handler().postDelayed(
            {
                text = originText
            }, 4000)
    }

    private fun changeBackgroundColorAnimation(colorFromId: Int, colorToId: Int) {
        val colorFrom = ContextCompat.getColor(context, colorFromId)
        val colorTo = ContextCompat.getColor(context, colorToId)
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)

        colorAnimation.duration = 400
        colorAnimation.addUpdateListener { animator -> setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()
    }

}
