package lib.apidata.repository

import lib.apidata.data.ItemData
import lib.apidata.response.Result

interface HomeRepo {
    suspend fun getList(): Result<MutableList<ItemData>?>?
}