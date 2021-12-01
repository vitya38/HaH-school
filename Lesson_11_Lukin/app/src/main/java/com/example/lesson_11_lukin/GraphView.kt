package com.example.lesson_11_lukin

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
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

    private val day = 1000 * 60 * 60 * 24L
    private var textColor = Color.GREEN
    private var rowColor = Color.YELLOW
    private var rowCount = 1
    private val formatter = SimpleDateFormat("MMM.dd", Locale.getDefault())
    private var today = Date().time
    private var animationHeight = 0f
    private var ratioOfProportion = 0f
    private var myHeight = 0f
    private var rows = mutableListOf<Data>()
    private var animation: ValueAnimator? = null


    private val rowPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = rowColor
            strokeWidth = resources.getDimension(R.dimen.row_width)
        }
    }

    private val datePaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = textColor
            textAlign = Paint.Align.CENTER
            textSize = resources.getDimensionPixelSize(R.dimen.text).toFloat()
        }
    }

    private val rowTextPaint by lazy {
        Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = rowColor
            textAlign = Paint.Align.CENTER
            textSize = resources.getDimensionPixelSize(R.dimen.text).toFloat()
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (rows.isNotEmpty()) {
            val maxValue = maxValue()
            val h = 600
            ratioOfProportion = (600f - 35 - 35) / maxValue
            setMeasuredDimension(width, h)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val row = rowCount + 1
        for (i in 1 until row) {
            myHeight = rows[i - 1].value * animationHeight * ratioOfProportion
            val centerX = width * i / row.toFloat()
            val offset = resources.getDimensionPixelOffset(R.dimen.offset)
            val round = resources.getDimensionPixelOffset(R.dimen.round)
            canvas.drawRoundRect(
                centerX - offset,
                height - myHeight - 35f,
                centerX + offset,
                height - 35f,
                round * 1f,
                round * 1f,
                rowPaint
            )
            canvas.drawText(rows[i - 1].title, centerX, height - 5f, datePaint)
            canvas.drawText(rows[i - 1].value.toString(), centerX, height - myHeight - 40, rowTextPaint)
        }
    }

    fun myAnimation() {
        if (animation != null) {
            animation?.cancel()
            animation = null
            animationHeight = 0f
            invalidate()
        } else {
            animation = ValueAnimator.ofFloat(0f, 1f).apply {
                duration = 1000
                addUpdateListener { animation ->
                    animationHeight = animation.animatedValue as Float
                    invalidate()
                }
                start()
            }
            return
        }
    }

    fun setData(count: Int) {
        rowCount = count
        for (i in 0 until rowCount) {
            rows.add(Data(formatter.format(today), (0..100).random()))
            today -= day
        }
        rows.reverse()
    }

    private fun maxValue(): Int {
        var max = 0
        for (i in 0 until rowCount) {
            if (max < rows[i].value) {
                max = rows[i].value
            }
        }
        return max
    }

}