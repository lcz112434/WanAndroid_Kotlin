package com.lcz.wanaroid_kotlin.Bean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/1 15:01 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
data class HomeBanner(
    val `data`: List<Data>,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val desc: String, // 享学~
        val id: Int, // 29
        val imagePath: String, // https://wanandroid.com/blogimgs/184b499f-dc69-41f1-b519-ff6cae530796.jpeg
        val isVisible: Int, // 1
        val order: Int, // 0
        val title: String, // Android开发简历怎么写？让你的简历通过率提高200%！
        val type: Int, // 0
        val url: String // https://www.bilibili.com/video/BV1fA411x7bV
    )
}