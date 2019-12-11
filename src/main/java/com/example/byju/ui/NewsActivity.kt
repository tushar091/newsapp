package com.example.byju.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.byju.HeadLinesRepository
import com.example.byju.NewsApplication
import com.example.byju.R
import com.example.byju.di.ViewModelFactory
import com.example.byju.ui.adapter.HeadlineAdapter
import com.example.byju.ui.viewModel.HeadLineViewModel
import javax.inject.Inject

class NewsActivity : FragmentActivity() {

    @Inject
    lateinit var headLinesRepository: HeadLinesRepository

    lateinit var headLineVM: HeadLineViewModel
    lateinit var headlineAdapter: HeadlineAdapter
    lateinit var headlinesRv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        (application as NewsApplication).getApplicationComponent().inject(this)
        val factory = ViewModelFactory(headLinesRepository)
        headLineVM = ViewModelProviders.of(this, factory)[HeadLineViewModel::class.java]
        headlineAdapter = HeadlineAdapter()
        headlinesRv = findViewById(R.id.headlinesRv)
        headlinesRv.adapter = headlineAdapter
        headlinesRv.layoutManager = LinearLayoutManager(this)
        headLineVM.headLinesLiveData.observe(this, Observer {
            headlineAdapter.headlineList = it.articles.toMutableList()
            headlineAdapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        headLineVM.fetchNews()
    }
}