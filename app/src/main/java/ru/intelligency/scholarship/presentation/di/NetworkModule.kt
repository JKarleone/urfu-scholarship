package ru.intelligency.scholarship.presentation.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.intelligency.scholarship.data.myapplications.ApplicationsApi
import ru.intelligency.scholarship.data.portfolio.DocumentsApi
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Provides
    fun provideDocumentsApi(retrofit: Retrofit): DocumentsApi {
        return retrofit.create(DocumentsApi::class.java)
    }

    @Provides
    fun provideApplicationApi(retrofit: Retrofit): ApplicationsApi {
        return retrofit.create(ApplicationsApi::class.java)
    }

    companion object {

        private const val BASE_URL = "http://10.0.2.2:5000/"
    }
}
