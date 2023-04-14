package co.com.monkeymobile.product_searcher.data

import androidx.room.Database
import androidx.room.RoomDatabase
import co.com.monkeymobile.product_searcher.data.source.local.dao.SiteDao
import co.com.monkeymobile.product_searcher.data.source.local.entities.SiteEntity

@Database(
    entities = [
        SiteEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "product-searcher-local"
    }

    abstract fun siteDao(): SiteDao
}
