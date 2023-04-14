package co.com.monkeymobile.product_searcher.domain.use_case

import co.com.monkeymobile.product_searcher.di.DefaultDispatcher
import co.com.monkeymobile.product_searcher.domain.model.Site
import co.com.monkeymobile.product_searcher.domain.repository.SiteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class GetSiteListUseCase @Inject constructor(
    private val siteRepository: SiteRepository,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : SuspendUseCase<NoParams, GetSiteListUseCaseResult>(dispatcher) {

    override suspend fun execute(parameters: NoParams) = GetSiteListUseCaseResult(
        siteRepository.fetchSitesList()
    )
}

data class GetSiteListUseCaseResult(val sites: List<Site>)
