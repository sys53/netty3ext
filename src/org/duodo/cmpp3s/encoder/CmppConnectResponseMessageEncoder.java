package org.duodo.cmpp3s.encoder;

import org.duodo.cmpp3s.message.CmppConnectResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 *
 * @author huzorro(huzorro@gmail.com)
 */
public class CmppConnectResponseMessageEncoder extends OneToOneEncoder {
    private PacketType packetType;
    /**
     * 
     */
    public CmppConnectResponseMessageEncoder() {
        this(CmppPacketType.CMPPCONNECTRESPONSE);
    }
    public CmppConnectResponseMessageEncoder(PacketType packetType) {
        this.packetType = packetType;
    }

    /* (non-Javadoc)
     * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
     */
    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel,
            Object msg) throws Exception {  
        if(!(msg instanceof Message)) return msg;
        Message message = (Message) msg;
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()) return msg;
        
        CmppConnectResponseMessage responseMessage = (CmppConnectResponseMessage) msg;

        ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();
        
        bodyBuffer.writeInt((int) responseMessage.getStatus());
        bodyBuffer.writeBytes(responseMessage.getAuthenticatorISMG());
        bodyBuffer.writeByte(responseMessage.getVersion());
        
        message.setBodyBuffer(bodyBuffer.copy().array());
        
        ChannelBuffer messageBuffer = ChannelBuffers.dynamicBuffer();
        
        messageBuffer.writeBytes(message.getHeader().getHeadBuffer());
        messageBuffer.writeBytes(message.getBodyBuffer());
        return messageBuffer;
    }

}
