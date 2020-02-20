package app.kotlinassignment.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import app.kotlinassignment.R
import app.kotlinassignment.base.AppBaseActivity
import app.kotlinassignment.databinding.ActivityMainBinding

class HomeActivity : AppBaseActivity<ActivityMainBinding, HomeViewModel>() {

    override fun layoutId() = R.layout.activity_main

    override fun provideViewModelClass() = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.valueData.observe(this, Observer {
            it?.let {
                HomeItemDialog().show(supportFragmentManager, null)
            }
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setSupportActionBar(viewBinding.toolbar)
        viewBinding.rvList.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        viewModel.loadData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(R.string.reload)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.title) {
            getString(R.string.reload) -> {
                viewModel.loadData(true)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
