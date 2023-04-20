package co.com.monkeymobile.product_searcher.domain.use_case

import android.util.Log
import co.com.monkeymobile.product_searcher.domain.model.Site
import co.com.monkeymobile.product_searcher.domain.repository.SiteRepository
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class GetSiteListUseCaseTest {

    private val siteRepository: SiteRepository = mock()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default

    private val getSiteListUseCase = GetSiteListUseCase(siteRepository, dispatcher)

    @Before
    fun prepareEnvironment() {
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
    }

    @Test
    fun `test when success`() = runTest {
        val sitesList: List<Site> = mock()
        whenever(siteRepository.fetchSitesList()).thenReturn(sitesList)

        val result: Result<GetSiteListUseCaseResult> = getSiteListUseCase(NoParams)

        assert(result is Result.Success)
        assert((result as Result.Success).data.sites == sitesList)
    }

    @Test
    fun `test when error`() = runTest {
        whenever(siteRepository.fetchSitesList()).thenThrow(RuntimeException())

        val result: Result<GetSiteListUseCaseResult> = getSiteListUseCase(NoParams)

        assert(result is Result.Error)
    }
}
