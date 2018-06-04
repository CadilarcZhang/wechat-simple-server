<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <title>微信公众号首页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
		.avator {
			    width: 50px;
			    height: 50px;
			    border-radius: 25px;
			    margin-left: 5px;
		}
	</style>
  </head>
  
  <body>
        <section>
        	<img class="avator" src="${userInfo.headimgurl}"></img>
        	<span>${userInfo.nickname}</span>
        </section>
    	<h1>点击进行微信网页授权</h1> 
    	<a style="font-size: 40px;" href="wxLogin">点我授权登录</a>
  </body>
  <script type="text/javascript">
//   	function share1() {
//   		wx.onMenuShareTimeline({
// 		    title: '', // 分享标题
// 		    link: '', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
// 		    imgUrl: '', // 分享图标
// 		    success: function () {
// 		    用户确认分享后执行的回调函数
// 			}
//   		)};
//   	}
// 	$(function(){
// 		wx.checkJsApi({
// 		    jsApiList: ['chooseImage'], // 需要检测的JS接口列表，所有JS接口列表见附录2,
// 		    success: function(res) {
// 		    以键值对的形式返回，可用的api值true，不可用为false
// 		    如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
// 		    	alert("ok!");
// 		    }
// 		});
// 		$("#share1").click(function() {
// 			wx.onMenuShareTimeline({
//                     title: '这是一个测试的标题--程高伟的博客',
//                     link: 'http://blog.csdn.net/frankcheng5143',
//                     imgUrl: 'http://avatar.csdn.net/E/B/6/1_frankcheng5143.jpg',
//                     success: function () { 
//                         用户确认分享后执行的回调函数
//                          alert('分享到朋友圈成功');
//                     },
//                     cancel: function () { 
//                         用户取消分享后执行的回调函数
//                          alert('你没有分享到朋友圈');
//                     }
//                 });
// 		});
// 		var url = window.location.href;
// 		var url = location.href.split('#').toString();//url不能写死
// 		ajax注入权限验证
// 		$.ajax({
// 			url:"http://www.2510491430.com.tunnel.qydev.com/wechat-access/wx/access",
// 			dataType: 'json',
// 			data: {"url" : url},
// 			complete: function(XMLHttpRequest, textStatus){
				
// 			},
// 			error: function(XMLHttpRequest, textStatus, errorThrown){
// 				alert("发生错误："+errorThrown);
// 			},
// 			success: function(res){
// 				var appId = res.appId;
// 				var noncestr = res.noncestr;
// 				var jsapi_ticket = res.jsapi_ticket;
// 				var timestamp = res.timestamp;
// 				var signature = res.signature;
// 				wx.config({
// 				    debug: true, //开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
// 				    appId: appId, //必填，公众号的唯一标识
// 				    timestamp: timestamp, // 必填，生成签名的时间戳
// 				    nonceStr: noncestr, //必填，生成签名的随机串
// 				    signature: signature,// 必填，签名，见附录1
// 				    jsApiList: ['onMenuShareTimeline','onMenuShareAppMessage','onMenuShareQQ',
// 				                'onMenuShareWeibo','onMenuShareQZone','chooseImage',
// 				                'uploadImage','downloadImage','startRecord','stopRecord',
// 				                'onVoiceRecordEnd','playVoice','pauseVoice','stopVoice',
// 				                'translateVoice','openLocation','getLocation','hideOptionMenu',
// 				                'showOptionMenu','closeWindow','hideMenuItems','showMenuItems',
// 				                'showAllNonBaseMenuItem','hideAllNonBaseMenuItem'] //必填，需要使用的JS接口列表，所有JS接口列表 见附录2
// 				});
// 			}
// 		});
// 		wx.ready(function(){
//                 wx.hideOptionMenu();
//                 wx.onMenuShareTimeline({
//                     title: '这是一个测试的标题--程高伟的博客',
//                     link: 'http://blog.csdn.net/frankcheng5143',
//                     imgUrl: 'http://avatar.csdn.net/E/B/6/1_frankcheng5143.jpg',
//                     success: function () { 
//                         用户确认分享后执行的回调函数
//                          alert('分享到朋友圈成功');
//                     },
//                     cancel: function () { 
//                         用户取消分享后执行的回调函数
//                          alert('你没有分享到朋友圈');
//                     }
//                 });
//                 wx.onMenuShareAppMessage({
//                       title: '这是一个测试的标题--百度',
//                       desc: '这个是要分享内容的一些描述--百度一下，你就知道',
//                       link: 'http://www.baidu.com',
//                       imgUrl: 'https://www.baidu.com/img/bd_logo1.png',
//                       trigger: function (res) {
//                         不要尝试在trigger中使用ajax异步请求修改本次分享的内容，因为客户端分享操作是一个同步操作，这时候使用ajax的回包会还没有返回
//                       },
//                       success: function (res) {
//                           alert('分享给朋友成功');
//                       },
//                       cancel: function (res) {
//                         alert('你没有分享给朋友');
//                       },
//                       fail: function (res) {
//                         alert(JSON.stringify(res));
//                       }
//                     });
//             });
	
// 	});
//   </script>
</html>
