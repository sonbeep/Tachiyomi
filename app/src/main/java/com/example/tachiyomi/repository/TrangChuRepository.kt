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


interface TrangChuRepository {
    suspend fun getAllMoviePopular(): Either<Failure, AllMovie?>
    suspend fun getAllMovieUpComing(): Either<Failure, AllMovie?>
    suspend fun getAllMovieTopRate(): Either<Failure, AllMovie?>
}

class TrangChuRepositoryImpl(
    private val apiService: APIService,
    private val networkService: NetworkService,
) : TrangChuRepository {
    override suspend fun getAllMoviePopular(): Either<Failure, AllMovie?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response = apiService.getAllMoviePopular().execute()
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

    override suspend fun getAllMovieUpComing(): Either<Failure, AllMovie?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response = apiService.getAllMovieUpComing().execute()
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

    override suspend fun getAllMovieTopRate(): Either<Failure, AllMovie?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response = apiService.getAllMovieTopRate().execute()
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