package co.com.monkeymobile.product_searcher.presentation.product_list

import co.com.monkeymobile.product_searcher.di.DefaultDispatcher
import co.com.monkeymobile.product_searcher.domain.use_case.GetItemListUseCase
import co.com.monkeymobile.product_searcher.domain.use_case.GetItemListUseCaseParams
import co.com.monkeymobile.product_searcher.domain.use_case.Result
import co.com.monkeymobile.product_searcher.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<ProductListViewState, ProductListViewEvent>(coroutineDispatcher) {

    override fun getInitialState() = ProductListViewState.Initial

    override suspend fun processEvent(event: ProductListViewEvent) {
        when (event) {
            is ProductListViewEvent.Initialize -> initalizeEvent(event)
        }
    }

    private suspend fun initalizeEvent(event: ProductListViewEvent.Initialize) {
        setState(ProductListViewState.Loading)

        val siteId = event.siteId
        val query = event.query

        val params = GetItemListUseCaseParams(siteId, query)

        when (val result = getItemListUseCase(params)) {
            is Result.Success -> {
                setState(ProductListViewState.Content(result.data.items))
            }

            is Result.Error -> {
                toastMessage.postValue(result.toString())
                setState(ProductListViewState.Content(emptyList()))
            }
        }
    }
}
