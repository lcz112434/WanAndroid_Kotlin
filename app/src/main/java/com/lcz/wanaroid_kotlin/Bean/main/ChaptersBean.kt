package com.lcz.wanaroid_kotlin.Bean.main

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/1 14:35 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
data class ChaptersBean(
    val `data`: List<Data>,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val children: List<Any>,
        val courseId: Int, // 13
        val id: Int, // 408
        val name: String, // 鸿洋
        val order: Int, // 190000
        val parentChapterId: Int, // 407
        val userControlSetTop: Boolean, // false
        val visible: Int // 1
    )
}