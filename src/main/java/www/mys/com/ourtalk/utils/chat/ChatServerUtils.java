package www.mys.com.ourtalk.utils.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import www.mys.com.ourtalk.utils.LogUtils;

import java.net.InetSocketAddress;

public class ChatServerUtils {

    private final String host;
    private final String split;
    private final int port;
    private final ServerBack serverBack;
    private boolean running;
    private WebSocketChannelInitializer initializer;

    public static void main(String[] args) {
        ChatServerUtils chatServerUtils = new ChatServerUtils(
                "0.0.0.0", "/ws", 5000
                , new ServerBack() {
            @Override
            public void onConnect(ChannelHandlerContext ctx) {

            }

            @Override
            public void onDisConnect(ChannelHandlerContext ctx) {

            }

            @Override
            public void onReceiveMessage(ChannelHandlerContext ctx, WebSocketFrame frame) {

            }
        });
    }

    public static class BaseSimpleChannelInboundHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

        private ServerBack serverBack;

        public BaseSimpleChannelInboundHandler(ServerBack serverBack) {
            this.serverBack = serverBack;
        }

        protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame frame) throws Exception {
            serverBack.onReceiveMessage(ctx, frame);
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            serverBack.onConnect(ctx);

        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
            serverBack.onDisConnect(ctx);
        }
    }

    private void init() {
        running = true;
        LogUtils.log("host=" + host + ";port=" + port + ";split=" + split);
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            initializer = new WebSocketChannelInitializer(split, serverBack);
            serverBootstrap
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_BACKLOG, 1024 * 1024 * 10)
                    .childHandler(initializer);
            ChannelFuture channelFuture = serverBootstrap.bind(new InetSocketAddress(host, port)).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            LogUtils.log("ChatServerUtils init error.e=" + e);
            running = false;
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {

        private String split;
        private ServerBack serverBack;

        public WebSocketChannelInitializer(String split, ServerBack serverBack) {
            this.split = split;
            this.serverBack = serverBack;
        }

        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
            pipeline.addLast(new HttpServerCodec());
            //以块的方式来写的处理器
            pipeline.addLast(new ChunkedWriteHandler());
            //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
            pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 1024));
            pipeline.addLast(new WebSocketServerProtocolHandler(split, null
                    , true, 65535));
            pipeline.addLast(new BaseSimpleChannelInboundHandler(serverBack));
        }
    }

    public ChatServerUtils(String host, String split, int port, ServerBack serverBack) {
        this.host = host;
        this.split = split;
        this.port = port;
        this.serverBack = serverBack;
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    public boolean isRunning() {
        return running;
    }

    public static interface ServerBack {
        public void onConnect(ChannelHandlerContext ctx);

        public void onDisConnect(ChannelHandlerContext ctx);

        public void onReceiveMessage(ChannelHandlerContext ctx, WebSocketFrame frame);
    }

}
