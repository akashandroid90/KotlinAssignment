package app.kotlinassignment.base

import android.app.Dialog
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


abstract class AppBaseActivity<VB : ViewDataBinding, VM : BaseViewModel> :
    DaggerAppCompatActivity() {
    abstract fun layoutId(): Int
    protected lateinit var viewBinding: VB
    abstract fun provideViewModelClass(): Class<VM>
    protected lateinit var viewModel: VM
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[provideViewModelClass()]
        viewBinding = DataBindingUtil.setContentView(this, layoutId())
        viewBinding.lifecycleOwner = this
        viewBinding.setVariable(BR.viewModel, viewModel)
        viewBinding.executePendingBindings()

        viewModel.showProgress.observe(this, Observer {
            if (it) {
                dialog =
                    AlertDialog.Builder(this).setView(ProgressBar(this)).show().also { dialog ->
                        dialog.window?.setBackgroundDrawable(null)
                    }
            } else dialog?.dismiss()
        })
    }
}