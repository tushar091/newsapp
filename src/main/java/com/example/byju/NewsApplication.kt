package com.example.byju

import android.app.Application
import androidx.room.Room
import com.example.byju.dao.NewsDatabase
import com.example.byju.di.ApplicationComponent
import com.example.byju.di.ApplicationModule
import com.example.byju.di.DaggerApplicationComponent

class NewsApplication : Application() {

    private lateinit var applicationComponent: ApplicationComponent
    private lateinit var db: NewsDatabase
    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this)).build()
    }

    public fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}