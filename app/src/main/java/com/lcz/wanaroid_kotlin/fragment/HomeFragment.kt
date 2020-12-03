package com.lcz.wanaroid_kotlin.fragment

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.lcz.lczed_mvpbase.base.BaseFragment
import com.lcz.lczed_mvpbase.utils.LogUtils
import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.HomeDataBean
import com.lcz.wanaroid_kotlin.R
import com.lcz.wanaroid_kotlin.data.HomeP
import com.lcz.wanaroid_kotlin.data.HomeV
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeP>(), HomeV {
    val mHomeP: HomeP = HomeP()
    val mlist = ArrayList<HomeDataBean.Data.Data>()
    override fun createPresenter(): HomeP {
        return mHomeP
    }

    override fun initView(): Int {
        homg_tv.text = "主页"
        return R.id.home_flv
    }

    override fun initData() {
        val num = 0
        mHomeP.getBanner()
        mHomeP.getData(num)
        mHomeP.setView(this)
        showLoading()

    }

    override fun setLayout(): Int {
        return R.layout.fragment_home
    }

    override fun showData(data: HomeDataBean?) {
        super.showData(data)

        mlist.addAll(data?.data?.datas!!)
        val size = mlist.size
        LogUtils.d("主页$size")
        if (size!! > 0) {
            showSucceed()
        }
    }

    override fun showbannerData(data: HomeBanner?) {
        super.showbannerData(data)
        val list = data?.data?.size
        LogUtils.d("Banner$list")
    }

    override fun showErr(mag: String?) {
        super.showErr(mag)
        showDataError(mag, View.OnClickListener { })
    }

    override fun showbannerErr(mag: String?) {
        super.showbannerErr(mag)
        showDataError(mag, View.OnClickListener { })
    }


}