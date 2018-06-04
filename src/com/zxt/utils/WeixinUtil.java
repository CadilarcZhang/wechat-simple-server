package com.zxt.utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
//import java.util.List;
//import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.zxt.pojo.AccessToken;
import com.zxt.pojo.menu.Button;
import com.zxt.pojo.menu.ClickButton;
import com.zxt.pojo.menu.Menu;
import com.zxt.pojo.menu.ViewButton;

/**
 * ΢�Ź�����
 * 
 * @author zxt
 * 
 */
public class WeixinUtil {

	public static final String APPID = "wx74c1475c5055a2b8";// wxc26986936af1b35e,wx9623a4fcc845a55d，wx74c1475c5055a2b8
	
	public static final String XCXAPPID = "wx16b8f171a89653d8";// wxc26986936af1b35e,wx9623a4fcc845a55d，wx74c1475c5055a2b8

	public static final String APPSECRET = "e0f7be0618fb30de10df382e318a382b";// 3da98a7e9835d8ad7ac4cdb955aea90d,bde08a9c85276580cf2a1dca4ce288d1，e0f7be0618fb30de10df382e318a382b

	public static final String SNSAPI_BASE = "snsapi_base";

	public static final String SNSAPI_USERINFO = "snsapi_userinfo";

	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	private static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	private static final String DOWNLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

	private static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

	private static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

	public static final String WYSQ_CODE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";

	public static final String WYSQ_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

	public static final String WYSQ_REFRESH_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";

