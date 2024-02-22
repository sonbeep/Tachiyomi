package com.example.tachiyomi.api

import com.example.tachiyomi.model.app_util.ApiResponse
import com.example.tachiyomi.model.app_util.Either
import com.example.tachiyomi.model.app_util.ErrorType
import com.example.tachiyomi.model.app_util.Failure
import com.example.tachiyomi.model.app_util.toApiResponseFail
import retrofit2.Response

object HandleApiResponse {
    fun whenInternetError(networkService: NetworkService): Either<Failure, Nothing>? {
        if (!networkService.isConnected()) {
            return Either.failure(
                Failure(
                    ErrorType.LOST_INTERNET,
                    "No internet connection"
                )
            )
        }

        return null
    }

    fun <R> processResponseData(response: Response<ApiResponse<R>>): Either<Failure, R> {
        if (response.isSuccessful) {
            val body = response.body()

            if (body != null) {
                if (body.success == true) {
                    return Either.success(body.data) as Either<Failure, R>
                }

                return Either.failure(
                    Failure(
                        ErrorType.SERVER_RESPONSE_ERROR,
                        body.message
                    )
                )
            }
        }

        val responseFail = response.toApiResponseFail()
        return Either.failure(
            Failure(
                ErrorType.SERVER_RESPONSE_ERROR,
                responseFail?.message,
                responseFail?.code
            )
        )
    }
}
