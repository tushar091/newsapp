package com.example.byju.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.byju.HeadLinesRepository
import com.example.byju.ui.viewModel.HeadLineViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {
    private val headLinesRepository: HeadLinesRepository

    constructor(headLinesRepository: HeadLinesRepository) {
        this.headLinesRepository = headLinesRepository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeadLineViewModel(headLinesRepository) as T
    }

}