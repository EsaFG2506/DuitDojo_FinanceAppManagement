package com.capstone.duitdojo_financeappmanagement.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.lifecycleScope
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.data.pref.UserPreference
import com.capstone.duitdojo_financeappmanagement.data.pref.dataStore
import com.capstone.duitdojo_financeappmanagement.ui.MainActivity
import com.capstone.duitdojo_financeappmanagement.ui.slider.SliderActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var pref: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        pref = UserPreference.getInstance(this.dataStore)

        handler.postDelayed({
            lifecycleScope.launch {
                val userModel = pref.getSession().first()
                if (userModel.isLogin) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                } else {
                    startActivity(Intent(this@SplashActivity, SliderActivity::class.java))
                }
                finish()
            }
        },3000)
    }
}