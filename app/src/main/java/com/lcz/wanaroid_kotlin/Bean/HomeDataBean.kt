package com.lcz.wanaroid_kotlin.Bean

/**
 *
 *
 * @author: Lczed
 * @date on 2020/12/1 15:00 星期二
 * E-mail: lcz3466601343@163.com
 * Description :
 */
data class HomeDataBean(
    val `data`: Data,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val curPage: Int, // 1
        val datas: List<Data>,
        val offset: Int, // 0
        val over: Boolean, // false
        val pageCount: Int, // 481
        val size: Int, // 20
        val total: Int // 9606
    ) {
        data class Data(
            val apkLink: String,
            val audit: Int, // 1
            val author: String, // DiscoverForever
            val canEdit: Boolean, // false
            val chapterId: Int, // 294
            val chapterName: String, // 完整项目
            val collect: Boolean, // false
            val courseId: Int, // 13
            val desc: String, // Flutter仿京东客户端
            val descMd: String,
            val envelopePic: String, // https://wanandroid.com/blogimgs/2c444bad-d283-42bd-a8ea-9bbca53e8bb1.png
            val fresh: Boolean, // true
            val id: Int, // 16278
            val link: String, // https://www.wanandroid.com/blog/show/2805
            val niceDate: String, // 16小时前
            val niceShareDate: String, // 16小时前
            val origin: String,
            val prefix: String,
            val projectLink: String, // https://github.com/DiscoverForever/learn_flutter
            val publishTime: Long, // 1606746800000
            val realSuperChapterId: Int, // 293
            val selfVisible: Int, // 0
            val shareDate: Long, // 1606746800000
            val shareUser: String,
            val superChapterId: Int, // 294
            val superChapterName: String, // 开源项目主Tab
            val tags: List<Tag>,
            val title: String, // Flutter仿京东客户端
            val type: Int, // 0
            val userId: Int, // -1
            val visible: Int, // 1
            val zan: Int // 0
        ) {
            data class Tag(
                val name: String, // 项目
                val url: String // /project/list/1?cid=294
            )
        }
    }
}