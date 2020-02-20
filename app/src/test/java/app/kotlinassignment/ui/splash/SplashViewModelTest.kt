package app.kotlinassignment.ui.splash

import app.kotlinassignment.base.BaseUnitTest
import app.kotlinassignment.constants.ClickActionEvents
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class SplashViewModelTest : BaseUnitTest<SplashViewModel>() {
    @Before
    fun setUp() {
        viewModel = SplashViewModel()
    }

    @Test
    fun testNavigateToHomeScreenSuccess() = runBlockingTest {
        Assert.assertNotNull(viewModel)
        viewModel?.navigateToHomeScreen()
        Assert.assertNotNull(viewModel?.openScreen?.value)
        Assert.assertEquals(viewModel?.openScreen?.value, ClickActionEvents.HOME)
    }

    @Test
    fun testNavigateToHomeScreenFail() = runBlockingTest {
        Assert.assertNotNull(viewModel)
        viewModel?.navigateToHomeScreen()
        Assert.assertNotEquals(viewModel?.openScreen?.value, "")
    }
}
