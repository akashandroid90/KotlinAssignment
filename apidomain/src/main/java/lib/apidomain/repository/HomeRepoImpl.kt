package lib.apidomain.repository

import lib.apidata.data.ItemData
import lib.apidata.repository.HomeRepo
import lib.apidata.response.Result
import lib.apidomain.network.HomeApi
import lib.apidomain.retryIO
import retrofit2.await
import java.net.UnknownHostException

class HomeRepoImpl(private val api: HomeApi) : HomeRepo {
    private fun onError(e: Exception, lastcall: Boolean = false): Result<MutableList<ItemData>?>? {
        return when {
            lastcall -> Result.Error(e)
            e is UnknownHostException -> null
            else -> Result.Error(e)
        }
    }

    override suspend fun getList(): Result<MutableList<ItemData>?>? {
        return try {
            retryIO(times = 3, onError = { onError(it) }) {
                Result.Success(api.getListAsync().await())
            }
        } catch (e: java.lang.Exception) {
            onError(e, true)
        }
    }
}