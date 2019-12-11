package com.example.byju

import android.util.Log
import com.example.byju.dao.ArticlesTable
import com.example.byju.dao.NewsDatabase
import com.example.byju.model.Articles
import com.example.byju.model.HeadLines
import com.example.byju.model.NewsSource
import com.example.byju.network.NewsService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.*
import java.util.concurrent.TimeUnit

class HeadLinesRepository(private val newsService: NewsService, private val db: NewsDatabase) {
    private val compositeDisposable = CompositeDisposable()
    var headLinesSubject: PublishSubject<HeadLines> = PublishSubject.create()

    fun fetchHeadLines() {
        compositeDisposable.add(
            newsService.getArticles(COUNTRY_INDIA, API_KEY)
                .timeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    saveAndPublishData(headLines = it)
                }, {
                    Log.e("error 2", it.message)
                    fetchHeadLinesFromDb()
                })
        )
    }

    private fun saveAndPublishData(headLines: HeadLines) {
        if (headLines.articles.isNotEmpty()) {
            val disposable = Single.fromCallable { db.articlesDao().deleteArticles() }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("Db", "deletion done") },
                    { Log.e("error 1", "insertFailed " + it.message) })
            compositeDisposable.add(disposable)

        }
        compositeDisposable.add(
            Single.fromCallable {
                headLines.articles.map {
                    val articlesRow = ArticlesTable(
                        author = it.author,
                        content = it.content, title = it.title, description = it.description,
                        url = it.url, urlToImage = it.urlToImage, publishedAt = it.publishedAt,
                        source = it.source?.name
                    )
                    db.articlesDao().insertArticle(articlesRow)
                }
            }
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.d("Db", "insertion done") },
                    { Log.e("error 1", "insertFailed " + it.message) })
        )
        headLinesSubject.onNext(headLines)
    }

    private fun fetchHeadLinesFromDb() {
        val disposable = Single.fromCallable {
            val articles = db.articlesDao().getArticles().map {
                Articles(
                    author = it.author, title = it.title,
                    content = it.content, description = it.description,
                    urlToImage = it.urlToImage,
                    url = it.url, publishedAt = it.publishedAt,
                    source = NewsSource(EMPTY_STRING, it.source)
                )
            }
            HeadLines("fromDb", articles.size, articles)
        }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ headLinesSubject.onNext(it) }, { Log.e("Db error", it.message) })

        compositeDisposable.add(disposable)
    }


    fun onClose() {
        compositeDisposable.dispose()
    }
}