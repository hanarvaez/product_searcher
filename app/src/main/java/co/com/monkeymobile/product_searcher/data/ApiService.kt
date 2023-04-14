package co.com.monkeymobile.product_searcher.data

import co.com.monkeymobile.product_searcher.data.source.remote.response.SiteBackendResponse
import retrofit2.http.GET

object ApiUrl {
    const val REST_BASE_URL = "https://api.mercadolibre.com/"

    const val SITES_PARTICLE = "sites/"
}

interface ApiService {

    @GET(ApiUrl.SITES_PARTICLE)
    suspend fun fetchSitesList(): List<SiteBackendResponse>
}