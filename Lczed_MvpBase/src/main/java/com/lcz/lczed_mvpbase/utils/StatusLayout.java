package com.lcz.lczed_mvpbase.utils;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.lcz.lczed_mvpbase.R;

/**
 * @author: lichengze
 * @date on 2020/12/1 23:40 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class StatusLayout extends RelativeLayout {

    public Context mContext;
    //没有数据布局
    private LinearLayout ll_page_state_empty;
    //没有网络布局
    private RelativeLayout ll_page_state_nonetwork;
    //加载页面布局
    private RelativeLayout ll_page_state_logding;
    //网络加载失败，重新加载页面
    private RelativeLayout ll_page_state_error;
    //总页面
    private RelativeLayout mStateLayout;

    //定时器
    private CountDownTimer mTimer;

    public StatusLayout(Context context) {
        this(context, null);

    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public void init() {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.layout_state, this, true);
        ll_page_state_empty = inflate.findViewById(R.id.rlv_nobody);
        ll_page_state_nonetwork = inflate.findViewById(R.id.rlv_nonetwork);
        ll_page_state_logding = inflate.findViewById(R.id.rlv_loding);
        ll_page_state_error = inflate.findViewById(R.id.rlv_error);
        mStateLayout = inflate.findViewById(R.id.rlv_state);
    }

    /**
     * 切换页面的布局
     *
     * @param pageState 页面状态 NORMAL  EMPTY  ERROR LODING
     */

    public void changePageState(PageState pageState, OnClickListener onClickListener) {
        switch (pageState) {
            case NORMAL:
                if (mStateLayout.getVisibility() == View.VISIBLE) {
                    mStateLayout.setVisibility(View.GONE);
                }
                break;
            case NoWork:
                caseNoNetWork(onClickListener);
                break;
            case EMPTY:
                caseEMPTY();
                break;
            case LODING:
                caseLoding();
                break;
            case ERROR:
                caseERROR(onClickListener);
                break;
        }
    }

    public enum PageState {
        /**
         * 数据内容显示正常
         */
        NORMAL,
        /**
         * 数据为空
         */
        EMPTY,
        /**
         * 无网络
         */
        NoWork,
        /**
         * 数据加载中
         */
        LODING,
        /**
         * 数据失败
         */
        ERROR
    }

    /**
     * 数据加载中
     */
    public void showLoading() {

        changePageState(PageState.LODING, null);
    }

    /**
     * 数据加载完成，页面隐藏
     */

    public void showSucceed() {
        changePageState(PageState.NORMAL, null);
    }

    /**
     * 设置无网络状态
     */

    public void showNetError(View.OnClickListener onClickListener) {
        changePageState(PageState.NoWork, onClickListener);
    }

    /**
     * 数据加载失败
     */

    public void showDataError(View.OnClickListener onClickListener) {
        changePageState(PageState.ERROR, onClickListener);
    }

    /**
     * 数据为空
     */
    public void showDataNull() {
        changePageState(PageState.EMPTY, null);
    }


    //数据加载失败
    private void caseERROR(OnClickListener onClickListener) {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.VISIBLE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setOnClickListener(onClickListener);
        } else {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.VISIBLE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setOnClickListener(onClickListener);
        }
    }

    //数据加载中
    private void caseLoding() {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.VISIBLE);
        } else {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.VISIBLE);
        }
    }

    //数据为空
    private void caseEMPTY() {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.VISIBLE);
        } else {
            ll_page_state_nonetwork.setVisibility(View.GONE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_empty.setVisibility(View.VISIBLE);
        }
    }

    //无网络状态
    private void caseNoNetWork(OnClickListener onClickListener) {
        if (mStateLayout.getVisibility() == View.GONE) {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.VISIBLE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_nonetwork.setOnClickListener(onClickListener);
            ll_page_state_empty.setVisibility(View.GONE);
        } else {
            mStateLayout.setVisibility(View.VISIBLE);
            ll_page_state_nonetwork.setVisibility(View.VISIBLE);
            ll_page_state_logding.setVisibility(View.GONE);
            ll_page_state_error.setVisibility(View.GONE);
            ll_page_state_nonetwork.setOnClickListener(onClickListener);
            ll_page_state_empty.setVisibility(View.GONE);
        }
    }

    //定时判断是否有网络
    private void onTimeing(OnClickListener mClickListener) {

        mTimer = new CountDownTimer((long) (5 * 1000), 1000) {
            int a = 1;

            @Override
            public void onTick(long millisUntilFinished) {
                if (SystemUtils.checkNetWork()) {
                    LogUtils.d("有网络");
                    showSucceed();
                    a = 0;
                } else {
                    a = 1;
                }
            }

            @Override
            public void onFinish() {
                if (a == 1) {
                    showNetError(mClickListener);
                    ToastUtil.showShort("加载失败");
                } else {
                    ToastUtil.showShort("加载完成");
                }
            }
        };
        mTimer.start();
    }

}