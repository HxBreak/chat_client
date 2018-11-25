package com.noname.Main

import android.content.Context
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslContextBuilder
import java.io.FileInputStream
import java.security.KeyStore
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLEngine
import javax.net.ssl.TrustManagerFactory

object SslUtil{

    fun createSslContext(): SslContext {
        val keyStore = KeyStore.getInstance("JKS")
        val ksIn = FileInputStream("key/server.jks")
        keyStore.load(ksIn, "password".toCharArray())
        val kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        kmf.init(keyStore, "password".toCharArray())
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(kmf.keyManagers, null, null)
        return SslContextBuilder.forServer(kmf).build()
    }

    fun createClientSslContext(ctx:Context): SslContext {
        val keyStore = KeyStore.getInstance("JKS")
        val ksIn = ctx.assets.open("client.jks")
        keyStore.load(ksIn, "password".toCharArray())
        val tf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        tf.init(keyStore)
        return SslContextBuilder.forClient()
                .trustManager(tf)
                .build();
    }
}