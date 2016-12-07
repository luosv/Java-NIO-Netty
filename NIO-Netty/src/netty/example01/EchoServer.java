package netty.example01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 应答服务器-启动服务器
 * Created by luosv on 2016/12/6 0006.
 * <p>
 * ·创建 ServerBootstrap 实例来引导绑定和启动服务器
 * ·创建 NioEventLoopGroup 对象来处理事件，如接受新连接、接收数据、写数据等
 * ·指定 InetSocketAddress 服务器监听此端口
 * ·设置 childHandler 执行所有的连接请求
 * ·调用 ServerBootstrap.bind() 方法来绑定服务器
 */
public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        // 指定 NioEventLoopGroup 来接受和处理新连接
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // create ServerBootstrap instance 创建 ServerBootstrap 对象
            ServerBootstrap b = new ServerBootstrap();
            // Specifies NIO transport, local socket address
            // Adds handler to channel pipeline
            // 指定通道类型为 NioServerSocketChannel, 设置 InetSocketAddress 让服务器监听某个端口以等待客户端连接
            // 调用 childHandler 用来指定连接后调用的 ChannelHandler
            // 这个方法传 ChannelInitializer 类型的参数， ChannelInitializer 是个抽象类， 所以需要实现 initChannel 方法， 用来设置 ChannelHandler
            b.group(group).channel(NioServerSocketChannel.class).localAddress(port).childHandler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new EchoServerHandler());
                }
            });
            // Binds server, waits for server to close, and releases resources
            // 绑定服务器等待直到绑定完成， 调用 sync() 方法会阻塞直到服务器完成绑定， 然后服务器等待通道关闭， 因为使用 sync(), 所以关闭操作也会被阻塞
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            // 关闭 EventLoopGroup 释放所有资源和创建的线程
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new EchoServer(65535).start();
    }

}
