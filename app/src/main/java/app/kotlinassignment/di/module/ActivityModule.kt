package app.kotlinassignment.di.module

import app.kotlinassignment.ui.home.HomeActivity
import app.kotlinassignment.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * provides builder methods for Dagger
 */
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeActivity(): HomeActivity
}