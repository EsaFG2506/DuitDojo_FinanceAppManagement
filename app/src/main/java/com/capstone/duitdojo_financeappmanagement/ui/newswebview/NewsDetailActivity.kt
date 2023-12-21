package com.capstone.duitdojo_financeappmanagement.ui.newswebview

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.capstone.duitdojo_financeappmanagement.R
import com.capstone.duitdojo_financeappmanagement.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        val title = intent.getStringExtra("title")
        val publishedAt = intent.getStringExtra("publishedAt")
        val source = intent.getStringExtra("source")
        val url = intent.getStringExtra("url")
        val description = intent.getStringExtra("description")
        val content = intent.getStringExtra("content")

        binding.apply {
            textTitle.text = title
            textPublishedAt.text = publishedAt
            textSource.text = source
            textUrl.text = url
            textDescription.text = description
            textContent.text = content

            textDescription.gravity = Gravity.START
            textDescription.textAlignment = View.TEXT_ALIGNMENT_TEXT_START

            Linkify.addLinks(textUrl, Linkify.WEB_URLS)
            textUrl.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    @Suppress("DEPRECATION")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}