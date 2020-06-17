package com.studio1hub.app.vadsoft.data.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.studio1hub.app.vadsoft.utils.State
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.lang.Exception

@ExperimentalCoroutinesApi
abstract class NetworkBoundRepository<RESULT,REQUEST>{

    fun asFlow() = flow<State<RESULT>>{

        emit(State.loading())

        try {

            emit(State.success(fetchFromLocal().first()))

            val apiResponse = fetchFromRemote()

            val remoteUser = apiResponse.body()

            if (apiResponse.isSuccessful && remoteUser!=null)
            {
                saveRemoteData(remoteUser)
            }
            else
            {
                emit(State.error(apiResponse.message()))
            }

        }
        catch (e: Exception)
        {
            emit(State.error("Please check your network!"))
        }

        emitAll(fetchFromLocal().map {
            State.success(it)
        })

    }

    @MainThread
    abstract suspend fun fetchFromRemote(): Response<REQUEST>

    @WorkerThread
    abstract suspend fun saveRemoteData(result: REQUEST)

    @WorkerThread
    abstract fun fetchFromLocal(): Flow<RESULT>
}
