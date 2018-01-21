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
     * Default position of the stick.
     */
    private int defaultY[];

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
        defaultY = new int[2];
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
        defaultY[0] = centerY + stickRadius;
        defaultY[1] = centerY;

        for(int i = 0; i < 2; i++) {
            resetStick(i);
        }
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
                canvas.drawCircle(centerX[i], defaultY[i], stickRadius / 2, stickStyle);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int fingerCount = event.getPointerCount();

        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int finger = 0; finger < fingerCount; finger++) {
                    for (int stick = 0; stick < 2; stick++) {
                        updateStick(event.getX(finger), event.getY(finger), finger, stick);
                    }
                }
                break;
            
            // One of two fingers released.
            // Reset one stick and update the other one.
            case MotionEvent.ACTION_POINTER_UP:
                for (int i = 0; i < 2; i++) {
                    if (stickFingerId[i] == event.getActionIndex()) {
                        resetStick(i);
                    } else {
                        updateStick(event.getX(stickFingerId[i]), event.getY(stickFingerId[i]),
                                stickFingerId[i], i);
                    }
                }
                break;

            // All sticks released. Reset the sticks.
            case MotionEvent.ACTION_UP:
                for(int i = 0; i < 2; i ++) {
                    resetStick(i);
                }
                break;
            default:
        }

        // Send data to drone.
        int[] rcValues = new int[4];

        for (int i = 0; i < 2; i++) {
            rcValues[i] = Math.round(calcRCX(inputX[i], centerX[i]));
        }

        for (int i = 0; i < 2; i++) {
            rcValues[i + 2] = Math.round(calcRCY(inputY[i], centerY));
        }

        if (drone != null) {
            drone.sendRC(rcValues[2], rcValues[1], rcValues[3], rcValues[0]);
        }
        Log.d(TAG, "T: " + rcValues[2] + " Y: " + rcValues[0]
                + " P: " + rcValues[3] + " R: " + rcValues[1]);

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

    private void resetStick(int stickId) {
            pressed[stickId] = false;
            inputY[stickId] = defaultY[stickId];
            inputX[stickId] = centerX[stickId];
    }

    private void updateStick(float touchX, float touchY, int fingerId, int stickId) {
        double centerDistance = Math.abs(touchX - centerX[stickId]) + Math.abs(touchY - centerY);

        if (centerDistance <= stickRadius) {
            pressed[stickId] = true;
            stickFingerId[stickId] = fingerId;
            inputX[stickId] = touchX;
            inputY[stickId] = touchY;
        } else if (pressed[stickId] && stickFingerId[stickId] == fingerId) {
            double ratio = stickRadius / (centerDistance);
            stickFingerId[stickId] = fingerId;
            inputX[stickId] = (float) (centerX[stickId] + (touchX - centerX[stickId]) * ratio);
            inputY[stickId] = (float) (centerY + (touchY - centerY) * ratio);
        }
    }

    public void setDrone(Drone drone) {
        this.drone = drone;
    }
}