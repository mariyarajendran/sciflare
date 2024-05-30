package com.task.data.remote

import com.squareup.moshi.Moshi

import com.task.data.remote.moshiFactories.MyStandardJsonAdapters
import com.task.BASE_URL
import com.task.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


private const val timeoutRead = 60   //In seconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val languageCode = "LanguageCode"
private const val timeoutConnect = 60   //In seconds

@Singleton
class ServiceGenerator @Inject constructor() {
    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit

    private var headerInterceptor = Interceptor { chain ->
        val original = chain.request()
        val request = original.newBuilder().header(contentType, contentTypeValue)
            .addHeader(languageCode, Locale.getDefault().language)
            .method(original.method, original.body).build()
        chain.proceed(request)

    }

    private val logger: HttpLoggingInterceptor
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
//            if (BuildConfig.DEBUG) {
//
//            }
            return loggingInterceptor
        }


    init {
        val trustAllCertificates: Array<TrustManager> = arrayOf(object : X509TrustManager {
            override fun checkClientTrusted(
                chain: Array<java.security.cert.X509Certificate>, authType: String
            ) {
            }

            override fun checkServerTrusted(
                chain: Array<java.security.cert.X509Certificate>, authType: String
            ) {
            }

            override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                return arrayOf()
            }
        })

        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCertificates, java.security.SecureRandom())

        okHttpBuilder.addInterceptor(headerInterceptor)
        okHttpBuilder.addInterceptor(logger)
        okHttpBuilder.connectTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(timeoutRead.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(timeoutConnect.toLong(), TimeUnit.SECONDS)
        okHttpBuilder.sslSocketFactory(
            sslContext.socketFactory, trustAllCertificates[0] as X509TrustManager
        )
        val client = okHttpBuilder.build()
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).client(client)
            .addConverterFactory(MoshiConverterFactory.create(getMoshi())).build()
    }

    fun <S> createService(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder().add(MyKotlinJsonAdapterFactory()).add(MyStandardJsonAdapters.FACTORY)
            .build()
    }
}
