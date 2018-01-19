package de.hs_heilbronn.stud.areinsch.dronecontrol.drone;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by AnAnd on 29.12.2017.
 */

public class DroneSticks extends View {

    private static final String TAG = "MY_STICKS";

    private Drone drone;

    /**
     * Width of the view.
     */
    private int width;

    /**
     * Height of the view.
     */
    private int height;

    /**
     * Radius/Size of the control sticks.
     */
    private int stickRadius;

    /**
     * Y coordinate of the control sticks in the view.
     */
    private int sticksY;

    /**
     * X coordinate of the control sticks in the view. Left, then right.
     */
    private int[] sticksX;

    private float[] stickInputX;

    private float[] stickInputY;

    private int[] stickFinger;

    /**
     * User input on the sticks?
     */
    private boolean[] stickPressed;

    /**
     * Style of the stick background.
     */
    private Paint stickBGStyle;

    private Paint stickBorderStyle;

    /**
     * Style of the sticks.
     */
    private Paint stickStyle;

    public DroneSticks(Context context) {
        super(context);

        init(context);
    }

    public DroneSticks(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public DroneSticks(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context);
    }

    private void init(Context context) {

        drone = null;
        width = 0;
        height = 0;
        stickRadius = 0;
        sticksY = 0;
        sticksX = new int[2];
        stickFinger = new int[2];
        stickInputX = new float[2];
        stickInputY = new float[2];
        stickPressed = new boolean[2];

        stickStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        stickStyle.setColor(Color.BLACK);
        stickStyle.setStyle(Paint.Style.FILL);


        stickBGStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        stickStyle.setStyle(Paint.Style.FILL);
        stickBGStyle.setColor(0xff101010);
        stickBGStyle.setAlpha(50);
        stickBGStyle.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

        stickBorderStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        stickBorderStyle.setStyle(Paint.Style.STROKE);
        stickBorderStyle.setStrokeWidth(10.0f);
        stickBorderStyle.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d(TAG, MeasureSpec.toString(widthMeasureSpec));
        Log.d(TAG, MeasureSpec.toString(heightMeasureSpec));

        int prefWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int prefHeight = 500 + getPaddingBottom() + getPaddingTop();

        width = measureDimension(prefWidth ,widthMeasureSpec);
        height = measureDimension(prefHeight, heightMeasureSpec);

        int stickDiameter = height - getPaddingTop() - getPaddingBottom();
        if (stickDiameter % 2 == 1) {
            stickRadius = (stickDiameter - 1) / 2;
        } else {
            stickRadius = stickDiameter / 2;
        }

        sticksY = stickRadius + getPaddingTop();
        sticksX[0] = stickRadius + getPaddingLeft();
        sticksX[1] = width - stickRadius - getPaddingRight();

        Log.d(TAG, "Width: " + width);
        Log.d(TAG, "Height: " + height);
        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));
    }

    private int measureDimension(int desiredSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = desiredSize;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize){
            Log.e(TAG, "View to small.");
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < 2; i ++) {
            canvas.drawCircle(sticksX[i], sticksY, stickRadius, stickBGStyle);
            canvas.drawCircle(sticksX[i], sticksY, stickRadius, stickBorderStyle);
            if (stickPressed[i]) {
                canvas.drawCircle(stickInputX[i], stickInputY[i], stickRadius / 2, stickStyle);

            }else {
                canvas.drawCircle(sticksX[i], sticksY, stickRadius / 2, stickStyle);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int fingerCount = event.getPointerCount();

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                for (int finger = 0; finger < fingerCount; finger++) {
                    float fingerY = event.getY(finger);
                    float fingerX = event.getX(finger);
                    for (int stick = 0; stick < 2; stick++) {
                        double x = Math.abs(fingerX - sticksX[stick]);
                        double y = Math.abs(fingerY - sticksY);
                        if (x + y <= stickRadius) {
                            stickPressed[stick] = true;
                            stickFinger[stick] = finger;
                            stickInputX[stick] = fingerX;
                            stickInputY[stick] = fingerY;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for (int finger = 0; finger < fingerCount; finger++) {
                    float fingerY = event.getY(finger);
                    float fingerX = event.getX(finger);
                    for (int stick = 0; stick < 2; stick++) {
                        double x = Math.abs(fingerX - sticksX[stick]);
                        double y = Math.abs(fingerY - sticksY);
                        if (stickPressed[stick] && stickFinger[stick] == finger) {
                            if (x + y <= stickRadius) {
                                stickFinger[stick] = finger;
                                stickInputX[stick] = fingerX;
                                stickInputY[stick] = fingerY;
                                Log.d(TAG, "Pressed stick:" + stick);
                                break;
                            } else {
                                double ratio = stickRadius / (x + y);
                                stickFinger[stick] = finger;
                                stickInputX[stick] = (float) (sticksX[stick] + (fingerX - sticksX[stick]) * ratio);
                                stickInputY[stick] = (float) (sticksY + (fingerY - sticksY) * ratio);
                                Log.d(TAG, "Pressed stick out:" + stick);
                                break;
                            }
                        }

                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG,"Reset sticks.");
                for(int i = 0; i < 2; i ++) {
                    stickPressed[i] = false;
                    stickInputY[i] = sticksY;
                    stickInputX[i] = sticksX[i];
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                Log.d(TAG, "P_UP: " + event.getActionIndex());
                for (int i = 0; i < 2; i++) {
                    if (stickFinger[i] == event.getActionIndex()) {
                        stickPressed[i] = false;
                        stickInputY[i] = sticksY;
                        stickInputX[i] = sticksX[i];
                        Log.d(TAG, "Release stick: " + i);
                    } else {
                        float fingerY = event.getY(stickFinger[i]);
                        float fingerX = event.getX(stickFinger[i]);
                        double x = Math.abs(fingerX - sticksX[i]);
                        double y = Math.abs(fingerY - sticksY);
                        if (x + y <= stickRadius) {
                            stickPressed[i] = true;
                            stickInputX[i] = fingerX;
                            stickInputY[i] = fingerY;
                            Log.d(TAG, "Pressed stick:" + i);
                        }
                    }
                }
                break;
            default:
        }
        invalidate();
        return true;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}