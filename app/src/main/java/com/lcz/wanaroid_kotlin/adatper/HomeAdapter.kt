package com.lcz.wanaroid_kotlin.adatper

import android.widget.ImageView
import android.widget.TextView

import com.chad.library.adapter.base.BaseBinderAdapter
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lcz.lczed_mvpbase.utils.GlideUtlis
import com.lcz.wanaroid_kotlin.Bean.HomeDataBean
import com.lcz.wanaroid_kotlin.R

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/8 15:27 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
class HomeAdapter() :
    BaseQuickAdapter<HomeDataBean.Data.Data, BaseViewHolder>(R.layout.item_home) {

    override fun convert(holder: BaseViewHolder, item: HomeDataBean.Data.Data) {
        holder.getView<TextView>(R.id.itemhome_tv).setText(item.title)
        GlideUtlis.loadImageView(
            context,
            item.envelopePic,
            holder.getView<ImageView>(R.id.itemhome_iv)
        )
    }
}