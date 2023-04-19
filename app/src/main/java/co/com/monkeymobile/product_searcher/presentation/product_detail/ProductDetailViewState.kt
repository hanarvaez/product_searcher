package co.com.monkeymobile.product_searcher.presentation.product_detail

import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.presentation.ViewState

sealed class ProductDetailViewState : ViewState {

    object Initial : ProductDetailViewState() {

        override fun getName() = "ProductDetailViewState.Initial"
    }

    class Content(item: Item) : ProductDetailViewState() {

        override fun getName() = "ProductDetailViewState.Content"
    }

    object Error : ProductDetailViewState() {

        override fun getName() = "ProductDetailViewState.Error"
    }
}
