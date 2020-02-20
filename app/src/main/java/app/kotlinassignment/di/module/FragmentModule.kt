package app.kotlinassignment.di.module

import app.kotlinassignment.ui.home.HomeItemDialog
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * provides builder methods for Dagger
 */
@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeItemDialog(): HomeItemDialog
}