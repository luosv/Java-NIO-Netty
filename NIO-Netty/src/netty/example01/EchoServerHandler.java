package netty.example01;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 应答服务器-服务器业务逻辑
 * 功能：将客户端发给服务器的数据返回给客户端
 * Created by luosv on 2016/12/6 0006.
 * <p>
 * ·Netty使用多个 ChannelHandler 来达到对事件处理的分离，因为可以很容的添加、更新、删除业务逻辑处理 handler
 * ·Handler 的每个方法都可以被重写，它的所有的方法中只有 channelRead 方法是必须要重写的
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server received: " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    // 重写 exceptionCaught 方法捕获服务器异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
