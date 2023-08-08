package com.example.news.ui.browser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.news.api.ApiManager
import com.example.news.databinding.FragmentBrowserBinding
import com.example.news.model.Article
import com.example.news.model.ArticlesResponse
import com.example.news.model.TabDM
import com.example.news.model.TabsResponse
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrowserFragment: Fragment( ) {
    private lateinit var binding: FragmentBrowserBinding
    lateinit var articleAdapter: ArticlesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBrowserBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getTabs()
        binding.tabs.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                val id = tab!!.tag as String
                getArticles(id)
            }

            override fun onTabUnselected(tab: Tab?) {
                
            }

            override fun onTabReselected(tab: Tab?) {
                val id = tab!!.tag as String
                getArticles(id)
            }

        })
        articleAdapter = ArticlesAdapter(null)
        binding.RC.adapter = articleAdapter

    }
    fun getTabs(){
        ApiManager.getApis().getTabs(ApiManager.apiKey)
            .enqueue(object : Callback<TabsResponse>{
                override fun onResponse(
                    call: Call<TabsResponse>,
                    response: Response<TabsResponse>
                ) {
                    binding.progress.isVisible = false
                    Log.e("onResponse","${response.body()}")
                    if (response.body()?.code == null){
                        showTabs(response.body()?.tabs!!)
                    }

                }

                override fun onFailure(call: Call<TabsResponse>, t: Throwable) {
                    binding.progress.isVisible = false
                    Log.e("onFailure","$t")
                    Toast.makeText(this@BrowserFragment.context
                        ,"Some thing wrong please try again later!"
                    ,Toast.LENGTH_LONG).show()
                }

            })
    }

    private fun getArticles(tabId : String){
        binding.progress.isVisible = true
        ApiManager.getApis().getArticles(
            ApiManager.apiKey,
            tabId
        ).enqueue(object :Callback<ArticlesResponse>{
            override fun onResponse(
                call: Call<ArticlesResponse>,
                response: Response<ArticlesResponse>
            ) {
                binding.progress.isVisible = false
                Log.e("onResponse","${response.body()?.articles}")
                articleAdapter.changeData(response?.body()?.articles as List<Article>?)

            }

            override fun onFailure(call: Call<ArticlesResponse>, t: Throwable) {
                binding.progress.isVisible = false
                Log.e("onFailure","$t")
                Toast.makeText(this@BrowserFragment.context
                    ,"Some thing wrong please try again later!"
                    ,Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun showTabs(tabs: List<TabDM?>) {
        tabs.forEach{
            val newTab = binding.tabs.newTab()
                newTab.text=it?.name
            newTab.tag = it?.id?:""
            binding.tabs.addTab(newTab)
        }
    }
}