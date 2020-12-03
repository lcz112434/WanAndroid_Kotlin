package com.lcz.lczed_mvpbase.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.lcz.lczed_mvpbase.R;

/**
 * @data 2018-08-01
 * @desc
 */

public class StatusUtils {

    private Activity mActivity = null;
    private ViewGroup mViewGroup;
    private StatusLayout mStatusLayout;
    private RelativeLayout mRlStatus;

    static StatusUtils mStatusUtils = null;

    public StatusUtils(Activity activity) {
        this.mActivity = activity;
    }

    public StatusUtils(ViewGroup viewGroup) {
        mViewGroup = viewGroup;
    }

    public static StatusUtils create(Activity activity) {
        mStatusUtils = new StatusUtils(activity);
        return mStatusUtils;
    }

    public static StatusUtils create(ViewGroup viewGroup) {
        mStatusUtils = new StatusUtils(viewGroup);
        return mStatusUtils;
    }

    public void showLoading() {
        if (mActivity != null) {
            LogUtils.d("mActivity显示");
            mRlStatus = mActivity.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showLoading();
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showLoading();
        }
    }

    public void showSucceed() {
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showSucceed();
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }

            mStatusLayout.showSucceed();
        }
    }

    public void showNetError(View.OnClickListener onClickListener) {
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showNetError(onClickListener);
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showNetError(onClickListener);
        }
    }

    public void showDataError(View.OnClickListener onClickListener) {
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showDataError(onClickListener);
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showDataError(onClickListener);
        }
    }

    public void showDataNull() {
        if (mActivity != null) {
            mRlStatus = mActivity.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mActivity);
                mActivity.addContentView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showDataNull();
        } else if (mViewGroup != null) {
            mRlStatus = mViewGroup.findViewById(R.id.rlv_state);
            if (mRlStatus == null) {
                mStatusLayout = new StatusLayout(mViewGroup.getContext());
                mViewGroup.addView(mStatusLayout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                mStatusLayout = (StatusLayout) mRlStatus.getParent();
            }
            mStatusLayout.showDataNull();
        }
    }

}