package com.azaqaryan.newsapp.tests.new

import com.azaqaryan.newsapp.common.CommonStates
import com.azaqaryan.newsapp.common.GeneralResult
import com.azaqaryan.newsapp.domain.entity.News
import com.azaqaryan.newsapp.domain.usecase.NewsItemsUseCase
import com.azaqaryan.newsapp.ui.viewmodel.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.azaqaryan.newsapp.data.entity.error.NewsAppException
import kotlinx.coroutines.flow.first

@ExperimentalCoroutinesApi
class NewsViewModelTest {

	@get:Rule
	val instantTaskExecutorRule = InstantTaskExecutorRule()

	private val testDispatcher = StandardTestDispatcher()
	private lateinit var viewModel: NewsViewModel
	private lateinit var newsUseCase: NewsItemsUseCase

	@Before
	fun setup() {
		MockitoAnnotations.openMocks(this)
		Dispatchers.setMain(testDispatcher)
		newsUseCase = mock(NewsItemsUseCase::class.java)
		viewModel = NewsViewModel(newsUseCase)
	}


	@Test
	fun `fetchSources should update state to Normal and update _sources with result data when NewsUseCase returns Success`() =
		runTest {
			//Given
			val sources = listOf(
				News("source1", "NewsSource 1", "url1", "desc1"),
				News("source2", "NewsSource 2", "url2", "desc2")
			)
			`when`(newsUseCase.fetchNews()).thenReturn(
				GeneralResult.Success(sources)
			)

			//When
			newsUseCase.fetchNews()
			testDispatcher.scheduler.advanceUntilIdle()

			//Then
			assert(viewModel.state.value == CommonStates.NORMAL)
			assert(viewModel.sources.first() == sources)
		}

	@Test
	fun `fetchSources should update state to NotFound when NewsUseCase returns Error`() = runTest {
		// GIVEN
		`when`(newsUseCase.fetchNews()).thenReturn(
			GeneralResult.Error(NewsAppException(message = "News not found"))
		)

		// WHEN
		newsUseCase.fetchNews()
		testDispatcher.scheduler.advanceUntilIdle()

		// THEN
		assert(viewModel.state.value == CommonStates.NOT_FOUND)
	}

	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}
}