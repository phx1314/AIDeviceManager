package com.deepblue.aidevicemanager.view;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.deepblue.aidevicemanager.R;

public class TextSwitch extends View implements ValueAnimator.AnimatorUpdateListener, ValueAnimator.AnimatorListener {
    //默认的宽高比例
    private static final float DEFAULT_WIDTH_HEIGHT_PERCENT = 0.45f;
    //动画最大的比例
    private static final float ANIMATION_MAX_FRACTION = 1;

    private int mWidth, mHeight;

    //画跑道型背景
    private Paint mBackgroundPain;
    //画背景上的字
    private Paint mDisaboleTextPaint;
    private Paint mEnableTextPaint;
    //画白色圆点
    private Paint mSlidePaint;

    //是否正在动画
    private boolean isAnimation;

    private String switch_id;

    private ValueAnimator mValueAnimator;

    private float mAnimationFraction;

    public void setOpenText(String openText) {
        this.openText = openText;
        this.invalidate();//刷新
    }

    public void setCloseText(String closeText) {
        this.closeText = closeText;
        this.invalidate();//刷新
    }

    private String openText;
    private String closeText;
    private int mOpenBgColor, mCloseBgColor;
    private int mCloseCircleColor = Color.WHITE;
    private int mOpenCircleColor = Color.parseColor("#d2d2d2");
    private int mCurrentBgColor;

    //监听
    private OnCheckedChangeListener mCheckedChangeListener;

    private boolean isChecked;

    public TextSwitch(Context context) {
        this(context, null);
    }

