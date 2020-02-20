package app.kotlinassignment.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.android.support.DaggerAppCompatDialogFragment
import javax.inject.Inject

abstract class AppBaseDialogFragment<VB : ViewDataBinding, VM : BaseViewModel> :
    DaggerAppCompatDialogFragment() {
    abstract fun provideViewModelClass(): Class<VM>
    abstract fun layoutId(): Int
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getViewModelOwner(): ViewModelStoreOwner
    protected lateinit var viewBinding: VB
    protected lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(getViewModelOwner(), viewModelFactory)[provideViewModelClass()]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        viewBinding.lifecycleOwner = this
        return viewBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(context!!, theme)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()
    }
}