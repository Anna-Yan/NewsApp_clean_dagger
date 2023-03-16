package com.azaqaryan.newsapp.ui.news

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.azaqaryan.newsapp.R
import com.azaqaryan.newsapp.appComponent
import com.azaqaryan.newsapp.databinding.FragmentArticlesBinding
import com.azaqaryan.newsapp.showSnackBar
import com.azaqaryan.newsapp.ui.ArticlesViewModel
import com.azaqaryan.newsapp.ui.adapter.ArticleAdapter
import com.azaqaryan.newsapp.ui.adapter.ArticleLoadStateAdapter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

class ArticlesFragment : Fragment() {
	private var binding: FragmentArticlesBinding? = null
	private val args: ArticlesFragmentArgs by navArgs()

	@Inject
	lateinit var viewModelFactory: ArticlesViewModel.ArticlesFactory.Factory
	private val viewModel: ArticlesViewModel by viewModels {
		viewModelFactory.create(args.sourceId)
	}

	override fun onAttach(context: Context) {
		context.appComponent.inject(this)
		super.onAttach(context)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		val binding = FragmentArticlesBinding.inflate(inflater, container, false)
		this.binding = binding
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val articleAdapter = ArticleAdapter()
		binding?.recyclerArticles?.apply {
			layoutManager = LinearLayoutManager(requireContext())
			adapter = articleAdapter.withLoadStateFooter(
				footer = ArticleLoadStateAdapter { articleAdapter.retry() }
			)
			setHasFixedSize(true)
		}

		//fetch articles
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.fetchArticles().collectLatest { pagingData ->
					articleAdapter.submitData(pagingData)
				}
			}
		}
		//observe loadState
		articleAdapter.addLoadStateListener { loadStates ->
			val refreshState = loadStates.refresh
			val isListEmpty = articleAdapter.itemCount == 0
			val isLoading = loadStates.source.refresh is LoadState.Loading
			// Show/hide the loader view based on the load state
			binding?.progressBar?.visibility = if (isLoading && isListEmpty) View.VISIBLE else View.GONE

			when (refreshState) {
				is LoadState.Error -> {
					if (refreshState.error is IOException) {
						binding?.root?.showSnackBar(R.string.toast_no_connection)
					}
					else
						binding?.root?.showSnackBar(R.string.not_found)
				}
				else -> {}
			}
		}
}

	override fun onDestroy() {
		super.onDestroy()
		binding = null
	}
}
