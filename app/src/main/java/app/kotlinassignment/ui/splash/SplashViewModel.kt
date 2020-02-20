package app.kotlinassignment.ui.splash

import androidx.lifecycle.MutableLiveData
import app.kotlinassignment.base.BaseViewModel
import app.kotlinassignment.constants.ClickActionEvents
import kotlinx.coroutines.delay
import javax.inject.Inject

class SplashViewModel @Inject constructor() : BaseViewModel() {
    @ClickActionEvents
    val openScreen = MutableLiveData<String>()

    suspend fun navigateToHomeScreen() {
        delay(1000)
        openScreen.postValue(ClickActionEvents.HOME)
    }
}