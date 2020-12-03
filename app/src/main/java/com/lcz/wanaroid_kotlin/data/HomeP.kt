package com.lcz.wanaroid_kotlin.data

import com.lcz.lczed_mvpbase.base.IBasePresenter
import com.lcz.lczed_mvpbase.utils.BaseObserver
import com.lcz.lczed_mvpbase.utils.HttpUtils
import com.lcz.lczed_mvpbase.utils.RxUtils
import com.lcz.wanaroid_kotlin.BaseApi
import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.HomeDataBean
import com.lcz.wanaroid_kotlin.Bean.MainBean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/2 16:43 星期三
 * E-mail: lcz3466601343@163.com
 * Description :
 */
public class HomeP : IBasePresenter<HomeV>() {
    var mview: HomeV? = null

    public override fun getData() {

    }

    public override fun getData(num: Int) {
        val classApi = HttpUtils.getInstance().getApiserver(BaseApi.BaseApi, BaseApi::class.java)
        val max = classApi.getHomeData(num)
        max.compose(RxUtils.rxObserableSchedulerHelper())
            .subscribe(object : BaseObserver<HomeDataBean?>() {

                override fun onNext(mainBean: HomeDataBean) {
                    mview?.showData(mainBean)
                }

                override fun onError(e: Throwable) {
                    mview?.showErr(e.message)
                }
            })
    }

    fun getBanner() {
        val classApi = HttpUtils.getInstance().getApiserver(BaseApi.BaseApi, BaseApi::class.java)
        val max = classApi.getHomeBannerData()
        max.compose(RxUtils.rxObserableSchedulerHelper())
            .subscribe(object : BaseObserver<HomeBanner?>() {

                override fun onNext(mainBean: HomeBanner) {
                    mview?.showbannerData(mainBean)
                }

                override fun onError(e: Throwable) {
                    mview?.showbannerErr(e.message)
                }
            })
    }

    public  override fun setView(view: HomeV?) {
        mview = view
    }
}