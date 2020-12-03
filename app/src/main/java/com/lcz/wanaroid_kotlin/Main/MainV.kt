package com.lcz.wanaroid_kotlin.Main

import com.lcz.lczed_mvpbase.base.BaseView
import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.MainBean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/11/26 10:35 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
interface MainV : BaseView {
    override fun showErr(mag: String?) {
    }

    fun showData(data: HomeBanner?) {

    }
}