package lib.apidomain.network

import lib.apidata.data.ItemData
import retrofit2.Call
import retrofit2.http.GET

/**
 *  provides methods to fetch data from network
 */

interface HomeApi {
    @GET("/todos")
    fun getListAsync(): Call<MutableList<ItemData>>

    @GET("/todos")
    fun getListAsyncAwait(): Call<ItemData>
}
