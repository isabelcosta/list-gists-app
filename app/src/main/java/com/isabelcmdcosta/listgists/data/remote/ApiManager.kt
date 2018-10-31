package com.isabelcmdcosta.listgists.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is responsible for managing the API services
 */
class ApiManager {

    companion object {
        const val GITHUB_GISTS_API_BASE_URL = "https://api.github.com/"
    }

    private lateinit var retrofit: Retrofit
    private lateinit var gistService: GistService

    init {
        initialiseRetrofit()
    }

    private fun <T> createService(clazz: Class<T>): T {
        return retrofit.create(clazz)
    }

    private fun initServices() {
        gistService = createService(GistService::class.java)
    }

    private fun initialiseRetrofit() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

        retrofit = Retrofit.Builder()
                .baseUrl(GITHUB_GISTS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()

        initServices()
    }

    /**
     * @return a pointer to an initialised [GistService]
     */
    fun getGistService(): GistService = gistService
}
