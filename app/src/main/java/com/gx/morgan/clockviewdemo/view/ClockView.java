package com.gx.morgan.clockviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.gx.morgan.clockviewdemo.R;
import com.gx.morgan.clockviewdemo.util.ViewUtil;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * description：时钟
 * <br>author：caowugao
 * <br>time：2018/3/11 14:19
 */
public class ClockView extends CustomView {


    private Paint paint;
    private Paint hourTextPaint;
    private Rect hourTextBound;
    private Path path;

    private float outLineWidth;//外圆线的宽度
    private int outLineColor=Color.RED;//外圆颜色
    private int scaleLineColor=Color.RED;//刻度线颜色
    private int hourPointerColor=Color.BLUE;//时针颜色
    private int minutePointerColor=Color.BLACK;//分针颜色
    private int secondPointerColor=Color.BLUE;//秒针颜色
    private float centerCircleRadius=20;//中心圆点半径
    private int centerCircleColor=Color.RED;//中心圆点颜色
    private float outLineScaleLinePadding;//外部圆到刻度线的距离
    private float thickScaleLineLength;//时的刻度线的长度，比如1时，2时，3时...12时
    private float normalScaleLineLength;//分的刻度线长度
    private float hourTextSize=20;//3、6、9、12的字体大小
    private int hourTextColor=Color.GREEN;//3、6、9、12的颜色

    public float getOutLineWidth() {
        return outLineWidth;
    }

    public void setOutLineWidth(float outLineWidth) {
        this.outLineWidth = outLineWidth;
        invalidate();
    }

    public int getOutLineColor() {
        return outLineColor;
    }

    public void setOutLineColor(int outLineColor) {
        this.outLineColor = outLineColor;
        invalidate();
    }

    public int getScaleLineColor() {
        return scaleLineColor;
    }

    public void setScaleLineColor(int scaleLineColor) {
        this.scaleLineColor = scaleLineColor;
        invalidate();
    }

    public int getHourPointerColor() {
        return hourPointerColor;
    }

    public void setHourPointerColor(int hourPointerColor) {
        this.hourPointerColor = hourPointerColor;
        invalidate();
    }

    public int getMinutePointerColor() {
        return minutePointerColor;
    }

    public void setMinutePointerColor(int minutePointerColor) {
        this.minutePointerColor = minutePointerColor;
        invalidate();
    }

    public int getSecondPointerColor() {
        return secondPointerColor;
    }

    public void setSecondPointerColor(int secondPointerColor) {
        this.secondPointerColor = secondPointerColor;
        invalidate();
    }

    public float getCenterCircleRadius() {
        return centerCircleRadius;
    }

    public void setCenterCircleRadius(float centerCircleRadius) {
        this.centerCircleRadius = centerCircleRadius;
        invalidate();
    }

    public int getCenterCircleColor() {
        return centerCircleColor;
    }

    public void setCenterCircleColor(int centerCircleColor) {
        this.centerCircleColor = centerCircleColor;
        invalidate();
    }

    public float getOutLineScaleLinePadding() {
        return outLineScaleLinePadding;
    }

    public void setOutLineScaleLinePadding(float outLineScaleLinePadding) {
        this.outLineScaleLinePadding = outLineScaleLinePadding;
        invalidate();
    }

    public float getThickScaleLineLength() {
        return thickScaleLineLength;
    }

    public void setThickScaleLineLength(float thickScaleLineLength) {
        this.thickScaleLineLength = thickScaleLineLength;
        invalidate();
    }

    public float getHourTextSize() {
        return hourTextSize;
    }

    public void setHourTextSize(float hourTextSize) {
        this.hourTextSize = hourTextSize;
        invalidate();
    }

    public int getHourTextColor() {
        return hourTextColor;
    }

    public void setHourTextColor(int hourTextColor) {
        this.hourTextColor = hourTextColor;
        invalidate();
    }

    public ClockView(Context context) {
        super(context);
        init(context, null);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        hourTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


        hourTextBound = new Rect();
        path = new Path();

        obtainStyledAttrs(context,attrs);

    }

