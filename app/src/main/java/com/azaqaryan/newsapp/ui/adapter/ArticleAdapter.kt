package com.azaqaryan.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.azaqaryan.newsapp.databinding.ItemArticleListBinding
import com.azaqaryan.newsapp.domain.entity.Article

class ArticleAdapter : PagingDataAdapter<Article, ArticleAdapter.ArticleViewHolder>(COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
		val binding = ItemArticleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return ArticleViewHolder(binding)
	}

	override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
		getItem(position)?.let { article ->
			holder.bind(article)
		}
	}

	inner class ArticleViewHolder(
		private val binding: ItemArticleListBinding,
	) : RecyclerView.ViewHolder(binding.root) {

		fun bind(article: Article) = with(binding) {
				articleTitle.text = article.title
				articleDescription.text = article.description
				articleAuthor.text = article.author ?: "unknown author"
				articlePublishedAt.text = article.publishedAt?.format()
			  articleImage.load(article.urlToImage)
		}
	}

	companion object {
		val COMPARATOR = object : DiffUtil.ItemCallback<Article>() {
			override fun areItemsTheSame(
				oldItem: Article,
				newItem: Article
			): Boolean = oldItem.id == newItem.id

			override fun areContentsTheSame(
				oldItem: Article,
				newItem: Article
			): Boolean = oldItem == newItem
		}
	}
}
