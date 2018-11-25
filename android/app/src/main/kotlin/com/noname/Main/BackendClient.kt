package com.noname.Main

import android.content.Context
import com.noname.backend.BackendPayload
import com.noname.backend.Payload
import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.protobuf.ProtobufDecoder
import io.netty.handler.codec.protobuf.ProtobufEncoder
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender
import io.netty.handler.ssl.SslHandler
import io.netty.handler.timeout.IdleStateHandler
import java.util.concurrent.TimeUnit

class BackendClient{

    fun start(ctx:Context, ip:String){
        val group = NioEventLoopGroup()
        try {
            val bootstrap = Bootstrap()
            val context = SslUtil.createClientSslContext(ctx)
            bootstrap.group(group).channel(NioSocketChannel::class.java)
                    .handler(object : ChannelInitializer<SocketChannel>(){
                        override fun initChannel(ch: SocketChannel?) {
                            ch?.pipeline()?.apply {
                                addFirst("ssl", SslHandler(context.newEngine(ch.alloc())))
                                addLast(ProtobufVarint32FrameDecoder())
                                addLast(IdleStateHandler(0, 4, 0, TimeUnit.SECONDS))
                                addLast(HeartBeatClientHandler())
                                addLast(ProtobufDecoder(Payload.getDefaultInstance()))
                                addLast(ProtobufVarint32LengthFieldPrepender())
                                addLast(ProtobufEncoder())
                                addLast(SimpleClientHandler())
                            }
                        }
                    })
            val f = bootstrap.connect(ip, 45678).sync()
            f.channel().closeFuture().sync()
        }catch (e:Exception){
            e.printStackTrace()
        }finally {
            group.shutdownGracefully()
        }
    }

}