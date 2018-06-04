package com.zxt.test;


import net.sf.json.JSONObject;

import com.zxt.pojo.AccessToken;
import com.zxt.utils.WeixinUtil;


public class WeixinTest {
	public static void main(String[] args) {
		try {
			AccessToken token = WeixinUtil.getAccessToken();
			System.out.println("票据:"+token.getToken());
			System.out.println("有效时间:"+token.getExpiresIn());
			
//			String path = "E:\\WeChatApp\\ReaderMovie\\images\\avatar.png";
//			String mediaId = WeixinUtil.upload(path, token.getToken(), "thumb");
//			System.out.println(mediaId);
//			
//			String dowloadUrl = WeixinUtil.downLoad(token.getToken(), mediaId);
//			System.out.println(dowloadUrl);
			
			String menu = JSONObject.fromObject(WeixinUtil.initYdbgMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if (result == 0) {
				System.out.println("菜单创建成功！");
			} else {
				System.out.println("错误码：" + result);
			}
			
//			JSONObject jsonObject = WeixinUtil.queryMenu(token.getToken());
//			System.out.println(jsonObject);
			
//			int result = WeixinUtil.deleteMenu(token.getToken());
//			if (result == 0) {
//				System.out.println("菜单删除成功！");
//			} else {
//				System.out.println("错误码：" + result);
//			}
			
//			String result = WeixinUtil.translate("my name is laobi");
			//String result = WeixinUtil.translateFull("");
//			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
