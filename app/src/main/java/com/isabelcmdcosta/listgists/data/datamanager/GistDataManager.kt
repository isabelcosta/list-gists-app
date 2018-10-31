package com.isabelcmdcosta.listgists.data.datamanager

import android.util.Log
import com.isabelcmdcosta.listgists.ListGistsApplication
import com.isabelcmdcosta.listgists.data.database.AppDatabase
import com.isabelcmdcosta.listgists.data.models.Gist
import com.isabelcmdcosta.listgists.data.models.GistSimplified
import com.isabelcmdcosta.listgists.data.remote.ApiManager
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * This class represents the data manager related to Gist API
 */
class GistDataManager {

    private val apiManager: ApiManager = ApiManager()
//    private val localManager: AppDatabase = AppDatabase.getInstance(ListGistsApplication.getContext())!!

    /**
     * This will GistService to fetch all public gists
     * @return an Observable of a list of [Gist]
     */
    fun getGists(): Observable<List<Gist>> {
        return apiManager.getGistService().getGists()
    }

//    fun getUsersFromDb(): Observable<List<GistSimplified>> {
//        return localManager.gistDao().getAll().filter { it.isNotEmpty() }
//                .toObservable()
//                .doOnNext {
//                    Timber.d("Dispatching ${it.size} users from DB...")
//                }
//    }
//
//    fun getUsersFromApi(): Observable<List<GistSimplified>> {
//        return apiManager.getGistService().getGists()
//                .map(object : Function<List<Gist>, List<GistSimplified>>() {
//                        @Throws(Exception::class)
//                        fun apply(gist: Gist) : GistSimplified {
//
//                        val gistSimplified = GistSimplified(
//                                id = gist.id,
//                                author = gist.owner.username,
//                                createdAt = gist.createdAt,
//                                updatedAt = gist.updatedAt,
//                                description = gist.description
//                        )
//
//                        return gistSimplified
//                        }
//                    }
//                ).doOnNext {
//                    storeGistsInDb(it)
//                }
//
//    }
//
//    private fun storeGistsInDb(gists: List<GistSimplified>) {
//        Observable.fromCallable { localManager.gistDao().insertAll(gists) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .subscribe {
//                    Log.d("Inserted ${gists.size} gists from API in DB...")
//                }
//    }
}