package co.com.monkeymobile.product_searcher.data.repository

import co.com.monkeymobile.product_searcher.data.source.remote.ItemListRemoteDataSource
import co.com.monkeymobile.product_searcher.data.source.remote.response.toItem
import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.domain.repository.ItemListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ItemListRepositoryImpl @Inject constructor(
    private val remoteDataSource: ItemListRemoteDataSource
) : ItemListRepository {

    override suspend fun fetchItemList(siteId: String, query: String): List<Item> {
        val remoteItemList = remoteDataSource.fetchItemList(siteId, query)
        return remoteItemList.mapNotNull { it.toItem() }
    }
}
