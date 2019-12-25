package com.hqgml.chat.flash.protocol.request;


import com.hqgml.chat.flash.protocol.Packet;

import static com.hqgml.chat.flash.protocol.command.Command.LOGIN_REQUEST;


public class LoginRequestPacket extends Packet {
    private String userName;
    private String password;

    @Override
    public Byte getCommand() {

        return LOGIN_REQUEST;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
