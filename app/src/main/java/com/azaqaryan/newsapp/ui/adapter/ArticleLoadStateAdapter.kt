package com.azaqaryan.newsapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.azaqaryan.newsapp.databinding.LayoutListItemProgressBarBinding

class ArticleLoadStateAdapter(
	private val onRetry: () -> Unit,
) : LoadStateAdapter<ArticleLoadStateAdapter.LoadStateViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
		LoadStateViewHolder(
			LayoutListItemProgressBarBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)

	override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) =
		holder.bind(loadState)

	inner class LoadStateViewHolder(private val binding: LayoutListItemProgressBarBinding) :
		RecyclerView.ViewHolder(binding.root) {

		fun bind(item: LoadState) = with(binding) {
			listItemProgressBar.isVisible = item is LoadState.Loading
			listItemRetryButton.isVisible = item is LoadState.Error
			listItemErrorMsg.isVisible = item is LoadState.Error
			listItemRetryButton.setOnClickListener { onRetry() }
		}
	}
}