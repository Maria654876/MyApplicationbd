package com.example.myapplication.bd

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class CachingCatsWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    companion object {
        private const val TAG = "CachingArtistsWorker"
    }

    override fun doWork(): Result {
        //Delay 5 seconds
        Thread.sleep(5000)
        Log.d(TAG, "Work progress")

        return Result.success()
    }
}