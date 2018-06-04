package com.zxt.service;

import java.io.IOException;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//import org.apache.http.HttpException;
import org.apache.http.ParseException;

//import com.alibaba.fastjson.JSONObject;
import com.zxt.pojo.UserInfo;
//import com.zxt.utils.WeChatUtil;
import com.zxt.utils.WeixinUtil;

public class WeChatUserService {
	
	public static final String APPID = "wxc26986936af1b35e";//appID
	
	public static final String APPSECRET = "3da98a7e9835d8ad7ac4cdb955aea90d";//appsecret
	
	public static final String unionidURL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";//appsecret
	
	/**
     * 获取公众号关注的用户openid
     * @return
     */
    public List<String> getUserOpenId(String access_token, String nextOpenid) {
        
    	String path = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        path = path.replace("ACCESS_TOKEN", access_token).replace("NEXT_OPENID", nextOpenid);
//    	String path = unionidURL.replace("ACCESS_TOKEN", access_token).replace("OPENID", nextOpenid);
        System.out.println(path);
        List<String> result = new ArrayList<String>();
        try {
			JSONObject jsonObject = WeixinUtil.doGetStr(path);
			System.out.println(jsonObject.toString());
			if(jsonObject!=null){
//				String data = jsonObject.getString("data");
//				System.out.println("data:" + data);
				JSONObject dataJsonObject = jsonObject.getJSONObject("data");
				JSONArray jsonArray = dataJsonObject.getJSONArray("openid");
				System.out.println("返回结果："+jsonArray.toString());
				if (jsonArray.size() > 0) {
					for (int i = 0; i < jsonArray.size(); i++) {
						String openid = jsonArray.getString(i);
//						System.out.println("openid:" + openid);
						result.add(openid);
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//        try {
//            String strResp = WeChatUtil.doHttpsGet(path, "");
//            System.out.println(strResp);
//            Map map = WeChatUtil.jsonToMap(strResp);
//            Map tmapMap = (Map) map.get("data");
//            result = (List<String>) tmapMap.get("openid");
//            System.out.println(result.toString());
//        } catch (HttpException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;
    }
    
    /**
     * 通过用户openid 获取用户信息
     * @param userOpenids
     * @return
     * @throws IOException 
     * @throws ParseException 
     */
    public List<UserInfo> getUserInfo(List<String> userOpenids) throws ParseException, IOException {
        // 1、获取access_token
        // 使用测试 wx9015ccbcccf8d2f5 02e3a6877fa5fdeadd78d0f6f3048245
//        WeChatTokenService tWeChatTokenService = new WeChatTokenService();
//        String tAccess_Token = tWeChatTokenService.getToken("wx9015ccbcccf8d2f5", "02e3a6877fa5fdeadd78d0f6f3048245").getToken();
    	String tAccess_Token = WeixinUtil.getAccessToken().getToken();
//    	String tAccess_Token = "7_AHu0dEGEnA6vUv5NPx6Jl7oa5pXFuWfH8R4eh6JKlWuMRY18nalz8gP2reUmIPPuWnWjowiFfoqUbLQqlNuKL_MPOu7fhiJgNQR90QYVtbU1CtTkvzY8_QIZ26H2-SQlwAwmHOQdNO34hwluMUMcAAAVDV";
    	List<UserInfo> userList = new ArrayList<UserInfo>();
    	for (int i = 0; i < userOpenids.size(); i++) {
	    	String path = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+tAccess_Token
	    			+"&openid="+ userOpenids.get(i)+"&lang=zh-CN";
	    	JSONObject jsonObject = WeixinUtil.doGetStr(path);
	    	System.out.println(jsonObject.toString());
//			String openid = userOpenids.get(i);
			UserInfo user = new UserInfo();
			user.setSubscribe(jsonObject.getInt("subscribe"));
			user.setOpenId(jsonObject.getString("openid"));
			user.setNickname(jsonObject.getString("nickname"));
			user.setSex(jsonObject.getInt("sex"));
			user.setLanguage(jsonObject.getString("language"));
			user.setCity(jsonObject.getString("city"));
			user.setProvince(jsonObject.getString("province"));
			user.setCountry(jsonObject.getString("country"));
			user.setHeadimgurl(jsonObject.getString("headimgurl"));
			user.setSubscribetime(jsonObject.getInt("subscribe_time"));
			user.setRemark(jsonObject.getString("remark"));
			user.setGroupid(jsonObject.getInt("groupid"));
			userList.add(user);
		}
    	return userList;
//    	// 2、封装请求数据
//        List user_list = new ArrayList<Map>();
//        for (int i = 0; i < userOpenids.size(); i++) {
//            String openid = userOpenids.get(i);
//            Map tUserMap = new HashMap<String, String>();
//            tUserMap.put("openid", openid);
//            tUserMap.put("lang", "zh_CN");
//            user_list.add(tUserMap);
//        }
//        System.out.println(user_list.toString());
//        Map requestMap = new HashMap<String, List>();
//        requestMap.put("user_list", user_list);
//        String tUserJSON = JSONObject.fromObject(requestMap).toString();
//        // 3、请求调用
//        String result = getUserInfobyHttps(tAccess_Token, tUserJSON);
//        System.out.println(result);
//        // 4、解析返回将结果
//        return parseUserInfo(result);
    } 
//    /**
//     * 解析返回用户信息数据
//     * @param userInfoJSON
//     * @return
//     */
//    private List<UserInfo> parseUserInfo(String userInfoJSON) {
//        List user_info_list = new ArrayList<UserInfo>();
//
//        Map tMapData = WeChatUtil.jsonToMap(userInfoJSON);
//
//        List<Map> tUserMaps = (List<Map>) tMapData.get("user_info_list");
//
//        for (int i = 0; i < tUserMaps.size(); i++)
//        {
//            UserInfo tUserInfo = new UserInfo();
//            tUserInfo.setSubscribe((Integer) tUserMaps.get(i).get("subscribe"));
//            tUserInfo.setSex((Integer) tUserMaps.get(i).get("sex"));
//            tUserInfo.setOpenId((String) tUserMaps.get(i).get("openid"));
//            tUserInfo.setNickname((String) tUserMaps.get(i).get("nickname"));
//            tUserInfo.setLanguage((String) tUserMaps.get(i).get("language"));
//            tUserInfo.setCity((String) tUserMaps.get(i).get("city"));
//            tUserInfo.setProvince((String) tUserMaps.get(i).get("province"));
//            tUserInfo.setCountry((String) tUserMaps.get(i).get("country"));
//            tUserInfo.setHeadimgurl((String) tUserMaps.get(i).get("headimgurl"));
//            tUserInfo.setSubscribetime((Integer) tUserMaps.get(i).get("subscribe_time"));
//            tUserInfo.setRemark((String) tUserMaps.get(i).get("remark"));
//            tUserInfo.setGroupid((Integer) tUserMaps.get(i).get("groupid"));
//            user_info_list.add(tUserInfo);
//        }
//
//        return user_info_list;
//    }
//
//    /**
//     * 调用HTTPS接口，获取用户详细信息
//     * @param access_token
//     * @param requestData
//     * @return
//     */
//    private String getUserInfobyHttps(String access_token, String requestData) {
//        // 返回报文
//        String strResp = "";
//        String path = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
//        path = path.replace("ACCESS_TOKEN", access_token);
//
//        try {
//            strResp = WeChatUtil.doHttpsPost(path, requestData);
//        }
//        catch (HttpException e) {
//            // 发生致命的异常，可能是协议不对或者返回的内容有问题
//            System.out.println("Please check your provided http address!" + e);
//            e.printStackTrace();
//        }
//        catch (IOException e) {
//            // 发生网络异常
//        }
//        catch (Exception e) {
//            System.out.println(e);
//        }
//        finally {}
//        return strResp;
//    }
}
