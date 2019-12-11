package com.example.byju.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.byju.R
import com.example.byju.databinding.ActivityNewsDetailBinding
import com.example.byju.model.Articles
import com.example.byju.ui.viewModel.DetailViewModel

class DetailActivity : FragmentActivity() {

    companion object {
        const val ARTICLE = "ARTICLE"
        fun getIntent(context: Context?, articles: Articles): Intent {
            return Intent(context, DetailActivity::class.java).apply {
                putExtra(ARTICLE, articles)
            }
        }
    }

    private lateinit var detailVm: DetailViewModel
    lateinit var binding: ActivityNewsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_detail)
        val article = intent.getParcelableExtra<Articles>(ARTICLE)
        detailVm = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        if (article != null) {
            detailVm.displayContent(article)
        }
        binding.detailVm = detailVm
    }
}