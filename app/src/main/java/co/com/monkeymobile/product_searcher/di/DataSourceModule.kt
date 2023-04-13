package co.com.monkeymobile.product_searcher.di

import co.com.monkeymobile.product_searcher.data.source.local.SiteLocalDataSource
import co.com.monkeymobile.product_searcher.data.source.local.impl.SiteLocalDataSourceImpl
import co.com.monkeymobile.product_searcher.data.source.remote.SiteRemoteDataSource
import co.com.monkeymobile.product_searcher.data.source.remote.impl.SiteRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun provideSiteRemoteDataSource(source: SiteRemoteDataSourceImpl): SiteRemoteDataSource

    @Binds
    abstract fun provideSiteLocalDataSource(source: SiteLocalDataSourceImpl): SiteLocalDataSource
}
