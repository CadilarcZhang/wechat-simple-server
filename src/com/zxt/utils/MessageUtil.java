package com.zxt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.zxt.pojo.ClickMenuMsg;
import com.zxt.pojo.Image;
import com.zxt.pojo.ImageMessage;
import com.zxt.pojo.Music;
import com.zxt.pojo.MusicMessage;
import com.zxt.pojo.NewsItem;
import com.zxt.pojo.NewsMessage;
import com.zxt.pojo.TextMessage;

public class MessageUtil {
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LOCATION = "location";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_EVENT = "event";//事件推送
	public static final String MESSAGE_SUBSCRIBE = "subscribe";//关注
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";//未关注
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";
	
	public static final String DOUBAN_BASE_URL = "https://api.douban.com";
	
	/**
	 * xml转出map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		InputStream is = request.getInputStream();
		Document doc = reader.read(is);
		Element root = doc.getRootElement();
		@SuppressWarnings("unchecked")
		List<Element> list = root.elements();
		for (Element ele : list) {
			map.put(ele.getName(), ele.getText());
		}
		is.close();
		return map;
	}
	
	/**
	 * 文本对象转成xml
	 * @param msg
	 * @return
	 */
	public static String textMessageToXml(TextMessage msg) {
		XStream xStream = new XStream();
		xStream.alias("xml", TextMessage.class);//将根节点设成"xml"
		return xStream.toXML(msg);
	}
	
	/**
	 * 图文对象转成xml
	 * @param msg
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", NewsMessage.class);//将根节点设成"xml"
		xStream.alias("item", NewsItem.class);//将内容节点改成"item"
		return xStream.toXML(newsMessage);
	}
	
	/**
	 * 处理文本消息
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @param msgId 
	 * @return
	 */
	public static String handleMessage(String fromUserName, String toUserName, String content, String msgId) {
		TextMessage msg = new TextMessage();
		msg.setFromUserName(toUserName);
		msg.setToUserName(fromUserName);
		msg.setCreateTime(new Date().getTime());
		msg.setMsgType(MESSAGE_TEXT);
//		msg.setContent("你发送的信息是：" + content);
		msg.setContent(content);
		msg.setMsgId(msgId);
		return textMessageToXml(msg);
	}
	
	public static String handleMessage2(String fromUserName, String toUserName, String content, String msgId) {
		TextMessage msg = new TextMessage();
		msg.setFromUserName(toUserName);
		msg.setToUserName(fromUserName);
		msg.setCreateTime(new Date().getTime());
		msg.setMsgType(MESSAGE_TEXT);
		msg.setContent("你发送的信息是：" + content);
//		msg.setContent(content);
		msg.setMsgId(msgId);
		return textMessageToXml(msg);
	}
	
	/**
	 * 处理click菜单消息
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @param msgId 
	 * @return
	 */
	public static String handleClickMenuMsg(String fromUserName, String toUserName, String event, String eventKey) {
		ClickMenuMsg msg = new ClickMenuMsg();
		msg.setFromUserName(toUserName);
		msg.setToUserName(fromUserName);
		msg.setCreateTime(new Date().getTime());
		msg.setMsgType(event);
		msg.setEventKey(eventKey);
		return CLickMenuMsgToXml(msg);
	}
	
	private static String CLickMenuMsgToXml(ClickMenuMsg msg) {
		XStream xStream = new XStream();
		xStream.alias("xml", ClickMenuMsg.class);//将根节点设成"xml"
		return xStream.toXML(msg);
	}

	/**
	 * 拼接主菜单
	 * @return
	 */
	public static String menuText() {
		StringBuilder menuText = new StringBuilder();
		menuText.append("欢迎您的关注，请按照菜单提示操作：\n\n");
		menuText.append("1.足坛快讯.\n\n");
		menuText.append("2.音乐分享.\n\n");
		menuText.append("3.心灵鸡汤.\n\n");
		menuText.append("4.图片分享.\n\n");
		menuText.append("5.歌曲速递.\n\n");
		menuText.append("回复？调用此菜单");
		return menuText.toString();
	}
	
