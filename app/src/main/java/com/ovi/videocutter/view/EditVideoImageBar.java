package com.ovi.videocutter.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;


public class EditVideoImageBar extends ImageView {
    private List<Float> cutPoint = new ArrayList<Float>();
    private Map<Float, float[]> selectAreas = new HashMap<Float, float[]>();

    private List<float[]> selectPoints = new ArrayList<float[]>();


    private Paint cutPaint;


    private Paint mSelectedPaint;


    public EditVideoImageBar(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();


    }


    public EditVideoImageBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditVideoImageBar(Context context) {
        super(context);
        init();
    }

    /**
     * Initialize split line
     */
    private void init() {

        setWillNotDraw(false);

        cutPaint = new Paint();
        cutPaint.setColor(Color.rgb(0, 0, 0));
        cutPaint.setStrokeWidth(6);
        cutPaint.setAntiAlias(true);

        mSelectedPaint = new Paint();
        mSelectedPaint.setStrokeWidth(6);
        mSelectedPaint.setStyle(Style.STROKE);
        mSelectedPaint.setAntiAlias(false);
        mSelectedPaint.setColor(Color.RED);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();

        for (int i = 0; i < cutPoint.size(); i++) {
            canvas.drawLine(cutPoint.get(i), 0, cutPoint.get(i), measuredHeight, cutPaint);
        }

        for (int j = 0; j < cutPoint.size(); j++) {
            if (j == 0 && touchX != 0) {
                if (touchX < cutPoint.get(j)) {
                    float[] cutPostion = new float[2];
                    if (selectAreas.containsKey(0.0F)) {
                        selectAreas.remove(0.0F);

                    } else {
                        cutPostion[0] = 0;
                        cutPostion[1] = cutPoint.get(j);
                        selectAreas.put(0.0F, cutPostion);
                    }
                    touchX = 0;
                    break;
                }
            }
            if (j == cutPoint.size() - 1) {
                if (touchX > cutPoint.get(j)) {
                    float[] cutPostion = new float[2];
                    if (selectAreas.containsKey(cutPoint.get(j))) {
                        selectAreas.remove(cutPoint.get(j));

                    } else {
                        cutPostion[0] = cutPoint.get(j);
                        cutPostion[1] = getWidth();
                        selectAreas.put(cutPoint.get(j), cutPostion);
                    }
                    touchX = 0;
                    break;
                }
            }

            if (j < cutPoint.size()) {
                if (touchX > cutPoint.get(j) && touchX < cutPoint.get(j + 1) && touchX != 0) {
                    float[] cutPostion = new float[2];
                    if (selectAreas.containsKey(cutPoint.get(j))) {
                        selectAreas.remove(cutPoint.get(j));
                    } else {
                        cutPostion[0] = cutPoint.get(j);
                        cutPostion[1] = cutPoint.get(j + 1);
                        selectAreas.put(cutPoint.get(j), cutPostion);
                    }
                    touchX = 0;
                    break;
                }
            }
        }


        selectPoints.clear();
        Iterator<Float> iterator = selectAreas.keySet().iterator();
        while (iterator.hasNext()) {
            //获取key值
            Float next = iterator.next();
            float[] fs = selectAreas.get(next);
            canvas.drawRect(fs[0], 0.0F, fs[1],
                    measuredHeight, mSelectedPaint);
        }

    }


    /**
     * Set the clip position
     *
     * @param position
     */
    public void setCutPostion(int position) {
        touchX = 0;
        int temp = cutPoint.size();
        for (int i = 0; i < cutPoint.size(); i++) {
            if (cutPoint.get(i) > position) {
                temp = i;
                break;
            }
        }
        cutPoint.add(temp, (float) position);
        invalidate();

    }


    private float touchX = 0;
    private float touchX1;


    /**
     * Returns a collection of split points
     *
     * @return
     */
    public List<float[]> getCutPostion() {

        float[] tempArray = new float[selectAreas.size()];

        Iterator<Float> iterator = selectAreas.keySet().iterator();
        int flag = 0;
        while (iterator.hasNext()) {
            //获取key值
            Float next = iterator.next();
            tempArray[flag] = next;
            flag = flag + 1;
        }
        for (int i = 0; i < tempArray.length; i++) {
            for (int j = 0; j < tempArray.length; j++) {
                if (tempArray[i] < tempArray[j]) {
                    float temp = 0;
                    temp = tempArray[j];
                    tempArray[j] = tempArray[i];
                    tempArray[i] = temp;
                }
            }
        }
        for (int i = 0; i < tempArray.length; i++) {
            selectPoints.add(selectAreas.get(tempArray[i]));
        }
        return selectPoints;
    }


    /**
     * Show selected area
     *
     * @param isFling
     */
    public void showSelectArea(boolean isFling) {
        if (!isFling) {
            touchX = touchX1;
            invalidate();
        }
    }


    /**
     * Returns the coordinates of the selected area
     *
     * @return
     */
    public float getSelcetPoint() {
        return touchX;
    }

    /**
     * Clear the selected breakpoint position and the selected area
     */
    public void clearPosition() {
        cutPoint.clear();
        touchX = 0;
        selectAreas.clear();
        selectPoints.clear();
        invalidate();
    }


    /**
     * Perform touch event processing of the control (record the coordinates of the touch)
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchX1 = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;

            default:
                break;
        }

        return super.onTouchEvent(event);
    }


}
