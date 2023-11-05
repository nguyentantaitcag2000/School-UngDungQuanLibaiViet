package com.example.baith3_postmanager

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.baith3_postmanager.Constants.BUNDLE_NEWS_ID
import com.example.baith3_postmanager.databinding.ItemNewsBinding

class NewsAdapter(context: Context): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private lateinit var binding: ItemNewsBinding
    private lateinit var context: Context
    private var mContext = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNewsBinding.inflate(inflater, parent, false)
        context = parent.context
        return ViewHolder()
    }
    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.setIsRecyclable(false)
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    inner class ViewHolder : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: NewsEntity) {
//InitView
            binding.apply {
//Set text
                tvTitle.text = item.newsTitle
                tvDesc.text= item.newsDesc
                root.setOnClickListener {
                    val intent= Intent(context, UpdateNewsActivity::class.java)
                    intent.putExtra(BUNDLE_NEWS_ID, item.newsId)
                    context.startActivity(intent)
//                    startActivityForResult(intent, 100)
                    (mContext as MainActivity).checkItem()
                    Log.d("Adapter cua toi", "zzzzzzzzzz")

                }

            }
        }

    }
    fun updateUI()
    {

    }
    private val differCallback = object : DiffUtil.ItemCallback<NewsEntity>() {
        override fun areItemsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem.newsId == newItem.newsId
        }
        override fun areContentsTheSame(oldItem: NewsEntity, newItem: NewsEntity): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}