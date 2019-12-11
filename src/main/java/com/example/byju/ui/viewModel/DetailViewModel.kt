package com.example.byju.ui.viewModel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.byju.model.Articles

class DetailViewModel : ViewModel() {
    var imageUrl = ObservableField<String>()
    var content = ObservableField<String>()
    var source = ObservableField<String>()
    var time = ObservableField<String>()
    var headLines = ObservableField<String>()

    fun displayContent(articles: Articles) {
        imageUrl.set(articles.urlToImage)
        content.set(articles.content)
        source.set(articles.source?.name)
        time.set(articles.publishedAt)
        headLines.set(articles.title)
    }
}