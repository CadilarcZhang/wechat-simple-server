<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta charset="utf-8">
  <title>微信JS-SDK Demo</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
  <link rel="stylesheet" href="../css/style.css">
  <script type="text/javascript" src="../js/jquery.min.js"></script>
</head>
  
  <body ontouchstart="">
	<div class="wxapi_container">
    <div class="wxapi_index_container">
      <ul class="label_box lbox_close wxapi_index_list">
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-basic">基础接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-share">分享接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-image">图像接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-voice">音频接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-smart">智能接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-device">设备信息接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-location">地理位置接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-webview">界面操作接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-scan">微信扫一扫接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-shopping">微信小店接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-card">微信卡券接口</a></li>
        <li class="label_item wxapi_index_item"><a class="label_inner" href="#menu-pay">微信支付接口</a></li>
      </ul>
    </div>
    <div class="lbox_close wxapi_form">
      <h3 id="menu-basic">基础接口</h3>
      <span class="desc">判断当前客户端是否支持指定JS接口</span>
      <button class="btn btn_primary" id="checkJsApi">checkJsApi</button>

      <h3 id="menu-share">分享接口</h3>
      <span class="desc">获取“分享到朋友圈”按钮点击状态及自定义分享内容接口</span>
      <button class="btn btn_primary" id="onMenuShareTimeline">onMenuShareTimeline</button>
      <span class="desc">获取“分享给朋友”按钮点击状态及自定义分享内容接口</span>
      <button class="btn btn_primary" id="onMenuShareAppMessage">onMenuShareAppMessage</button>
      <span class="desc">获取“分享到QQ”按钮点击状态及自定义分享内容接口</span>
      <button class="btn btn_primary" id="onMenuShareQQ">onMenuShareQQ</button>
      <span class="desc">获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口</span>
      <button class="btn btn_primary" id="onMenuShareWeibo">onMenuShareWeibo</button>
      <span class="desc">获取“分享到QZone”按钮点击状态及自定义分享内容接口</span>
      <button class="btn btn_primary" id="onMenuShareQZone">onMenuShareQZone</button>

      <h3 id="menu-image">图像接口</h3>
      <span class="desc">拍照或从手机相册中选图接口</span>
      <button class="btn btn_primary" id="chooseImage">chooseImage</button>
      <span class="desc">预览图片接口</span>
      <button class="btn btn_primary" id="previewImage">previewImage</button>
      <span class="desc">上传图片接口</span>
      <button class="btn btn_primary" id="uploadImage">uploadImage</button>
      <span class="desc">下载图片接口</span>
      <button class="btn btn_primary" id="downloadImage">downloadImage</button>

      <h3 id="menu-voice">音频接口</h3>
      <span class="desc">开始录音接口</span>
      <button class="btn btn_primary" id="startRecord">startRecord</button>
      <span class="desc">停止录音接口</span>
      <button class="btn btn_primary" id="stopRecord">stopRecord</button>
      <span class="desc">播放语音接口</span>
      <button class="btn btn_primary" id="playVoice">playVoice</button>
      <span class="desc">暂停播放接口</span>
      <button class="btn btn_primary" id="pauseVoice">pauseVoice</button>
      <span class="desc">停止播放接口</span>
      <button class="btn btn_primary" id="stopVoice">stopVoice</button>
      <span class="desc">上传语音接口</span>
      <button class="btn btn_primary" id="uploadVoice">uploadVoice</button>
      <span class="desc">下载语音接口</span>
      <button class="btn btn_primary" id="downloadVoice">downloadVoice</button>

      <h3 id="menu-smart">智能接口</h3>
      <span class="desc">识别音频并返回识别结果接口</span>
      <button class="btn btn_primary" id="translateVoice">translateVoice</button>

      <h3 id="menu-device">设备信息接口</h3>
      <span class="desc">获取网络状态接口</span>
      <button class="btn btn_primary" id="getNetworkType">getNetworkType</button>

      <h3 id="menu-location">地理位置接口</h3>
      <span class="desc">使用微信内置地图查看位置接口</span>
      <button class="btn btn_primary" id="openLocation">openLocation</button>
      <span class="desc">获取地理位置接口</span>
      <button class="btn btn_primary" id="getLocation">getLocation</button>

      <h3 id="menu-webview">界面操作接口</h3>
      <span class="desc">隐藏右上角菜单接口</span>
      <button class="btn btn_primary" id="hideOptionMenu">hideOptionMenu</button>
      <span class="desc">显示右上角菜单接口</span>
      <button class="btn btn_primary" id="showOptionMenu">showOptionMenu</button>
      <span class="desc">关闭当前网页窗口接口</span>
      <button class="btn btn_primary" id="closeWindow">closeWindow</button>
      <span class="desc">批量隐藏功能按钮接口</span>
      <button class="btn btn_primary" id="hideMenuItems">hideMenuItems</button>
      <span class="desc">批量显示功能按钮接口</span>
      <button class="btn btn_primary" id="showMenuItems">showMenuItems</button>
      <span class="desc">隐藏所有非基础按钮接口</span>
      <button class="btn btn_primary" id="hideAllNonBaseMenuItem">hideAllNonBaseMenuItem</button>
      <span class="desc">显示所有功能按钮接口</span>
      <button class="btn btn_primary" id="showAllNonBaseMenuItem">showAllNonBaseMenuItem</button>

      <h3 id="menu-scan">微信扫一扫</h3>
      <span class="desc">调起微信扫一扫接口</span>
      <button class="btn btn_primary" id="scanQRCode0">scanQRCode(微信处理结果)</button>
      <button class="btn btn_primary" id="scanQRCode1">scanQRCode(直接返回结果)</button>

      <h3 id="menu-shopping">微信小店接口</h3>
      <span class="desc">跳转微信商品页接口</span>
      <button class="btn btn_primary" id="openProductSpecificView">openProductSpecificView</button>

      <h3 id="menu-card">微信卡券接口</h3>
      <span class="desc">批量添加卡券接口</span>
      <button class="btn btn_primary" id="addCard">addCard</button>
      <span class="desc">调起适用于门店的卡券列表并获取用户选择列表</span>
      <button class="btn btn_primary" id="chooseCard">chooseCard</button>
      <span class="desc">查看微信卡包中的卡券接口</span>
      <button class="btn btn_primary" id="openCard">openCard</button>

      <h3 id="menu-pay">微信支付接口</h3>
      <span class="desc">发起一个微信支付请求</span>
      <button class="btn btn_primary" id="chooseWXPay">chooseWXPay</button>
    </div>
  </div>
