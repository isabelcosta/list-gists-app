package com.isabelcmdcosta.listgists.viewmodels

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.isabelcmdcosta.listgists.ListGistsApplication
import com.isabelcmdcosta.listgists.R
import com.isabelcmdcosta.listgists.data.models.Gist
import com.isabelcmdcosta.listgists.data.datamanager.GistDataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.io.IOException
import java.util.concurrent.TimeoutException

/**
 * This class represents the [ViewModel] component used for the ListGists Activity
 */
class ListGistsViewModel : ViewModel() {

    var TAG = ListGistsViewModel::class.java.simpleName

    private val gistDataManager: GistDataManager = GistDataManager()

    val successful: MutableLiveData<Boolean> = MutableLiveData()
    lateinit var message: String
    lateinit var gistList: List<Gist>

    /**
     * Fetches gists list from getGists method of the GistService
     */
    @SuppressLint("CheckResult")
    fun getGists() {
        gistDataManager.getGists()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<Gist>>() {
                    override fun onNext(gistListResponse: List<Gist>) {
                        gistList = gistListResponse
                        successful.value = true
                    }

                    override fun onError(throwable: Throwable) {
                        when (throwable) {
                            is IOException -> {
                                message = ListGistsApplication.getContext()
                                        .getString(R.string.error_please_check_internet)
                            }
                            is TimeoutException -> {
                                message = ListGistsApplication.getContext()
                                        .getString(R.string.error_request_timed_out)
                            }
                            else -> {
                                message = ListGistsApplication.getContext()
                                        .getString(R.string.error_something_went_wrong)
                                Log.e(TAG, throwable.localizedMessage)
                            }
                        }
                        successful.value = false
                    }

                    override fun onComplete() {
                    }
                })
    }
}
