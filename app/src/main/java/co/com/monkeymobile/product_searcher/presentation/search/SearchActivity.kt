package co.com.monkeymobile.product_searcher.presentation.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import co.com.monkeymobile.product_searcher.databinding.ActivitySearchBinding
import co.com.monkeymobile.product_searcher.presentation.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<SearchViewModel, SearchViewState, SearchViewEvent>() {

    companion object {

        fun getIntent(context: Context) = Intent(context, SearchActivity::class.java)
    }

    override val viewModel: SearchViewModel by viewModels()
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun buildState(state: SearchViewState) {
        when (state) {
            SearchViewState.Initial -> buildInitialState()
            SearchViewState.Loading -> buildLoadingState()
            is SearchViewState.Content -> buildContentState(state)
        }
    }

    private fun buildInitialState() {
        dispatchEvent(SearchViewEvent.Initialize)
    }

    private fun buildLoadingState() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            siteSelectorLabel.visibility = View.GONE
            siteSelectorSpinner.visibility = View.GONE
            searchInputLabel.visibility = View.GONE
            searchInput.visibility = View.GONE
            buttonSearch.visibility = View.GONE
        }
    }

    private fun buildContentState(state: SearchViewState.Content) {
        val sites = state.sites
        val isSitesEmpty = sites.isEmpty()

        val (isFormVisible, isErrorVisible) = if (isSitesEmpty) {
            Pair(View.GONE, View.VISIBLE)
        } else {
            Pair(View.VISIBLE, View.GONE)
        }

        if (!isSitesEmpty) {
            val spinnerAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                sites.map { it.name }
            )

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            binding.siteSelectorSpinner.adapter = spinnerAdapter
            binding.siteSelectorSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        val site = sites[position]
                        dispatchEvent(SearchViewEvent.SiteSelected(site))
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
                }

        }

        with(binding) {
            progressBar.visibility = View.GONE
            errorMessage.visibility = isErrorVisible
            siteSelectorLabel.visibility = isFormVisible
            siteSelectorSpinner.visibility = isFormVisible
            searchInputLabel.visibility = isFormVisible
            searchInput.visibility = isFormVisible
            buttonSearch.visibility = isFormVisible
        }
    }
}
