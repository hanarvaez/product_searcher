package co.com.monkeymobile.product_searcher.presentation.product_detail

import co.com.monkeymobile.product_searcher.di.DefaultDispatcher
import co.com.monkeymobile.product_searcher.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<ProductDetailViewState, ProductDetailViewEvent>(coroutineDispatcher) {

    override fun getInitialState() = ProductDetailViewState.Initial

    override suspend fun processEvent(event: ProductDetailViewEvent) {
        when (event) {
            is ProductDetailViewEvent.Initialize -> buildInitialState(event)
        }
    }

    private fun buildInitialState(event: ProductDetailViewEvent.Initialize) {
        val item = event.item
        if (item == null) {
            setState(ProductDetailViewState.Error)
        } else {
            setState(ProductDetailViewState.Content(item))
        }
    }
}
