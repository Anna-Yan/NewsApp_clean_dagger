package com.azaqaryan.newsapp.tests

import MainCoroutineRule
import com.azaqaryan.newsapp.CommonStates
import com.azaqaryan.newsapp.FakeNewsItemsUseCase
import com.azaqaryan.newsapp.data.entity.Source
import com.azaqaryan.newsapp.ui.NewsViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsViewModelTest {

	@get:Rule
	val mainCoroutineRule = MainCoroutineRule()
	private lateinit var viewModel: NewsViewModel
	private lateinit var fakeNewsItemsUseCase: FakeNewsItemsUseCase

	@Before
	fun setup() {
		// Create a fake NewsItemsUseCase implementation and pass it to the NewsViewModel constructor
		fakeNewsItemsUseCase = FakeNewsItemsUseCase()
		viewModel = NewsViewModel(fakeNewsItemsUseCase)
	}

	@Test
	fun `fetchSources success`() = mainCoroutineRule.runBlockingTest {
		val sources = listOf(
			Source("source1", "Source 1", "url1", "desc1"),
			Source("source2", "Source 2","url2", "desc2")
		)
		fakeNewsItemsUseCase.setSources(sources)
		viewModel.fetchSources()

		// Check that the state flow emits the correct values
		val expectedStates = listOf(
			CommonStates.PROGRESS,
			CommonStates.NORMAL
		)
		val actualStates = viewModel.state.take(2).toList()
		assertEquals(expectedStates, actualStates)

		val actualSources = viewModel.sources.first()
		assertEquals(sources, actualSources)
	}

	@Test
	fun `fetchSources failure`() = mainCoroutineRule.runBlockingTest {
		fakeNewsItemsUseCase.setSources(null)
		viewModel.fetchSources()

		val expectedStates = listOf(
			CommonStates.PROGRESS,
			CommonStates.NOT_FOUND
		)
		val actualStates = viewModel.state.take(2).toList()
		assertEquals(expectedStates, actualStates)

		// Check that the sources flow emits an empty list
		val expectedSources = emptyList<Source>()
		val actualSources = viewModel.sources.first()
		assertEquals(expectedSources, actualSources)
	}

}
