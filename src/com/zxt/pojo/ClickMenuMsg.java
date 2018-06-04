package com.zxt.pojo;

/**
 * click类型菜单的实体
 * @author Administrator
 *
 */
public class ClickMenuMsg extends BaseMessage {
	private String Event;
	private String EventKey;
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
}
