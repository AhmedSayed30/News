package com.example.news.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.news.databinding.FragmentCategoriesBinding
import com.example.news.ui.browser.ArticlesAdapter

class CategoriesFragment: Fragment( ) {
    private lateinit var binding: FragmentCategoriesBinding
    var categoryAdapter = CategoriesAdapter(Categories.getCategoriesList())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.rcCategories.adapter = categoryAdapter
    }
}