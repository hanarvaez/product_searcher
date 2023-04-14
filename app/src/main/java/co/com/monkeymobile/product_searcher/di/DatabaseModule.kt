package co.com.monkeymobile.product_searcher.di

import android.content.Context
import androidx.room.Room
import co.com.monkeymobile.product_searcher.data.AppDatabase
import co.com.monkeymobile.product_searcher.data.AppDatabase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
}
