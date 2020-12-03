package com.lcz.lczed_mvpbase.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.lcz.lczed_mvpbase.R;
import com.lcz.lczed_mvpbase.utils.LogUtils;
import com.lcz.lczed_mvpbase.utils.StatusLayout;
import com.lcz.lczed_mvpbase.utils.StatusUtils;
import com.lcz.lczed_mvpbase.utils.ToastUtil;

import butterknife.BindView;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:33 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    private Context mContext;
    protected P presenter;
    private StatusUtils mStatusUtils;
    private View mRootView;

    /**
     * 记录是否已经创建了,防止重复创建
     */
    private boolean mIsMulti = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(setLayout(), null);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();

        if (parent != null) {
            parent.removeView(mRootView);
        }

        if (presenter == null) {
            presenter = createPresenter();
            presenter.attachView(this);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            initData();
            int viewid = initView();
            ViewGroup byid = findByid(viewid);
            getViewGroup(byid);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            initData();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    protected void getViewGroup(ViewGroup viewGroup) {
        if (mStatusUtils == null) {
            mStatusUtils = StatusUtils.create(viewGroup);
        }
    }

    protected abstract P createPresenter();

    protected abstract int initView();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }


    protected abstract void initData();

    protected abstract int setLayout();

    /**
     * 简化findViewById
     *
     * @param resId
     * @param <T>
     * @return
     */
    protected <T extends View> T findByid(int resId) {
        return mRootView.findViewById(resId);
    }

    /**
     * 显示Toast消息
     *
     * @param message 消息文本字符串
     */
    public final void showToast(@NonNull String message) {
        if (!TextUtils.isEmpty(message)) ToastUtil.showShort(message);
    }


    /**
     * 跳转到指定的Activity
     *
     * @param targetActivity 要跳转的目标Activity
     */
    protected final void startActivity(@NonNull Class<?> targetActivity) {
        startActivity(new Intent(getContext(), targetActivity));
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的String类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull String extraValue, @NonNull Class<?> targetActivity) {
        if (TextUtils.isEmpty(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    /**
     * 跳转到指定的Activity
     *
     * @param extraName      要传递的值的键名称
     * @param extraValue     要传递的int类型值
     * @param targetActivity 要跳转的目标Activity
     */
    public final void startActivity(@NonNull String extraName, @NonNull int extraValue, @NonNull Class<?> targetActivity) {
        if (TextUtils.isEmpty(extraName))
            throw new NullPointerException("传递的值的键名称为null或空");
        final Intent intent = new Intent(getContext(), targetActivity);
        intent.putExtra(extraName, extraValue);
        startActivity(intent);
    }

    /**
     * 带返回值的跳转
     *
     * @param context
     * @param clazz
     * @param bundle
     * @param requestCode
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle, int requestCode) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(bundle);
        getActivity().startActivityForResult(intent, requestCode);
    }

    //加载中
    @Override
    public void showLoading() {
        if (mStatusUtils != null) {
            mStatusUtils.showLoading();
        }
    }

    //加载成功
    @Override
    public void showSucceed() {
        if (mStatusUtils != null) {
            mStatusUtils.showSucceed();
        }
    }

    //无网络
    @Override
    public void showNetError(View.OnClickListener onClickListener) {
        if (mStatusUtils != null) {
            mStatusUtils.showNetError(onClickListener);
        }
    }


    //数据加载失败，提示信息
    @Override
    public void showDataError(String msg, View.OnClickListener onClickListener) {
        showToast(msg);
        if (mStatusUtils != null) {
            mStatusUtils.showDataError(onClickListener);
        }
    }

    @Override
    public void showDataNull() {
        if (mStatusUtils != null) {
            mStatusUtils.showDataNull();
        }
    }
}