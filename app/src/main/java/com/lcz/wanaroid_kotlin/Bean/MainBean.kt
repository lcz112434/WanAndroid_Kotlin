package com.lcz.wanaroid_kotlin.Bean

 data class MainBean(
    val `data`: Data,
    val errorCode: Int, // 0
    val errorMsg: String
) {
    data class Data(
        val curPage: Int, // 1
        val datas: List<Data>,
        val offset: Int, // 0
        val over: Boolean, // false
        val pageCount: Int, // 13
        val size: Int, // 15
        val total: Int // 181
    ) {
        data class Data(
            val apkLink: String,
            val audit: Int, // 1
            val author: String, // wangjianxiandev
            val canEdit: Boolean, // false
            val chapterId: Int, // 294
            val chapterName: String, // 完整项目
            val collect: Boolean, // false
            val courseId: Int, // 13
            val desc: String, // 简单天气APP，使用彩云天气api，使用Kotlin语言基于MVVM模式结合JetPack组件库：LiveData、ViewModel、Lifecycle、Navigation、Room组件，以及使用协程+Retrofit进行网络请求 开发的一款天气app
            val descMd: String,
            val envelopePic: String, // https://www.wanandroid.com/blogimgs/1493b21f-34d7-4e0f-b33d-1ebe1b432691.png
            val fresh: Boolean, // false
            val id: Int, // 15181
            val link: String, // https://www.wanandroid.com/blog/show/2786
            val niceDate: String, // 2020-09-09 23:42
            val niceShareDate: String, // 2020-09-09 23:42
            val origin: String,
            val prefix: String,
            val projectLink: String, // https://github.com/wangjianxiandev/Weather
            val publishTime: Long, // 1599666121000
            val realSuperChapterId: Int, // 293
            val selfVisible: Int, // 0
            val shareDate: Long, // 1599666121000
            val shareUser: String,
            val superChapterId: Int, // 294
            val superChapterName: String, // 开源项目主Tab
            val tags: List<Tag>,
            val title: String, // 简单天气--Kotlin+JetPack+协程+MVVM架构
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