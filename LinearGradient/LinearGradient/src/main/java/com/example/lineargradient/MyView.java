package com.example.lineargradient;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
 
 Paint BackPaint = new Paint();
 Context MyContext;

 public MyView(Context context) {
	 super(context);
	 init(context);
 }

 public MyView(Context context, AttributeSet attrs) {
	 super(context, attrs);
	 init(context);
 }

 public MyView(Context context, AttributeSet attrs, int defStyle) {
	 super(context, attrs, defStyle);
	 init(context);
 }
 
 private void init(Context ctx){
	  MyContext = ctx;
	  BackPaint.setStyle(Paint.Style.FILL);
	  BackPaint.setColor(Color.BLACK);
  
 }

 @Override
 protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	  int w = MeasureSpec.getSize(widthMeasureSpec);
	  int h = MeasureSpec.getSize(heightMeasureSpec);
	  setMeasuredDimension(w, h);
 }
 

 
 //Linear Gradient
 @Override
 protected void onDraw(Canvas canvas) {

  float w, h, cx, cy, radius;
  w = getWidth();
  h = getHeight();
  cx = w/2;
  cy = h/2;
  
  if(w > h){
   radius = h/4;
  }else{
   radius = w/4;
  }
  
  canvas.drawRect(0, 0, w, h, BackPaint);
  
  Paint MyPaint = new Paint();
  MyPaint.setStyle(Paint.Style.FILL);

  int shaderColor0 = Color.RED;
  int shaderColor1 = Color.BLUE;

  MyPaint.setAntiAlias(true);
  Shader linearGradientShader;

  linearGradientShader = new LinearGradient(
    0, 0, w, h,
    shaderColor1, shaderColor0, Shader.TileMode.MIRROR);

  MyPaint.setShader(linearGradientShader);
  canvas.drawRect(0, 0, w, h, MyPaint);

  linearGradientShader = new LinearGradient(
    cx, cy, cx+radius, cy+radius,
    shaderColor0, shaderColor1, Shader.TileMode.REPEAT);

  MyPaint.setShader(linearGradientShader);
  canvas.drawCircle(cx, cy, radius, MyPaint);
  
 };


//SweepGradient
/*
@Override
	protected void onDraw(Canvas canvas) {
	
		  float w, h, cx, cy, radius;
		  w = getWidth();
		  h = getHeight();
		  cx = w/2;
		  cy = h/2;
		  
		  if(w > h){
		   radius = h/4;
		  }else{
		   radius = w/4;
		  }
		  
		  canvas.drawRect(0, 0, w, h, BackPaint);
		  
		  Paint MyPaint = new Paint();
		  MyPaint.setStyle(Paint.Style.FILL);
		  
		  float shaderCx = cx;
		  float shaderCy = cy;
		  int shaderColor0 = Color.RED;
          int shaderColor2 = Color.GREEN;
		  int shaderColor1 = Color.BLUE;
		  
		  MyPaint.setAntiAlias(true);
		  MyPaint.setShader(new SweepGradient(
		        shaderCx, 
		        shaderCy, 
		        shaderColor0, 
		        shaderColor1));
		  
		  canvas.drawCircle(cx, cy, radius, MyPaint);
	};
*/

}