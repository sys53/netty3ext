/**
 * 
 */
package org.duodo.cmpp3s.handler;

import org.duodo.cmpp3s.message.CmppDeliverResponseMessage;
import org.duodo.cmpp3s.packet.CmppPacketType;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CmppDeliverResponseMessageHandler extends
		SimpleChannelUpstreamHandler {
	private PacketType packetType;
	
	public CmppDeliverResponseMessageHandler() {
		this(CmppPacketType.CMPPDELIVERRESPONSE);
	}

	public CmppDeliverResponseMessageHandler(PacketType packetType) {
		this.packetType = packetType;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message message = (Message) e.getMessage();
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }	
        CmppDeliverResponseMessage responseMessage = (CmppDeliverResponseMessage) message; 
        
        ((Session) ctx.getChannel().getAttachment()).responseAndScheduleTask(responseMessage);
 
		super.messageReceived(ctx, e);
	}
	
}
