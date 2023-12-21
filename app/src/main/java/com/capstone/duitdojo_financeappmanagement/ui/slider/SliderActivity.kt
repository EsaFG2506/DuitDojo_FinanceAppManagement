package com.capstone.duitdojo_financeappmanagement.ui.slider

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.data.model.SliderResponse
import com.capstone.duitdojo_financeappmanagement.databinding.ActivitySliderBinding
import com.capstone.duitdojo_financeappmanagement.ui.login.LoginActivity

class SliderActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySliderBinding
    private lateinit var sliderAdapter: SliderAdapter
    private lateinit var sliderList: ArrayList<SliderResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySliderBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.idBtnSkip.setOnClickListener {
            val i = Intent(this@SliderActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }

        sliderList = ArrayList()

        sliderList.add(
            SliderResponse(
                "Welcome To DuitDojo",
                "Welcome to DuitDojo, your financial companion empowering students with intuitive budgeting tools and financial literacy for a stable future.",
                R.drawable.welcome_slider
            )
        )

        sliderList.add(
            SliderResponse(
                "Manage Your Finance",
                "Effortlessly manage your finances with DuitDojo user-friendly features, facilitating budget planning and expense tracking for efficient financial management during your academic journey.",
                R.drawable.managefinance_slider
            )
        )

        sliderList.add(
            SliderResponse(
                "Update With News",
                "Stay informed and empowered – access the latest financial insights, news, and tips tailored for students to make informed decisions and enhance financial literacy through DuitDojo News Updates.",
                R.drawable.updatenews_slider
            )
        )

        sliderAdapter = SliderAdapter(this, sliderList)
        binding.apply {
            idViewPager.adapter = sliderAdapter
            idViewPager.addOnPageChangeListener(viewListener)
        }
    }

    private var viewListener: ViewPager.OnPageChangeListener = object : ViewPager.OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            binding.apply {
                when (position) {
                    0 -> {
                        idTVSlideTwo.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.grey))
                        idTVSlideThree.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.grey))
                        idTVSlideOne.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.white))
                        idBtnSkip.setText(R.string.skip)
                    }
                    1 -> {
                        idTVSlideTwo.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.white))
                        idTVSlideThree.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.grey))
                        idTVSlideOne.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.grey))
                        idBtnSkip.setText(R.string.skip)
                    }
                    else -> {
                        idTVSlideTwo.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.grey))
                        idTVSlideThree.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.white))
                        idTVSlideOne.setTextColor(ContextCompat.getColor(this@SliderActivity, R.color.grey))
                        idBtnSkip.setText(R.string.start)
                    }
                }
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}