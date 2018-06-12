/**
 * 
 */
package org.duodo.cmpp3s.message;

import java.util.concurrent.atomic.AtomicLong;

import org.duodo.cmpp3s.packet.CmppReportRequest;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.util.MsgId;

/**
 * @author huzorro(huzorro@gmail.com)
 * @param <T>
 *
 */
public class CmppReportRequestMessage extends DefaultMessage {
    private static final long serialVersionUID = -4631945859346437882L;
    private MsgId msgId = new MsgId();
	private String stat = new String(
			new byte[CmppReportRequest.STAT.getLength()],
			GlobalVars.defaultTransportCharset);
	private String submitTime, doneTime = String.format("%ty%<tm%<td%<tH%<tM", System.currentTimeMillis());
	private String destterminalId = new String(
			new byte[CmppReportRequest.DESTTERMINALID.getLength()],
			GlobalVars.defaultTransportCharset);
	private final static AtomicLong atomicLong = new AtomicLong(); 
	private long smscSequence = (atomicLong.compareAndSet(Integer.MAX_VALUE, 0)
			? atomicLong.getAndIncrement()
			: atomicLong.getAndIncrement());
	/**
	 * @return the msgId
	 */
	public MsgId getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(MsgId msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}
	/**
	 * @param stat the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}
	/**
	 * @return the submitTime
	 */
	public String getSubmitTime() {
		return submitTime;
	}
	/**
	 * @param submitTime the submitTime to set
	 */
	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}
	/**
	 * @return the doneTime
	 */
	public String getDoneTime() {
		return doneTime;
	}
	/**
	 * @param doneTime the doneTime to set
	 */
	public void setDoneTime(String doneTime) {
		this.doneTime = doneTime;
	}
	/**
	 * @return the destterminalId
	 */
	public String getDestterminalId() {
		return destterminalId;
	}
	/**
	 * @param destterminalId the destterminalId to set
	 */
	public void setDestterminalId(String destterminalId) {
		this.destterminalId = destterminalId;
	}
	/**
	 * @return the smscSequence
	 */
	public long getSmscSequence() {
		return smscSequence;
	}
	/**
	 * @param smscSequence the smscSequence to set
	 */
	public void setSmscSequence(long smscSequence) {
		this.smscSequence = smscSequence;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("CmppReportRequestMessage [msgId=%s, stat=%s, submitTime=%s, doneTime=%s, destterminalId=%s, smscSequence=%s]",
						msgId, stat, submitTime, doneTime, destterminalId,
						smscSequence);
	}
	
}
