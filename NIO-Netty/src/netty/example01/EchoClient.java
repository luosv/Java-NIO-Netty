package netty.example01;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 应答程序-引导客户端
 * Created by luosv on 2016/12/6 0006.
 * <p>
 * ·连接服务器
 * ·写数据到服务器
 * ·等待接受服务器返回相同的数据
 * ·关闭连接
 */
public class EchoClient {

    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        // 创建 EventLoopGroup 对象并设置到 Bootstrap 中，EventLoopGroup 可以理解为是一个线程池，这个线程池用来处理连接、接受数据、发送数据
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建 Bootstrap 对象用来引导启动客户端
            Bootstrap b = new Bootstrap();
            // 创建 InetSocketAddress 并设置到 Bootstrap 中，InetSocketAddress 是指定连接的服务器地址
            // 添加一个 ChannelHandler，客户端成功连接服务器后就会被执行
            b.group(group).channel(NioSocketChannel.class).remoteAddress(new InetSocketAddress(host, port)).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new EchoClientHandler());
                }
            });
            // 调用 Bootstrap.connect() 来连接服务器
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            // 最后关闭 EventLoopGroup 来释放资源
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoClient("localhost", 20000).start();
    }

}
