package com.example.moviesample.api.service

import okio.IOException
import retrofit2.HttpException
import retrofit2.Response

class TheMovieDbApi(val endpoints: TheMovieDbEndpoints) {


        suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
            val response: Response<T>
            try {
                response = call.invoke()
            }
            catch (e: HttpException) {
                return Result.Error(NetworkException)
            } catch (e: IOException) {
                return Result.Error(NetworkException)
            }
            catch (t: Throwable) {
                return Result.Error(Exception())
            }
            return if (!response.isSuccessful) {
                @Suppress("BlockingMethodInNonBlockingContext")
                Result.Error(ApiException(response.code()))
            } else {
                return if (response.body() == null) {
                    Result.Error(Exception())
                } else {
                    Result.Success(response.body()!!)
                }
            }
        }

        data class ApiException(val code: Int) : Exception()
        object  NetworkException : Exception()
}