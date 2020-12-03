package com.lcz.wanaroid_kotlin.Bean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/1 15:03 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
data class FriendDataBean(
    val `data`: List<Data>,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val category: String, // 源码
        val icon: String,
        val id: Int, // 22
        val link: String, // https://www.androidos.net.cn/sourcecode
        val name: String, // androidos
        val order: Int, // 11
        val visible: Int // 1
    )
}