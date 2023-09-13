package com.example.news.ui.browser

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.api.ApiManager
import com.example.news.databinding.FragmentBrowserBinding
import com.example.news.model.Article
import com.example.news.model.ArticlesResponse
import com.example.news.model.TabDM
import com.example.news.model.TabsResponse
import com.example.news.ui.categories.Categories
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.Tab
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BrowserFragment: Fragment( ) {
    private lateinit var binding: FragmentBrowserBinding
    lateinit var articleAdapter: ArticlesAdapter
    lateinit var viewModel: BrowserViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(BrowserViewModel::class.java)
        binding = FragmentBrowserBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getTabs(category.id)
        binding.tabs.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                val id = tab!!.tag as String
                viewModel.getArticles(id)
            }

            override fun onTabUnselected(tab: Tab?) {
                
            }

            override fun onTabReselected(tab: Tab?) {
                val id = tab!!.tag as String
                viewModel.getArticles(id)
            }

        })
        articleAdapter = ArticlesAdapter(null)
        binding.RC.adapter = articleAdapter
        subscribeToLiveData()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun subscribeToLiveData() {
        viewModel.tabsLiveData.observe(viewLifecycleOwner,
            Observer{
                showTabs(it)
            })
        viewModel.progressVisibletyLiveData.observe(viewLifecycleOwner,
        Observer {
            if (it){
                binding.progress.visibility = View.VISIBLE
            }else{
                binding.progress.visibility = View.GONE
            }
        })
        viewModel.articleLiveData.observe(viewLifecycleOwner,
        Observer {
            articleAdapter.changeData(it as List<Article>?)
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner,
        Observer {
            if (it)
            Toast.makeText(this@BrowserFragment.context
              ,"Some thing wrong please try again later!"
             ,Toast.LENGTH_LONG).show()
        })
    }


    private fun showTabs(tabs: List<TabDM?>?) {
        tabs?.forEach{
            val newTab = binding.tabs.newTab()
            newTab.text=it?.name
            newTab.tag = it?.id?:""
            binding.tabs.addTab(newTab)
            val layoutParams =LinearLayout.LayoutParams(newTab.view.layoutParams)
            layoutParams.marginEnd = 12
            layoutParams.marginStart = 12
            newTab.view.layoutParams = layoutParams
        }
    }
    lateinit var category: Categories
    companion object{
        fun getInstance(category: Categories):BrowserFragment{
            val fragment = BrowserFragment()
            fragment.category = category
            return fragment
        }
    }
}