package com.capstone.duitdojo_financeappmanagement.ui.slider

import android.content.Context
import android.graphics.text.LineBreaker
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.PagerAdapter
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.data.model.SliderResponse

class SliderAdapter(
    private val context: Context,
    private val sliderList: ArrayList<SliderResponse>
) : PagerAdapter() {

    override fun getCount(): Int {
        return sliderList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as RelativeLayout
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater: LayoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val view: View = layoutInflater.inflate(R.layout.slider_item, container, false)

        val imageView: ImageView = view.findViewById(R.id.idIVSlider)
        val sliderHeadingTV: TextView = view.findViewById(R.id.idTVSliderTitle)
        val sliderDescTV: TextView = view.findViewById(R.id.idTVSliderDescription)

        val sliderResponse: SliderResponse = sliderList[position]
        sliderHeadingTV.text = sliderResponse.slideTitle
        sliderDescTV.text = sliderResponse.slideDescription

        sliderDescTV.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD

        imageView.setImageResource(sliderResponse.slideImage)

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }

}