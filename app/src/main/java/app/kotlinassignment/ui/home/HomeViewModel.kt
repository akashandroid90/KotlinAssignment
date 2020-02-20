package app.kotlinassignment.ui.home

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import app.kotlinassignment.R
import app.kotlinassignment.base.BaseViewModel
import kotlinx.coroutines.*
import lib.apidata.data.ItemData
import lib.apidata.repository.HomeRepo
import lib.apidata.response.Result
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val api: HomeRepo
) : BaseViewModel() {
    val itemBinding by lazy {
        ItemBinding.of<ItemData>(BR.data, R.layout.adapter_item).bindExtra(BR.viewModel, this)
    }
    val items = DiffObservableList(ItemDiffUtil())
    val valueData = MutableLiveData<ItemData>()

    fun loadData(reloadData: Boolean = false): Job {
        return GlobalScope.launch(Dispatchers.Main) {
            if (reloadData) items.update(Collections.emptyList())
            withContext(Dispatchers.IO) {
                showProgress.postValue(true)
                api.getList()
            }.let {
                when (it) {
                    is Result.Success -> it.value?.let { it1 -> items.update(it1) }
                    is Result.Error -> it.exception.message
                }
                showProgress.postValue(false)
            }
        }
    }

    fun showData(data: ItemData) {
        valueData.value = data
    }
}