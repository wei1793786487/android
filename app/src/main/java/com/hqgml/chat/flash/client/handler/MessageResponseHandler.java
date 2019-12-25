package com.hqgml.chat.flash.client.handler;

import com.hqgml.chat.flash.protocol.response.MessageResponsePacket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        if (messageResponsePacket.getMessage().contains("用户不在线")) {
            System.out.println("该用户不在线");
        }else {
            System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket.getMessage());
        }

    }
}
