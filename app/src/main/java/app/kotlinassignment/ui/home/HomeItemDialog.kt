package app.kotlinassignment.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelStoreOwner
import app.kotlinassignment.R
import app.kotlinassignment.base.AppBaseDialogFragment
import app.kotlinassignment.databinding.AdapterItemBinding

class HomeItemDialog : AppBaseDialogFragment<AdapterItemBinding, HomeViewModel>() {
    override fun provideViewModelClass() = HomeViewModel::class.java

    override fun layoutId() = R.layout.adapter_item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.data = viewModel.valueData.value
        viewBinding.root.isClickable = false
        viewBinding.executePendingBindings()
    }

    override fun getViewModelOwner(): ViewModelStoreOwner {
        activity?.let {
            return it
        }
        return this
    }

}