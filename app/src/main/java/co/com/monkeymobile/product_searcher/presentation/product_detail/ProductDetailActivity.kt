package co.com.monkeymobile.product_searcher.presentation.product_detail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import co.com.monkeymobile.product_searcher.R
import co.com.monkeymobile.product_searcher.databinding.ActivityProductDetailBinding
import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.presentation.BaseActivity
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailActivity :
    BaseActivity<ProductDetailViewModel, ProductDetailViewState, ProductDetailViewEvent>() {

    companion object {

        private const val EXTRA_ITEM = "item"

        fun getIntent(context: Context, item: Item) = Intent(context, ProductDetailActivity::class.java).apply {
            putExtra(EXTRA_ITEM, item)
        }
    }

    override val viewModel: ProductDetailViewModel by viewModels()
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun buildState(state: ProductDetailViewState) {
        when(state) {
            ProductDetailViewState.Initial -> buildInitialState()
            is ProductDetailViewState.Content -> buildContentState(state)
            ProductDetailViewState.Error -> buildErrorState()
        }
    }

    private fun buildInitialState() {
        val item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_ITEM, Item::class.java)
        } else {
            intent.getParcelableExtra(EXTRA_ITEM) as? Item
        }

        dispatchEvent(ProductDetailViewEvent.Initialize(item))
    }

    private fun buildContentState(state: ProductDetailViewState.Content){
        val item = state.item
        with(binding) {
            errorMessage.visibility = View.GONE

            title.text = item.title
            Glide.with(this@ProductDetailActivity)
                .load(item.imageUrl)
                .into(image)
            condition.text = item.condition
            price.text = getString(R.string.price_placeholder).format(item.price.toString(), item.currency)
            scroll.visibility = View.VISIBLE
        }
    }

    private fun buildErrorState() {
        with(binding) {
            errorMessage.visibility = View.VISIBLE
            scroll.visibility = View.GONE
        }
    }
}
