package com.lcz.wanaroid_kotlin.data

import com.lcz.lczed_mvpbase.base.BaseView
import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.HomeDataBean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/2 16:43 星期三
 * E-mail: lcz3466601343@163.com
 * Description :
 */
interface HomeV : BaseView {
    override fun showErr(mag: String?) {
    }

      fun showbannerErr(mag: String?) {
    }

    fun showData(data: HomeDataBean?) {
    }

    fun showbannerData(data: HomeBanner?) {
    }

}