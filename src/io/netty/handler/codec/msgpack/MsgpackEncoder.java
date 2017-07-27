package io.netty.handler.codec.msgpack;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MsgpackEncoder extends MessageToByteEncoder<Object>{

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out)
	    throws Exception {
	// TODO Auto-generated method stub
	MessagePack msgpack = new MessagePack();
	byte[] raw = msgpack.write(msg);
	out.writeBytes(raw);
    }

}
