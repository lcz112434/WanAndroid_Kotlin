package com.lcz.wanaroid_kotlin.fragment

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.lcz.lczed_mvpbase.base.BaseFragment
import com.lcz.wanaroid_kotlin.Main.MainP
import com.lcz.wanaroid_kotlin.R
import kotlinx.android.synthetic.main.fragment_my.*

class MyFragment : BaseFragment<MainP>() {
    val mainP = MainP()

    override fun createPresenter(): MainP {
        return mainP
    }

    override fun initView(): Int {
        return R.id.flv_my
    }

    override fun initData() {

    }

    override fun setLayout(): Int {
        return R.layout.fragment_my
    }

}