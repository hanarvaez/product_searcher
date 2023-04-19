package co.com.monkeymobile.product_searcher.presentation.product_detail

import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.presentation.ViewEvent

sealed class ProductDetailViewEvent : ViewEvent {

    class Initialize(val item: Item?) : ProductDetailViewEvent() {

        override fun getName() = "ProductDetailViewEvent.Initialize"
    }
}
