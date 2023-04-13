package co.com.monkeymobile.product_searcher.presentation.product_list

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.com.monkeymobile.product_searcher.R

class ProductListActivity : AppCompatActivity() {

    companion object {

        private const val EXTRA_SITE_ID = "siteId"
        private const val EXTRA_QUERY = "query"

        fun getIntent(context: Context, siteId: String, query: String) =
            Intent(context, ProductListActivity::class.java).apply {
                putExtra(EXTRA_SITE_ID, siteId)
                putExtra(EXTRA_QUERY, query)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)
    }
}
