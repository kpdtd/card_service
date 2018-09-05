//common.js为所有页面通用的一些方法

//判断是否为微信环境
var isWX = (function() {
	        	var ua = window.navigator.userAgent.toLowerCase();
		        if(ua.match(/MicroMessenger/i) == 'micromessenger'){
		        	return true;
		        }else{
		        	return false;
		        }
	    	})();