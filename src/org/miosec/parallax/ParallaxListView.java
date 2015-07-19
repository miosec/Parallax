package org.miosec.parallax;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ListView;

public class ParallaxListView extends ListView{
	private ImageView imageView;
	private int maxHeight;
	private int originalHeight;
	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
		originalHeight = imageView.getHeight();
		System.out.println(originalHeight);
		maxHeight = imageView.getDrawable().getIntrinsicHeight();
	}
	public ParallaxListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	@SuppressLint("NewApi")
	@Override
	/**
	 * ��������ͷ��ʱ�����
	 */
	protected boolean overScrollBy(int deltaX, int deltaY, int scrollX,
			int scrollY, int scrollRangeX, int scrollRangeY,
			int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
		//deltaY: Y���򻬶��ľ���
		//maxOverScrollY ��ͷ��,���ɹ����ķ�Χ
		//isTouchEvent �Ƿ��Ǵ�������,true���ǰ��Ż���ͷ,false���ǹ��Ի�
		if(deltaY<0&&isTouchEvent){
			int newHeight = imageView.getHeight() - deltaY/3;
			if(newHeight > maxHeight){
				newHeight = maxHeight;
			}
			imageView.getLayoutParams().height = newHeight;
			imageView.requestLayout();//���²����Ͳ���
		}
		return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX,
				scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_UP:
			ResetHeightAnimation resetHeightAnimation = new ResetHeightAnimation(imageView, originalHeight);
			startAnimation(resetHeightAnimation);
			break;
		case MotionEvent.ACTION_MOVE:
			
			break;
		case MotionEvent.ACTION_DOWN:
			
			break;

		default:
			break;
		}
		
		return super.onTouchEvent(ev);
	}
}
