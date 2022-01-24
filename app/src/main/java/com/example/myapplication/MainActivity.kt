package com.example.myapplication

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.work.*
import com.example.myapplication.bd.CachingCatsWorker
import com.example.myapplication.bd.PeriodicWorker
import com.example.myapplication.preferences.AppPreferencesImpl
import com.example.myapplication.fragment.CatFragment
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_cat)

        if (savedInstanceState == null) {
            if (AppPreferencesImpl.getInstance(this).getToken().isBlank()) {
                openFragment(CatFragment(), tag = "LocationFragment")
            }

        }
    }
    private fun openFragment(fragment: Fragment, doClearBackStack: Boolean = false, tag: String? = null) {
        if (doClearBackStack) {
            clearBackStack()
        }
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment_container, fragment, tag)
            .addToBackStack(null)
            .commit()
    }

    private fun clearBackStack() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    private fun registerWorkManagers(){
        val oneTimeConstraints: Constraints = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(false)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .build()
        } else {
            Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(false)
                .setRequiresCharging(false)
                .build()
        }

        val worker = OneTimeWorkRequestBuilder<CachingCatsWorker>()
            .addTag("CachingArtistsWorker1")
            .setConstraints(oneTimeConstraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        val worker2 = OneTimeWorkRequestBuilder<CachingCatsWorker>()
            .addTag("CachingArtistsWorker2")
            .setConstraints(oneTimeConstraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        val worker3 = OneTimeWorkRequestBuilder<CachingCatsWorker>()
            .addTag("CachingArtistsWorker3")
            .setConstraints(oneTimeConstraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        WorkManager.getInstance(this)
            .beginWith(worker)
            .then(listOf(worker2, worker3))
            .enqueue()

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(worker2.id).observe(this, { workInfo ->
            if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
            }
        })


        val periodicConstraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(false)
            .build()

        val periodicWorker = PeriodicWorkRequestBuilder<PeriodicWorker>(PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS, TimeUnit.MILLISECONDS)
            .addTag("PeriodicWorker")
            .setConstraints(periodicConstraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        WorkManager.getInstance(this).enqueue(periodicWorker)
    }
}