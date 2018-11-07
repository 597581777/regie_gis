<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<div>
	<label for="special">触发时间:</label> 
	<select id="special" name="special" class="select" onchange="updateformdisplay()"></select>
	 &nbsp; <span id="special_selected_hourly" style="display: none">每个小时0分</span> <span
		id="special_selected_daily" style="display: none">每天0点</span> <span
		id="special_selected_weekly" style="display: none">每周一0点</span> <span
		id="special_selected_monthly" style="display: none">每月1日0点</span>
</div>
<div id="schedule_area" style="display: none">
	<div>
		<label for="when_minutes">分</label>
		<select id="when_minutes" name="when_minutes" class="select"  onchange="changeField('minutes')"></select>
		<div id="minutes_area" style="display: none">
			<input id="when_minutes_specify" name="when_minutes_specify" type="text" class="text"  onchange="updateCron()"/>
		</div>
	</div>
	<div>
		<label for="when_hours">时</label>
		<select id="when_hours" name="when_hours" class="select"  onchange="changeField('hours')"></select>
		<div id="hours_area" style="display: none">
			<input id="when_hours_specify" name="when_hours_specify" type="text" class="text"  onchange="updateCron()"/>
		</div>
	</div>
	<div>
		<label for="when_dayofmonth">日</label>
		<select id="when_dayofmonth" name="when_dayofmonth" class="select"  onchange="changeField('dayofmonth')"></select>
		<div id="dayofmonth_area" style="display: none">
			<input id="when_dayofmonth_specify" name="when_dayofmonth_specify" type="text" class="text"  onchange="updateCron()"/>
		</div>
	</div>
	<div>
		<label for="when_month">月</label>
		<select id="when_month" name="when_month" class="select"  onchange="changeField('month')"></select>
		<div id="month_area" style="display: none">
			<input id="when_month_specify" name="when_month_specify" type="text" class="text"  onchange="updateCron()"/>
		</div>
	</div>
	<div>
		<label for="when_dayofweek">周</label>
		<select id="when_dayofweek" name="when_dayofweek" class="select"  onchange="changeField('dayofweek')"></select>
		<div id="dayofweek_area" style="display: none">
			<input id="when_dayofweek_specify" name="when_dayofweek_specify" type="text" class="text"  onchange="updateCron()"/>
		</div>
	</div>
</div>
<div>
	<input id="cron_result" name="cron_result" type="text" class="text" />
</div>
