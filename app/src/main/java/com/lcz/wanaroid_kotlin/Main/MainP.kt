package com.lcz.wanaroid_kotlin.Main

import com.lcz.lczed_mvpbase.base.IBasePresenter
import com.lcz.lczed_mvpbase.utils.BaseObserver
import com.lcz.lczed_mvpbase.utils.HttpUtils
import com.lcz.lczed_mvpbase.utils.RxUtils
import com.lcz.wanaroid_kotlin.BaseApi
import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.MainBean
import com.lcz.wanaroid_kotlin.Constant

/**
 *
 *
 * @author: Lczed
 * @date on 2020/11/26 10:35 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
class MainP : IBasePresenter<MainV>() {
    var mView: MainV? = null

    public override fun getData() {
        val classApi = HttpUtils.getInstance().getApiserver(BaseApi.BaseApi, BaseApi::class.java)
        val max = classApi.getHomeBannerData()
        max.compose(RxUtils.rxObserableSchedulerHelper())
            .subscribe(object : BaseObserver<HomeBanner?>() {

                override fun onNext(mainBean: HomeBanner) {
                    mView?.showData(mainBean)
                }

                override fun onError(e: Throwable) {
                    mView?.showErr(e.message)
                }
            })
    }

    public override fun setView(view: MainV?) {
        mView = view
    }

    override fun getData(num: Int) {

    }
}