package com.lcz.wanaroid_kotlin.fragment

import android.R.attr.banner
import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.lcz.lczed_mvpbase.base.BaseFragment
import com.lcz.lczed_mvpbase.utils.GlideUtlis
import com.lcz.lczed_mvpbase.utils.LogUtils
import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.HomeDataBean
import com.lcz.wanaroid_kotlin.R
import com.lcz.wanaroid_kotlin.adatper.HomeAdapter
import com.lcz.wanaroid_kotlin.data.HomeP
import com.lcz.wanaroid_kotlin.data.HomeV
import com.lxj.xpopup.interfaces.XPopupImageLoader
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import com.youth.banner.loader.ImageLoader

import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : BaseFragment<HomeP>(), HomeV {

    val mHomeP: HomeP = HomeP()
    val mlist = ArrayList<HomeDataBean.Data.Data>()
    val madapter = HomeAdapter();
    val mBannerList = ArrayList<String>()

    override fun createPresenter(): HomeP {
        return mHomeP
    }

    override fun initView(): Int {
        home_rlv.layoutManager = LinearLayoutManager(context)
        home_rlv.adapter = madapter
        LogUtils.d("initView")




        return R.id.home_flv
    }

    override fun initData() {
        LogUtils.d("initData")
        val num = 1
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
        val size = data?.data?.datas?.size
        if (size != null) {
            if (size > 0) {
                showSucceed()
                mlist.addAll(data?.data?.datas!!)
                LogUtils.d("$size")
                madapter.setNewInstance(mlist)
                madapter.notifyDataSetChanged()
            }
        }
    }

    override fun showbannerData(data: HomeBanner?) {
        super.showbannerData(data)
        for (datum in data?.data!!) {
            mBannerList.add(datum.imagePath);
        }
        if(data.data!=null){
            //设置内置样式，共有六种可以点入方法内逐一体验使用。
            home_banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
            //设置图片加载器，图片加载器在下方
            home_banner.setImageLoader(MyLoader());
            //设置图片网址或地址的集合
            home_banner.setImages(mBannerList);
            //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
            home_banner.setBannerAnimation(Transformer.Default);
            //设置轮播间隔时间
            home_banner.setDelayTime(3000);
            //设置是否为自动轮播，默认是“是”。
            home_banner.isAutoPlay(true);
            //设置指示器的位置，小点点，左中右。
            home_banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                //必须最后调用的方法，启动轮播图。
                .start();
        }
    }

    override fun showErr(mag: String?) {
        super.showErr(mag)
        showDataError(mag, View.OnClickListener { })
    }

    override fun showbannerErr(mag: String?) {
        super.showbannerErr(mag)
        showDataError(mag, View.OnClickListener { })
    }

    //自定义的图片加载器
    private inner class MyLoader : ImageLoader() {
        override fun displayImage(context: Context, path: Any, imageView: ImageView) {
            Glide.with(context).load(path as String).into(imageView)
        }
    }
}



