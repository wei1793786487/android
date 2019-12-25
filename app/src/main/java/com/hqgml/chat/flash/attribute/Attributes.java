package com.hqgml.chat.flash.attribute;

import com.hqgml.chat.flash.session.Session;

import io.netty.util.AttributeKey;

public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
