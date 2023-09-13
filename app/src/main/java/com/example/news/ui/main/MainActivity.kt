package com.example.news.ui.main


import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.ui.browser.BrowserFragment
import com.example.news.ui.categories.Categories
import com.example.news.ui.categories.CategoriesFragment
import com.example.news.ui.settings.SettingsFragment


class MainActivity : AppCompatActivity(), CategoriesFragment.OnCategoryClickListener {
    private lateinit var binding: ActivityMainBinding
    val categoriesFragment = CategoriesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        categoriesFragment.onCategoryClickListener = this

        val toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.setDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_categories->{
                    showCategoriesFragment()
                }
                R.id.nav_settings->{
                    showSettingsFragment()
                }
            }
            return@setNavigationItemSelectedListener true
        }
        showCategoriesFragment()
    }
    fun showBrowserFragment(category: Categories){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, BrowserFragment.getInstance(category))
            .addToBackStack("null")
            .commit()
    }

    override fun onCategoryClick(category: Categories) {
        showBrowserFragment(category)
    }
    fun showCategoriesFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, categoriesFragment)
            .addToBackStack("null")
            .commit()
    }
    fun showSettingsFragment(){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, SettingsFragment())
            .addToBackStack("null")
            .commit()
    }
}