	public static final String WYSQ_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	/**
	 * get请求
	 * 
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException,
			IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if (entity != null) {
			String result = EntityUtils.toString(entity, "UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}

	/**
	 * POST请求
	 * 
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url, String outStr)
			throws ParseException, IOException {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		JSONObject jsonObject = null;
		httpost.setEntity(new StringEntity(outStr, "UTF-8"));
		HttpResponse response = client.execute(httpost);
		String result = EntityUtils.toString(response.getEntity(), "UTF-8");
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}

	/**
	 * �ļ��ϴ�
	 * 
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	public static String upload(String filePath, String accessToken, String type)
			throws IOException, NoSuchAlgorithmException,
			NoSuchProviderException, KeyManagementException {
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("该文件不存在");
		}

		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace(
				"TYPE", type);

		URL urlObj = new URL(url);
		// ����
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();

		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false);

		// ��������ͷ��Ϣ
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");

		// ���ñ߽�
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);

		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");

		byte[] head = sb.toString().getBytes("utf-8");

		// ��������
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// �����ͷ
		out.write(head);

		// �ļ����Ĳ���
		// ���ļ������ļ��ķ�ʽ ���뵽url��
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();

		// ��β����
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// ���������ݷָ���

		out.write(foot);

		out.flush();
		out.close();

		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			// ����BufferedReader����������ȡURL����Ӧ
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if (!"image".equals(type)) {
			typeName = type + "_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}

	public static String downLoad(String accessToken, String mediaId) {
		String dowloadUrl = DOWNLOAD_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("MEDIA_ID", mediaId);
		// JSONObject jsonObject = null;
		// try {
		// jsonObject = doGetStr(dowloadUrl);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return dowloadUrl;
	}

	/**
	 * ��ȡaccessToken
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static AccessToken getAccessToken() throws ParseException,
			IOException {
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace(
				"APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if (jsonObject != null) {
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return token;
	}

	public static Menu initYdbgMenu2() {
		ViewButton button11 = new ViewButton();
		button11.setName("李扬1");
		button11.setType("view");
		button11.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang1&approveType=0");
		ViewButton button12 = new ViewButton();
		button12.setName("李扬2");
		button12.setType("view");
		button12.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang2&approveType=0");
		ViewButton button13 = new ViewButton();
		button13.setName("李扬3");
		button13.setType("view");
		button13.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang3&approveType=0");
		ViewButton button14 = new ViewButton();
		button14.setName("李扬4");
		button14.setType("view");
		button14.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang4&approveType=0");
		ViewButton button15 = new ViewButton();
		button15.setName("李扬5");
		button15.setType("view");
		button15.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang5&approveType=0");
		Button gwglButton = new Button();
		gwglButton.setName("待办理");
		gwglButton.setSub_button(new Button[] { button11, button12, button13,
				button14, button15 });

		ViewButton button21 = new ViewButton();
		button21.setName("李扬1");
		button21.setType("view");
		button21.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang1&approveType=101");
		ViewButton button22 = new ViewButton();
		button22.setName("李扬2");
		button22.setType("view");
		button22.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang2&approveType=101");
		ViewButton button23 = new ViewButton();
		button23.setName("李扬3");
		button23.setType("view");
		button23.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang7&approveType=101");
		ViewButton button24 = new ViewButton();
		button24.setName("李扬4");
		button24.setType("view");
		button24.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang4&approveType=101");
		ViewButton button25 = new ViewButton();
		button25.setName("李扬5");
		button25.setType("view");
		button25.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/workflow/homeIndex?loginid=liyang5&approveType=101");
		Button gzlButton = new Button();
		gzlButton.setName("已办理");
		gzlButton.setSub_button(new Button[] { button21, button22, button23,
				button24, button25 });
		// 未读邮件（二级）
		ViewButton button31 = new ViewButton();
		button31.setName("未读邮件");
		button31.setType("view");
		button31.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/email/homeIndex?type=notread");
		// 收件箱（二级）
		ViewButton button32 = new ViewButton();
		button32.setName("收件箱");
		button32.setType("view");
		button32.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/email/homeIndex?type=receive");
		// 收件箱（二级）
		ViewButton button33 = new ViewButton();
		button33.setName("发件箱");
		button33.setType("view");
		button33.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/email/homeIndex?type=sended");
		// 收件箱（二级）
		ViewButton button34 = new ViewButton();
		button34.setName("草稿箱");
		button34.setType("view");
		button34.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/email/homeIndex?type=draft");
		// 邮件箱（一级）
		Button yjxButton = new Button();
		yjxButton.setName("邮件箱");
		yjxButton.setSub_button(new Button[] { button31, button32, button33,
				button34 });
		Menu menu = new Menu();
		menu.setButton(new Button[] {gwglButton, gzlButton, yjxButton});
		return menu;
	}
	
	public static Menu initMiniProgramMenu() {
		ViewButton viewButton = new ViewButton();
		viewButton.setName("小程序");
		viewButton.setType("miniprogram");
		viewButton.setUrl("http://www.baidu.com");
		viewButton.setAppid(WeixinUtil.XCXAPPID);
		viewButton.setPagepath("/pages/index/index");
		Menu menu = new Menu();
		menu.setButton(new Button[] {viewButton});
		return menu;
	}
	
	public static Menu initYdbgMenu() {
//		// 公文管理（二级）
//		ViewButton button11 = new ViewButton();
//		button11.setName("公文管理");
//		button11.setType("view");
//		button11.setUrl("http://thunisoftwx.mynatapp.cc/MOAWeChat/mobile/oa/document/homeIndex?approveType=0");
		// 工作流程（二级）
		ViewButton button12 = new ViewButton();
		button12.setName("待办理");
		button12.setType("view");
		button12.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/workflow/homeIndex?approveType=0");
		// 待办理（一级）
//		Button dblButton = new Button();
//		dblButton.setName("待办理");
//		dblButton.setSub_button(new Button[] { button11, button12 });
//		// 公文管理（二级）
//		ViewButton button21 = new ViewButton();
//		button21.setName("公文管理");
//		button21.setType("view");
//		button21.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/document/homeIndex?approveType=101");
		// 工作流程（二级）
		ViewButton button22 = new ViewButton();
		button22.setName("已办理");
		button22.setType("view");
		button22.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/workflow/homeIndex?approveType=101");
//		// 已办理（一级）
//		Button yblButton = new Button();
//		yblButton.setName("已办理");
//		yblButton.setSub_button(new Button[] { button11, button12 });
		// 未读邮件（二级）
		ViewButton button31 = new ViewButton();
		button31.setName("未读邮件");
		button31.setType("view");
		button31.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/email/homeIndex?type=notread");
		// 收件箱（二级）
		ViewButton button32 = new ViewButton();
		button32.setName("收件箱");
		button32.setType("view");
		button32.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/email/homeIndex?type=receive");
		// 收件箱（二级）
		ViewButton button33 = new ViewButton();
		button33.setName("发件箱");
		button33.setType("view");
		button33.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/email/homeIndex?type=sended");
		// 收件箱（二级）
		ViewButton button34 = new ViewButton();
		button34.setName("草稿箱");
		button34.setType("view");
		button34.setUrl("http://ynsftydoa.cn/MOAWeChat/mobile/oa/email/homeIndex?type=draft");
		// 邮件箱（一级）
		Button yjxButton = new Button();
		yjxButton.setName("邮件箱");
		yjxButton.setSub_button(new Button[] { button31, button32, button33,
				button34 });

		Menu menu = new Menu();
		menu.setButton(new Button[] { button12, button22, yjxButton });
		return menu;
	}

	/**
	 * 菜单的组装
	 * 
	 * @return
	 */
	public static Menu initMenu() {
		Menu menu = new Menu();

		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");

		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://zxt.ydbg.tunnel.qydev.com/MOAWeChat/mobile/oa/workflow/homeIndex");
		// button21.setUrl("http://zxt.ydbg.tunnel.qydev.com/wechat-access");
		// button21.setUrl("https://xy.weixin.tunnel.qydev.com/mobileOA/mobile/oa/document/homeIndex");
		// button21.setUrl("http://172.18.40.236:8081/ssfxpg-wx/ssfxpg/anon/startSsfxpg?fy=1401");
		// button21.setUrl("http://ydbg.com.tunnel.qydev.com/mobileOA/mobile/oa/document/homeIndex");

		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");

		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");

		// ClickButton button33 = new ClickButton();
		// button32.setName("微信发图");
		// button32.setType("pic_weixin");
		// button32.setKey("33");

		Button button = new Button();
		button.setName("功能菜单");
		button.setSub_button(new Button[] { button31, button32 });

		menu.setButton(new Button[] { button11, button21, button });
		return menu;
	}

