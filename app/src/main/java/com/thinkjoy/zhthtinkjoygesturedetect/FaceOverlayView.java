package com.thinkjoy.zhthtinkjoygesturedetect;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


/**
 * Created by thinkjoy on 17-8-9.
 */

public class FaceOverlayView extends View {

    private Paint mPaint;
    private Paint mFacePaint;
    private Paint mTextPaint;
    private int imageWidth;
    private int imageHeight;
    private int winWidth;
    private int winHeight;
    private float left, right, bottom, top;
    private int shape;
    private long processTime;
    private String shapetype;

    public FaceOverlayView(Context context) {
        super(context);
    }
    public FaceOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FaceOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);  //
        mPaint.setDither(true);
        mPaint.setColor(Color.GREEN);   //颜色
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.STROKE);  //控制是否空心

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setDither(true);
        mTextPaint.setTextSize(60);
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setStyle(Paint.Style.FILL);

        mFacePaint = new Paint();
        mFacePaint.setAntiAlias(true);
        mFacePaint.setDither(true);
        mFacePaint.setColor(Color.WHITE);
        mFacePaint.setStrokeWidth(5);
        mFacePaint.setStyle(Paint.Style.STROKE);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        winHeight = getHeight();
        winWidth = getWidth();
        switch (shape) {
            case 0:
                mPaint.setColor(Color.RED);
                shapetype = "FIST";
                break;
            case 1:
                mPaint.setColor(Color.YELLOW);
                shapetype = "PALM";
                break;
            case 2:
                mPaint.setColor(Color.BLUE);
                shapetype = "OK";
                break;
            case 3:
                mPaint.setColor(Color.GREEN);
                shapetype = "YEAH";
                break;
            case 4:
                mPaint.setColor(Color.WHITE);
                shapetype = "ONE FINGER";
                break;
            default:
                mPaint.setColor(Color.BLACK);
                shapetype = "NO GESTURE";
                break;
        }
            float ratex = (float)winWidth / imageWidth;
            float ratey = (float)winHeight / imageHeight;
            if (left > 0.5) {

                canvas.drawRect(left * ratex,  top * ratey, right * ratex,  bottom * ratey, mPaint);
                canvas.drawText("TYPE: " + shapetype , 20, 100, mTextPaint);
                canvas.drawText("TIME:"+Long.toString(processTime) + "ms", 20, 160
                        , mTextPaint);
            } else {
                canvas.drawText("TYPE: " + shapetype , 20, 100, mTextPaint);
                canvas.drawText("TIME:"+Long.toString(processTime) + "ms", 20, 160, mTextPaint);
                canvas.drawRect(0, 0, 0, 0, mPaint);
            }

    }

    public void setGestureResult(GestureDetectResult gestureResult, long processTime) {
        if (GlobalInfo.IS_IMAGE_REVERSE == false) {
            this.right = imageWidth - gestureResult.left - 0.5f;
            this.left = imageWidth - gestureResult.right - 0.5f;
        } else {
            this.left = gestureResult.left + 0.5f;
            this.right = gestureResult.right + 0.5f;
        }

        this.top = (float)(gestureResult.top + 0.5);
        this.bottom = (float)(gestureResult.bottom + 0.5);
        this.shape = gestureResult.shape ;
        this.processTime = processTime;
    }
    public void setWindowSize() {
        this.winHeight = this.getHeight();
        this.winWidth = this.getWidth();
    }

    public void setImageSize(int imageWidth, int imageHeight) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

}
