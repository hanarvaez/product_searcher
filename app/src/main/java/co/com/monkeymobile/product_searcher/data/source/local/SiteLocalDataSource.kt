package co.com.monkeymobile.product_searcher.data.source.local

import co.com.monkeymobile.product_searcher.data.source.local.entities.SiteEntity

interface SiteLocalDataSource {

    suspend fun fetchSitesList(): List<SiteEntity>
}