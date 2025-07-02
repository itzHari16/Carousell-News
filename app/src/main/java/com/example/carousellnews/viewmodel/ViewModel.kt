package com.example.carousellnews.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carousellnews.data.Data
import com.example.carousellnews.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _news = MutableStateFlow<List<Data>>(emptyList())
    val news: StateFlow<List<Data>> = _news

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            try {
                _news.value = repository.fetchArticles().sortedByDescending { it.time }
            } catch (e: Exception) {
                _news.value = emptyList()
            }
        }
    }

    fun sort(type: SortType) {
        val list = _news.value
        _news.value = when (type) {
            SortType.Recent -> list.sortedByDescending { it.time }
            SortType.Popular -> list.sortedWith(
                compareByDescending<Data> { it.rank }.thenByDescending { it.time }
            )
        }
    }
}

enum class SortType {
    Recent,
    Popular
}