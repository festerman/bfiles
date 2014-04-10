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
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOption

/**
 * Handles a server-side channel.
 */
class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

	override def channelRead(ctx: ChannelHandlerContext, msg: Any): Unit = { // (2)
	  
	    val in: ByteBuf = msg.asInstanceOf[ByteBuf]
		
	    /* echo ... */
	    ctx.write(in) // (1)
        ctx.flush() // (2)

        /* write to console, *then* discard input 
	    try {
	    	while (in.isReadable()) { // (1)
        	
	    		System.out.print(in.readByte().asInstanceOf[Char])
	    		System.out.flush();
	    	}
	    } finally {
	    	in.release() // (2)
	    }
	    * */
	    
        // original discardserver
		// Discard the received data silently.
		// s.release() // (3)
	}

	override def exceptionCaught(ctx:ChannelHandlerContext, cause: Throwable) { // (4)
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}

class DiscardServer(val port:Integer) {
  	
	def run = {
	  val bossGroup: EventLoopGroup = new NioEventLoopGroup();
	  val workerGroup: EventLoopGroup = new NioEventLoopGroup(); 
	  try {
         var b: ServerBootstrap = new ServerBootstrap() // (2)
         b = b.group(bossGroup, workerGroup)
         b = b.channel(classOf[NioServerSocketChannel]) // (3)
         b = b.childHandler(new ChannelInitializer[SocketChannel]() { // (4)                
                 override def initChannel(ch:SocketChannel): Unit = {
                     ch.pipeline().addLast(new DiscardServerHandler())
                 }
             })
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

object DiscardServer {
  
  
  def main(args: Array[String]) {
    def port: Integer = if (args.size > 0) Integer.decode(args(0)) else 8080
    new DiscardServer(port).run
  }

}