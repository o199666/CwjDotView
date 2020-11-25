package com.cwj.oftenview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Color.red
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.dp.dotview.R


/**
 *  author : ChenWenJie
 *  email  : 1181620038@qq.com
 *  date   : 2020/11/19
 *  desc   : 带有红点的/或者带有指定图案的图片。View 等。先绘制一个小红点位于右上角
 */
class DotView : View {
    constructor(
        context: Context,
        number: Int = 0,
        bgcolor: Int = R.color.red,
        textColor: Int = R.color.white,
        dotRadioSize: Float = 20f,
        numberDotSize: Float = 50f,
        numberTextSize: Float = 35f,
        isOverstep: Boolean = true
    ) : super(context) {
        this.number = number
        this.bgcolor = bgcolor
        this.textColor = textColor
        this.dotRadioSize = dotRadioSize
        this.numberDotSize = numberDotSize
        this.numberTextSize = numberTextSize
        this.isOverstep = isOverstep
        initView()
    }

    fun initView() {
        when (number) {
            0 -> {
                initDotView()
            }
            else -> {
                initDotTextView()
            }

        }
        //设置当前view的位置
        setLayoutParams()

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {

    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {

    }

    var mDotMode = 0// 0 小点 1数字的点
    private var isOverstep = true// 是否越界,越界的值为 圆点的一半

    //传递过来的数值
    private var number: Int = 0

    //声明画笔
    private var dotVpaint = Paint() //红点的画笔
    private var numberPaint = Paint()//数字的画笔
    private var numberTextSize = 35f//文字大小
    private var dotRadioSize = 20f //红点默认大小

    //最终画布宽高大小
    private var mWidth = 0
    private var mHeight = 0

    init {
        Log.e("DotView", "init,${this.mDotMode}")

    }

    /**
     * 绘制主体
     *
     * */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        when (number) {
            0 -> {
                drawDotView(canvas)

            }
            else -> {
                drawDotTextView(canvas)
            }
        }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("DotView", "onMeasure")
        // 获取宽-测量规则的模式和大小
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        // 获取高-测量规则的模式和大小
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        // 设置wrap_content的默认宽 / 高值
        // 默认宽/高的设定并无固定依据,根据需要灵活设置
        // 类似TextView,ImageView等针对wrap_content均在onMeasure()对设置默认宽 / 高值有特殊处理,具体读者可以自行查看
        val mWidth = mWidth
        val mHeight = mHeight
        // 当布局参数设置为wrap_content时，设置默认值
        if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT && layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight)
            // 宽 / 高任意一个布局参数为= wrap_content时，都设置默认值
        } else if (layoutParams.width == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, heightSize)
        } else if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(widthSize, mHeight)
        }


    }


    fun initDotView() {
        mWidth = dotRadioSize.toInt()
        mHeight = dotRadioSize.toInt()
        //设置抗锯齿开关
        dotVpaint.isAntiAlias = true
        //设置绘制模式 STROKE:不填充，FILL:填充 ，FILL_AND_STROKE
        dotVpaint.style = Paint.Style.FILL
        //设置颜色
        dotVpaint.color = ContextCompat.getColor(context, bgcolor)
        //设置线条宽度
        dotVpaint.strokeWidth = 1f
//        可以设置点的形状，但这个方法并不是专门用来设置点的形状的，而是一个设置线条端点形状的方法。端点有圆头 (ROUND)、平头 (BUTT) 和方头 (SQUARE) 三种，具体会在下一节里面讲。
        dotVpaint.strokeCap = Paint.Cap.BUTT
    }

    //矩形大小
    var f1: RectF? = RectF()

    //小圆点的大小
    var littleDotSize = 20f

    //带有数字的圆点大小
    var numberDotSize = 50f
    var bgcolor = R.color.red
    var textColor = R.color.white


    /**
     * 初始化 带有文字的圆点
     */
    fun initDotTextView() {
        Log.e("DotView", "number$number")
        //双数
        when {
            //单数圆点
            number in 1..9 -> {
                f1 = RectF(0f, 0f, numberDotSize, numberDotSize)
                Log.e("DotView", "ttttttttttt$number")
                mWidth = f1!!.right.toInt()
                mHeight = f1!!.bottom.toInt()
            }
            //双数圆点
            number > 10 -> {
                Log.e("DotView", "rrrrrrrrrrr$number")
                f1 = RectF(0f, 0f, numberDotSize + numberDotSize / 2, numberDotSize)
                if (number > 99) {
                    //超过了99数量 就显示99
                    f1 = RectF(0f, 0f, numberDotSize + numberDotSize / 1.5f, numberDotSize)

                }
                mWidth = f1!!.right.toInt()
                mHeight = f1!!.bottom.toInt()
            }

        }

        //设置抗锯齿开关
        dotVpaint.isAntiAlias = true
        //设置绘制模式 STROKE:不填充，FILL:填充 ，FILL_AND_STROKE
        dotVpaint.style = Paint.Style.FILL
        //设置颜色
        dotVpaint.color = ContextCompat.getColor(context, bgcolor)
        dotVpaint.strokeCap = Paint.Cap.ROUND
        //设置背景大小
//        onMeasureDotTextView()

        //拿到字符串的宽度
        numberPaint.strokeCap = Paint.Cap.ROUND
        numberPaint.color = ContextCompat.getColor(context, textColor)
        //居中
        numberPaint.textSize = numberTextSize
        numberPaint.textAlign = Paint.Align.CENTER
    }

    /**
     *
     *
     */
    private fun drawDotView(canvas: Canvas?) {
        canvas?.drawCircle(dotRadioSize / 2, dotRadioSize / 2, dotRadioSize / 2, dotVpaint)
        canvas?.save()
    }

    /**
     * 绘制带有文字的圆点
     */
    private fun drawDotTextView(canvas: Canvas?) {
        canvas?.drawRoundRect(f1!!, 50f, 50f, dotVpaint)
        var num = number.toString()
        if (number > 99) {
            num = "99+"
        }
        val fontMetrics: Paint.FontMetrics = numberPaint.getFontMetrics()
        val top = fontMetrics.top //为基线到字体上边框的距离,即上图中的top

        val bottom = fontMetrics.bottom //为基线到字体下边框的距离,即上图中的bottom
        val baseLineY = (f1!!.centerY() - top / 2 - bottom / 2) //基线中间点的y轴计算公式

        //画文字。 -15代表 文字在绘制的时候，底线需要特殊处理。这里直接减文字的的一半
        canvas!!.drawText(num, f1!!.centerX(), baseLineY, numberPaint)
    }


    /**
     * 设置小红的依附指定的view
     */
    fun setTargetView(view: View) {
        if (parent != null) {
            (parent as ViewGroup).removeView(this)
        }
        if (view == null) {
            return
        }
        if (view.parent is FrameLayout) {
            (view.parent as FrameLayout).addView(this)
        } else if (view.parent is ViewGroup) {
            val parentContainer = view.parent as ViewGroup
            val groupIndex = parentContainer.indexOfChild(view)
            parentContainer.removeView(view)
            val fargme = FrameLayout(context)
            val parentLayoutParams = view.layoutParams
            fargme.layoutParams = parentLayoutParams //clipChildren
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            parentContainer.clipChildren = false
            parentContainer.addView(fargme, groupIndex, parentLayoutParams)
            fargme.addView(view)
            fargme.addView(this)

        } else {

        }
    }

    /**
     * 删除
     */
    fun remove(view: View) {
        if (parent != null) {
            (parent as ViewGroup).removeView(this)
        }
    }


    //设置this view 的位置
    private fun setLayoutParams() {
        if (layoutParams !is FrameLayout.LayoutParams) {
            val lParams = FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Gravity.RIGHT or Gravity.TOP
            )
            //这里设置溢出多少。 这个是根据圆点半径来的
            if (isOverstep) {
                lParams.topMargin = -mHeight / 2
                lParams.rightMargin = -mWidth / 2
            }

            layoutParams = lParams
        }
    }


}

 