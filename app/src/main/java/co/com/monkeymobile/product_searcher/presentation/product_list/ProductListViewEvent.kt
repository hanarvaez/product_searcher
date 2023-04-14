package co.com.monkeymobile.product_searcher.presentation.product_list

import co.com.monkeymobile.product_searcher.presentation.ViewEvent

interface ProductListViewEvent : ViewEvent {

    object Initialize : ProductListViewEvent {

        override fun getName() = "ProductListViewEvent.Initialize"
    }
}
