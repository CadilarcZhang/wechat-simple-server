package com.zxt.pojo.menu;

public class Button {
	//�˵�����
	private String type;
	//�˵����
	private String name;
	
	private String appid;
	
	private String pagepath;
	//�����˵�
	private Button[] sub_button;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Button[] getSub_button() {
		return sub_button;
	}
	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPagepath() {
		return pagepath;
	}
	public void setPagepath(String pagepath) {
		this.pagepath = pagepath;
	}
}
