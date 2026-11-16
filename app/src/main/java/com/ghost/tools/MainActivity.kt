package com.ghost.hack

import android.app.Activity
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {
    private val background = Color.rgb(10, 18, 30)
    private val surface = Color.rgb(19, 31, 47)
    private val accent = Color.rgb(69, 220, 180)
    private val primaryText = Color.rgb(239, 245, 250)
    private val secondaryText = Color.rgb(158, 177, 195)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = background
        window.navigationBarColor = background

        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutDirection = View.LAYOUT_DIRECTION_RTL
            setPadding(dp(24), dp(28), dp(24), dp(28))
            setBackgroundColor(background)
        }

        content.addView(label("الأمان الرقمي الشخصي", accent, 12f, true))
        content.addView(text("كن أكثر أمانًا،\nكل يوم.", primaryText, 34f, true).apply {
            setPadding(0, dp(14), 0, dp(10))
        })
        content.addView(text(
            "عادات بسيطة يمكن أن تُحدث فرقًا كبيرًا في حماية حياتك الرقمية.",
            secondaryText,
            16f
        ).apply {
            setPadding(0, 0, 0, dp(26))
        })
        content.addView(label("قائمة تحقق سريعة", secondaryText, 12f, true).apply {
            setPadding(0, 0, 0, dp(12))
        })

        val tips = listOf(
            "استخدم كلمات مرور فريدة" to "استخدم مدير كلمات مرور لإنشاء كلمة مرور مختلفة لكل حساب وحفظها.",
            "فعّل التحقق بخطوتين" to "أضف خطوة تحقق ثانية إلى بريدك الإلكتروني وحساباتك المالية والاجتماعية.",
            "حدّث هاتفك باستمرار" to "ثبّت تحديثات النظام والتطبيقات للاستفادة من أحدث إصلاحات الأمان.",
            "راجع أذونات التطبيقات" to "تحقق مما يمكن لكل تطبيق الوصول إليه وأوقف الأذونات التي لا يحتاجها.",
            "تحقّق قبل الضغط" to "تأكد من الروابط والطلبات غير المتوقعة عبر قناة موثوقة قبل الاستجابة لها."
        )

        tips.forEachIndexed { index, (title, description) ->
            content.addView(tipCard(index + 1, title, description).apply {
                val bottom = if (index == tips.lastIndex) 0 else dp(10)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply { bottomMargin = bottom }
            })
        }

        content.addView(text(
            "خصوصيتك أولويتنا  |  يعمل دون اتصال",
            secondaryText,
            11f,
            true
        ).apply {
            gravity = Gravity.CENTER
            setPadding(0, dp(24), 0, 0)
        })

        val scrollView = ScrollView(this).apply {
            isFillViewport = true
            addView(content)
        }
        setContentView(scrollView)
    }

    private fun tipCard(number: Int, title: String, description: String): View {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutDirection = View.LAYOUT_DIRECTION_RTL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), dp(16), dp(16), dp(16))
            background = roundedBackground(surface, 18)
        }
        val badge = TextView(this).apply {
            text = number.toString().padStart(2, '0')
            setTextColor(accent)
            textSize = 13f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
            background = roundedBackground(Color.rgb(27, 55, 62), 12)
        }
        card.addView(badge, LinearLayout.LayoutParams(dp(42), dp(42)))

        val copy = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutDirection = View.LAYOUT_DIRECTION_RTL
            setPadding(dp(14), 0, 0, 0)
        }
        copy.addView(text(title, primaryText, 16f, true))
        copy.addView(text(description, secondaryText, 13f).apply {
            setPadding(0, dp(5), 0, 0)
        })
        card.addView(copy, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        return card
    }

    private fun label(value: String, color: Int, size: Float, bold: Boolean = false) =
        text(value, color, size, bold)

    private fun text(value: String, color: Int, size: Float, bold: Boolean = false) =
        TextView(this).apply {
            text = value
            setTextColor(color)
            textSize = size
            if (bold) typeface = Typeface.DEFAULT_BOLD
            setLineSpacing(dp(2).toFloat(), 1f)
        }

    private fun roundedBackground(color: Int, radiusDp: Int) = GradientDrawable().apply {
        setColor(color)
        cornerRadius = dp(radiusDp).toFloat()
    }

    private fun dp(value: Int) = (value * resources.displayMetrics.density).toInt()
}