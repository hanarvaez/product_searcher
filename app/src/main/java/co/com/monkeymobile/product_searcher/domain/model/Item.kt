package co.com.monkeymobile.product_searcher.domain.model

data class Item (
    val id: String,
    val title: String,
    val condition: String,
    val imageUrl: String,
    val currency: String,
    val price: Double
)
