package com.example.mvvmtest.injection.module

import com.example.mvvmtest.api.Api
import com.example.mvvmtest.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@Suppress("unused")
object NetworkModule {


    @Provides
    @Reusable
    @JvmStatic
    internal fun providesApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }


    @Provides
    @Reusable
    @JvmStatic
    internal fun providesRetrofitInterface(): Retrofit {

        val logging = HttpLoggingInterceptor()

        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()

        okHttpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient.build())
            .build()
    }


}