import com.azaqaryan.newsapp.FakeNewsItemsUseCase
import com.azaqaryan.newsapp.data.entity.ArticleSource
import com.azaqaryan.newsapp.ui.ArticlesViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArticlesViewModelTest {

	@get:Rule
	val mainCoroutineRule = MainCoroutineRule()

	private lateinit var fakeNewsItemsUseCase: FakeNewsItemsUseCase
	private lateinit var articlesViewModel: ArticlesViewModel
	// A fake source ID for testing purposes
	private val fakeSourceId = "fake_source_id"

	@Before
	fun setup() {
		// Initialize the fake use case and paging source
		fakeNewsItemsUseCase = FakeNewsItemsUseCase()
		// Set up the ArticlesViewModel
		articlesViewModel = ArticlesViewModel.ArticlesFactory(fakeSourceId, fakeNewsItemsUseCase)
			.create(ArticlesViewModel::class.java)
	}

	@Test
	fun `fetchArticles success`() = mainCoroutineRule.runBlockingTest {
		// Prepare test data
		val articles = listOf(
			ArticleSource("article1", "ArticleSource 1", "url1", "desc1", "1"),
			ArticleSource("article2", "ArticleSource 2", "url2", "desc2", "2")
		)
		fakeNewsItemsUseCase.setArticles(fakeSourceId, articles)
		val result = articlesViewModel.fetchArticles()

		// Check that the result contains the expected articles
		assertEquals(articles, result)
	}

	@Test
	fun `fetchArticles failure`() = mainCoroutineRule.runBlockingTest {
		fakeNewsItemsUseCase.setArticles(fakeSourceId, null)

		// Check that the articles flow emits an empty list
		val expectedArticles = emptyList<ArticleSource>()
		val actualArticles = articlesViewModel.fetchArticles().first()
		assertEquals(expectedArticles, actualArticles)
	}
}
