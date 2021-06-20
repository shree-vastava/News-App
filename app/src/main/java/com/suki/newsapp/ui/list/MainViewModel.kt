package com.suki.newsapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suki.newsapp.entities.Doc
import com.suki.newsapp.repository.NewsRepository
import com.suki.newsapp.util.Constants.Companion.API_KEY
import com.suki.newsapp.util.Constants.Companion.COUNTRY
import com.suki.newsapp.util.Resource
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val repository: NewsRepository)  : ViewModel(){

    var newsData = MutableLiveData<Resource<List<Doc>?>>()
    var pageNo = 1

    /**
     * Get news function helps update the UI in
     * the activity and fetched the data from repository
     */
    fun getNews(){
            newsData.value = Resource.loading(null)
            viewModelScope.launch {
            var response = repository.getNews(API_KEY, COUNTRY,pageNo)
                when (response.status) {
                   Resource.Status.SUCCESS  -> {
                       newsData.value = Resource.success(response.data?.response?.docs)
                       pageNo += 1
                    }
                   Resource.Status.ERROR  -> {
                       newsData.postValue(response.message?.let { Resource.error(it,null) })
                    }
                }
        }
    }


}