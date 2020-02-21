package app.kotlinassignment.di.component

import app.kotlinassignment.MyApplication
import app.kotlinassignment.di.module.*
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 *  initialize modules used for Dagger to provide instance to application
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, NetworkModule::class,
        ActivityModule::class, FragmentModule::class, ViewModelModule::class, ApiRepoModule::class, ApiModule::class]
)
interface AppComponent {
    fun inject(app: MyApplication)
}