</body>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
  /*
   * 注意：
   * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
   * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
   * 3. 常见问题及完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
   *
   * 开发中遇到问题详见文档“附录5-常见错误及解决办法”解决，如仍未能解决可通过以下渠道反馈：
   * 邮箱地址：weixin-open@qq.com
   * 邮件主题：【微信JS-SDK反馈】具体问题
   * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
   */
//   wx.config({
//       debug: false,
//       appId: 'wxf8b4f85f3a794e77',
//       timestamp: 1520140833,
//       nonceStr: 'owQWY0uS0FDesqbs',
//       signature: '6c2e0c10e3386c24d3f8814073b39e1e2e61f3e2',
//       jsApiList: [
//         'checkJsApi',
//         'onMenuShareTimeline',
//         'onMenuShareAppMessage',
//         'onMenuShareQQ',
//         'onMenuShareWeibo',
//         'onMenuShareQZone',
//         'hideMenuItems',
//         'showMenuItems',
//         'hideAllNonBaseMenuItem',
//         'showAllNonBaseMenuItem',
//         'translateVoice',
//         'startRecord',
//         'stopRecord',
//         'onVoiceRecordEnd',
//         'playVoice',
//         'onVoicePlayEnd',
//         'pauseVoice',
//         'stopVoice',
//         'uploadVoice',
//         'downloadVoice',
//         'chooseImage',
//         'previewImage',
//         'uploadImage',
//         'downloadImage',
//         'getNetworkType',
//         'openLocation',
//         'getLocation',
//         'hideOptionMenu',
//         'showOptionMenu',
//         'closeWindow',
//         'scanQRCode',
//         'chooseWXPay',
//         'openProductSpecificView',
//         'addCard',
//         'chooseCard',
//         'openCard'
//       ]
//   });
	wx.error(function(res){
	    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
	});
  $(function() {
  	 var url = location.href.split('#').toString();//url不能写死
		//ajax注入权限验证
		$.ajax({
			url:"<%=basePath%>wx/access",
			dataType: 'json',
			data: {"url" : url},
			complete: function(XMLHttpRequest, textStatus){
				
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				alert("发生错误："+errorThrown);
			},
			success: function(res){
				var appId = res.appId;
				var noncestr = res.noncestr;
				var jsapi_ticket = res.jsapi_ticket;
				var timestamp = res.timestamp;
				var signature = res.signature;
				wx.config({
				    debug: true, //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
				    appId: appId, //必填，公众号的唯一标识
				    timestamp: timestamp, // 必填，生成签名的时间戳
				    nonceStr: noncestr, //必填，生成签名的随机串
				    signature: signature,// 必填，签名，见附录1
				    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ',
				                'onMenuShareWeibo','onMenuShareQZone','chooseImage',
				                'uploadImage','downloadImage','startRecord','stopRecord',
				                'onVoiceRecordEnd','playVoice','pauseVoice','stopVoice',
				                'translateVoice','openLocation','getLocation','hideOptionMenu',
				                'showOptionMenu','closeWindow','hideMenuItems','showMenuItems',
				                'showAllNonBaseMenuItem','hideAllNonBaseMenuItem', 'addCard'] //必填，需要使用的JS接口列表，所有JS接口列表 见附录2
				});
			}
		});
  })
</script>

<script src="../js/zepto.min.js"></script>
<script src="../js/demo.js"> </script>
</html>
