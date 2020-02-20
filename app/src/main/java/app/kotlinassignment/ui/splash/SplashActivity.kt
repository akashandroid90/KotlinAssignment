package app.kotlinassignment.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import app.kotlinassignment.R
import app.kotlinassignment.base.AppBaseActivity
import app.kotlinassignment.databinding.ActivitySplashBinding
import app.kotlinassignment.ui.home.HomeActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppBaseActivity<ActivitySplashBinding, SplashViewModel>() {
    override fun layoutId() = R.layout.activity_splash

    override fun provideViewModelClass() = SplashViewModel::class.java

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.openScreen.observe(this, Observer {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        })
        GlobalScope.launch {
            viewModel.navigateToHomeScreen()
        }
    }

}