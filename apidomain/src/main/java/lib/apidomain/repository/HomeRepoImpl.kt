package lib.apidomain.repository

import lib.apidata.data.ItemData
import lib.apidata.repository.HomeRepo
import lib.apidata.response.Result
import lib.apidomain.network.HomeApi
import retrofit2.await

class HomeRepoImpl(private val api: HomeApi) : HomeRepo {
    override suspend fun getList(): Result<MutableList<ItemData>?> {
        return try {
            Result.Success(api.getListAsync().await())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(e)
        }
    }
}