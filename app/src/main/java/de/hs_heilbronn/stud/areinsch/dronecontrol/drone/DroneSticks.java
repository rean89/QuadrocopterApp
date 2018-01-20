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

    /**
     * Logcat tag.
     */
    private static final String TAG = "MY_STICKS";

    /**
     * Drone to send the rc data.
     */
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
    private int centerY;

    /**
     * X coordinate of the control sticks in the view. Left, then right.
     */
    private int[] centerX;

    /**
     * X axis input of the  sticks.
     */
    private float[] inputX;

    /**
     * Y axis input of the sticks.
     */
    private float[] inputY;

    /**
     * FingerId for each stick.
     */
    private int[] stickFingerId;

    /**
     * User input on the sticks?
     */
    private boolean[] pressed;

    /**
     * Style of the stick background.
     */
    private Paint stickBGStyle;

    /**
     * Style for the stick border.
     */
    private Paint borderStyle;

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
        centerY = 0;
        centerX = new int[2];
        stickRadius = 0;
        stickFingerId = new int[2];
        inputX = new float[2];
        inputY = new float[2];
        pressed = new boolean[2];

        stickStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        stickStyle.setColor(Color.BLACK);
        stickStyle.setStyle(Paint.Style.FILL);


        stickBGStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        stickBGStyle.setColor(0xff101010);
        stickBGStyle.setAlpha(50);
        stickBGStyle.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.NORMAL));

        borderStyle = new Paint(Paint.ANTI_ALIAS_FLAG);
        borderStyle.setStyle(Paint.Style.STROKE);
        borderStyle.setStrokeWidth(10.0f);
        borderStyle.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Log.d(TAG, MeasureSpec.toString(widthMeasureSpec));
        //Log.d(TAG, MeasureSpec.toString(heightMeasureSpec));

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

        centerY = stickRadius + getPaddingTop();
        centerX[0] = stickRadius + getPaddingLeft();
        centerX[1] = width - stickRadius - getPaddingRight();

        //Log.d(TAG, "Width: " + width);
        //Log.d(TAG, "Height: " + height);
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
           //Log.e(TAG, "View to small.");
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i = 0; i < 2; i ++) {
            canvas.drawCircle(centerX[i], centerY, stickRadius, stickBGStyle);
            canvas.drawCircle(centerX[i], centerY, stickRadius, borderStyle);
            if (pressed[i]) {
                canvas.drawCircle(inputX[i], inputY[i], stickRadius / 2, stickStyle);

            }else {
                canvas.drawCircle(centerX[i], centerY, stickRadius / 2, stickStyle);
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
                        double x = Math.abs(fingerX - centerX[stick]);
                        double y = Math.abs(fingerY - centerY);
                        if (x + y <= stickRadius) {
                            pressed[stick] = true;
                            stickFingerId[stick] = finger;
                            inputX[stick] = fingerX;
                            inputY[stick] = fingerY;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for (int finger = 0; finger < fingerCount; finger++) {
                    float fingerY = event.getY(finger);
                    float fingerX = event.getX(finger);
                    for (int stick = 0; stick < 2; stick++) {
                        double x = Math.abs(fingerX - centerX[stick]);
                        double y = Math.abs(fingerY - centerY);
                        if (pressed[stick] && stickFingerId[stick] == finger) {
                            if (x + y <= stickRadius) {
                                stickFingerId[stick] = finger;
                                inputX[stick] = fingerX;
                                inputY[stick] = fingerY;
                                //Log.d(TAG, "Pressed stick:" + stick);
                                break;
                            } else {
                                double ratio = stickRadius / (x + y);
                                stickFingerId[stick] = finger;
                                inputX[stick] = (float) (centerX[stick] + (fingerX - centerX[stick]) * ratio);
                                inputY[stick] = (float) (centerY + (fingerY - centerY) * ratio);
                                //Log.d(TAG, "Pressed stick out:" + stick);
                                break;
                            }
                        }

                    }
                }
                break;
            // Reset sticks.
            case MotionEvent.ACTION_UP:
                //Log.d(TAG,"Reset sticks.");
                for(int i = 0; i < 2; i ++) {
                    pressed[i] = false;
                    inputY[i] = centerY;
                    inputX[i] = centerX[i];
                }
                break;
            // One of two fingers released.
            // Reset the released finger and update the other one.
            case MotionEvent.ACTION_POINTER_UP:
                //Log.d(TAG, "P_UP: " + event.getActionIndex());
                for (int i = 0; i < 2; i++) {
                    if (stickFingerId[i] == event.getActionIndex()) {
                        // Reset stick.
                        pressed[i] = false;
                        inputY[i] = centerY;
                        inputX[i] = centerX[i];
                        //Log.d(TAG, "Release stick: " + i);
                    } else {
                        // Update stick.
                        float fingerY = event.getY(stickFingerId[i]);
                        float fingerX = event.getX(stickFingerId[i]);
                        double x = Math.abs(fingerX - centerX[i]);
                        double y = Math.abs(fingerY - centerY);
                        if (x + y <= stickRadius) {
                            pressed[i] = true;
                            inputX[i] = fingerX;
                            inputY[i] = fingerY;
                            //Log.d(TAG, "Pressed stick:" + i);
                        }
                    }
                }
                break;
            default:
        }

        // Send data to drone.
        float[] rcValue = new float[4];

        for (int i = 0; i < 2; i++) {
            rcValue[i] = calcRCX(inputX[i], centerX[i]);
        }

        for (int i = 0; i < 2; i++) {
            rcValue[i + 2] = calcRCY(inputY[i], centerY);
        }

        if (drone != null) {
            drone.sendRC(rcValue[2], rcValue[1], rcValue[3], rcValue[0]);
        }
        Log.d(TAG, "T: " + rcValue[2] + " Y: " + rcValue[0]
                + " P: " + rcValue[3] + " R: " + rcValue[1]);

        // Request view update.
        invalidate();
        return true;
    }

    private float calcRCX(float input, float center) {
        float cleanInput =  input - (center - stickRadius);
        return ((cleanInput / (stickRadius * 2)) * 1000) + 1000;
    }

    private float calcRCY(float input, float center) {
        float cleanInput =  input - (center - stickRadius);
        return ((1 - (cleanInput / (stickRadius * 2))) * 1000) + 1000;
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}