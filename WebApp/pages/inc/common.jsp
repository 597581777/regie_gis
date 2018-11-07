<div id="msgDiv" style="display:none;z-index:1;width:300px;position:absolute;left:5;top:5;" class="msgDiv"> 
  <div class="msgtitle">
    <div id="titleTxt" class="title"><fmt:message key="msg.title"/></div>
	<img class="icon" onClick="hide()" src="<c:url value='/styles/skin/${webConfig["csstheme"]}/images/wclose.gif'/>"/>	
  </div>
  <div id="content" class="content" style="padding:4px; display:block">
  </div>
</div>
<biz:display level="info,error,success,action" />
<%-- 
	如果要保留信息，remove="false"。
	默认从request、session和actionContext中查询数据，可以使用scope指定具体的域：
	scope="request" 表示从request中查询数据，注意scope不支持request,session的形式，只能不设置或者单独设置。
--%>

<script type="text/javascript">
<!--
function hide() {
	document.getElementById('msgDiv').style.display = "none";
}
setTimeout(hide,5000);
//-->
</script>


<%-- ********通用提示对话框******** --%>
	
<script type="text/javascript">
	
function showMessage(msg,title,timeout,callback){
	new biz.alert({type:"alert",message:msg,title:I18N.promp,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

//show common message
function showInfo(msg,timeout,callback){
	new biz.alert({type:"alert",message:msg,title:I18N.promp,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

//show error message
function showError(msg,timeout,callback){
	new biz.alert({type:"alert",message:msg,title:I18N.error,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

// show warn message
function showWarn(msg,timeout,callback){
	new biz.alert({type:"alert",message:msg,title:I18N.warn,callback:callback}) ;
	new biz.alert({type:"hide",times:timeout}) ;
}

</script>