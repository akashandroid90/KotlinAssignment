package app.kotlinassignment.ui.home

import app.kotlinassignment.MyApplication
import app.kotlinassignment.base.BaseUnitTest
import app.kotlinassignment.di.module.ApiModule
import app.kotlinassignment.di.module.ApiRepoModule
import app.kotlinassignment.di.module.AppModule
import app.kotlinassignment.di.module.NetworkModule
import app.kotlinassignment.utils.MockResponse
import lib.apidata.data.ItemData
import me.tatarka.bindingcollectionadapter2.ItemBinding
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class HomeViewModelTest : BaseUnitTest<HomeViewModel>() {
    private val mockWebServer = MockWebServer()
    @Before
    fun setUp() {
        mockWebServer.start()
        val networkModule = NetworkModule()
        val appModule = AppModule(mock(MyApplication::class.java))
        val itemBinding: ItemBinding<ItemData> =
            mock(ItemBinding::class.java) as ItemBinding<ItemData>
        `when`(ItemBinding.of<ItemData>(anyInt(), anyInt())).thenReturn(itemBinding)
        viewModel = HomeViewModel(
            ApiRepoModule().provideHomeRepoImpl(
                ApiModule().provideHomeApi(networkModule.provideRetrofitInterface(networkModule.providesOkHttpClient()))
            )
        )
    }

    @Before
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testLoadDataSuccess() {
        Assert.assertNotNull(viewModel)
        mockWebServer.enqueue(
            MockResponse.createMockResponse(
                "api_response_success",
                HttpURLConnection.HTTP_OK
            )
        )
        viewModel?.loadData()
        viewModel?.items?.isNotEmpty()?.let {
            Assert.assertTrue(it)
        }
    }
}