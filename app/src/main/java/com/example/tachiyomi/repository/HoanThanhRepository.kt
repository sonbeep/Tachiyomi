package com.example.tachiyomi.repository

import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.HandleApiResponse
import com.example.tachiyomi.api.NetworkService
import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.app_util.Either
import com.example.tachiyomi.model.app_util.ErrorType
import com.example.tachiyomi.model.app_util.Failure
import com.example.tachiyomi.model.app_util.toApiResponseFail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface HoanThanhRepository {
    suspend fun getAllMovieFavorite(): Either<Failure, AllMovie?>
}

class HoanThanhRepositoryImpl(
    private val apiService: APIService,
    private val networkService: NetworkService,
) : HoanThanhRepository {
    override suspend fun getAllMovieFavorite(): Either<Failure, AllMovie?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response = apiService.getAllMovieFavorite().execute()
                if (response.isSuccessful) {
                    return@withContext Either.success(response.body())
                }
                val reponseFail = response.toApiResponseFail()
                return@withContext Either.failure(
                    Failure(
                        ErrorType.SERVER_RESPONSE_ERROR, reponseFail?.message, reponseFail?.code
                    )
                )
            } catch (e: Exception) {
                return@withContext Either.failure(
                    Failure(
                        ErrorType.SERVER_ERROR, e.message
                    )
                )
            }

        }

}