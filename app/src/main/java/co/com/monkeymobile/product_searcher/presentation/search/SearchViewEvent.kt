package co.com.monkeymobile.product_searcher.presentation.search

import co.com.monkeymobile.product_searcher.domain.model.Site
import co.com.monkeymobile.product_searcher.presentation.ViewEvent

sealed class SearchViewEvent : ViewEvent {

    object Initialize : SearchViewEvent() {

        override fun getName() = "SearchViewEvent.Initialize"
    }

    class SiteSelected(val site: Site) : SearchViewEvent() {

        override fun getName() = "SearchViewEvent.SiteSelected"
    }
}
