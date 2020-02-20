package app.kotlinassignment.di.module

import dagger.Module
import dagger.Provides
import lib.apidomain.network.HomeApi
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}