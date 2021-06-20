package com.suki.newsapp.ui.details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.suki.newsapp.R
import com.suki.newsapp.databinding.ActivityNewsDetailsBinding
import com.suki.newsapp.entities.Doc

class NewsDetailsActivity : AppCompatActivity() {

    lateinit var newsItem: Doc
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = DataBindingUtil.setContentView<ActivityNewsDetailsBinding>(this, R.layout.activity_news_details)

        newsItem = intent.getParcelableExtra("newsItem")!!
        binding.newsItem = newsItem
    }
}