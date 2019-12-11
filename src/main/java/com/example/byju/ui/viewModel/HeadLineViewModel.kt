package com.example.byju.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.byju.HeadLinesRepository
import com.example.byju.model.HeadLines
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable

class HeadLineViewModel(private val repository: HeadLinesRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    var headLinesLiveData = MutableLiveData<HeadLines>()
    init {
        val disposable = repository.headLinesSubject.subscribe {
            headLinesLiveData.postValue(it)
        }
        compositeDisposable.add(disposable)
    }

    fun fetchNews() {
        repository.fetchHeadLines()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
        repository.onClose()
    }
}