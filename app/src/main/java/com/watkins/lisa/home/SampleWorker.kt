package com.watkins.lisa.home

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.watkins.lisa.di.ChildWorkerFactory
import javax.inject.Inject

class SampleWorker(context: Context, workerParams: WorkerParameters): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        return Result.success()
    }

    class Factory @Inject constructor(
    ) : ChildWorkerFactory {
        override fun create(appContext: Context, params: WorkerParameters): CoroutineWorker {
            return SampleWorker(
                appContext,
                params
            )
        }
    }
}