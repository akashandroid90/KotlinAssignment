package app.kotlinassignment.di.module

import dagger.Module
import dagger.Provides
import lib.apidata.repository.HomeRepo
import lib.apidomain.network.HomeApi
import lib.apidomain.repository.HomeRepoImpl

@Module
class ApiRepoModule {

    @Provides
    fun provideHomeRepoImpl(api: HomeApi): HomeRepo =
        HomeRepoImpl(api)
}