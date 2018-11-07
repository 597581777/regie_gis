<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/pages/inc/taglibs.jsp"%>
	<div class="ui-table ui-widget ui-corner-all ui-margin">
		<table class="table forview" style="width: 100%">
			<tr>
				<td class="inputLabelTd">任务名称</td>
				<td class="inputTd" colspan="3"><c:out value="${job.name}"/></td>
			</tr>
			<tr>
				<td class="inputLabelTd">beanId</td>
				<td class="inputTd"><c:out value="${job.data['bizjob.bean.id']}"/></td>
				<td class="inputLabelTd">计划任务</td>
				<td class="inputTd"><c:out value="${job.cron}"/></td>
			</tr>
			<tr>
				<td class="inputLabelTd">起始时间</td>
				<td class="inputTd"><c:out value="${job.startTime}"/></td>
				<td class="inputLabelTd">结束时间</td>
				<td class="inputTd"><c:out value="${job.endTime}"/></td>
			</tr>
			<tr>
				<td class="inputLabelTd">自定义属性</td>
				<td class="inputTd"><pre><c:out value="${job.data['bizjob.custom.props']}"/></pre></td>
				<td class="inputLabelTd">任务描述</td>
				<td class="inputTd"><pre><c:out value="${job.data['bizjob.desc']}"/></pre></td>
			</tr>
		</table>
	</div>
