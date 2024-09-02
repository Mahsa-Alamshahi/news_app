package org.nextoptech.news.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.nextoptech.news.common.AppConstant.API_KEY
import org.nextoptech.news.common.AppConstant.BASE_URL
import org.nextoptech.news.data.remote.NewsApiService
import org.nextoptech.news.data.remote.dto.NewsApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-access-token", API_KEY)
                    .build()
                chain.proceed(newRequest)
            }
        return builder.build()
    }



    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()



    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }



    @Singleton
    @Provides
    fun provideNewsApiService(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)


}