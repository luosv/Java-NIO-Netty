package netty.example01;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 应答程序-客户端业务逻辑
 * Created by luosv on 2016/12/6 0006.
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private final ByteBuf message;

    public EchoClientHandler() {
        byte[] req = "Hello from client".getBytes();
        message = Unpooled.buffer(req.length);
        message.writeBytes(req);
    }

    // 客户端连接服务器后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        ctx.writeAndFlush(message);
    }

    // 从服务器接收到数据后调用
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf msg) throws Exception {
        System.out.println("Client received: " + ByteBufUtil.hexDump(msg.readBytes(msg.readableBytes())));
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        try {
            String body = new String(req, "UTF-8");
            System.out.println(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 重写 exceptionCaught 方法捕获服务器异常
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
