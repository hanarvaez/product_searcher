package co.com.monkeymobile.product_searcher.data.source.remote.impl

import co.com.monkeymobile.product_searcher.data.source.remote.SiteRemoteDataSource
import co.com.monkeymobile.product_searcher.di.NetworkModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SiteRemoteDataSourceImpl @Inject constructor() : SiteRemoteDataSource {

    override suspend fun fetchSitesList() = NetworkModule.getApiService().fetchSitesList()
}
