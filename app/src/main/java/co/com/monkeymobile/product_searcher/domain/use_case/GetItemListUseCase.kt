package co.com.monkeymobile.product_searcher.domain.use_case

import co.com.monkeymobile.product_searcher.di.DefaultDispatcher
import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.domain.repository.ItemListRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class GetItemListUseCase @Inject constructor(
    private val itemListRepository: ItemListRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<GetItemListUseCaseParams, GetItemListUseCaseResult>(dispatcher) {

    override suspend fun execute(parameters: GetItemListUseCaseParams) = GetItemListUseCaseResult(
        itemListRepository.fetchItemList(
            parameters.siteId,
            parameters.query
        )
    )
}

data class GetItemListUseCaseParams(val siteId: String, val query: String)

data class GetItemListUseCaseResult(val items: List<Item>)
