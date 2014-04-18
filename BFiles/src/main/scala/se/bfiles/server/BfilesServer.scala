package se.bfiles.server

import io.netty.channel.EventLoopGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.channel.ChannelFuture
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.channel.ChannelOption
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.HttpVersion.HTTP_1_1
import io.netty.handler.codec.http.HttpResponseStatus.{CONTINUE,OK}
import io.netty.handler.codec.http.HttpHeaders.{is100ContinueExpected,isKeepAlive}
import io.netty.handler.codec.http.FullHttpResponse
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.HttpHeaders.Names._
import io.netty.channel.ChannelFutureListener
import io.netty.handler.codec.http.HttpHeaders.Values
import io.netty.channel.ChannelPipeline
import io.netty.handler.codec.http.HttpServerCodec
import io.netty.handler.codec.http.LastHttpContent
import io.netty.handler.codec.http.HttpContent

class BfilesServerHandler extends ChannelInboundHandlerAdapter {
	private val CONTENT: Array[Byte] = Array('H', 'e', 'j')
	
	override def channelReadComplete(ctx: ChannelHandlerContext) = ctx.flush()
	override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = {
	  
	  msg match {
	    case a: HttpRequest => {
	    	val request: HttpRequest = msg.asInstanceOf[HttpRequest]
	    	val b = is100ContinueExpected(request)
	    	if (b)  ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE))
	    	val keepAlive = isKeepAlive(request)
            
	    	val response: FullHttpResponse  = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(CONTENT));
            response.headers().set(CONTENT_TYPE, "text/plain");
            response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

            if (!keepAlive) ctx.write(response).addListener(ChannelFutureListener.CLOSE) else {
                response.headers().set(CONNECTION, Values.KEEP_ALIVE)
                ctx.write(response)
            }
	    }
	    case b: LastHttpContent => {}
	    case c: HttpContent => {}
	    case _ => {}
	  }
	}
}

class BfilesServer(val port:Integer) {
  	
	def run = {
	  val bossGroup: EventLoopGroup = new NioEventLoopGroup();
	  val workerGroup: EventLoopGroup = new NioEventLoopGroup(); 
	  try {
         var b: ServerBootstrap = new ServerBootstrap() // (2)
         b = b.group(bossGroup, workerGroup)
         b = b.channel(classOf[NioServerSocketChannel]) // (3)
         b = b.childHandler(new BfilesServerInitializer())
         b = b.option[Integer](ChannelOption.SO_BACKLOG, 128)          // (5)
         b = b.childOption[java.lang.Boolean](ChannelOption.SO_KEEPALIVE, true) // (6)

         // Bind and start to accept incoming connections.
         val f:ChannelFuture = b.bind(port).sync() // (7)

         // Wait until the server socket is closed.
         // In this example, this does not happen, but you can do that to gracefully
         // shut down your server.
         f.channel().closeFuture().sync()
        } finally {
        	workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
	}
}

class BfilesServerInitializer () extends ChannelInitializer[SocketChannel] {
	override def initChannel(ch:SocketChannel): Unit = {
			val p: ChannelPipeline = ch.pipeline()
			p.addLast("codec", new HttpServerCodec())
			p.addLast(new BfilesServerHandler())
    }
}

object BfilesServer {
  def main(args: Array[String]) {
    def port: Integer = if (args.size > 0) Integer.decode(args(0)) else 8080
    new BfilesServer(port).run
	
}

}