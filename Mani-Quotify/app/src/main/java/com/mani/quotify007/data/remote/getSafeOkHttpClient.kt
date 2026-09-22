package com.mani.quotify007.data.remote

import com.mani.quotify007.QuotifyApp
import com.mani.quotify007.R
import okhttp3.OkHttpClient
import java.security.KeyStore
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

fun getSafeOkHttpClient(quotifyApp: QuotifyApp): OkHttpClient {
    return try {
        // Load the trusted certificate
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType()).apply {
            load(null, null)
            // quotable io trusted certificate
            quotifyApp.resources.openRawResource(R.raw.api_quotable_io).use { certInputStream ->
                val certificateFactory = java.security.cert.CertificateFactory.getInstance("X.509")
                val certificate = certificateFactory.generateCertificate(certInputStream)
                setCertificateEntry("api_quotable_io", certificate)
            }
        }

        // Create a TrustManager that trusts the CAs in our KeyStore
        val trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm()).apply {
            init(keyStore)
        }
        val trustManagers = trustManagerFactory.trustManagers
        val sslContext = SSLContext.getInstance("TLS").apply {
            init(null, trustManagers, java.security.SecureRandom())
        }
        val sslSocketFactory = sslContext.socketFactory

        OkHttpClient.Builder()
            .sslSocketFactory(sslSocketFactory, trustManagers[0] as X509TrustManager)
            .build()
    } catch (e: Exception) {
        throw RuntimeException(e)
    }
}