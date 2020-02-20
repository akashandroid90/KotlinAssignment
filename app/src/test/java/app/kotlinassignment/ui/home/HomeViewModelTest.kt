package app.kotlinassignment.ui.home

import app.kotlinassignment.base.BaseUnitTest
import app.kotlinassignment.di.module.ApiModule
import app.kotlinassignment.di.module.ApiRepoModule
import app.kotlinassignment.di.module.NetworkModule
import app.kotlinassignment.utils.MockResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest : BaseUnitTest<HomeViewModel>() {
    private val mockWebServer = MockWebServer()
    @Before
    fun setUp() {
        mockWebServer.start()
        Dispatchers.setMain(TestCoroutineDispatcher())
        val networkModule = NetworkModule()
        viewModel = HomeViewModel(
            ApiRepoModule().provideHomeRepoImpl(
                ApiModule().provideHomeApi(networkModule.provideRetrofitInterface(networkModule.providesOkHttpClient()))
            )
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadDataFail() {
        runBlocking {
            Assert.assertNotNull(viewModel)
//            viewModel?.items?.clear()
            mockWebServer.enqueue(
                MockResponse.createMockResponse(
                    "api_response_fail",
                    HttpURLConnection.HTTP_OK
                )
            )
            viewModel?.loadData()?.join()
            viewModel?.items?.isEmpty()?.let {
                Assert.assertTrue(it)
            }
        }
    }

    @Test
    fun testLoadDataSuccess() {
        runBlocking {
            Assert.assertNotNull(viewModel)
            mockWebServer.enqueue(
                MockResponse.createMockResponse(
                    "api_response_success",
                    HttpURLConnection.HTTP_OK
                )
            )
            viewModel?.loadData()?.join()
            viewModel?.items?.isNotEmpty()?.let {
                Assert.assertTrue(it)
            }
        }
    }
}