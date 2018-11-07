<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<%@page import="com.chinasofti.ro.bizframework.core.About" %>
<%@page import="com.chinasofti.ro.bizframework.core.mvc.Router" %>
<%@page import="java.lang.System" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><fmt:message key="welcome.message"/></title>
<%@ include file="/pages/inc/header.jsp" %>
</head>
<body>
	<div id="container" class="main" style="margin:20px auto;width:90%;">
	    <div class="barTitle">
			<div class="content"><a href="javascript:;" onfocus="this.blur();"></a> <span>欢迎使用BizFoudation</span></div>
		</div>
	    <hr class="barTitleHr"></hr>
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table forview">
				<tr>
					<td class="inputLabelTd">产品名称：</td>
					<td class="inputTd"><%=About.NAME %></td>
	
					<td class="inputLabelTd">产品版本：</td>
					<td class="inputTd"><%=About.VERSION %></td>
	
				</tr>
				<tr>
					<td class="inputLabelTd">发布日期：</td>
					<td class="inputTd"><%=About.PUBLISH_TIME %></td>
	
					<td class="inputLabelTd">联系我们：</td>
					<td class="inputTd"><a href="mailto:resourceone@chinasofti.com">resourceone@chinasofti.com</a></td>

				</tr>
				<tr>
					<td class="inputLabelTd">公司网址：</td>
					<td class="inputTd"><a href="<%=About.CONTACT_US %>"><%=About.CONTACT_US %></a></td>
	
				</tr>
			</table>
		</div>
	    <div class="barTitle">
			<div class="content"><a href="javascript:;" onfocus="this.blur();"></a> <span>运行环境</span></div>
		</div>
	    <hr class="barTitleHr"></hr>
		<div class="ui-table ui-widget ui-corner-all ui-border">
			<table class="table forview">
				<tr>
					<td class="inputLabelTd">操作系统：</td>
					<td class="inputTd"><%=System.getProperty("os.name") %></td>
	
					<td class="inputLabelTd">Java版本：</td>
					<td class="inputTd"><%=System.getProperty("java.runtime.version") %></td>
	
				</tr>
				<tr>
					<td class="inputLabelTd">JVM字符编码：</td>
					<td class="inputTd"><%=System.getProperty("file.encoding") %></td>
	
					<td class="inputLabelTd">上下文根：</td>
					<td class="inputTd"><%=Router.prefix %></td>
					
				</tr>
			</table>
		</div>
	</div>
</body>
</html>