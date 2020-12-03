package com.lcz.wanaroid_kotlin

import com.lcz.wanaroid_kotlin.Bean.HomeBanner
import com.lcz.wanaroid_kotlin.Bean.HomeDataBean
import com.lcz.wanaroid_kotlin.Bean.MainBean
import com.lcz.wanaroid_kotlin.Bean.OldChaptersListBean
import com.lcz.wanaroid_kotlin.Bean.main.ChaptersBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *
 *
 * @author: Lczed
 * @date on 2020/11/26 10:49 星期四
 * E-mail: lcz3466601343@163.com
 * Description :
 */
interface BaseApi {

    //测试环境
    companion object {
        const val BaseApi = "https://www.wanandroid.com"
    }

    //获取公众号列表
    @GET("/wxarticle/chapters/json")
    fun getChapters(): Observable<ChaptersBean>

    //查看某个公众号历史数据
    @GET("/wxarticle/list/{id}/1/json")
    fun getoldChapters(@Path("id") id: Int): Observable<OldChaptersListBean>

    //首页
    @GET("/article/list/{code}/json")
    fun getHomeData(@Path("code") code: Int): Observable<HomeDataBean>

    //首页Banner
    @GET("/banner/json")
    fun getHomeBannerData(): Observable<HomeBanner>

//    //常用网站
//    @GET("/friend/json")
//    fun getFriendData(): Observable<HomeDataBean>
//
    @GET("/project/list/1/json?cid=294")
    fun getData(): Observable<MainBean>
//
//    //查看热搜https://www.wanandroid.com/
//    @GET("/hotkey/json")
//    fun getTopData(): Observable<MainBean>

}