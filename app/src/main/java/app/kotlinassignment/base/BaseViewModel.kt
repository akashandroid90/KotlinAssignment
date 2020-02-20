package app.kotlinassignment.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val showProgress = MutableLiveData(false)
}