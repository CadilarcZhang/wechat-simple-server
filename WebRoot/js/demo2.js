var _weui;
var _weui2;
/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};

/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {

/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;

/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};

/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);

/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;

/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}


/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;

/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;

/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";

/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
([function(module, exports, __webpack_require__) {

	'use strict';

//	var _fastclick = __webpack_require__(1);

//	var _fastclick2 = _interopRequireDefault(_fastclick);

	_weui = __webpack_require__(2);

	_weui2 = _interopRequireDefault(_weui);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	// import 'weui';
//	_fastclick2.default.attach(document.body);
},
/* 2 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

//	var _dialog = __webpack_require__(3);
//
//	var _dialog2 = _interopRequireDefault(_dialog);

	var _alert = __webpack_require__(9);

	var _alert2 = _interopRequireDefault(_alert);

//	var _confirm = __webpack_require__(10);
//
//	var _confirm2 = _interopRequireDefault(_confirm);
//
//	var _toast = __webpack_require__(11);
//
//	var _toast2 = _interopRequireDefault(_toast);
//
//	var _loading = __webpack_require__(13);
//
//	var _loading2 = _interopRequireDefault(_loading);
//
//	var _actionSheet = __webpack_require__(15);
//
//	var _actionSheet2 = _interopRequireDefault(_actionSheet);
//
//	var _topTips = __webpack_require__(17);
//
//	var _topTips2 = _interopRequireDefault(_topTips);
//
//	var _searchBar = __webpack_require__(19);
//
//	var _searchBar2 = _interopRequireDefault(_searchBar);
//
//	var _tab = __webpack_require__(20);
//
//	var _tab2 = _interopRequireDefault(_tab);
//
//	var _form = __webpack_require__(21);
//
//	var _form2 = _interopRequireDefault(_form);
//
//	var _uploader = __webpack_require__(22);
//
//	var _uploader2 = _interopRequireDefault(_uploader);
//
//	var _picker = __webpack_require__(26);
//
//	var _gallery = __webpack_require__(32);
//
//	var _gallery2 = _interopRequireDefault(_gallery);
//
//	var _slider = __webpack_require__(34);
//
//	var _slider2 = _interopRequireDefault(_slider);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	/*
	* Tencent is pleased to support the open source community by making WeUI.js available.
	* 
	* Copyright (C) 2017 THL A29 Limited, a Tencent company. All rights reserved.
	* 
	* Licensed under the MIT License (the "License"); you may not use this file except in compliance
	* with the License. You may obtain a copy of the License at
	* 
	*       http://opensource.org/licenses/MIT
	* 
	* Unless required by applicable law or agreed to in writing, software distributed under the License is
	* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
	* either express or implied. See the License for the specific language governing permissions and
	* limitations under the License.
	*/

	exports.default = {
	    dialog: _dialog2.default,
	    alert: _alert2.default,
	    confirm: _confirm2.default,
	    toast: _toast2.default,
	    loading: _loading2.default,
	    actionSheet: _actionSheet2.default,
	    topTips: _topTips2.default,
	    searchBar: _searchBar2.default,
	    tab: _tab2.default,
	    form: _form2.default,
	    uploader: _uploader2.default,
	    picker: _picker.picker,
	    datePicker: _picker.datePicker,
	    gallery: _gallery2.default,
	    slider: _slider2.default
	};
	module.exports = exports['default'];

/***/ },
/* 3 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});
//
//	var _util = __webpack_require__(4);
//
//	var _util2 = _interopRequireDefault(_util);
//
//	var _dialog = __webpack_require__(8);
//
//	var _dialog2 = _interopRequireDefault(_dialog);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	/*
	* Tencent is pleased to support the open source community by making WeUI.js available.
	* 
	* Copyright (C) 2017 THL A29 Limited, a Tencent company. All rights reserved.
	* 
	* Licensed under the MIT License (the "License"); you may not use this file except in compliance
	* with the License. You may obtain a copy of the License at
	* 
	*       http://opensource.org/licenses/MIT
	* 
	* Unless required by applicable law or agreed to in writing, software distributed under the License is
	* distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
	* either express or implied. See the License for the specific language governing permissions and
	* limitations under the License.
	*/

	var _sington = void 0;

	/**
	 * dialog，弹窗，alert和confirm的父类
	 *
	 * @param {object=} options 配置项
	 * @param {string=} options.title 弹窗的标题
	 * @param {string=} options.content 弹窗的内容
	 * @param {string=} options.className 弹窗的自定义类名
	 * @param {array=} options.buttons 按钮配置项
	 *
	 * @param {string} [options.buttons[].label=确定] 按钮的文字
	 * @param {string} [options.buttons[].type=primary] 按钮的类型 [primary, default]
	 * @param {function} [options.buttons[].onClick=$.noop] 按钮的回调
	 *
	 * @example
	 * weui.dialog({
	 *     title: 'dialog标题',
	 *     content: 'dialog内容',
	 *     className: 'custom-classname',
	 *     buttons: [{
	 *         label: '取消',
	 *         type: 'default',
	 *         onClick: function () { alert('取消') }
	 *     }, {
	 *         label: '确定',
	 *         type: 'primary',
	 *         onClick: function () { alert('确定') }
	 *     }]
	 * });
	 * 
	 * // 主动关闭
	 * var $dialog = weui.dialog({...});
	 * $dialog.hide(function(){
	 *      console.log('`dialog` has been hidden');
	 * });
	 */
	function dialog() {
	    var options = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : {};

	    if (_sington) return _sington;

	    var isAndroid = _util2.default.os.android;
	    options = _util2.default.extend({
	        title: null,
	        content: '',
	        className: '',
	        buttons: [{
	            label: '确定',
	            type: 'primary',
	            onClick: _util2.default.noop
	        }],
	        isAndroid: isAndroid
	    }, options);

	    var $dialogWrap = (0, _util2.default)(_util2.default.render(_dialog2.default, options));
	    var $dialog = $dialogWrap.find('.weui-dialog');
	    var $mask = $dialogWrap.find('.weui-mask');

	    function _hide(callback) {
	        _hide = _util2.default.noop; // 防止二次调用导致报错

	        $mask.addClass('weui-animate-fade-out');
	        $dialog.addClass('weui-animate-fade-out').on('animationend webkitAnimationEnd', function () {
	            $dialogWrap.remove();
	            _sington = false;
	            callback && callback();
	        });
	    }
	    function hide(callback) {
	        _hide(callback);
	    }

	    (0, _util2.default)('body').append($dialogWrap);
	    // 不能直接把.weui-animate-fade-in加到$dialog，会导致mask的z-index有问题
	    $mask.addClass('weui-animate-fade-in');
	    $dialog.addClass('weui-animate-fade-in');

	    $dialogWrap.on('click', '.weui-dialog__btn', function (evt) {
	        var index = (0, _util2.default)(this).index();
	        if (options.buttons[index].onClick) {
	            if (options.buttons[index].onClick.call(this, evt) !== false) hide();
	        } else {
	            hide();
	        }
	    });

	    _sington = $dialogWrap[0];
	    _sington.hide = hide;
	    return _sington;
	}
	exports.default = dialog;
	module.exports = exports['default'];

/***/ },
/* 9 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';

	Object.defineProperty(exports, "__esModule", {
	    value: true
	});

	var _typeof = typeof Symbol === "function" && typeof Symbol.iterator === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof Symbol === "function" && obj.constructor === Symbol && obj !== Symbol.prototype ? "symbol" : typeof obj; }; 

//	var _util = __webpack_require__(4);
//
//	var _util2 = _interopRequireDefault(_util);
//
//	var _dialog = __webpack_require__(3);
//
//	var _dialog2 = _interopRequireDefault(_dialog);

	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

	/**
	 * alert 警告弹框，功能类似于浏览器自带的 alert 弹框，用于提醒、警告用户简单扼要的信息，只有一个“确认”按钮，点击“确认”按钮后关闭弹框。
	 * @param {string} content 弹窗内容
	 * @param {function=} yes 点击确定按钮的回调
	 * @param {object=} options 配置项
	 * @param {string=} options.title 弹窗的标题
	 * @param {string=} options.className 自定义类名
	 * @param {array=} options.buttons 按钮配置项，详情参考dialog
	 *
	 * @example
	 * weui.alert('普通的alert');
	 * weui.alert('带回调的alert', function(){ console.log('ok') });
	 * var alertDom = weui.alert('手动关闭的alert', function(){
	 *     return false; // 不关闭弹窗，可用alertDom.hide()来手动关闭
	 * });
	 * weui.alert('自定义标题的alert', { title: '自定义标题' });
	 * weui.alert('带回调的自定义标题的alert', function(){
	 *    console.log('ok')
	 * }, {
	 *    title: '自定义标题'
	 * });
	 * weui.alert('自定义按钮的alert', {
	 *     title: '自定义按钮的alert',
	 *     buttons: [{
	 *         label: 'OK',
	 *         type: 'primary',
	 *         onClick: function(){ console.log('ok') }
	 *     }]
	 * });
	 *
	 * // 多次使用
	 * var alert = weui.alert('hello');
	 * alert.hide(function(){
	 *     weui.alert('world');
	 * });
	 */
	function alert() {
	    var content = arguments.length > 0 && arguments[0] !== undefined ? arguments[0] : '';
	    var yes = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : _util2.default.noop;
	    var options = arguments[2];

	    if ((typeof yes === 'undefined' ? 'undefined' : _typeof(yes)) === 'object') {
	        options = yes;
	        yes = _util2.default.noop;
	    }

	    options = _util2.default.extend({
	        content: content,
	        buttons: [{
	            label: '确定',
	            type: 'primary',
	            onClick: yes
	        }]
	    }, options);

	    return (0, _dialog2.default)(options);
	}
	exports.default = alert;
	module.exports = exports['default'];

/***/ },])