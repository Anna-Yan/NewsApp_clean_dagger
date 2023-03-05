import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.azaqaryan.newsapp.data.CommonStates
import com.azaqaryan.newsapp.databinding.FragmentArticlesBinding
import com.azaqaryan.newsapp.ui.NewsViewModel
import com.azaqaryan.newsapp.ui.adapter.ArticleAdapter
import com.azaqaryan.newsapp.ui.adapter.ArticleLoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ArticlesFragment : Fragment() {
	private var binding: FragmentArticlesBinding? = null
	private val viewModel: NewsViewModel by viewModels()
	private lateinit var articleAdapter: ArticleAdapter
	private val args: ArticlesFragmentArgs by navArgs()

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
		binding?.recyclerArticles?.apply {
			layoutManager = LinearLayoutManager(requireContext())
			adapter = articleAdapter.withLoadStateFooter(
				footer = ArticleLoadStateAdapter {
					articleAdapter.retry()
				}
			)
		}
		viewLifecycleOwner.lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.fetchArticles(args.sourceId).collectLatest { pagingData ->
					articleAdapter.submitData(pagingData)
				}
			}
		}
		articleAdapter.addLoadStateListener { state: CombinedLoadStates ->
			binding?.progressBar?.isVisible =
				state.refresh == LoadState.Loading && articleAdapter.itemCount == 0
		}
	}

	override fun onDestroy() {
		super.onDestroy()
		binding = null
	}
}
