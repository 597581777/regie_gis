<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/pages/inc/taglibs.jsp"%>
<form id="addScheduleForm" action="<c:url value='/bizconsole/schedule/add' />" method="post">
	<div class="ui-table ui-widget ui-corner-all ui-margin">
		<table class="table forview" width="100%">
			<tr height="0" style="font-size:1px">
				<td height="0" style="height:0px" width="100"></td>
				<td height="0" style="height:0px"></td>
				<td height="0" style="height:0px" width="100"></td>
				<td height="0" style="height:0px"></td>
			</tr>
			<tr>
				<td class="inputLabelTd">任务名称</td>
				<td class="inputTd" colspan="3"><input id="name" name="name"
					type="text" class="text" value="" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd">beanId</td>
				<td class="inputTd"><input id="beanName" name="beanName"
					type="text" class="text" value="" /></td>
				<td class="inputLabelTd">计划任务</td>
				<td class="inputTd"><input id="cron" name="cron" type="text"
					class="text" value="* * * * * ?" onclick="buildCron();"/></td>
			</tr>
			<tr>
				<td class="inputLabelTd">起始时间</td>
				<td class="inputTd"><input id="startTime" name="startTime"
					type="text" class="Wdate" /></td>
				<td class="inputLabelTd">结束时间</td>
				<td class="inputTd"><input id="endTime" name="endTime"
					type="text" class="Wdate" /></td>
			</tr>
			<tr>
				<td class="inputLabelTd">自定义属性<a href="javascript:;" style="color: red; text-decoration: none;" title="（ 属性名=值 的形式，一行一个）">*</a></td>
				<td class="inputTd"><textarea id="customProps"
						name="customProps" class="textarea" rows="5"></textarea></td>
				<td class="inputLabelTd">任务描述</td>
				<td class="inputTd"><textarea id="desc"
						name="desc" class="textarea" rows="5"></textarea></td>
			</tr>
			<tr>
				<td colspan="4" valign="bottom" align="center"><input type="submit" value="提交"
					class="button" id="submit_button" /></td>
			</tr>
		</table>
	</div>
</form>