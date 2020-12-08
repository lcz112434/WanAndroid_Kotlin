package com.lcz.wanaroid_kotlin.activity

import android.content.Intent
import android.view.KeyEvent
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lcz.lczed_mvpbase.base.BaseActivity
import com.lcz.lczed_mvpbase.utils.LogUtils
import com.lcz.wanaroid_kotlin.Main.MainP
import com.lcz.wanaroid_kotlin.Main.MainV
import com.lcz.wanaroid_kotlin.R
import com.lcz.wanaroid_kotlin.fragment.HomeFragment
import com.lcz.wanaroid_kotlin.fragment.KnowledgeFragment
import com.lcz.wanaroid_kotlin.fragment.MyFragment
import com.lcz.wanaroid_kotlin.fragment.ProjectFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainP>(), MainV {
    val mainP: MainP = MainP()

    override fun initListener() {
    }

    override fun createPresenter(): MainP {
        LogUtils.d("这是子分支")
        return mainP
    }

    override fun initData() {
//        mainP.getData()
        mainP.setView(this)
    }

    override fun initView() {
        val HomeFragment = HomeFragment()
        val MyFragment = MyFragment()
        val KnowledgeFragment = KnowledgeFragment()
        val ProjectFragment = ProjectFragment()
        addFragment(R.id.flv_home, HomeFragment)
        addFragment(R.id.flv_home, MyFragment)
        addFragment(R.id.flv_home, KnowledgeFragment)
        addFragment(R.id.flv_home, ProjectFragment)
        replaceFragment(R.id.flv_home, HomeFragment)
        initTobar("首页")

        nav_home.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.item_navigation_home -> {
                        initTobar("首页")
                        replaceFragment(R.id.flv_home, HomeFragment)
                    }

                    R.id.item_navigation_knowledge -> {
                        initTobar("知识体系")
                        replaceFragment(
                            R.id.flv_home,
                            KnowledgeFragment
                        )
                    }
                    R.id.item_navigation_project -> {
                        initTobar("项目")
                        replaceFragment(R.id.flv_home, ProjectFragment)
                    }
                    R.id.item_navigation_mine -> {
                        initTobar("我的")
                        replaceFragment(R.id.flv_home, MyFragment)
                    }
                }
                return true
            }
        })
        toolbar_title.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, TestActivity::class.java)
            startActivity(intent)
        })
    }

    fun initTobar(title: String) {
        toolbar_title.setText(title)
    }

    override fun initLayout(): Int {
        return R.layout.activity_main
    }

    //点击返回
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2click()
            return true
        }
        return super.onKeyDown(keyCode, event);
    }
}