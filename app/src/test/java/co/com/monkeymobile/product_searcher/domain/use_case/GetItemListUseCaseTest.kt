package co.com.monkeymobile.product_searcher.domain.use_case

import android.util.Log
import co.com.monkeymobile.product_searcher.domain.model.Item
import co.com.monkeymobile.product_searcher.domain.repository.ItemListRepository
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
class GetItemListUseCaseTest {

    private val itemListRepository: ItemListRepository = mock()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default

    private val getItemListUseCase = GetItemListUseCase(itemListRepository, dispatcher)

    private val params: GetItemListUseCaseParams = mock()

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
        val itemList: List<Item> = mock()
        whenever(params.siteId).thenReturn("")
        whenever(params.query).thenReturn("")
        whenever(itemListRepository.fetchItemList(params.siteId, params.query)).thenReturn(itemList)

        val result: Result<GetItemListUseCaseResult> = getItemListUseCase(params)

        assert(result is Result.Success)
        assert((result as Result.Success).data.items == itemList)
    }

    @Test
    fun `test when error`() = runTest {
        whenever(params.siteId).thenReturn("")
        whenever(params.query).thenReturn("")
        whenever(itemListRepository.fetchItemList(params.siteId, params.query)).thenThrow(RuntimeException())

        val result: Result<GetItemListUseCaseResult> = getItemListUseCase(params)

        assert(result is Result.Error)
    }
}
