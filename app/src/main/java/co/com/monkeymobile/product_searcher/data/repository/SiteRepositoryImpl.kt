package co.com.monkeymobile.product_searcher.data.repository

import co.com.monkeymobile.product_searcher.data.source.local.SiteLocalDataSource
import co.com.monkeymobile.product_searcher.data.source.local.entities.SiteEntity
import co.com.monkeymobile.product_searcher.data.source.local.entities.toSite
import co.com.monkeymobile.product_searcher.data.source.remote.SiteRemoteDataSource
import co.com.monkeymobile.product_searcher.domain.model.Site
import co.com.monkeymobile.product_searcher.domain.repository.SiteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SiteRepositoryImpl @Inject constructor(
    private val localDataSource: SiteLocalDataSource,
    private val remoteDataSource: SiteRemoteDataSource
) : SiteRepository {

    override suspend fun fetchSitesList(): List<Site> {
        val savedSites = localDataSource.fetchSitesList().map { it.toSite() }.toMutableList()

        if (savedSites.isEmpty()) {
            val remoteSites = remoteDataSource.fetchSitesList()

            val siteEntities = remoteSites.mapNotNull {
                if (it.defaultCurrencyId.isNullOrEmpty() || it.id.isNullOrEmpty() || it.name.isNullOrEmpty()) {
                    null
                } else {
                    SiteEntity(it.defaultCurrencyId, it.id, it.name)
                }
            }

            localDataSource.saveSite(*siteEntities.toTypedArray())

            savedSites.addAll(siteEntities.map { it.toSite() })
            savedSites.sortBy { it.name }
        }

        return savedSites.toList()
    }
}
