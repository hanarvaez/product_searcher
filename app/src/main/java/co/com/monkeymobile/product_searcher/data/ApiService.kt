package co.com.monkeymobile.product_searcher.data

import co.com.monkeymobile.product_searcher.data.source.remote.response.SearchBackendResponse
import co.com.monkeymobile.product_searcher.data.source.remote.response.SiteBackendResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

object ApiUrl {
    const val REST_BASE_URL = "https://api.mercadolibre.com/"

    const val SITE_ID_PATH = "siteId"

    const val SITES_PARTICLE = "sites/"
    const val SEARCH_PARTICLE = "{$SITE_ID_PATH}/search/"

    const val Q_QUERY = "q"
}

interface ApiService {

    @GET(ApiUrl.SITES_PARTICLE)
    suspend fun fetchSitesList(): List<SiteBackendResponse>

    @GET(ApiUrl.SEARCH_PARTICLE)
    suspend fun fetchProductList(
        @Path(ApiUrl.SITE_ID_PATH) siteId: String,
        @Query(ApiUrl.Q_QUERY) query: String
    ): SearchBackendResponse
}
