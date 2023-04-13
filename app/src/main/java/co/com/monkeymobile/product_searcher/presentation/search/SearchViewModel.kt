package co.com.monkeymobile.product_searcher.presentation.search

import co.com.monkeymobile.product_searcher.di.DefaultDispatcher
import co.com.monkeymobile.product_searcher.domain.use_case.GetSiteListUseCase
import co.com.monkeymobile.product_searcher.domain.use_case.NoParams
import co.com.monkeymobile.product_searcher.domain.use_case.Result
import co.com.monkeymobile.product_searcher.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSiteListUseCase: GetSiteListUseCase,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<SearchViewState, SearchViewEvent>(coroutineDispatcher) {

    override fun getInitialState() = SearchViewState.Initial

    override suspend fun processEvent(event: SearchViewEvent) {
        when (event) {
            SearchViewEvent.Initialize -> initializeEvent()
            is SearchViewEvent.SiteSelected -> Unit
            is SearchViewEvent.Search -> Unit
        }
    }

    private suspend fun initializeEvent() {
        setState(SearchViewState.Loading)

        when (val result = getSiteListUseCase(NoParams)) {
            is Result.Success -> {
                setState(SearchViewState.Content(result.data.sites))
            }

            is Result.Error -> {
                toastMessage.postValue(result.toString())
                setState(SearchViewState.Content(emptyList()))
            }
        }
    }

}
