package co.com.monkeymobile.product_searcher.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SiteBackendResponse(
    @SerializedName("default_currency_id") val defaultCurrencyId: String?,
    val id: String?,
    val name: String?
) : Parcelable
