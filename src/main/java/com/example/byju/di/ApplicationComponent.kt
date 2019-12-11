package com.example.byju.di

import com.example.byju.ui.NewsActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(newsActivity: NewsActivity)
}