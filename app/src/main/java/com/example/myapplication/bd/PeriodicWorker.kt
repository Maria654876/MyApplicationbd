package com.example.myapplication.bd

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class PeriodicWorker (context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        private const val TAG = "PeriodicWorker"
    }

    private val achingCountDao = AppDatabaseImp.getInstance(context).getCachingCountDao()


    override fun doWork(): Result {
        Log.d(TAG, "Work progress")
        return try {
            val cachingCount = achingCountDao.getCountById(0) ?: CachingCount(0, 0)
            val updatedCachingCount = cachingCount.copy(count = cachingCount.count.plus(1))

            achingCountDao.insertCount(updatedCachingCount)

            Log.d(TAG, "CachingCount was updated: $updatedCachingCount")
            Result.success()
        } catch (exception: Exception) {
            Log.e(TAG, exception.message ?: "Exception during updating CachingCount")
            Result.retry()
        }
    }
}