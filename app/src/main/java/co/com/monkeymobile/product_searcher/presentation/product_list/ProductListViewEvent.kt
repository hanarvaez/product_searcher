package co.com.monkeymobile.product_searcher.presentation.product_list

import co.com.monkeymobile.product_searcher.presentation.ViewEvent

interface ProductListViewEvent : ViewEvent {

    class Initialize(val siteId: String, val query: String) : ProductListViewEvent {

        override fun getName() = "ProductListViewEvent.Initialize"
    }
}
