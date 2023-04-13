package co.com.monkeymobile.product_searcher.presentation.search

import co.com.monkeymobile.product_searcher.domain.model.Site
import co.com.monkeymobile.product_searcher.presentation.ViewState

sealed class SearchViewState : ViewState {

    object Initial : SearchViewState() {

        override fun getName() = "SearchViewState.Initial"
    }

    object Loading : SearchViewState() {

        override fun getName() = "SearchViewState.Loading"
    }

    class Content(val sites: List<Site>) : SearchViewState() {

        override fun getName() = "SearchViewState.Content"
    }
}
