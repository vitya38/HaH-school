package com.example.lesson_11_lukin

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GraphView : View {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private val twoDp = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        2.toFloat(), resources.displayMetrics
    )

    private val day = 1000 * 60 * 60 * 24L
    private var textColor = Color.GREEN
    private var rowColor = Color.YELLOW
    private var rowCount = 9
    private var rowValues = listOf(10, 20, 30, 40, 50, 60, 70, 80, 90)
    private val formatter = SimpleDateFormat("dd.MM", Locale.getDefault())
    private val today = Date()
    private var animationFlag = false
    private var myHeight = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        2.toFloat(), resources.displayMetrics
    )
    private val gestureListener: GestureDetector.OnGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }
    }
    private val gestureDetector = GestureDetector(context, gestureListener)
    private var animation: ValueAnimator? = null


    private val rowPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = rowColor
            val dpSize = 4
            val scaledSizeInPixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpSize.toFloat(), resources.displayMetrics
            )
            strokeWidth = scaledSizeInPixels
        }
    }

    private val textPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            val spSize = 12
            color = textColor
            val scaledSizeInPixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spSize.toFloat(), resources.displayMetrics
            )
            textAlign = Paint.Align.CENTER
            textSize = scaledSizeInPixels
        }
    }

    private val rowTextPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            val spSize = 12
            color = rowColor
            val scaledSizeInPixels = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP,
                spSize.toFloat(), resources.displayMetrics
            )
            textAlign = Paint.Align.CENTER
            textSize = scaledSizeInPixels
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun init(context: Context, attrs: AttributeSet?) {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.GraphView, 0, 0)
        try {
            textColor = a.getColor(R.styleable.GraphView_textColor, Color.BLACK)
            rowColor = a.getColor(R.styleable.GraphView_rowColor, Color.RED)
        } finally {
            a.recycle()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rows = rowCount + 1
        var today = today.time.minus(day * (rowCount - 1))
        for (i in 1 until rows) {
            myHeight = if (animationFlag) {
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    rowValues[i - 1].toFloat(), resources.displayMetrics
                )
            } else {
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    2.toFloat(), resources.displayMetrics
                )
            }
            val centerX = width * i / rows.toFloat()
            val centerY = (height / 2.0f)
            canvas.drawRoundRect(
                centerX - twoDp,
                centerY - myHeight,
                centerX + twoDp,
                centerY,
                twoDp * 2,
                twoDp * 2,
                rowPaint
            )
            canvas.drawText(formatter.format(today), centerX, centerY + 40, textPaint)
            canvas.drawText(rowValues[i - 1].toString(), centerX, centerY - myHeight - 20, rowTextPaint)
            today += day
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touch = true
        return if (touch) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> true
                MotionEvent.ACTION_UP -> {
                    myAnimation()
                    true
                }
                else -> false
            }
        } else {
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun myAnimation() {
        if (animation != null) {
            animation?.cancel()
            animation = null
            animationFlag = false
            invalidate()
        } else {
            animation = ValueAnimator.ofInt(0, 1).apply {
                duration = 1000
                addUpdateListener {
                    animationFlag = true
                    invalidate()
                }
                start()
            }
            return
        }
    }

    fun setData(count: Int) {
        rowCount = count
        rowValues = List(count) { (0..100).random() }
    }

}