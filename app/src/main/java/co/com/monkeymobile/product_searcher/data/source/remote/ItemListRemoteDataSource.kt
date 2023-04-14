package co.com.monkeymobile.product_searcher.data.source.remote

import co.com.monkeymobile.product_searcher.data.source.remote.response.ItemBackendResponse

interface ItemListRemoteDataSource {

    suspend fun fetchItemList(siteId: String, query: String): List<ItemBackendResponse>
}
