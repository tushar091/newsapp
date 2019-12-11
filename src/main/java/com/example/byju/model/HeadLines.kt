package com.example.byju.model

data class HeadLines(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)