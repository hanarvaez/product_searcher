package co.com.monkeymobile.product_searcher.di

import co.com.monkeymobile.product_searcher.data.repository.SiteRepositoryImpl
import co.com.monkeymobile.product_searcher.domain.repository.SiteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindSiteRepository(siteRepositoryImpl: SiteRepositoryImpl): SiteRepository
}