	/**
	 * 菜单的创建和更改（重复执行此方法，新创建的菜单会覆盖）
	 * 
	 * @param token
	 * @param menu
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static int createMenu(String token, String menu)
			throws ParseException, IOException {
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doPostStr(url, menu);
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	/**
	 * 菜单的查询
	 * 
	 * @param token
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject queryMenu(String token) throws ParseException,
			IOException {
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}

	/**
	 * 菜单的删除
	 * 
	 * @param token
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static int deleteMenu(String token) throws ParseException,
			IOException {
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = doGetStr(url);
		int result = 0;
		if (jsonObject != null) {
			result = jsonObject.getInt("errcode");
		}
		return result;
	}

	/**
	 * 通过snsapi_base方式获取用户的openid
	 * 
	 * @param url
	 * @return
	 */
	public static String wysqByBase(String url) {
		String openid = null;
		String code_url = WYSQ_CODE.replace("APPID", APPID)
				.replace("REDIRECT_URI", url).replace("SCOPE", "snsapi_base")
				.replace("STATE", "123");
		try {
			doGetStr(code_url);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return openid;
	}

	// public static String translate(String source) throws ParseException,
	// IOException{
	// String url =
	// "http://openapi.baidu.com/public/2.0/translate/dict/simple?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
	// url = url.replace("KEYWORD", URLEncoder.encode(source, "UTF-8"));
	// JSONObject jsonObject = doGetStr(url);
	// String errno = jsonObject.getString("errno");
	// Object obj = jsonObject.get("data");
	// StringBuffer dst = new StringBuffer();
	// if("0".equals(errno) && !"[]".equals(obj.toString())){
	// TransResult transResult = (TransResult) JSONObject.toBean(jsonObject,
	// TransResult.class);
	// Data data = transResult.getData();
	// Symbols symbols = data.getSymbols()[0];
	// String phzh = symbols.getPh_zh()==null ? "" :
	// "����ƴ����"+symbols.getPh_zh()+"\n";
	// String phen = symbols.getPh_en()==null ? "" :
	// "ӢʽӢ�꣺"+symbols.getPh_en()+"\n";
	// String pham = symbols.getPh_am()==null ? "" :
	// "��ʽӢ�꣺"+symbols.getPh_am()+"\n";
	// dst.append(phzh+phen+pham);
	//
	// Parts[] parts = symbols.getParts();
	// String pat = null;
	// for(Parts part : parts){
	// pat = (part.getPart()!=null && !"".equals(part.getPart())) ?
	// "["+part.getPart()+"]" : "";
	// String[] means = part.getMeans();
	// dst.append(pat);
	// for(String mean : means){
	// dst.append(mean+";");
	// }
	// }
	// }else{
	// dst.append(translateFull(source));
	// }
	// return dst.toString();
	// }
	//
	// public static String translateFull(String source) throws ParseException,
	// IOException{
	// String url =
	// "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=jNg0LPSBe691Il0CG5MwDupw&q=KEYWORD&from=auto&to=auto";
	// url = url.replace("KEYWORD", URLEncoder.encode(source, "UTF-8"));
	// JSONObject jsonObject = doGetStr(url);
	// StringBuffer dst = new StringBuffer();
	// List<Map> list = (List<Map>) jsonObject.get("trans_result");
	// for(Map map : list){
	// dst.append(map.get("dst"));
	// }
	// return dst.toString();
	// }
}
