package co.com.monkeymobile.product_searcher.data.source.remote

import co.com.monkeymobile.product_searcher.data.source.remote.response.SiteBackendResponse

interface SiteRemoteDataSource {

    suspend fun fetchSitesList(): List<SiteBackendResponse>
}
