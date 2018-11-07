<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="author" content="CSI"/>

<link rel="stylesheet" type="text/css" href="<c:url value='/styles/reset.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/styles/layout.${webConfig["csssuffix"]}'/>" media="screen, projection"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/theme.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/components/jqgrid/jquery.jqgrid.<c:out value='${webConfig["csssuffix"]}'/>"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/styles/skin/<%=request.getAttribute("csstheme")%>/components/tree/zTreeStyle.<c:out value='${webConfig["csssuffix"]}'/>"/>
<script type="text/javascript" src="<c:url value='/js/lib/jquery-all.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/WdatePicker.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/widgets.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/i18n/i18n_${webConfig["language"]}.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/tree.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/grid.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/validate.${webConfig["jssuffix"]}'/>"></script>
<script type="text/javascript" src="<c:url value='/js/lib/biz.${webConfig["jssuffix"]}'/>"></script>

<script type="text/javascript">
	var contextPath = "<%=request.getContextPath() %>";
</script>

<% /**注:此文件禁止修改,会影响平台升级。如果想通过include方式为所有页面添加js库或css库请参照此文件自行新建jsp   */ %>