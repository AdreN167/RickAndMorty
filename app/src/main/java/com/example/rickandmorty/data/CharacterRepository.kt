package com.example.rickandmorty.data


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

fun OkHttpClient.Builder.ignoreAllSslErrors(): OkHttpClient.Builder {
    val naiveTrustManager = object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
    }

    val insecureSocketFactory = SSLContext.getInstance("TLSv1.2").apply {
        val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
        init(null, trustAllCerts, SecureRandom())
    }.socketFactory

    sslSocketFactory(insecureSocketFactory, naiveTrustManager)
    hostnameVerifier { _, _ -> true }
    return this
}

class CharacterRepository {

    private companion object {

        const val BASE_URL = "https://rickandmortyapi.com/api/"
        const val CONNECT_TIMEOUT = 10L
        const val WRITE_TIMEOUT = 10L
        const val READ_TIMEOUT = 10L
    }

    private val retrofit = Retrofit.Builder()
        .client(provideOkHttpClientWithProgress())
        .baseUrl(BASE_URL)
        .build()

    private fun provideOkHttpClientWithProgress(): OkHttpClient =
        OkHttpClient().newBuilder()
            .ignoreAllSslErrors()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .build()

    private val characterApi by lazy {
        retrofit.create(CharacterApi::class.java)
    }

    suspend fun getAllCharacters() : List<Character> = characterApi.getAll().results

}