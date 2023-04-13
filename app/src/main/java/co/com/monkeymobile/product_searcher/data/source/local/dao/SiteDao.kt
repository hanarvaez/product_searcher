package co.com.monkeymobile.product_searcher.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.com.monkeymobile.product_searcher.data.source.local.entities.SITE_NAME_COLUMN_NAME
import co.com.monkeymobile.product_searcher.data.source.local.entities.SITE_TABLE_NAME
import co.com.monkeymobile.product_searcher.data.source.local.entities.SiteEntity

@Dao
interface SiteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserSite(vararg siteEntity: SiteEntity)

    @Query("SELECT * FROM $SITE_TABLE_NAME ORDER BY $SITE_NAME_COLUMN_NAME")
    fun getAllSites(): List<SiteEntity>
}
