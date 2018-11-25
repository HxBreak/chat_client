package com.example.chat

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder

import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity : FlutterActivity(), ServiceConnection {
    private var service: MessageService? = null
    private lateinit var eventChannel: EventChannel
    private var currentSink:EventChannel.EventSink? = null

    override fun onServiceDisconnected(name: ComponentName?) {
        service = null
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        if (service is MessageService.MessageServiceBinder) {
            this.service = service.service
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GeneratedPluginRegistrant.registerWith(this)
        startService(Intent(this, MessageService::class.java))
        bindService(Intent(this, MessageService::class.java), this, 0)
        MethodChannel(flutterView, "com.noname.backend/method")
                .setMethodCallHandler(object :MethodChannel.MethodCallHandler{
                    override fun onMethodCall(p0: MethodCall?, p1: MethodChannel.Result?) {
                        when(p0?.method){
                            "connect" -> {
                                val ip = p0.argument<String>("ip")
                                service?.let {
                                    if(ip != null) {
                                        it.connect(ip)
                                    } else {
                                        p1?.error("error", null, null)
                                        return
                                    }
                                }
                                p1?.success("success")
                                return
                            }
                            else -> p1?.notImplemented()
                        }
                    }
                })
        eventChannel = EventChannel(flutterView, "com.noname.backend/event")
                .apply {
                    setStreamHandler(object : EventChannel.StreamHandler {
                        override fun onListen(p0: Any?, p1: EventChannel.EventSink?) {
                            currentSink = p1
                        }

                        override fun onCancel(p0: Any?) {
                            currentSink = null
                        }
                    })
                }
    }
}
