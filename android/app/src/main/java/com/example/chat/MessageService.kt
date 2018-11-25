package com.example.chat

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.noname.Main.BackendClient

class MessageService : Service() {

    private var backend: BackendClient? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun connect(ip:String){
        backend = BackendClient()
        backend?.let {
            try {
                it.start(this, ip)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return MessageServiceBinder(this)
    }

    class MessageServiceBinder(public val service: MessageService): Binder() {
    }
}
