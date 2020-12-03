package com.lcz.wanaroid_kotlin.fragment

import android.view.ViewGroup
import android.widget.FrameLayout
import com.lcz.lczed_mvpbase.base.BaseFragment
import com.lcz.wanaroid_kotlin.Main.MainP
import com.lcz.wanaroid_kotlin.R

class ProjectFragment : BaseFragment<MainP>() {
    val mainP = MainP()


    override fun createPresenter(): MainP {
        return mainP
    }

    override fun initView(): Int {
        return R.id.flv_project
    }

    override fun initData() {
        showNetError { showToast("无网络") }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_project
    }

}