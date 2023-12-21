package com.capstone.duitdojo_financeappmanagement.ui.news

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.capstone.duitdojo_financeappmanagement.data.model.NewsResponse
import com.capstone.duitdojo_financeappmanagement.utils.Result
import com.capstone.duitdojo_financeappmanagement.repository.NewsRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class NewsViewModel(app: Application, val newsRepository: NewsRepository): AndroidViewModel(app) {

    val headlines: MutableLiveData<Result<NewsResponse>> = MutableLiveData()
    var headlinesPage = 1
    var headlinesResponse: NewsResponse? = null

    init {
        getHeadlines("id","business")
    }

    fun getHeadlines(countryCode: String, category: String) = viewModelScope.launch {
        headlinesInternet(countryCode,category)
    }

    private fun handleHeadlineResponse(response: Response<NewsResponse>): Result<NewsResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                headlinesPage++
                if (headlinesResponse == null){
                    headlinesResponse = resultResponse
                } else {
                    val oldArticle = headlinesResponse?.articles
                    val newArticle = resultResponse.articles
                    oldArticle?.addAll(newArticle)
                }
                return Result.Success(headlinesResponse ?: resultResponse)
            }
        }
        return Result.Error(response.message())
    }

    fun internetConnection(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE)as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }

    private suspend fun headlinesInternet(countryCode: String, category: String) {
        headlines.postValue(Result.Loading)
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsRepository.geHeadlines(countryCode, category, headlinesPage)
                headlines.postValue(handleHeadlineResponse(response))
            } else {
                headlines.postValue(Result.Error("No Internet Connection"))
            }
        } catch (t: Throwable) {
            when(t) {
                is IOException -> headlines.postValue(Result.Error("Unable to Connect"))
                else -> headlines.postValue(Result.Error("No Signal"))
            }
        }
    }
}