    public TextSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextSwitch);
        mOpenBgColor = typedArray.getColor(R.styleable.TextSwitch_openbgColor, Color.parseColor("#5FB878"));
        mCloseBgColor = typedArray.getColor(R.styleable.TextSwitch_closebgColor, Color.WHITE);
        switch_id = typedArray.getString(R.styleable.TextSwitch_swtich_id);
        mCurrentBgColor = mCloseBgColor;
        typedArray.recycle();
        init();
    }

    private void init() {
        mBackgroundPain = new Paint();
        mBackgroundPain.setAntiAlias(true);
        mBackgroundPain.setDither(true);
        mBackgroundPain.setColor(Color.GRAY);
        mDisaboleTextPaint = new Paint();
        mDisaboleTextPaint.setAntiAlias(true);
        mDisaboleTextPaint.setDither(true);
//        mDisaboleTextPaint.setStyle(Paint.Style.STROKE);
        mDisaboleTextPaint.setColor(Color.WHITE);
        mDisaboleTextPaint.setTextAlign(Paint.Align.CENTER);

        mEnableTextPaint = new Paint();
        mEnableTextPaint.setAntiAlias(true);
        mEnableTextPaint.setDither(true);
//        mEnableTextPaint.setStyle(Paint.Style.STROKE);
        mEnableTextPaint.setColor(Color.WHITE);
        mEnableTextPaint.setTextAlign(Paint.Align.CENTER);

        mSlidePaint = new Paint();
        mSlidePaint.setColor(Color.GRAY);
        mSlidePaint.setAntiAlias(true);
        mSlidePaint.setDither(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = (int) (width * DEFAULT_WIDTH_HEIGHT_PERCENT);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawSlide(canvas);
    }

    private void drawSlide(Canvas canvas) {
        float distance = mWidth - mHeight;
        mSlidePaint.setColor(mCurrentBgColor == mOpenBgColor ? mCloseCircleColor : mOpenCircleColor);
        canvas.drawCircle(mHeight / 2 + distance * mAnimationFraction, mHeight / 2, mHeight / 3, mSlidePaint);
    }

    private void drawBackground(Canvas canvas) {
        Path path = new Path();
        RectF rectF = new RectF(0, 0, mHeight, mHeight);
        path.arcTo(rectF, 90, 180);
        rectF.left = mWidth - mHeight;
        rectF.right = mWidth;
        path.arcTo(rectF, 270, 180);
        path.close();
        mBackgroundPain.setColor(mCurrentBgColor);
        canvas.drawPath(path, mBackgroundPain);

        mDisaboleTextPaint.setTextSize(mHeight / 2);
        mDisaboleTextPaint.setColor(Color.WHITE);
        mEnableTextPaint.setTextSize(mHeight / 2);
        mEnableTextPaint.setColor(Color.parseColor("#d2d2d2"));
        Paint.FontMetrics fontMetrics = mDisaboleTextPaint.getFontMetrics();
        float top = fontMetrics.top;
        float bottom = fontMetrics.bottom;
        int baseLine = (int) (mHeight / 2 + (bottom - top) * 0.3);

        mDisaboleTextPaint.setAlpha((int) (255 * mAnimationFraction));
        canvas.drawText(TextUtils.isEmpty(openText) ? "ON" : openText, mWidth * 0.4f, baseLine, mDisaboleTextPaint);

        mEnableTextPaint.setAlpha((int) (255 * (1 - mAnimationFraction)));
        canvas.drawText(TextUtils.isEmpty(closeText) ? "OFF" : closeText, mWidth * 0.6f, baseLine, mEnableTextPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;
            case MotionEvent.ACTION_CANCEL:
                break;
            case MotionEvent.ACTION_UP:
//                if (isAnimation) {
//                    return true;
//                }
//                if (isChecked) {
//                    startCloseAnimation();
//                    isChecked = false;
//                    if (mCheckedChangeListener != null) {
//                        mCheckedChangeListener.onCheckedChanged(switch_id, false);
//                    }
//                } else {
//                    startOpeAnimation();
//                    isChecked = true;
//                    if (mCheckedChangeListener != null) {
//                        mCheckedChangeListener.onCheckedChanged(switch_id, true);
//                    }
//                }
                return true;


        }
        return super.onTouchEvent(event);
    }

    private void startOpeAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(0.0f, ANIMATION_MAX_FRACTION);
        mValueAnimator.setDuration(500);
        mValueAnimator.addUpdateListener(this);
        mValueAnimator.addListener(this);
        mValueAnimator.start();
        startColorAnimation();
    }

    private void startCloseAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(ANIMATION_MAX_FRACTION, 0.0f);
        mValueAnimator.setDuration(500);
        mValueAnimator.addUpdateListener(this);
        mValueAnimator.addListener(this);
        mValueAnimator.start();
        startColorAnimation();
    }

    private void startColorAnimation() {
        int colorFrom = isChecked ? mOpenBgColor : mCloseBgColor;//mIsOpen为true则表示要启动关闭的动画
        int colorTo = isChecked ? mCloseBgColor : mOpenBgColor;
        ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(500); // milliseconds
        colorAnimation.addUpdateListener(animator -> mCurrentBgColor = (int) animator.getAnimatedValue());
        colorAnimation.start();
    }


    //设置监听
    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        mCheckedChangeListener = listener;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
        if (isChecked) {
            mCurrentBgColor = mOpenBgColor;
            mAnimationFraction = 1.0f;
        } else {
            mCurrentBgColor = mCloseBgColor;
            mAnimationFraction = 0.0f;
        }
        invalidate();
    }

    @Override
    public void onAnimationStart(Animator animator) {
        isAnimation = true;
    }

    @Override
    public void onAnimationEnd(Animator animator) {
        isAnimation = false;
    }

    @Override
    public void onAnimationCancel(Animator animator) {
        isAnimation = false;
    }

    @Override
    public void onAnimationRepeat(Animator animator) {
        isAnimation = true;
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        mAnimationFraction = (float) valueAnimator.getAnimatedValue();
        invalidate();
    }

    public interface OnCheckedChangeListener {
        void onCheckedChanged(String switch_id, boolean isChecked);
    }
}
