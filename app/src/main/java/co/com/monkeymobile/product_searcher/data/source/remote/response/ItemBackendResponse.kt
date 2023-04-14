package co.com.monkeymobile.product_searcher.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemBackendResponse(
    val id: String?,
    val title: String?,
    val condition: String?,
    @SerializedName("thumbnail") val imageUrl: String?,
    @SerializedName("currency_id") val currency: String?,
    val price: Int?
) : Parcelable
