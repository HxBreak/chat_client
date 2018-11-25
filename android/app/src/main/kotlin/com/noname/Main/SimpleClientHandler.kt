package com.noname.Main

import com.google.protobuf.MessageLite
import com.noname.backend.*
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler

class SimpleClientHandler : SimpleChannelInboundHandler<MessageLite>(){

    override fun channelActive(ctx: ChannelHandlerContext?) {
        super.channelActive(ctx)
        ctx?.channel()?.writeAndFlush(Payload.newBuilder()
                .apply {
                    payloadType = PayloadType.BASE_REQUEST
                    request = Request.newBuilder()
                            .apply {
                                requestCode = 1
                                content = "Hello From Netty Client Over TLS"
                            }.build()
                }.build())

        ctx?.channel()?.writeAndFlush(Payload.newBuilder()
                .apply {
                    payloadType = PayloadType.AUTH_LOGIN
                    loginRequest = DataLoginRequest.newBuilder()
                            .apply {
                                username = "Noname"
                                password = "nopwd"
                            }.build()
                }.build())
        ctx?.channel()?.writeAndFlush(Payload.newBuilder()
                .apply {
                    payloadType = PayloadType.USER_MAIL
                    mailRequest = DataMailRequest.newBuilder()
                            .apply {
                                to = "System"
                                content = "Hey"
                            }.build()
                }.build())
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: MessageLite?) {
        if(msg is Payload){
            if (msg.payloadType == PayloadType.BASE_RESPONSE){
                println("RequestCode:${msg.response.requestCode} Result:${msg.response.result} " +
                        "Content:${msg.response.content}");
            }
        }
    }
}