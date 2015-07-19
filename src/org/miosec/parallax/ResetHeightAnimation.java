package org.miosec.parallax;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;

public class ResetHeightAnimation extends Animation {
	private View view;
	private int originalHeight;
	private int totalValue;
	private int targetHeight;

	public ResetHeightAnimation(View view, int targetHeight) {
		super();
		this.view = view;
		this.targetHeight = targetHeight;
		originalHeight = view.getHeight();
		setDuration(400);
		setInterpolator(new OvershootInterpolator());
		totalValue = targetHeight - originalHeight;
	}

	/**
	 * interpolatedTime:0-1 标识动画执行的进度或者百分比 time 0-0.5-1 value 10 - 60 - 110
	 */
	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		super.applyTransformation(interpolatedTime, t);
		int newHeight = (int) (originalHeight + totalValue*interpolatedTime);
		view.getLayoutParams().height = newHeight;
		view.requestLayout();
	}
}
