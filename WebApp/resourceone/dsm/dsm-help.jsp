<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>没有可监控的数据源</title>
	<%@ include file="/pages/inc/header.jsp" %>
</head>

<body>
<%@ include file="/pages/inc/common.jsp" %>
<div class="main">
	<div class="barTitle">
		<div class="content">
			<a href="javascript:;"> </a>没有可监控的数据源
		</div>
	</div>
	<div class="ui-table ui-widget ui-corner-all ui-margin"><pre>
您没有配置支持监控的数据源，请按照下面的方式进行配置：
	1. 首先，打开applicationContext-custom.xml将正在使用的dataSource改名，如改名成dataSourceReal：
		&lt;jee:jndi-lookup id="dataSource<span style="color: red">Real</span>" jndi-name="${db.jndi.name}"/&gt;
	2. 追加一个监控数据源封装原来的数据源，如：
		&lt;bean id="dataSource" class="com.chinasofti.ro.bizframework.modules.dsmonitor.BizDataSource"
			autowire="byName"&gt;
			&lt;property name="dataSource"&gt;
				&lt;ref bean="<span style="color: red">dataSourceReal</span>" /&gt;
			&lt;/property&gt;
		&lt;/bean&gt;
	</pre></div>
</div>
<%@ include file="/pages/inc/footer.jsp" %>
</body>
</html>
<script type="text/javascript">

</script>