package com.example.tachiyomi.repository

import com.example.tachiyomi.api.APIService
import com.example.tachiyomi.api.HandleApiResponse
import com.example.tachiyomi.api.NetworkService
import com.example.tachiyomi.model.AllMovie
import com.example.tachiyomi.model.Cast
import com.example.tachiyomi.model.DetailMovie
import com.example.tachiyomi.model.app_util.Either
import com.example.tachiyomi.model.app_util.ErrorType
import com.example.tachiyomi.model.app_util.Failure
import com.example.tachiyomi.model.app_util.toApiResponseFail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface DetailMovieRepository {
    suspend fun getDetailMovie(movieId: Int): Either<Failure, DetailMovie?>
    suspend fun getListSimilarMovie(movieId: Int): Either<Failure, AllMovie?>
    suspend fun getActor(movieId: Int): Either<Failure, Cast?>

}

class DetailMovieRepositoryImpl(
    private val apiService: APIService,
    private val networkService: NetworkService,
) : DetailMovieRepository {

    override suspend fun getActor(movieId: Int): Either<Failure, Cast?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response =
                    apiService.getActor("/3/movie/$movieId/casts?api_key=e9e9d8da18ae29fc430845952232787c")
                        .execute()
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

    override suspend fun getDetailMovie(movieId: Int): Either<Failure, DetailMovie?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response =
                    apiService.getDetailMovie("/3/movie/$movieId?api_key=e9e9d8da18ae29fc430845952232787c&append_to_response=videos&language=vi")
                        .execute()
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

    override suspend fun getListSimilarMovie(movieId: Int): Either<Failure, AllMovie?> =
        withContext(Dispatchers.IO) {
            HandleApiResponse.whenInternetError(networkService)?.let { return@withContext it }
            try {
                val response =
                    apiService.getListSimilarMovie("/3/movie/$movieId/similar?api_key=e9e9d8da18ae29fc430845952232787c&language=vi&page=1")
                        .execute()
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