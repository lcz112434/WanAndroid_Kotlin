package com.lcz.wanaroid_kotlin.fragment

import android.view.ViewGroup
import android.widget.FrameLayout
import com.lcz.lczed_mvpbase.base.BaseFragment
import com.lcz.wanaroid_kotlin.Main.MainP
import com.lcz.wanaroid_kotlin.R


class KnowledgeFragment : BaseFragment<MainP>() {
    val mainP = MainP()


    override fun createPresenter(): MainP {
        return mainP
    }

    override fun initView(): Int {
        LogUtils.d("1")
        return R.id.fly_Knowledge
    }

    override fun initData() {
        showDataNull()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_knowledge
    }

}