	/**
	 * 处理第一个菜单消息
	 */
	public static String handleFirstMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("懂球帝是一款提供全球体育足球新闻、深度报道、足球社区的手机App，基本上可以满足球迷在手机上关于足球的一切需求。有诸多现役和退役的足球运动员、教练员、解说员和记者都在使用。");
		return sb.toString();
	}
	
	/**
	 * 处理第二个菜单消息
	 */
	public static String handleSecondMenu() {
		StringBuilder sb = new StringBuilder();
		sb.append("网易云音乐是一款由网易开发的音乐产品，是网易杭州研究院的成果[1]  ，依托专业音乐人、DJ、好友推荐及社交功能，在线音乐服务主打歌单、社交、大牌推荐和音乐指纹，以歌单、DJ节目、社交、地理位置为核心要素，主打发现和分享。\n[2]"); 
		sb.append("该产品2013年4月23日正式发布，截止2017年04月，产品已经包括iPhone、Android、Web、PC、iPad、WP8、Mac、Win10UWP、Linux九大平台客户端[3-5]  。2015年1月16日，网易云音乐荣膺百度中国好应用“年度优秀视觉设计奖”。");
		return sb.toString();
	}
	
	public static String handleNewsMessage(String fromUserName, String toUserName) {
		String message = null;
		List<NewsItem> newsList = new ArrayList<NewsItem>();
		NewsItem news = new NewsItem();
		news.setTitle("愿你走过半生，归来任是少年");
		news.setDescription("毫无理由的喜欢这个标题，一如毕业许多年依然怀念在学校里的青笼岁月。常常在想，如果能回到过去，是否可以改变未来？可惜人生无法回头，也无法重走，兴许这就是人生的魅力，一种遗憾、几许心酸、五味杂陈却心怀温暖。");
		news.setPicUrl("http://zxtwechat.nat100.top/wechat-access/images/home.jpg");
		news.setUrl("http://mp.weixin.qq.com/s/_p1KWfWpfeqyztVYnWyVvQ");
		newsList.add(news);
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setFromUserName(toUserName);
		newsMessage.setToUserName(fromUserName);
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticleCount(newsList.size());
		newsMessage.setArticles(newsList);
		message = newsMessageToXml(newsMessage);
		return message;
	}

	public static String handleImageMessage(String fromUserName,
			String toUserName) {
		String path = "E:\\WeChatApp\\ReaderMovie\\images\\avatar.png";
		Image image = new Image();
		String MediaId = null;
		try {
			String accessToken = WeixinUtil.getAccessToken().getToken();
			MediaId = WeixinUtil.upload(path, accessToken, "image");
		}  catch (Exception e) {
			e.printStackTrace();
		}
		image.setMediaId(MediaId);
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setImage(image);
		String message = imageMessageToXml(imageMessage);
		return message;
	}

	private static String imageMessageToXml(ImageMessage imageMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", ImageMessage.class);
		return xStream.toXML(imageMessage);
	}

	public static String handleMusicMessage(String fromUserName,
			String toUserName) {
		String path = "E:\\WeChatApp\\ReaderMovie\\images\\avatar.png";
		Music music = new Music();
		music.setTitle("See You Again");
		music.setDescription("速度与激情7主题曲");
		music.setMusicUrl("http://zxtwechat.nat100.top/wechat-access/music/See You Again.mp3");
		music.setHQMusicUrl("http://zxtwechat.nat100.top/wechat-access/music/See You Again.mp3");
		String mediaId = null;
		try {
			mediaId = WeixinUtil.upload(path, WeixinUtil.getAccessToken().getToken(), "thumb");
		} catch (Exception e) {
			e.printStackTrace();
		}
		music.setThumbMediaId(mediaId);
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setMusic(music);
		String message = musicMessageToXml(musicMessage);
		return message;
	}

	private static String musicMessageToXml(MusicMessage musicMessage) {
		XStream xStream = new XStream();
		xStream.alias("xml", MusicMessage.class);
		return xStream.toXML(musicMessage);
	}
	
	/**
	 * ��װ������Ϣ
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message = null;
		Music music = new Music();
		music.setThumbMediaId("bopWDlYFQgsL6Jin6cWwmUhQbnPn5ktBgGmVyxZ4V_Dzl3cK5f4Wzm5cqi60K7H8");
		music.setTitle("see you again");
		music.setDescription("速度与激情7主题曲");
		music.setMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");
		music.setHQMusicUrl("http://zapper.tunnel.mobi/Weixin/resource/See You Again.mp3");
		
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setMsgType(MESSAGE_MUSIC);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = musicMessageToXml(musicMessage);
		return message;
	}
}
