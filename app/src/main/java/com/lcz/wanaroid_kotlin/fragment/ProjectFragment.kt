package com.lcz.wanaroid_kotlin.fragment

import com.lcz.lczed_mvpbase.base.BaseFragment
import com.lcz.lczed_mvpbase.utils.LogUtils
import com.lcz.wanaroid_kotlin.Main.MainP
import com.lcz.wanaroid_kotlin.R

class ProjectFragment : BaseFragment<MainP>() {
    val mainP = MainP()


    override fun createPresenter(): MainP {
        return mainP
    }

     override fun initView(): Int {
         showNetError { showToast("无网络") }
        return R.id.flv_project
    }

    override fun initData() {


//        viewBinding.tvproject.text="111111111"
        LogUtils.d("11111")
    }


    override fun setLayout(): Int {
        return R.layout.fragment_project
    }


}