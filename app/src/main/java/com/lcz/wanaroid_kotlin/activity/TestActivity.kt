package com.lcz.wanaroid_kotlin.activity

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.lcz.lczed_mvpbase.base.BaseViewActivity
import com.lcz.lczed_mvpbase.utils.DataCleanManager
import com.lcz.lczed_mvpbase.utils.LogUtils
import com.lcz.wanaroid_kotlin.Main.MainP
import com.lcz.wanaroid_kotlin.R
import com.lcz.wanaroid_kotlin.databinding.ActivityTestBinding
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : BaseViewActivity<MainP, ActivityTestBinding>() {
    val mainP: MainP = MainP()

    override fun initListener() {

    }

    override fun getViewBinding(): Class<ActivityTestBinding> {
        return ActivityTestBinding::class.java
    }

    override fun createPresenter(): MainP {
        return mainP;
    }

    override fun initData() {
        viewBinding.tvNumber.text = "123"
//        showLoading()
    }

    override fun initView() {

    }

}