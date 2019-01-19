package com.ovi.videocutter.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.ovi.videocutter.R;

import java.util.ArrayList;

public class VideoEditProgressBar extends View {
	private Paint progressPaint;
	private float currentProgress;
	private MarkIndexListener mListener;
	private ArrayList<Float> markList=new ArrayList<Float>();
	private Paint markTextPaint;
	private Bitmap markIcon;
	private Rect srcRect;
	private Rect destRect;
	private Paint bottomHalfPaint;
	private Paint darkPaint;
	private Rect bottomHalfBgRect;
	private int bitWidth;
	private int bitHeight;
	private Paint paint;
	private int width;
	private int height;
	public ArrayList<Float> getMarkList() {
		return markList;
	}
	public void setMarkList(ArrayList<Float> markList) {
		this.markList = markList;
	}
	
	
	
	/**
	 * Clear mark
	 */
	public void clearPoint(){
		if(markList!=null&&!"".equals(markList)){
			markList.clear();
		}
		currentProgress=0;
		invalidate();
	}
	
	
	public VideoEditProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		
		progressPaint=new Paint();
		progressPaint.setColor(getResources().getColor(R.color.vine_green));
		paint=new Paint();
		
		
		bottomHalfPaint=new Paint();
		darkPaint=new Paint();
		
		
		darkPaint.setColor(getResources().getColor(R.color.dark_black));
		bottomHalfPaint.setColor(getResources().getColor(R.color.bottomBg));
		
		
		markTextPaint=new Paint();
		markTextPaint.setColor(getResources().getColor(R.color.hui));
		markTextPaint.setTextSize(20);
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setFilterBitmap(true);
		markIcon=((BitmapDrawable)getResources().getDrawable(R.drawable.edit_mark)).getBitmap();
		bitWidth = markIcon.getWidth();
		bitHeight = markIcon.getHeight();
	
	}

	public void setPausePoints(ArrayList<Float> points){
		markList=points;
		invalidate();  
	}
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
		super.onSizeChanged(w, h, oldw, oldh);
		width = getMeasuredWidth();
		height =getMeasuredHeight();
		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(srcRect==null){
			srcRect=new Rect(0, 0, bitWidth, bitHeight);
		}
		if(destRect==null){
			destRect=new Rect((width-bitWidth/2)/2, 0, (width-bitWidth/2)/2+bitWidth/2, bitHeight/2);
			
		}
		if(bottomHalfBgRect==null){
			bottomHalfBgRect=new Rect(0, height-20, width, height);
		}
		
		canvas.drawRect(bottomHalfBgRect, bottomHalfPaint);
		
		if(currentProgress>0){
			canvas.drawRect(0, height-20, currentProgress*width, height, progressPaint);
		}
		if(markList!=null&&!markList.isEmpty()){
			for (int i = 0; i < markList.size(); i++) {
				Float float1 = markList.get(i);
				int left=(int) (width*float1);
				destRect=new Rect((left-bitWidth/4), 0, (left-bitWidth/4)+bitWidth/2, bitHeight/2);
				canvas.drawBitmap(markIcon, srcRect, destRect, paint);
				
				String text=(i+1)+"";
				float textWidth = markTextPaint.measureText(text);
				FontMetricsInt fontMetricsInt = markTextPaint.getFontMetricsInt();
				
				int fontHeight=fontMetricsInt.bottom-fontMetricsInt.top;
				
				canvas.drawText(text, (left-textWidth/2), fontHeight-8, markTextPaint);
				if(float1<currentProgress){
					canvas.drawLine(left, height-20, left, height,bottomHalfPaint );
				}else{
					canvas.drawLine(left, height-20, left, height,progressPaint );
				}
			}
		}
	}

	public void setProgress(Float progress){
		currentProgress=progress;
		invalidate();
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(markList!=null&&!markList.isEmpty()){
				float x2 = event.getX();
				for (int i = 0; i < markList.size(); i++) {
					
					Float float1 = markList.get(i);
					if(Math.abs(float1*width-x2)<10&&mListener!=null){
						mListener.indexClick(i);
						break;
					}
				}
				break;
			}
			}
			
		return super.onTouchEvent(event);
	}
	/**
	 * Indexed callback
	 *
	 *
	 */
	public interface MarkIndexListener{
		/**
		 * Index of the tag being clicked
		 * @param index
		 */
		void indexClick(int index);
	}

}
