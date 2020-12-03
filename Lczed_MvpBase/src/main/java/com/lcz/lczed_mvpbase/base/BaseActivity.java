package com.lcz.lczed_mvpbase.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.gyf.immersionbar.ImmersionBar;
import com.lcz.lczed_mvpbase.utils.ActivityCollector;
import com.lcz.lczed_mvpbase.utils.OnMultiClickListener;
import com.lcz.lczed_mvpbase.utils.StatusUtils;
import com.lcz.lczed_mvpbase.utils.SystemUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import constant.UiType;
import model.UiConfig;
import update.UpdateAppUtils;

/**
 * @author: Lczed
 * @date on 2020/11/19 10:33 星期四
 * E-mail: lcz3466601343@163.com
 * Description :BaseActivity 基类
 */
public abstract class BaseActivity<P extends IBasePresenter> extends BasePermissionActivity implements IBaseView {

    protected AppCompatActivity activityCompat;
    //获取Context
    protected Context context;
    // 是否允许全屏
    private boolean mAllowFullScreen = false;
    //对应的P层
    protected P presenter;
    //butterknife引用类
    private Unbinder unbinder;
    //是否允许旋转屏幕
    private boolean isAllowScreenRoate = true;
    //定时器
    private CountDownTimer mTimer;
    //封装Toast对象
    private static Toast toast;
    private boolean isExit = false; // 是否退出按钮的转态标记
    private StatusUtils mStatusUtils = StatusUtils.create(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置竖屏模式
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ActivityCollector.addActivity(this);

        //设置屏幕是否可旋转
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        //是否可以全屏
        if (mAllowFullScreen) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        //透明状态栏
        SetTransparentBar();
        activityCompat = this;
        context = this;
        //判断是否有网络，如果无网络则提示对应页面
        if (!SystemUtils.checkNetWork()) {
//            initTitleBar(true, false, "WanAndroid");
            if (mStatusUtils != null) {
                mStatusUtils.showNetError(new OnMultiClickListener() {
                    @Override
                    public void onMultiClick(View v) {
                        onTimeing();
                    }
                });
            }
        } else {
            initCreae();
        }
    }

    //初始化方法
    public void initCreae() {
        setContentView(initLayout());
        ButterKnife.bind(this);
        initView();
        presenter = createPresenter();
        presenter.attachView(this);
        initData();
        initListener();
    }

    //点击事件方法
    protected abstract void initListener();

    /**
     * 页面布局绑定
     */
    protected abstract int initLayout();

    /**
     * 初始化 Toolbar
     *
     * @param toolbar
     * @param homeAsUpEnabled
     * @param title
     */
    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(homeAsUpEnabled);
    }

    protected void initToolBar(Toolbar toolbar, boolean homeAsUpEnabled, int resTitle) {
        initToolBar(toolbar, homeAsUpEnabled, getString(resTitle));
    }

    //是否调用APK更新
    public void setAPKUpdate(String apkUrl, String updateTitle, String updateContent) {
        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.PLENTIFUL);
        UpdateAppUtils
                .getInstance()
                .apkUrl(apkUrl)
                .uiConfig(uiConfig)
                .updateTitle(updateTitle)
                .updateContent(updateContent)
                .update();
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

    /**
     * @param apkUrl        apk下载地址
     * @param updateTitle   标题名称
     * @param updateContent 内容
     * @param imgid         需要配置的logo
     */
    public void setAPKUpdate(String apkUrl, String updateTitle, String updateContent, int imgid) {
        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.PLENTIFUL);
        uiConfig.setUpdateLogoImgRes(imgid);
        UpdateAppUtils
                .getInstance()
                .apkUrl(apkUrl)
                .uiConfig(uiConfig)
                .updateTitle(updateTitle)
                .updateContent(updateContent)
                .update();
    }

    private void onTimeing() {
        showLoading();
        mTimer = new CountDownTimer((long) (1 * 1000), 1000) {
            int a = 1;

            @Override
            public void onTick(long millisUntilFinished) {
                if (SystemUtils.checkNetWork()) {
                    a = 0;
                } else {
                    a = 1;
                }
            }

            @Override
            public void onFinish() {
                if (a == 1) {
                    showDataError("加载失败，请检查网络！", new OnMultiClickListener() {
                        @Override
                        public void onMultiClick(View v) {
                            onTimeing();
                        }
                    });
                } else {
                    initCreae();
                    showSucceed();
                }
            }
        };
        mTimer.start();
    }

    //创建对应的P层
    protected abstract P createPresenter();

    /**
     * 页面数据初始化
     */
    protected abstract void initData();

    /**
     * 页面控件初始化
     */
    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();
        //P层绑定V层
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    /*
    /**
     * 必须重写setContentView注意不能够添加这行代码 目的将当前界面的布局添加到BaseActivity中去
     * super.setContentView(R.layout.activity_base);
     */

    protected <T extends View> T findByid(int resId) {
        return (T) findViewById(resId);
    }

    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    @SuppressLint("ShowToast")
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }

    /**
     * /**
     * 是否允许屏幕旋转
     *
     * @param allowScreenRoate true or false
     */
    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    /**
     * 是否允许全屏
     *
     * @param allowFullScreen
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }


    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (ActivityCollector.activities.size() > 0) {
            ActivityCollector.finishAll();
        }
//        ImmersionBar.with(this).destoy();  //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态

    }


    /**
     * 双击退出程序
     */
    protected void exitBy2click() {
        Timer eExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(BaseActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            eExit = new Timer();
            eExit.schedule(new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {

            finishAffinity();
        }
    }

   /* //返回键返回事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
                finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }*/

}