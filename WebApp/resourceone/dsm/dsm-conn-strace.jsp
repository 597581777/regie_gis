<%@page import="com.chinasofti.ro.bizframework.modules.dsmonitor.ActiveConnection"%>
<%@page import="com.chinasofti.ro.bizframework.modules.dsmonitor.Monitor"%>
<%@page import="com.chinasofti.ro.bizframework.modules.dsmonitor.ds.DataSourceMonitor"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%><%@ taglib uri="c" prefix="c"%><%@ taglib uri="fmt" prefix="fmt"%>
<ul><%
	StackTraceElement[] stes = (StackTraceElement[])request.getAttribute("strace");
	for (int i = 0, max = stes.length; i < max; i++) {
		StackTraceElement ste = stes[i];
		String className = ste.getClassName();
		
		if (className.endsWith("Controller") //前面可以修改成您项目的基础包名
				|| className.endsWith("Service")) {%>
			<li class="dsm-st-hint"><%
		}else{%>
			<li><%
		}
		%><%=ste%></li><%
	}
%>
</ul>