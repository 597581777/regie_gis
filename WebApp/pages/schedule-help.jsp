<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>没有配置计划任务</title>
	<%@ include file="/pages/inc/header.jsp" %>
</head>

<body>
<%@ include file="/pages/inc/common.jsp" %>
	<div class="main">
		<div class="barTitle">
			<div class="content">
				<a href="#"> </a>
				<span>没有配置计划任务</span>
			</div>
		</div>
		<div class="ui-table ui-widget ui-corner-all ui-margin">
			<pre>
您没有配置计划任务，请打开applicationContext-custom.xml并打开下面内容：
	&lt;bean name="quartzTableCreator"
		class="com.chinasofti.ro.bizframework.modules.schedule.QuartzTableCreator"
		autowire="byName" >
		&lt;!-- quartz表名的前缀，要和quartz.properties中的匹配   -->
		&lt;constructor-arg index="0" type="java.lang.String" value="qrtz" />
		&lt;!-- 是否自动创建表   -->
		&lt;constructor-arg index="1" type="boolean" value="true" />
	&lt;/bean>
	&lt;bean id="quartzScheduler"
		class="org.springframework.scheduling.quartz.SchedulerFactoryBean"
		depends-on="quartzTableCreator">
		&lt;property name="jobFactory">
			&lt;bean class="org.springframework.scheduling.quartz.SpringBeanJobFactory" />
		&lt;/property>
		&lt;property name="configLocation" value="classpath:quartz.properties" />
		&lt;property name="waitForJobsToCompleteOnShutdown" value="true" />
		&lt;property name="dataSource" ref="dataSource" />
	&lt;/bean>
	&lt;bean name="scheduleManagementService"
		class="com.chinasofti.ro.bizframework.modules.schedule.ScheduleManagementServiceImpl"
		autowire="byName" />
			</pre>
		</div>
	</div>
<%@ include file="/pages/inc/footer.jsp" %>
</body>
</html>