package com.example.news.ui.main


import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.example.news.R
import com.example.news.databinding.ActivityMainBinding
import com.example.news.ui.categories.CategoriesFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,CategoriesFragment())
            .commit()
        val toggle = ActionBarDrawerToggle(
            this,
            binding.root,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.root.setDrawerListener(toggle)
        toggle.syncState()
    }
}