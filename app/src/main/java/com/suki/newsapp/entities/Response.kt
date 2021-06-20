package com.suki.newsapp.entities

data class Response(
    val docs: List<Doc>,
    val meta: Meta
)