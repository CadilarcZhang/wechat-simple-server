/**
 * 微信授权
 */
const APPID = "wxc26986936af1b35e";
const SCOPE = "snsapi_userinfo";
$(document).ready(function() {
//	$(function(){
//	    pushHistory();
//	    window.addEventListener("popstate", function(e) {
//	    // 新版中使用wx.closeWindow()方法
//	        WeixinJSBridge.call('closeWindow');
//	    }, false);
//	});
//
//	function pushHistory() {
//	    var state = {
//	        title: "myCenter",
//	        url: "__SELF__"
//	    };
//	    window.history.pushState(state, state.title, state.url);
//	}
	center.enterWxAuthor();
});
var center = {
	init: function() {
		
	},
	enterWxAuthor: function() {
		var userInfo = localStorage.getItem("userInfo");
		if (!userInfo) {
			var code = common.getUrlParam("code");
//			code = null;
			if (code) {
				console.log("code:" + code);
//				userInfo = common.getWxUserInfo();
				common.getOpenId(code);
				center.init();
			} else {
				window.location.href = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+
					"&redirect_uri="+window.location.href+"&response_type=code&scope="+SCOPE+"&state=123#wechat_redirect";
			} 
		} else {
			center.init();
		}
	}
}
var common = {
	getUrlParam : function(param) {
		var search = document.location.search;
        var pattern = new RegExp("[?&]" + param + "\=([^&]+)", "g");
        var matcher = pattern.exec(search);
        var items = null;
        if (null != matcher) {
            try {
                items = decodeURIComponent(decodeURIComponent(matcher[1]));
            } catch (e) {
                try {
                    items = decodeURIComponent(matcher[1]);
                } catch (e) {
                    items = matcher[1];
                }
            }
        }
        return items;
	},
	getOpenId: function(code) {
//		window.location.href = "http://www.2510491430.com.tunnel.qydev.com/wechat-access/wx/access?code="+code;
		var code_url = "http://zxt.ydbg.tunnel.qydev.com/wechat-access/authLogin";
		$.ajax({
			url: code_url,
			dataType: 'json',
			data: {"code" : code},
			success: function(res) {
				if (res && res.success) {
					var resultJson = res.result;
					var resultStr = JSON.stringify(resultJson);
					console.log("userInfo:" + resultStr);
					localStorage.setItem("userInfo", resultJson);
				}
			}
		});
	}
}