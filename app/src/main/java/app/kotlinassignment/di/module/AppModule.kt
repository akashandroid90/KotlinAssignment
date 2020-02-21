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
class AppModule() {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.baseContext
    }
}