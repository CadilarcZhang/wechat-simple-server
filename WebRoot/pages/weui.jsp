<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<meta name=viewport
	content="width=device-width,initial-scale=1,user-scalable=0">
<title>weui.js</title>
<link rel=stylesheet href=https://res.wx.qq.com/open/libs/weui/1.1.2/weui.min.css>
<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css"> -->
<!-- <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css"> -->
<style>
body,html {
	height: 100%;
	background-color: #f8f8f8
}

body {
	font-family: -apple-system-font, Helvetica Neue, Helvetica, sans-serif
}

.item {
	padding: 10px 0
}

.item__title {
	margin-bottom: 5px;
	padding-left: 15px;
	padding-right: 15px;
	color: #999;
	font-weight: 400;
	font-size: 14px
}

.item__ctn {
	padding: 0 15px
}

.page_feedback {
	padding: 15px;
	overflow: auto;
	background-color: #FFF
}

label>* {
	pointer-events: none
}

.weui-picker__item {
	padding: 0;
	height: 34px;
	line-height: 34px
}
</style>
</head>
<body ontouchstart>
	<p id=bear></p>
	<div class=weui-tab id=tab>
		<div class=weui-navbar>
			<div class=weui-navbar__item>反馈</div>
			<div class=weui-navbar__item>表单</div>
			<div class=weui-navbar__item>上传</div>
			<div class=weui-navbar__item>其它</div>
		</div>
		<div class=weui-tab__panel>
			<div class="weui-tab__content page_feedback">
				<a href=javascript:; id=alertBtn class="weui-btn weui-btn_default">Alert</a>
				<a href=javascript:; id=confirmBtn class="weui-btn weui-btn_default">Confirm</a>
				<a href=javascript:; id=toastBtn class="weui-btn weui-btn_default">Toast</a>
				<a href=javascript:; id=loadingBtn class="weui-btn weui-btn_default">Loading</a>
				<a href=javascript:; id=actionSheetBtn
					class="weui-btn weui-btn_default">ActionSheet</a> <a
					href=javascript:; id=topTipsBtn class="weui-btn weui-btn_default">TopTips</a>
				<a href=javascript:; id=pickerBtn class="weui-btn weui-btn_default">Picker</a>
				<a href=javascript:; id=datePickerBtn
					class="weui-btn weui-btn_default">DatePicker</a> <a
					href=javascript:; id=multiPickerBtn
					class="weui-btn weui-btn_default">Multi-Picker</a> <a
					href=javascript:; id=cascadePickerBtn
					class="weui-btn weui-btn_default">Cascade-Picker</a>
			</div>
			<div class=weui-tab__content>
				<div id=form>
					<div class=weui-cells__title>性别</div>
					<div class="weui-cells weui-cells_radio">
						<label class="weui-cell weui-check__label" for=r1>
							<div class=weui-cell__bd>男</div>
							<div class=weui-cell__ft>
								<input required type=radio class=weui-check name=sex value=male
									id=r1 tips=请选择性别> <span class=weui-icon-checked></span>
							</div>
						</label> <label class="weui-cell weui-check__label" for=r2>
							<div class=weui-cell__bd>女</div>
							<div class=weui-cell__ft>
								<input type=radio name=sex class=weui-check value=female id=r2>
								<span class=weui-icon-checked></span>
							</div>
						</label>
					</div>
					<div class=weui-cells__title>编码助手(1-2个)</div>
					<div class="weui-cells weui-cells_checkbox">
						<label class="weui-cell weui-check__label" for=c1>
							<div class=weui-cell__hd>
								<input required pattern={1,2} type=checkbox tips=请勾选1-2个敲码助手
									class=weui-check name=assistance id=c1> <i
									class=weui-icon-checked></i>
							</div>
							<div class=weui-cell__bd>黄药师</div>
						</label> <label class="weui-cell weui-check__label" for=c2>
							<div class=weui-cell__hd>
								<input type=checkbox name=assistance class=weui-check id=c2>
								<i class=weui-icon-checked></i>
							</div>
							<div class=weui-cell__bd>欧阳锋</div>
						</label> <label class="weui-cell weui-check__label" for=c3>
							<div class=weui-cell__hd>
								<input type=checkbox name=assistance class=weui-check id=c3>
								<i class=weui-icon-checked></i>
							</div>
							<div class=weui-cell__bd>段智兴</div>
						</label> <label class="weui-cell weui-check__label" for=c4>
							<div class=weui-cell__hd>
								<input type=checkbox name=assistance class=weui-check id=c4>
								<i class=weui-icon-checked></i>
							</div>
							<div class=weui-cell__bd>洪七公</div>
						</label>
					</div>
					<div class="weui-cells weui-cells_form">
						<div class=weui-cell>
							<div class=weui-cell__hd>
								<label class=weui-label>手机号</label>
							</div>
							<div class=weui-cell__bd>
								<input class=weui-input type=tel required pattern=^\d{11}$
									maxlength=11 placeholder=输入你现在的手机号 emptytips=请输入手机号
									notmatchtips=请输入正确的手机号>
							</div>
							<div class=weui-cell__ft>
								<i class=weui-icon-warn></i>
							</div>
						</div>
						<div class=weui-cell>
							<div class=weui-cell__hd>
								<label class=weui-label>身份证号码</label>
							</div>
							<div class=weui-cell__bd>
								<input class=weui-input type=text required pattern=REG_IDNUM
									maxlength=18 placeholder=输入你的身份证号码 emptytips=请输入身份证号码
									notmatchtips=请输入正确的身份证号码>
							</div>
							<div class=weui-cell__ft>
								<i class=weui-icon-warn></i>
							</div>
						</div>
						<div class="weui-cell weui-cell weui-cell_vcode">
							<div class=weui-cell__hd>
								<label class=weui-label>验证码</label>
							</div>
							<div class=weui-cell__bd>
								<input class=weui-input maxlength=4 type=text required
									pattern=REG_VCODE placeholder=点击验证码更换 tips=请输入验证码>
							</div>
							<div class=weui-cell__ft>
								<i class=weui-icon-warn></i> <img class=weui-vcode-img
									src=http://tencent.github.io/weui/images/vcode.jpg>
							</div>
						</div>
					</div>
					<div class=weui-btn-area>
						<a id=formSubmitBtn href=javascript:
							class="weui-btn weui-btn_primary">提交</a>
					</div>
				</div>
			</div>
			<div class=weui-tab__content>
				<p class=weui-cells__title>图片自动上传</p>
				<div class="weui-cells weui-cells_form" id=uploader>
					<div class=weui-cell>
						<div class=weui-cell__bd>
							<div class=weui-uploader>
								<div class=weui-uploader__hd>
									<p class=weui-uploader__title>图片上传</p>
									<div class=weui-uploader__info>
										<span id=uploadCount>0</span>/5
									</div>
								</div>
								<div class=weui-uploader__bd>
									<ul class=weui-uploader__files id=uploaderFiles></ul>
									<div class=weui-uploader__input-box>
										<input id=uploaderInput class=weui-uploader__input type=file
											  multiple="" />
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<p class=weui-cells__title>图片手动上传</p>
				<div class="weui-cells weui-cells_form" id=uploaderCustom>
					<div class=weui-cell>
						<div class=weui-cell__bd>
							<div class=weui-uploader>
								<div class=weui-uploader__hd>
									<p class=weui-uploader__title>图片上传</p>
								</div>
								<div class=weui-uploader__bd>
									<ul class=weui-uploader__files id=uploaderCustomFiles></ul>
									<div class=weui-uploader__input-box>
										<input id=uploaderCustomInput class=weui-uploader__input
											type="file" accept="image/*"  multiple="">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class=weui-btn-area>
					<a id=uploaderCustomBtn href=javascript:
						class="weui-btn weui-btn_primary">上传</a>
				</div>
			</div>
			<div class=weui-tab__content>
				<div class=item>
					<h4 class=item__title>搜索框</h4>
					<div class=weui-search-bar id=searchBar>
						<form class=weui-search-bar__form>
							<div class=weui-search-bar__box>
								<i class=weui-icon-search></i> <input type=search
									class=weui-search-bar__input placeholder=搜索 required="">
								<a href=javascript: class=weui-icon-clear></a>
							</div>
							<label class=weui-search-bar__label> <i
								class=weui-icon-search></i> <span>搜索</span>
							</label>
						</form>
						<a href=javascript: class=weui-search-bar__cancel-btn>取消</a>
					</div>
				</div>
				<div class=item>
					<h4 class=item__title>滑块</h4>
					<div class=item__ctn>
						<div class=weui-slider-box>
							<div id=slider class=weui-slider>
								<div class=weui-slider__inner>
									<div class=weui-slider__track></div>
									<div class=weui-slider__handler></div>
								</div>
							</div>
							<div id=sliderValue class=weui-slider-box__value></div>
						</div>
					</div>
				</div>
				<div class=item>
					<h4 class=item__title>滑块(step=10)</h4>
					<div class=item__ctn>
						<div class=weui-slider-box>
							<div id=sliderStep class=weui-slider>
								<div class=weui-slider__inner>
									<div class=weui-slider__track></div>
									<div class=weui-slider__handler></div>
								</div>
							</div>
							<div id=sliderStepValue class=weui-slider-box__value></div>
						</div>
					</div>
				</div>
				<div class=item>
					<h4 class=item__title>滑块(分3步)</h4>
					<div class=item__ctn>
						<div class=weui-slider-box>
							<div id=sliderBlock class=weui-slider>
								<div class=weui-slider__inner>
									<div class=weui-slider__track></div>
									<div class=weui-slider__handler></div>
								</div>
							</div>
							<div id=sliderBlockValue class=weui-slider-box__value></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<!-- 	<script src="https://google.github.io/traceur-compiler/bin/traceur.js"></script> -->
