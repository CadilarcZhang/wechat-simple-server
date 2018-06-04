package com.zxt.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.ParseException;

import com.zxt.pojo.AccessToken;
import com.zxt.pojo.UserInfo;
import com.zxt.service.WeChatUserService;
import com.zxt.utils.WeixinUtil;

public class UserInfoTest {
	public static void main(String[] args) throws ParseException, IOException {
		AccessToken token = WeixinUtil.getAccessToken();
//		String access_token = "7_-6noVgNAYoKQS9tTQICy6VgrIhT4sgA7jp3NILupSm7zHjfAApnus727a_ltEeN__RXTZAlTSXdgPneVn-833Czs5tXcaBcKfBMuhkaJhO5uyVkZv62tRubUS_mUbDktVCyAJr9fRVb4F3kcGWEjAIARHP";
		WeChatUserService tChatUserService = new WeChatUserService();
//		tChatUserService.getUserInfo(tChatUserService.getUserOpenId(new WeChatTokenService().getToken("appid", "appSceret").getToken(), ""));
//		tChatUserService.getUserInfo(tChatUserService.getUserOpenId(token.getToken(), ""));
//		List<String> openidList = tChatUserService.getUserOpenId(token.getToken(), "o5oZp1hyPHBJ2YlcUtW0m0GoFAzI");
		List<String> openidList = tChatUserService.getUserOpenId(token.getToken(), "");
		System.out.println("userList:"+openidList.toString());
		List<UserInfo> userList = tChatUserService.getUserInfo(openidList);
		System.out.println("用户信息：");
		for (UserInfo userInfo : userList) {
			System.out.println("subscribe:"+userInfo.getSubscribe());
			System.out.println("openid:"+userInfo.getOpenId());
			System.out.println("nickName:"+userInfo.getNickname());
			System.out.println("sex:"+userInfo.getSex());
			System.out.println("language:"+userInfo.getLanguage());
			System.out.println("city:"+userInfo.getCity());
			System.out.println("province:"+userInfo.getProvince());
			System.out.println("country:"+userInfo.getCountry());
			System.out.println("headimgurl:"+userInfo.getHeadimgurl());
			System.out.println("subscribe_time:"+userInfo.getSubscribetime());
			System.out.println("remark:"+userInfo.getRemark());
			System.out.println("groupid:"+userInfo.getGroupid());
			System.out.println("----------------------------------------------------------");
		}
		//o5oZp1hyPHBJ2YlcUtW0m0GoFAzI
		//oSmPq4kd2hwlhUaF4C4kzlTSR0cc
    }
	
}
