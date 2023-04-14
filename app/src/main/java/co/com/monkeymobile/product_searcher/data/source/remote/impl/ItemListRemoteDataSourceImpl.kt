package co.com.monkeymobile.product_searcher.data.source.remote.impl

import co.com.monkeymobile.product_searcher.data.source.remote.ItemListRemoteDataSource
import co.com.monkeymobile.product_searcher.di.NetworkModule
import javax.inject.Singleton

@Singleton
class ItemListRemoteDataSourceImpl : ItemListRemoteDataSource {

    override suspend fun fetchItemList(siteId: String, query: String) = NetworkModule
        .getApiService()
        .fetchProductList(siteId, query)
        .itemList ?: emptyList()
}
