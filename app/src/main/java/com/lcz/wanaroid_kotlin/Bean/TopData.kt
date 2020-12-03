package com.lcz.wanaroid_kotlin.Bean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/1 15:04 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
data class TopData(
    val `data`: List<Data>,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val id: Int, // 6
        val link: String,
        val name: String, // 面试
        val order: Int, // 1
        val visible: Int // 1
    )
}