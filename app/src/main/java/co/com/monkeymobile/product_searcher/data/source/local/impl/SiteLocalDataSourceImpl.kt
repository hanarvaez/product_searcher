package co.com.monkeymobile.product_searcher.data.source.local.impl

import co.com.monkeymobile.product_searcher.data.AppDatabase
import co.com.monkeymobile.product_searcher.data.source.local.SiteLocalDataSource
import co.com.monkeymobile.product_searcher.data.source.local.entities.SiteEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SiteLocalDataSourceImpl @Inject constructor(private val appDatabase: AppDatabase) :
    SiteLocalDataSource {

    override suspend fun fetchSitesList() = appDatabase.siteDao().getAllSites()

    override suspend fun saveSite(vararg siteEntity: SiteEntity) = appDatabase
        .siteDao()
        .insertSite(*siteEntity)
}