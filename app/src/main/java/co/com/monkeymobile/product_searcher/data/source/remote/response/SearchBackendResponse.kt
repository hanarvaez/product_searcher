package co.com.monkeymobile.product_searcher.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchBackendResponse(
    @SerializedName("results") val itemList: List<ItemBackendResponse>?
) : Parcelable
