package com.isabelcmdcosta.listgists.data.remote

import com.isabelcmdcosta.listgists.data.models.Gist
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * This interface describes the methods related to GitHub Gist API
 */
interface GistService {

    /**
     * This function returns all public gists
     * @return an observable instance of a list of [Gist]s
     */
    @GET("gists/public")
    fun getGists(): Observable<List<Gist>>
}
