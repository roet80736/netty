package io.netty.handler.codec.msgpack;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf>{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
	    List<Object> out) throws Exception {
	// TODO Auto-generated method stub
	final byte[] array;
	final int length = msg.readableBytes();
	array = new byte[length];
	msg.getBytes(msg.readerIndex(), array,0, length);
	MessagePack msgpack = new MessagePack();
	out.add(msgpack.read(array));
    }

}
