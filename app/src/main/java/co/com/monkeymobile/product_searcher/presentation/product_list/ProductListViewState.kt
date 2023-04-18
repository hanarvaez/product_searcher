package co.com.monkeymobile.product_searcher.presentation.product_list

import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.presentation.ViewState

sealed class ProductListViewState : ViewState {

    object Initial : ProductListViewState() {

        override fun getName() = "ProductListViewState.Initial"
    }

    object Loading : ProductListViewState() {

        override fun getName() = "ProductListViewState.Loading"
    }

    class Content(val items: List<Item>, val isError: Boolean = false) : ProductListViewState() {

        override fun getName() = "ProductListViewState.Content"
    }
}
