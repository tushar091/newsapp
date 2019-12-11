package com.example.byju.di

import android.content.Context
import androidx.room.Room
import com.example.byju.HeadLinesRepository
import com.example.byju.NewsApplication
import com.example.byju.dao.NewsDatabase
import com.example.byju.network.NewsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApplicationModule {

    private val applicationContext: NewsApplication

    constructor(application: NewsApplication) {
        this.applicationContext = application
    }

    @Singleton
    @Provides
    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    fun getApplicationContext(): Context {
        return applicationContext
    }

    @Provides
    fun getNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    fun getHeadlinesRepository(newsService: NewsService, db: NewsDatabase): HeadLinesRepository {
        return HeadLinesRepository(newsService, db)
    }

    @Singleton
    @Provides
    fun getNewsDatabase(context: Context): NewsDatabase {
        return Room.databaseBuilder(
            context.applicationContext, NewsDatabase::class.java, "news_db"
        ).fallbackToDestructiveMigration().build()
    }

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }
}