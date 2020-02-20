package app.kotlinassignment.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
abstract class BaseUnitTest<VM : BaseViewModel> {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    protected var viewModel: VM? = null
}
