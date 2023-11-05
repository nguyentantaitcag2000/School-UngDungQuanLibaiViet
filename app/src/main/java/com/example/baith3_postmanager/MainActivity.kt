package com.example.baith3_postmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.baith3_postmanager.Constants.NEWS_DATABASE
import com.example.baith3_postmanager.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var btnRefesh: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAddNews.setOnClickListener {
            startActivity(Intent(this,AddNewsActivity::class.java))
        }
        btnRefesh = findViewById(R.id.btnRefesh)
        btnRefesh.setOnClickListener{
            checkItem()
        }

    }
    private val newsDB : NewsDatabase by lazy {
        Room.databaseBuilder(this,NewsDatabase::class.java,NEWS_DATABASE)
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }
    private val newsAdapter by lazy { NewsAdapter(this) }
    override fun onResume() {
        super.onResume()
        checkItem()
    }

    public fun checkItem(){
        Log.d("MainActivity", "All News: ${newsDB.doa().getAllNews()}")

        binding.apply {
            if(newsDB.doa().getAllNews().isNotEmpty()){
                rvNewsList.visibility= View.VISIBLE
                tvEmptyText.visibility=View.GONE
                newsAdapter.differ.submitList(newsDB.doa().getAllNews())
                setupRecyclerView()
            }else{
                rvNewsList.visibility=View.GONE
                tvEmptyText.visibility=View.VISIBLE
            }
        }
    }
    private fun setupRecyclerView(){
        binding.rvNewsList.apply {
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=newsAdapter
        }
    }




}