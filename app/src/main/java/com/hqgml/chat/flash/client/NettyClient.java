package com.hqgml.chat.flash.client;

import android.app.Activity;

import com.hqgml.chat.flash.client.handler.LoginResponseHandler;
import com.hqgml.chat.flash.client.handler.MessageResponseHandler;
import com.hqgml.chat.flash.codec.PacketDecoder;
import com.hqgml.chat.flash.codec.PacketEncoder;
import com.hqgml.chat.flash.codec.Spliter;
import com.hqgml.chat.flash.protocol.request.LoginRequestPacket;
import com.hqgml.chat.flash.protocol.request.MessageRequestPacket;
import com.hqgml.chat.flash.util.SessionUtil;
import com.hqgml.chat.utlis.ToastUtils;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author 闪电侠
 */
public class NettyClient {
    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 10000;
    private static Activity activity = null;
    static Channel channel=null;
    public void main(Activity activity) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        this.activity = activity;
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                channel = ((ChannelFuture) future).channel();

            } else if (retry == 0) {
                ToastUtils.showToast(activity, "服务器连接失败");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

   public static void startConsoleThread(String username, String password) {
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserName(username);
        loginRequestPacket.setPassword(password);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()) {
                    if (!SessionUtil.hasLogin(channel)) {
                        channel.writeAndFlush(loginRequestPacket);
                        waitForLoginResponse();
                    } else {
                        System.out.println("输入你要发送的用户名称");
                        String toUserId = sc.next();
                        System.out.println("输入你要发送的信息");
                        String message = sc.next();
                        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
                    }
                }
            }
        }).start();
    }


    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
