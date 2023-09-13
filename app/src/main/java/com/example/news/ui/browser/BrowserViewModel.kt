package com.example.news.ui.browser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.news.api.ApiManager
import com.example.news.model.Article
import com.example.news.model.ArticlesResponse
import com.example.news.model.TabDM
import com.example.news.model.TabsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrowserViewModel :ViewModel(){
    var tabsLiveData = MutableLiveData<List<TabDM?>?>()
    var progressVisibletyLiveData = MutableLiveData<Boolean>()
    var articleLiveData = MutableLiveData<List<Article?>?>()
    var errorLiveData = MutableLiveData<Boolean>()
    fun getTabs(categoryId:String){
        progressVisibletyLiveData.value = true
        ApiManager.getApis().getTabs(ApiManager.apiKey,
            categoryId)
            .enqueue(object : Callback<TabsResponse>{
                override fun onResponse(
                    call: Call<TabsResponse>,
                    response: Response<TabsResponse>
                ) {
                    progressVisibletyLiveData.value = false
                    Log.e("onResponse","${response.body()}")
                    if (response.body()?.code == null){
                        tabsLiveData.value = response.body()?.tabs
                    //  showTabs(response.body()?.tabs!!)
                    }

                }

                override fun onFailure(call: Call<TabsResponse>, t: Throwable) {
                    progressVisibletyLiveData.value = false
                    //Log.e("onFailure","$t")
                    //Toast.makeText(this@BrowserFragment.context
                      //  ,"Some thing wrong please try again later!"
                        //,Toast.LENGTH_LONG).show()
                    errorLiveData.value = true
                }

            })
    }
    fun getArticles(tabId : String){
        progressVisibletyLiveData.value = true
        ApiManager.getApis().getArticles(
            ApiManager.apiKey,
            tabId
        ).enqueue(object :Callback<ArticlesResponse>{
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                progressVisibletyLiveData.value = false
               // Log.e("onResponse","${response.body()?.articles}")
                //articleAdapter.changeData(response?.body()?.articles as List<Article>?)
                articleLiveData.value = response.body()?.articles
            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                progressVisibletyLiveData.value = false
                //Log.e("onFailure","$t")
                //Toast.makeText(this@BrowserFragment.context
                  //  ,"Some thing wrong please try again later!"
                   // ,Toast.LENGTH_LONG).show()
                errorLiveData.value = true
            }

        })
    }
}