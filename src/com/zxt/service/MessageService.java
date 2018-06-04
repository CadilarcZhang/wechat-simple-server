package com.zxt.service;

import java.util.Map;

import com.zxt.utils.MessageUtil;

public class MessageService {
	
	public String getMessageByEvent(Map<String, String> map) {
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String msgType = map.get("MsgType");
		String content = map.get("Content");
		String msgId = map.get("MsgId");
		String message = "";
		if (MessageUtil.MESSAGE_TEXT.equals(msgType)) {
			if ("1".equals(content)) {//回复“1”
				message = MessageUtil.handleMessage(fromUserName, toUserName, MessageUtil.handleFirstMenu(), msgId);
			} else if ("2".equals(content)) {//回复“2”
				message = MessageUtil.handleMessage(fromUserName, toUserName, MessageUtil.handleSecondMenu(), msgId);
			} else if ("3".equals(content)) {//回复“3”
				message = MessageUtil.handleNewsMessage(fromUserName, toUserName);
			} else if ("4".equals(content)) {
				message = MessageUtil.handleImageMessage(fromUserName, toUserName);
			} else if ("5".equals(content)) {
//				message = MessageUtil.handleMusicMessage(fromUserName, toUserName);
				message = MessageUtil.initMusicMessage(toUserName, fromUserName);
			} else if("?".equals(content)||"？".equals(content)) {//回复”？“或“?”
				message = MessageUtil.handleMessage(fromUserName, toUserName, MessageUtil.menuText(), msgId);
			} else {
				message = MessageUtil.handleMessage2(fromUserName, toUserName, MessageUtil.menuText(), msgId);
			}
		} else if(MessageUtil.MESSAGE_EVENT.equals(msgType)) {
			String eventType = map.get("Event");
			// 关注公众号回复消息
			if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
				message = MessageUtil.handleMessage(fromUserName, toUserName, MessageUtil.menuText(), msgId);
			} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {//普通click菜单
				message = MessageUtil.handleMessage(fromUserName, toUserName, MessageUtil.menuText(), msgId);
			} else if (MessageUtil.MESSAGE_VIEW.equals(eventType)) {//view类型菜单
				String url = map.get("EventKey");
				message = MessageUtil.handleClickMenuMsg(fromUserName, toUserName, eventType, url);
			} else if (MessageUtil.MESSAGE_SCANCODE.equals(eventType)) {//扫码click菜单
				String key = map.get("EventKey");
				message = MessageUtil.handleClickMenuMsg(fromUserName, toUserName, eventType, key); 
			} 
		} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {//定位click菜单
			String label = map.get("Label");
//			message = MessageUtil.handleClickMenuMsg(fromUserName, toUserName, msgType, label);
			message = MessageUtil.handleMessage(fromUserName, toUserName, label, msgId);//用户消息自动回复
		}
		return message;
	}
	
}
