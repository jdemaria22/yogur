package com.leagueofjire.scripts.utils.core

import com.google.gson.Gson
import com.leagueofjire.scripts.ActivePlayer
import com.leagueofjire.scripts.utils.Api
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class IApi : Api {
    private val uriApi = "https://127.0.0.1:2999/liveclientdata/activeplayer"
    private val client = httpClient()
    private val request : HttpRequest = HttpRequest.newBuilder(URI.create(uriApi)).build()
    private val gson = Gson()
    private fun httpClient(): HttpClient {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate>? = null
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) {}
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) {}
        })
        val sslContext: SSLContext = SSLContext.getInstance("TLS")
        sslContext.init(null, trustAllCerts, SecureRandom())
        return HttpClient.newBuilder().sslContext(sslContext).build()
    }


    override fun getAttackSpeed(): Float {
        val response = client.send(request, HttpResponse.BodyHandlers.ofString())
        val userInfo = gson.fromJson(response.body(), ActivePlayer::class.java)
        return userInfo.championStats.attackSpeed.toFloat()
    }
}