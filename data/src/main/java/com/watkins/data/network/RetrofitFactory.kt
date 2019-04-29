package com.watkins.data.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.OkHttpClient

const val BASE_URL = "https://www.example.com"
const val CACHE_SIZE_BYTES: Long = 1024 * 1024 * 2 // 2 MB

/**
 * Creates retrofit instance for use with all API calls - sets up caching and logging.
 */
class RetrofitFactory(val context: Context) {
    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    /**
     * Enable cache by default. OkHttp will take care of e-tags if they exist.
     */
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .cache(Cache(context.cacheDir, CACHE_SIZE_BYTES))
        .addInterceptor{ chain ->
            val request = chain.request().apply {
                if (hasNetwork(context))
                    newBuilder().header(CACHE_CONTROL, CachePolicy.WITH_NETWORK.policy).build()
                else
                    newBuilder().header(CACHE_CONTROL, CachePolicy.CACHE_ONLY.policy).build()
            }
            chain.proceed(request)
        }

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BASE_URL)
            .client(okHttpClient.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}
