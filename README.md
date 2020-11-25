# CwjDotView
1.0消息小红点，支持数字显示。 默认右上角，可以选择是否越界显示，字体颜色，背景颜色等任意修改
使用如下
    
   ![](https://github.com/o199666/CwjDotView/blob/master/app/img/img1.jpg)  



Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.o199666:CwjDotView:Tag'
	}


代码：kotlin 
 
 
	
	 var dot4 = DotView(
		    context = this,//当前
		    number = 88,//数字 如果不写/0 就是小圆点。
		    bgcolor = R.color.red,//背景色
		    textColor = R.color.white,//字体颜色
		    dotRadioSize = 20f,//不带数字的圆点的大小
		    numberDotSize = 50f,//带有数字的圆点的 大小
		    numberTextSize = 35f,//文字大小。
		    isOverstep = false//是否越界 越界默认圆心为准
		)
		//设置指定view之前
		dot4.setTargetView(img1)
		//清除       
		dot4.remove(img1)
        
        
        
        
        
        
