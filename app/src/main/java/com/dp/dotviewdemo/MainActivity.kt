package com.dp.dotviewdemo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.cwj.oftenview.view.DotView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv1 = findViewById<TextView>(R.id.tv1)
        var tv2 = findViewById<TextView>(R.id.tv2)
        var img = findViewById<ImageView>(R.id.img)
        var img1 = findViewById<ImageView>(R.id.img1)
        var ll = findViewById<LinearLayout>(R.id.ll)
        var btn = findViewById<Button>(R.id.btn)
        var btn1 = findViewById<Button>(R.id.btn1)
        var ll_btn = findViewById<TextView>(R.id.ll_tv)
        var dot1 = DotView(this)
        dot1.setTargetView(tv1)

        var dot2 = DotView(
            this,//当前
            12,//数字
            R.color.teal_200,//背景色
            R.color.white,//字体颜色
            20f,//不带数字的圆点的大小
            50f,//带有数字的圆点的 大小
            35f,
            true//是否越界 越界默认圆心为准
        )
        dot2.setTargetView(tv2)


        var dot3 = DotView(
            this,//当前
            999//数字
        )
        dot3.setTargetView(img)


        var dot4 = DotView(
            context = this,//当前
            number = 88,//数字 如果不写/0 就是小圆点。
            bgcolor = R.color.purple_500,//背景色
            textColor = R.color.white,//字体颜色
            dotRadioSize = 20f,//不带数字的圆点的大小
            numberDotSize = 50f,//带有数字的圆点的 大小
            numberTextSize = 35f,//文字大小。
            isOverstep = false//是否越界 越界默认圆心为准
        )
        dot4.setTargetView(img1)
        var dot5 = DotView(
            context = this,//当前
            bgcolor = R.color.purple_200,
            dotRadioSize = 30f,
            isOverstep = false//是否越界 越界默认圆心为准
        )
        dot5.setTargetView(ll)
        var dot6 = DotView(this)
        dot6.setTargetView(ll_btn)
        //清除
        btn.setOnClickListener {
            dot1.remove(tv1)
            dot2.remove(tv2)
            dot3.remove(img)
            dot4.remove(img1)
            dot5.remove(ll)
            dot6.remove(ll_btn)

        }

        btn1.setOnClickListener {
            dot6.setTargetView(ll_btn)
            dot5.setTargetView(ll)
            dot4.setTargetView(img1)
            dot3.setTargetView(img)
            dot2.setTargetView(tv2)
            dot1.setTargetView(tv1)


        }

    }
}