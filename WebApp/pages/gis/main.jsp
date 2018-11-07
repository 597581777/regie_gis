<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	var contextPath = "<%=request.getContextPath() %>";
	var remote = window.location.protocol+"//"+window.location.host;
	window.location.href=remote+contextPath+"/gis/index.html";
</script>
