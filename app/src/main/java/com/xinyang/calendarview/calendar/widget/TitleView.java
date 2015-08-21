package com.xinyang.calendarview.calendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xinyang.calendarview.R;

/**
 * 页面头部的view
 *
 * @author xinyang
 * @time 2015/6/2.
 */
public class TitleView extends LinearLayout implements View.OnClickListener {

    private TextView mTitleBarTv;
    private TextView mTitleBarRightTv;
    private LinearLayout mTitleBarBack;
    private TitleViewListener mTitleViewListener;

    public TitleView(Context context) {
        super(context);
        initView();
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.title_view_layout, this);
        mTitleBarBack = (LinearLayout) view.findViewById(R.id.title_bar_back);
        mTitleBarBack.setOnClickListener(this);
        mTitleBarTv = (TextView) view.findViewById(R.id.title_bar_tv);
        mTitleBarRightTv = (TextView)view.findViewById(R.id.title_bar_right_tv);
        mTitleBarRightTv.setOnClickListener(this);
    }

    public void setTitleText(String title) {
        mTitleBarTv.setText(title);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_bar_back:
                mTitleViewListener.finishActivity();
                break;
            case R.id.title_bar_right_tv:
                mTitleViewListener.saveSchedule();
                break;
            default:

                break;
        }
    }

    public interface TitleViewListener {
        void finishActivity();

        void saveSchedule();
    }

    public void setmTitleViewListener(TitleViewListener mTitleViewListener) {
        this.mTitleViewListener = mTitleViewListener;
    }

    public void HiddenView() {
        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
        mHiddenAction.setDuration(200);
        this.startAnimation(mHiddenAction);
        this.setVisibility(GONE);
    }

    public void ShowView() {
        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowAction.setDuration(200);
        this.startAnimation(mShowAction);
        this.setVisibility(VISIBLE);
    }

    public LinearLayout getmTitleBarBack() {
        return mTitleBarBack;
    }

    public TextView getmTitleBarRightTv() {
        return mTitleBarRightTv;
    }
}
