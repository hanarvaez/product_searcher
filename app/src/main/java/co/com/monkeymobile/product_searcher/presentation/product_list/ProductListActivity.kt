package co.com.monkeymobile.product_searcher.presentation.product_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import co.com.monkeymobile.product_searcher.R
import co.com.monkeymobile.product_searcher.databinding.ActivityProductListBinding
import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListActivity :
    BaseActivity<ProductListViewModel, ProductListViewState, ProductListViewEvent>(),
    ItemAdapter.ItemAdapterListener {

    companion object {

        private const val EXTRA_SITE_ID = "siteId"
        private const val EXTRA_QUERY = "query"

        fun getIntent(context: Context, siteId: String, query: String) =
            Intent(context, ProductListActivity::class.java).apply {
                putExtra(EXTRA_SITE_ID, siteId)
                putExtra(EXTRA_QUERY, query)
            }
    }

    override val viewModel: ProductListViewModel by viewModels()
    private lateinit var binding: ActivityProductListBinding
    private var adapter: ItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductListBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun buildState(state: ProductListViewState) {
        when (state) {
            ProductListViewState.Initial -> buildInitialState()
            ProductListViewState.Loading -> buildLoadingState()
            is ProductListViewState.Content -> buildContentState(state)
        }
    }

    private fun buildInitialState() {
        initializeAdapter()

        val siteId = intent.getStringExtra(EXTRA_SITE_ID).orEmpty()
        val query = intent.getStringExtra(EXTRA_QUERY).orEmpty()

        dispatchEvent(ProductListViewEvent.Initialize(siteId, query))
    }

    private fun buildLoadingState() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            itemsRecyclerView.visibility = View.GONE
        }
    }

    private fun buildContentState(state: ProductListViewState.Content) {
        val items = state.items

        val (errorMessageVisibility, recyclerVisibility) = if (items.isEmpty()) {
            Pair(View.VISIBLE, View.GONE)
        } else {
            Pair(View.GONE, View.VISIBLE)
        }

        val errorText = if (state.isError) {
            getString(R.string.internet_error_text)
        } else {
            getString(R.string.search_no_results_text)
        }

        initializeAdapter()

        with(binding) {
            progressBar.visibility = View.GONE
            errorMessage.text = errorText
            errorMessage.visibility = errorMessageVisibility
            itemsRecyclerView.visibility = recyclerVisibility
        }

        adapter?.submitList(items)
    }

    override fun onItemClicked(item: Item) {

    }

    private fun initializeAdapter() {
        if (adapter == null) {
            adapter = ItemAdapter(this)
            binding.itemsRecyclerView.adapter = adapter
        }
    }
}
