
package www.mys.com.ourtalk.utils.chat;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.util.CharsetUtil;
import www.mys.com.ourtalk.utils.LogUtils;
import www.mys.com.ourtalk.utils.StringUtils;

import java.net.URI;

public class ChatClientUtils {

    private String url = "ws://localhost:5000/ws";
    private String userKey;
    private boolean running;
    private Channel channel;

    public SendBack sendText(BaseChatPOJO baseChatPOJO) {
        if (baseChatPOJO == null) {
            return new SendBack(-1, "baseChatPOJO is null.", null);
        }
        if (channel == null) {
            return new SendBack(-2, "user not connect.", null);
        }
        final StringBuilder result = new StringBuilder();
        ChatUtils.sendData(channel
                , baseChatPOJO
                , future -> {
                    LogUtils.log("operationComplete future=" + future);
                    LogUtils.log("operationComplete isDone=" + future.isDone());
                    if (future.isDone()) {
                        result.append(ChatUtils.SUCCESS);
                    } else {
                        result.append(ChatUtils.FAILED);
                    }
                });
        while (result.length() == 0) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
        }
        if (ChatUtils.SUCCESS.equals(result.toString())) {
            return new SendBack(1, "send success.", JSON.toJSONString(baseChatPOJO));
        } else {
            return new SendBack(-2, "send failed.", JSON.toJSONString(baseChatPOJO));
        }
    }

    public static class MockClientHandler extends SimpleChannelInboundHandler<Object> {

        private void onTextBack(TextWebSocketFrame textFrame) {
            String text = textFrame.text();
            LogUtils.log("onTextBack text=" + text);
        }

        private void onBinaryBack(BinaryWebSocketFrame binaryFrame) {

        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            LogUtils.log("channelRead0 ctx=" + ctx);
            LogUtils.log("channelRead0 msg=" + msg);
            LogUtils.log("channelRead0  " + webSocketClientHandshaker.isHandshakeComplete());
            Channel ch = ctx.channel();
            FullHttpResponse response;
            if (!webSocketClientHandshaker.isHandshakeComplete()) {
                handShake(ctx, msg);
            } else if (msg instanceof FullHttpResponse) {
                response = (FullHttpResponse) msg;
                throw new IllegalStateException("Unexpected FullHttpResponse (getStatus="
                        + response.status() + ", content=" + response.content().toString(CharsetUtil.UTF_8) + ')');
            } else {
                WebSocketFrame frame = (WebSocketFrame) msg;
                if (frame instanceof TextWebSocketFrame) {
                    onTextBack((TextWebSocketFrame) frame);
                } else if (frame instanceof BinaryWebSocketFrame) {
                    onBinaryBack((BinaryWebSocketFrame) frame);
                } else if (frame instanceof PongWebSocketFrame) {
                    PongWebSocketFrame pongFrame = (PongWebSocketFrame) frame;
                    LogUtils.log("WebSocket Client received pong data=" + new String(pongFrame.content().array()));
                } else if (frame instanceof CloseWebSocketFrame) {
                    CloseWebSocketFrame closeFrame = (CloseWebSocketFrame) frame;
                    LogUtils.log("receive close frame data=" + new String(closeFrame.content().array()));
                    ch.close();
                }
            }
        }

        private final WebSocketClientHandshaker webSocketClientHandshaker;
        private final ChatClientUtils chatClientUtils;
        private final String userKey;

        public MockClientHandler(WebSocketClientHandshaker webSocketClientHandshaker
                , ChatClientUtils chatClientUtils, String userKey) {
            this.webSocketClientHandshaker = webSocketClientHandshaker;
            this.chatClientUtils = chatClientUtils;
            this.userKey = userKey;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            super.channelActive(ctx);
            LogUtils.log("channelActive");
            chatClientUtils.channel = ctx.channel();
            webSocketClientHandshaker.handshake(ctx.channel());
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            super.channelInactive(ctx);
            LogUtils.log("channelInactive");
            chatClientUtils.channel = null;
        }

        private void handShake(ChannelHandlerContext ctx, Object msg) {
            try {
                FullHttpResponse response = (FullHttpResponse) msg;
                webSocketClientHandshaker.finishHandshake(ctx.channel(), response);
                ctx.newPromise().setSuccess();
                LogUtils.log("WebSocket Client connected! response headers[sec-websocket-extensions]:{}" + response.headers());
                ChatUtils.sendData(ctx.channel()
                        , new BaseChatPOJO(BaseChatPOJO.Type.HANDSHAKE, null
                                , null, null, null, userKey)
                        , future -> LogUtils.log("send userKey done:" + future.isDone()));
            } catch (WebSocketHandshakeException var7) {
                FullHttpResponse res = (FullHttpResponse) msg;
                String errorMsg = String.format("WebSocket Client failed to connect,status:%s,reason:%s", res.status()
                        , res.content().toString(CharsetUtil.UTF_8));
                ctx.newPromise().setFailure(new Exception(errorMsg));
            }
        }

    }

    public static class MockClientInitializer extends ChannelInitializer<SocketChannel> {
        private MockClientHandler mockClientHandler;

        MockClientInitializer(MockClientHandler mockClientHandler) {
            this.mockClientHandler = mockClientHandler;
        }

        @Override
        protected void initChannel(SocketChannel channel) {
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addLast(new HttpClientCodec());
            pipeline.addLast(new HttpObjectAggregator(1024 * 1024 * 1024));
            pipeline.addLast(mockClientHandler);
        }
    }

    public ChatClientUtils(String userKey) {
        this.userKey = userKey;
        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    public ChatClientUtils(String userKey, String url) {
        this.userKey = userKey;
        this.url = url;
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

    private void init() {
        running = true;
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            URI uri = new URI(url);
            Bootstrap bootstrap = new Bootstrap();
            MockClientHandler webSocketClientHandler = new MockClientHandler(
                    WebSocketClientHandshakerFactory.newHandshaker(uri
                            , WebSocketVersion.V13
                            , null
                            , false
                            , new DefaultHttpHeaders())
                    , this
                    , userKey);
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MockClientInitializer(webSocketClientHandler));
            Channel channel = bootstrap.connect(uri.getHost(), uri.getPort()).sync().channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            LogUtils.log("ChatClient2Utils init error.e=" + e);
            eventLoopGroup.shutdownGracefully();
            running = false;
        }
    }

    public static void main(String[] args) {
        ChatClientUtils chatClientUtils = new ChatClientUtils("user2");
        while (chatClientUtils.channel == null) {
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
            }
        }
        chatClientUtils.sendText(new BaseChatPOJO(
                BaseChatPOJO.Type.TALK_USER
                , "", "user2", "makai", null, "I`m a test."
        ));
    }

}
