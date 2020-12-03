package com.lcz.wanaroid_kotlin.Bean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/1 14:38 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
data class OldChaptersListBean(
    val `data`: Data,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val curPage: Int, // 1
        val datas: List<Data>,
        val offset: Int, // 0
        val over: Boolean, // false
        val pageCount: Int, // 54
        val size: Int, // 20
        val total: Int // 1065
    ) {
        data class Data(
            val apkLink: String,
            val audit: Int, // 1
            val author: String, // 鸿洋
            val canEdit: Boolean, // false
            val chapterId: Int, // 408
            val chapterName: String, // 鸿洋
            val collect: Boolean, // false
            val courseId: Int, // 13
            val desc: String,
            val descMd: String,
            val envelopePic: String,
            val fresh: Boolean, // false
            val id: Int, // 16274
            val link: String, // https://mp.weixin.qq.com/s/_I3ouVOtV70rxWMroqnIFw
            val niceDate: String, // 1天前
            val niceShareDate: String, // 16小时前
            val origin: String,
            val prefix: String,
            val projectLink: String,
            val publishTime: Long, // 1606665600000
            val realSuperChapterId: Int, // 407
            val selfVisible: Int, // 0
            val shareDate: Long, // 1606743984000
            val shareUser: String,
            val superChapterId: Int, // 408
            val superChapterName: String, // 公众号
            val tags: List<Tag>,
            val title: String, // 先学Dart，再战 Flutter | 给 Android 开发者的 Dart 教程
            val type: Int, // 0
            val userId: Int, // -1
            val visible: Int, // 1
            val zan: Int // 0
        ) {
            data class Tag(
                val name: String, // 公众号
                val url: String // /wxarticle/list/408/1
            )
        }
    }
}