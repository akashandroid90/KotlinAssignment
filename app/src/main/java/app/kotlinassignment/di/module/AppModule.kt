package app.kotlinassignment.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * provides application level instances
 */
@Module
class AppModule(private val application: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.baseContext
    }
}