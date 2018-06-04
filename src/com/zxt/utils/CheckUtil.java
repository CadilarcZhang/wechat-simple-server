package com.zxt.utils;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.http.Cookie;

public class CheckUtil {
	
	private static final String TOKEN = "zxt_weixin";
	public  static final String LOGIN_COOKIE = "login_cookie";
	public  static final String OPENID_COOKIE = "openid_cookie";
	
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		//1.将token、timestamp、nonce三个参数进行字典序排序 
		String[] arr = new String[] {TOKEN, timestamp, nonce};
		Arrays.sort(arr);
		//2.将三个参数字符串拼接成一个字符串进行sha1加密 
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		//3.开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		String temp = getSha1(content.toString());
		return temp.equals(signature);
	}
	
	public static String getSha1(String str){
        if(str==null||str.length()==0){
            return null;
        }
        char hexDigits[] = {'0','1','2','3','4','5','6','7','8','9',
                'a','b','c','d','e','f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j*2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];      
            }
            return new String(buf);
        } catch (Exception e) {
            return null;
        }
    }
	
	/**
	 * 获取指定的cookie对象
	 * @param cookies
	 * @param name
	 * @return
	 */
	public static Cookie getCookieByName(Cookie[] cookies, String name) {
		for (Cookie cookie : cookies) {
			if (LOGIN_COOKIE.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}
	
	/**
	 * 清除所有cookie
	 * @param cookies
	 */
	public static void removeAllCookies(Cookie[] cookies) {
		for (Cookie cookie : cookies) {
			cookie.setValue(null);  
            cookie.setMaxAge(0);// 立即销毁cookie 
		}
	}
}
