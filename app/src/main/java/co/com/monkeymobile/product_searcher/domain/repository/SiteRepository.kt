package co.com.monkeymobile.product_searcher.domain.repository

import co.com.monkeymobile.product_searcher.domain.model.Site

interface SiteRepository {

    suspend fun fetchSitesList(): List<Site>
}