package com.example.scaletopview;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.carter.R;


@SuppressLint("NewApi")
public class FocusView extends RelativeLayout {
	/**
	 * 属性动画使用的包装层，方便操作宽度和高度的变化
	 */
	private ViewWrapper viewWrapper = null;

	/**
	 * View的x和y的坐标的偏移量,考虑的是焦点图片的光圈
	 */
	public int xyOffset = 10;

	/**
	 * View的width和height的坐标的偏移量,考虑的是焦点图片的光圈
	 */
	private int whOffset = 20;
	
	private int hOffset = 0;
	private int yOffset = 0;

	/**
	 * 外部调用根据特殊的需要，设置y方向额外的偏移量
	 */
	private int outYOffset = -5;

	/**
	 * 外部调用根据特殊的需要，设置x方向额外的偏移量
	 */
	private int outXOffset = 0;

	/**
	 * 焦点框View的宽度变化的属性值对象
	 */
	PropertyValuesHolder pvhWidth = null;

	/**
	 * 焦点框View的高度变化的属性值对象
	 */
	PropertyValuesHolder pvhHeight = null;

	/**
	 * 焦点框View多属性动画变化的动画对象
	 */
	ObjectAnimator whAnimator = null;

	/**
	 * 高性能的View多属性动画对象
	 */
	ViewPropertyAnimator vpaXY = null;

	/**
	 * 先快后慢的动画差值器
	 */
	private AccelerateDecelerateInterpolator interpolator = null;

	private boolean isInitPositionOnScreen = false;

	/**
	 * 获取View在屏幕中坐标的数组
	 */
	private int[] location = new int[2];

	private View currentFocusView = null;

	private FocusViewAnimatorEndListener animatorEndListener = null;

	private int oldX = 0;
	private int oldY = 0;

	private final String TAG = "FocusView";
	private Context mContext;

	public FocusView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mContext = context;
		init();
	}

	public FocusView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public FocusView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	private void init() {
		setBackgroundResource(R.drawable.focus_img);
		setAlpha(0f);
		setFocusable(false);
		viewWrapper = new ViewWrapper(this);
		interpolator = new AccelerateDecelerateInterpolator();
		

	}

	public void setFocusBackgroundResource(int resid) {
		setBackgroundResource(resid);
	}


	public void initFocusView(View firstCallRequestFocusOfView, int duration) {

		Log.v(TAG,"init");
		firstCallRequestFocusOfView.requestFocus();

		if (isInitPositionOnScreen) {// 保证初始化一次
			return;
		}

		// 计算目标焦点View的坐标
		firstCallRequestFocusOfView.getLocationOnScreen(location);
		int targetX = location[0];
		int targetY = location[1];
		// 设置焦点View的在屏幕中的坐标
		setX(targetX - xyOffset - outXOffset);
		setY(targetY - xyOffset - outYOffset);
		// 设置焦点View的宽度和高度
		viewWrapper.setWidth(firstCallRequestFocusOfView.getWidth() + whOffset);
		viewWrapper.setHeight(firstCallRequestFocusOfView.getHeight() + whOffset);

		setAlpha(1f);
       
		currentFocusView = firstCallRequestFocusOfView;

		// 初始化多属性的动画对象，用于改变焦点框View的宽和高
		pvhWidth = PropertyValuesHolder.ofInt("width", firstCallRequestFocusOfView.getWidth());
		pvhHeight = PropertyValuesHolder.ofInt("height", firstCallRequestFocusOfView.getHeight());
		whAnimator = ObjectAnimator.ofPropertyValuesHolder(viewWrapper, pvhWidth, pvhHeight);
		// 设置动画时间和速度变化
		whAnimator.setInterpolator(interpolator);

		vpaXY = animate().x(targetX - xyOffset - outXOffset).y(targetY - xyOffset - outYOffset).setListener(new FocusViewMoveListener()).setInterpolator(interpolator);

		// 初始化完成
		isInitPositionOnScreen = true;

		setFocusViewAnimatonDuration(duration);
	}


	

	public void moveTo(View tagetView) {

		if (!isInitPositionOnScreen) {// 防止Activity中某个View在界面刚刚构建完成时抢焦点，造成的动画效果
			return;
		}

		// 计算目标焦 点View的宽和高
		int targetWidth = tagetView.getWidth() + whOffset;
		int targetHeight = tagetView.getHeight() + whOffset;

		tagetView.getLocationOnScreen(location);
		int targetX = location[0];
		int targetY = location[1];

		// 计算焦点框View在屏幕中的坐标
		int x = targetX - xyOffset - outXOffset;
		int y = targetY - xyOffset - outYOffset;

		pvhWidth.setIntValues(viewWrapper.getWidth(), targetWidth);
		pvhHeight.setIntValues(viewWrapper.getHeight(), targetHeight);

		 
		whAnimator.start();
		vpaXY.x(x).y(y);

		currentFocusView = tagetView;

		oldX = targetX;
		oldY = targetY;
		
	}

	
	public void setFocusWH(int wh){
		whOffset = wh;
	}
	
	public void recoveryFocusWH(){
	    hOffset = whOffset;
	}
	





	class FocusViewMoveListener implements AnimatorListener {

		@Override
		public void onAnimationStart(Animator animation) {
			if (null != animatorEndListener) {
				animatorEndListener.OnAnimateStart(currentFocusView);
			}
		}

		@Override
		public void onAnimationEnd(Animator animation) {
			if (null != animatorEndListener) {
				animatorEndListener.OnAnimateEnd(currentFocusView);
			}
		}

		@Override
		public void onAnimationCancel(Animator animation) {
		}

		@Override
		public void onAnimationRepeat(Animator animation) {
		}

	}


	public void animateWidthAndHeight(View target) {
		// 设置变化过的宽度和高度
		pvhWidth.setIntValues(viewWrapper.getWidth(), target.getWidth());
		pvhHeight.setIntValues(viewWrapper.getHeight(), target.getHeight());
		// 启动焦点View坐标和高度宽度变化
		whAnimator.start();
	}

	public void moveToByParams(int x, int y, int width, int height) {
		// 坐标变化
		if (x > 0 && y > 0) {
			animate().x(x).y(y);
		} else if (x > 0) {
			animate().x(x);
		} else if (y > 0) {
			animate().y(y);
		}

		animate().setListener(new AnimatorListenerAdapter() {

		});

		Log.d("DesktopView", "moveToByParams " + x);
		oldX = x;

		// 宽度和高度变化
		if ((width + xyOffset) != getWidth()) {
			pvhWidth.setIntValues(viewWrapper.getWidth(), width + whOffset);
		}

		if ((height + xyOffset) != getWidth()) {
			pvhHeight.setIntValues(viewWrapper.getHeight(), height + whOffset);
		}
		whAnimator.start();
	}


	public interface FocusViewAnimatorEndListener {
		public void OnAnimateStart(View currentFocusView);
		public void OnAnimateEnd(View currentFocusView);
	}

	public void setAnimatorEndListener(FocusViewAnimatorEndListener animatorEndListener) {
		this.animatorEndListener = animatorEndListener;
	}

	public void setFocusViewAnimatonDuration(int duration) {
		animate().setDuration(duration);
	}

}