    private void obtainStyledAttrs(Context context,AttributeSet attrs) {
        TypedArray typedArray = null;
        if (attrs != null) {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClockView);
        }
        outLineWidth= ViewUtil.optPixelSize(typedArray,R.styleable.ClockView_outLineWidth,ViewUtil.dp2px(context,2));
        outLineColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_outLineColor,outLineColor);
        scaleLineColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_scaleLineColor,scaleLineColor);
        hourPointerColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_hourPointerColor,hourPointerColor);
        minutePointerColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_minutePointerColor,minutePointerColor);
        secondPointerColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_secondPointerColor,secondPointerColor);
        centerCircleRadius=ViewUtil.optPixelSize(typedArray,R.styleable.ClockView_centerCircleRadius,ViewUtil.dp2px(context,5));
        centerCircleColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_centerCircleColor,centerCircleColor);
        outLineScaleLinePadding=ViewUtil.optPixelSize(typedArray,R.styleable.ClockView_outLineScaleLinePadding,ViewUtil.dp2px(context,8));
        thickScaleLineLength=ViewUtil.optPixelSize(typedArray,R.styleable.ClockView_thickScaleLineLength,ViewUtil.dp2px(context,12));
        normalScaleLineLength=ViewUtil.optPixelSize(typedArray,R.styleable.ClockView_normalScaleLineLength,ViewUtil.dp2px(context,10));
        hourTextSize=ViewUtil.optPixelSize(typedArray,R.styleable.ClockView_hourTextSize,ViewUtil.sp(context,20));
        hourTextColor=ViewUtil.optColor(typedArray,R.styleable.ClockView_hourTextColor,hourTextColor);


    }


    @Override
    protected int contentWidth(int widthMeasureSpec) {
        return (int) ViewUtil.dp2px(getContext(), 100);
    }

    @Override
    protected int contentHeight(int heightMeasureSpec) {
        return (int) ViewUtil.dp2px(getContext(), 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawOuntLine(canvas);//画外部圆

        drawScaleLine(canvas);//画刻度线

        drawScaleLineNumber(canvas);//画小时

        drawPointer(canvas);//画指针

    }


    private void drawPointer(Canvas canvas) {


        Calendar  calendar = Calendar.getInstance();
        int hour = calendar.get(GregorianCalendar.HOUR);
        int minute = calendar.get(GregorianCalendar.MINUTE);
        int second = calendar.get(GregorianCalendar.SECOND);

        float cx = getWidth() / 2;
        float cy = cx;

        drawHourPointer(canvas,hour, minute,second, cx, cy);
        drawMinutePointer(canvas, minute, cx, cy);
        drawSecondPointer(canvas, second, cx, cy);


        paint.setColor(Color.RED);
        canvas.drawCircle(cx, cy, centerCircleRadius, paint);

        postInvalidateDelayed(1000);//每1秒钟重绘一次
    }

    private void drawHourPointer(Canvas canvas, int hour, int minute, int second, float cx, float cy) {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth((float) (centerCircleRadius*0.9));//时针宽度默认为中心圆点半径的0.9倍
        paint.setColor(hourPointerColor);
        canvas.save();
        float degree=((float)minute/60+hour)*30;//画时针时画布应该旋转的度数
        canvas.rotate(degree,cx,cy);

        float x1 = cx;
        float y1 = outLineWidth+ outLineScaleLinePadding +thickScaleLineLength+2*thickScaleLineLength;//外部圆宽度+间距+时刻度线的长度+ 2倍时刻度线长度的间距

        float x2 = cx ;
        float y2 = cy;

        canvas.drawLine(x1,y1,x2,y2,paint);

        canvas.restore();
    }

    private void drawMinutePointer(Canvas canvas, int minute, float cx, float cy) {

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth((float) (centerCircleRadius*0.9));//分针宽度默认为中心圆点半径的0.9倍
        paint.setColor(minutePointerColor);
        canvas.save();

        float degree=((float)minute)/60*360;
        canvas.rotate(degree,cx,cy);

        float x1 = cx;
        float y1 = outLineWidth+ outLineScaleLinePadding +thickScaleLineLength;//外部圆宽度+间距+时刻度线的长度

        float x2 = cx ;
        float y2 = cy;

        canvas.drawLine(x1,y1,x2,y2,paint);

        canvas.restore();
    }

    private void drawSecondPointer(Canvas canvas, float second, float cx, float cy) {

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(secondPointerColor);

        canvas.save();
        float degree= second /60*360;//画秒针时画布应该旋转的度数
        canvas.rotate(degree,cx,cy);
        float x1 = cx;
        float y1 = outLineWidth+ outLineScaleLinePadding;
        float x2 = cx-centerCircleRadius/2 ;
        float y2 = cy+4*centerCircleRadius;
        float x3 = cx+centerCircleRadius/2;
        float y3 = y2;
        path.reset();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.close();
        canvas.drawPath(path, paint);
        canvas.restore();
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void drawScaleLineNumber(Canvas canvas) {

        String hour9 = "9";
        String hour12 = "12";
        String hour3 = "3";
        String hour6 = "6";

        hourTextPaint.setTextSize(hourTextSize);
        hourTextPaint.setColor(hourTextColor);
        //画9
        hourTextPaint.getTextBounds(hour9, 0, hour9.length(), hourTextBound);
        float outLineSpcing = outLineWidth + outLineScaleLinePadding + thickScaleLineLength + outLineScaleLinePadding;//外圆宽度+间距+粗线长度+间距
        float hour9X = outLineSpcing;
        float hour9Y = getWidth() / 2 + hourTextBound.height() / 2;
        canvas.drawText(hour9, hour9X, hour9Y, hourTextPaint);

        //画12
        hourTextPaint.getTextBounds(hour12, 0, hour12.length(), hourTextBound);
        float hour12X = getWidth() / 2 - hourTextBound.width() / 2;
        float hour12Y = outLineSpcing + hourTextBound.height();
        canvas.drawText(hour12, hour12X, hour12Y, hourTextPaint);

        //画3
        hourTextPaint.getTextBounds(hour3, 0, hour3.length(), hourTextBound);
        float hour3X = getWidth() - outLineSpcing - hourTextBound.width();
        float hour3Y = getHeight() / 2 + hourTextBound.height() / 2;
        canvas.drawText(hour3, hour3X, hour3Y, hourTextPaint);


        //画6
        hourTextPaint.getTextBounds(hour6, 0, hour6.length(), hourTextBound);
        float hour6X = hour12X + hourTextBound.width() / 2;
        float hour6Y = getHeight() - outLineSpcing;
        canvas.drawText(hour6, hour6X, hour6Y, hourTextPaint);
    }

    private void drawScaleLine(Canvas canvas) {
        canvas.save();

        int normalLineWidth = (int) ViewUtil.dp2px(getContext(), 1);
        int thickLineWidth = (int) ViewUtil.dp2px(getContext(), 2);



        float resulScaletLineLength = normalScaleLineLength;
        int cx=getWidth()/2;
        int cy = getHeight() / 2;

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(scaleLineColor);
        for (int i = 0; i < 60; i++) {
            if ((i + 1) % 5 == 1) {
                paint.setStrokeWidth(thickLineWidth);
                resulScaletLineLength = thickScaleLineLength;
            } else {
                paint.setStrokeWidth(normalLineWidth);
                resulScaletLineLength = normalScaleLineLength;
            }
            canvas.drawLine(outLineWidth + outLineScaleLinePadding, cy,
                    outLineWidth + outLineScaleLinePadding + resulScaletLineLength, cy, paint);
            canvas.rotate(6f, cx, cy);
        }

        canvas.restore();


    }

    /**
     * 画外部圆
     * @param canvas
     */
    private void drawOuntLine(Canvas canvas) {

        paint.setColor(outLineColor);
        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(outLineWidth);

        float cx = getWidth() / 2;
        float cy = getHeight() / 2;
        float outLineRadius = getWidth() / 2 - outLineWidth;
        canvas.drawCircle(cx, cy, outLineRadius, paint);
    }
}
