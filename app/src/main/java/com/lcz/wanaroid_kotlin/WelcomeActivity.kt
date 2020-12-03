package com.lcz.wanaroid_kotlin

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import com.lcz.lczed_mvpbase.base.BasePermissionActivity
import com.lcz.lczed_mvpbase.utils.ActivityCollector
import com.lcz.lczed_mvpbase.utils.LogUtils
import com.lcz.wanaroid_kotlin.activity.MainActivity

class WelcomeActivity : BasePermissionActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SetTransparentBar()
        setContentView(R.layout.activity_welcome)
        ActivityCollector.addActivity(this)
        var mTimer = object : CountDownTimer((2 * 1000).toLong(), 1000) {

            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                setIntent()
                LogUtils.d("用户进入到欢迎界面")
            }
        }
        mTimer.start()
    }

    fun setIntent() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}