package com.azaqaryan.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azaqaryan.newsapp.databinding.ItemNewsListBinding
import com.azaqaryan.newsapp.domain.entity.News

class NewsAdapter(private val onItemClick: (News) -> Unit) :
	ListAdapter<News, NewsAdapter.NewsSourceViewHolder>(NewsSourceDiffCallback()) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsSourceViewHolder {
		val binding = ItemNewsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return NewsSourceViewHolder(binding)
	}

	override fun onBindViewHolder(holder: NewsSourceViewHolder, position: Int) {
		val source = getItem(position)
		holder.bind(source)
	}

	inner class NewsSourceViewHolder(private val binding: ItemNewsListBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(source: News) {
			binding.sourceTitle.text = source.name
			binding.sourceDescription.text = source.description
			binding.root.setOnClickListener { onItemClick(source) }
		}
	}
}

class NewsSourceDiffCallback : DiffUtil.ItemCallback<News>() {
	override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
		return oldItem.id == newItem.id
	}

	override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
		return oldItem == newItem
	}
}