<!-- 	<script src="https://google.github.io/traceur-compiler/bin/BrowserSystem.js"></script> -->
<!-- 	<script src="https://google.github.io/traceur-compiler/src/bootstrap.js"></script> -->
	
 	
 	<script type="text/javascript" src="../js/example.js"></script> 
 	
<!--  	<script type="text/javascript" src="../js/demo2.js"></script>  -->
<!-- 	<script type="text/javascript" src="../js2/weui.js"></script> -->
<!-- 	<script type="text/javascript" src="../js2/dialog/dialog.js"></script> -->
<!-- 	<script type="text/javascript" src="../js2/util/util.js"></script> -->
	<!-- <script type="text/javascript">
		function alert(content = '', yes = $.noop, options) {
		    if (typeof yes === 'object') {
		        options = yes;
		        yes = $.noop;
		    }
		
		    options = $.extend({
		        content: content,
		        buttons: [{
		            label: '确定',
		            type: 'primary',
		            onClick: yes
		        }]
		    }, options);
		
		    return dialog(options);
		}
	</script> -->
</body>
<!-- body 最后 -->
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="../js/wxsq.js"></script> 
<!-- 如果使用了某些拓展插件还需要额外的JS -->
<!-- 	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script> -->
<!-- 	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script> -->
	
</html>
