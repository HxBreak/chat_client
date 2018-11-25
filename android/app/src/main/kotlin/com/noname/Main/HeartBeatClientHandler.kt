package com.noname.Main

import com.noname.backend.DataPing
import com.noname.backend.Payload
import com.noname.backend.PayloadType
import com.noname.backend.Request
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.handler.timeout.IdleState
import io.netty.handler.timeout.IdleStateEvent

class HeartBeatClientHandler: ChannelInboundHandlerAdapter(){
    override fun userEventTriggered(ctx: ChannelHandlerContext?, evt: Any?) {
        if(evt is IdleStateEvent){
            ctx?.channel()?.writeAndFlush(Payload.newBuilder()
                    .apply {
                        payloadType = PayloadType.BASE_PING
                        ping = DataPing.newBuilder().apply {
                            time = System.currentTimeMillis()
                        }.build()
                    }.build())
//            if(evt.state() == IdleState.WRITER_IDLE){
//            }
        }else{
            super.userEventTriggered(ctx, evt)
        }
    }
}