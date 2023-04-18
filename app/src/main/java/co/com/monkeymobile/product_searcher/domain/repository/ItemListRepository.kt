package co.com.monkeymobile.product_searcher.domain.repository

import co.com.monkeymobile.product_searcher.domain.model.Item

interface ItemListRepository {

    suspend fun fetchItemList(siteId: String, query: String): List<Item>
}