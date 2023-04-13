package co.com.monkeymobile.product_searcher.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

const val SITE_TABLE_NAME = "site"
const val SITE_ID_COLUMN_NAME = "id"
const val SITE_NAME_COLUMN_NAME = "name"

@Entity(tableName = SITE_TABLE_NAME)
data class SiteEntity(
    val defaultCurrencyId: String,
    @PrimaryKey @ColumnInfo(name = SITE_ID_COLUMN_NAME) val id: String,
    @ColumnInfo(name = SITE_NAME_COLUMN_NAME) val name: String
)
