<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/pages/inc/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>计划任务</title>
<%@ include file="/pages/inc/header.jsp"%>
</head>

<body>
	<%@ include file="/pages/inc/common.jsp"%>
	<div class="main">
		<div class="barTitle">
		<div class="content">
			<a href="#"> </a>
			<span>计划任务</span>
		</div>
		</div>
		<div class="ui-table ui-widget ui-corner-all ui-margin">
			<div class="nav">
				<button id="addBtn" onclick="add();">增加</button>
			</div>
			<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all">
			<table class="table forview" width="100%">
				<tr class="ui-jqgrid-labels">
					<td class="ui-state-default ui-th-column ui-th-ltr" colspan="7" style="width: 70%">任务配置</td>
					<td class="ui-state-default ui-th-column ui-th-ltr" colspan="3" style="width: 30%">上次执行情况</td>
				</tr>
				<tr class="ui-jqgrid-labels">
					<td class="ui-state-default ui-th-column ui-th-ltr">操作</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">任务名</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">调度状态</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">bean</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">起始时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">停止时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">调度计划</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">触发时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">执行时间</td>
					<td class="ui-state-default ui-th-column ui-th-ltr">执行状态</td>
				</tr>
				<c:forEach items="${jobs}" var="job">
					<tr class="ui-widget-content jqgrow ui-row-ltr">
						<td class="inputTd" style="white-space: normal;"><a
							href="<c:url value='/bizconsole/schedule/remove' />?name=<c:out value='${job.detail.name}' />">删除</a>
							 | 
							<c:choose>
								<c:when test="${job.triggerStatus==1}"><a
							href="<c:url value='/bizconsole/schedule/resume' />?name=<c:out value='${job.detail.name}' />">恢复</a></c:when>
								<c:otherwise><a
							href="<c:url value='/bizconsole/schedule/pause' />?name=<c:out value='${job.detail.name}' />">暂停</a></c:otherwise>
							</c:choose>
						</td>
						<td class="inputTd"  style="white-space: normal;"
							title="<c:out
								value='${job.name}' />">
							<a href="javascript:detail('<c:out value='${job.name}'/>');"><c:out value="${job.name}" /></a>
						</td>
						<td class="inputTd" style="white-space: normal;"
							title="<c:out
								value='${job.triggerStatusLabel}' />">
							<c:out value="${job.triggerStatusLabel}" />
						</td>
						<td class="inputTd"  style="white-space: normal;"
							title="<c:out
								value='${job.data["bizjob.bean.id" ]}' />">
							<c:out value="${job.data['bizjob.bean.id']}" />
						</td>
						<td class="inputTd" style="white-space: normal;" title='<fmt:formatDate value="${job.startTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />'><fmt:formatDate value="${job.startTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="inputTd" style="white-space: normal;" title='<fmt:formatDate value="${job.endTime}"
								pattern="yyyy-MM-dd HH:mm:ss" />'><fmt:formatDate value="${job.endTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="inputTd" style="white-space: normal;"><c:out value="${job.cron}" /></td>
						<td class="inputTd" style="white-space: normal;"><fmt:formatDate
								value="${job.data['bizjob.last.time']}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td class="inputTd" style="white-space: normal;"><c:out
								value="${job.data['bizjob.last.duration']}"
								/>秒</td>
						<td class="inputTd" style="white-space: normal;"><c:choose>
								<c:when test="${job.data['bizjob.last.status']=='ok'}">成功</c:when>
								<c:when test="${job.data['bizjob.last.status']=='exception'}">
									<a href="javascript:;" class="schd-err-link"
										rel="<c:out value='${job.detail.name}' />">出错</a>
								</c:when>
								<c:when test="${job.data['bizjob.last.status']=='timeout'}">超时</c:when>
								<c:otherwise>
									<c:out value="${job.data['bizjob.last.status']}" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
				<biz:page />
			</div>
		</div>
	</div>
	<div id="cronDialog"></div>
	<div id="addDialog"></div>
	<div id="detailDialog"></div>
	<div id="errorDialog"></div>
	<%@ include file="/pages/inc/footer.jsp"%>
</body>
</html>
<script type="text/javascript">
	new biz.button({
		id : "#addBtn",
		icons : {
			primary : 'ui-icon-plus'
		}
	});
	new biz.datepicker({
		id : "#startTime"
	});
	new biz.datepicker({
		id : "#endTime"
	});

	$(".schd-err-link")
			.click(
					function() {
						console.log(this);
						var name = $(this).attr("rel");
						if (name != undefined && name != null) {
							$
									.ajax({
										url : "<c:url value='/bizconsole/schedule/getLastException' />?name="
												+ name,
										cache : false,
										success : function(data, textStatus,
												jqXHR) {
											var addDialog = new biz.dialog({
												id : "#addDialog",
												autoOpen : false,
												title : "追踪信息",
												width : "80%",
												modal : true
											});
											$(addDialog.id).html(data)
											addDialog.open();
										},
										error : errorInfo
									});
						}
					});

	function add() {
		$.ajax({
			url : "<c:url value='/bizconsole/schedule/addDialog' />",
			cache : false,
			success : function(data, textStatus, jqXHR) {
				var addDialog = new biz.dialog({
					id : "#addDialog",
					autoOpen : false,
					title : "添加任务",
					width : "760px",
					modal : true
				});
				$(addDialog.id).html(data)
				addDialog.open();
				new biz.validate({
					id : "#addScheduleForm",
					rules : {
						"name":{
							required:true
						},
						"beanName":{
							required:true
						},
						"cron":{
							required:true
						}
					}
				});
				new biz.button({id:"#submit_button"}).click(function() {/*提交处理*/});
			},
			error : errorInfo
		});
	}

	function detail(name) {
		$.ajax({
			type : "POST",
			url : "<c:url value='/bizconsole/schedule/detail' />",
			data : {
				name : name
			},
			cache : false,
			success : function(data, textStatus, jqXHR) {
				var detailDialog = new biz.dialog({
					id : "#detailDialog",
					autoOpen : false,
					title : "任务信息",
					width : "80%",
					modal : true
				});
				$(detailDialog.id).html(data)
				detailDialog.open();
			},
			error : errorInfo
		});
	}

	function errorInfo(request, textStatus, errorThrown) {
		var errorDialog = new biz.dialog({
			id : "#errorDialog",
			modal : true,
			width : "80%",
			height : 400,
			title : "<fmt:message key='error.title'/>",
			close : function(event, ui) {
				errorDialog.destroy();
			}
		});
		$("#errorDialog").html(request.responseText);
		errorDialog.open();
	}

	var cronDialog;
	var cronDialogOption = {
		id:"#cronDialog",
		autoOpen:false,
		title:"编辑cron",
		width: 360,
		//height: 280,
		modal:true,
		buttons: [
			{
				text: "确定",
				click: function() { 
					$("#cron").val($("#cron_result").val());
					cronDialog.close();
					cronDialog.destroy();
				}
			},
			{
				text: "取消",
				click: function() { 
					cronDialog.close();
					cronDialog.destroy();
				}
			}
		]
	};

	function buildCron(){
		
		$("#cron").blur();
		$.ajax({
			url: "<c:url value='/bizconsole/schedule/cron' />",
			cache:true,
			success: function(data, textStatus, jqXHR){
				cronDialog = new biz.dialog(cronDialogOption);
				$(cronDialog.id).html(data)
				cronDialog.open();
				bindCronEditor();
				$("#cron_result").val($("#cron").val());
				updateCron();
			},
			error:biz.utils.loadError
		});
	}

	var cronEditor={};
	function bindCronEditor(){
		cronEditor["special"]=new biz.select({
			id:"#special",
			data:[{name:"手写",value:"@@"},{name:"每小时",value:"@hourly"},{name:"每天",value:"@daily"},{name:"每周",value:"@weekly"},{name:"每个月",value:"@monthly"},{name:"自定义",value:"specify"}]
		});
		cronEditor["minutes"]=new biz.select({			id:"#when_minutes",			data:[{name:"每分钟",value:"*"},{name:"每5分钟",value:"*/5"},{name:"每10分钟",value:"*/10"},{name:"每15分钟",value:"*/15"},{name:"每20分钟",value:"*/20"},{name:"每半小时",value:"*/30"},{name:"0分",value:"0"},{name:"自定义",value:"specify"}]		});		cronEditor["minutes_specify"]=new biz.comboboxlist({			id:"#when_minutes_specify",			data:[{name:"0",value:"0"},{name:"1",value:"1"},{name:"2",value:"2"},{name:"3",value:"3"},{name:"4",value:"4"},{name:"5",value:"5"},{name:"6",value:"6"},{name:"7",value:"7"},{name:"8",value:"8"},{name:"9",value:"9"},{name:"10",value:"10"},{name:"11",value:"11"},{name:"12",value:"12"},{name:"13",value:"13"},{name:"14",value:"14"},{name:"15",value:"15"},{name:"16",value:"16"},{name:"17",value:"17"},{name:"18",value:"18"},{name:"19",value:"19"},{name:"20",value:"20"},{name:"21",value:"21"},{name:"22",value:"22"},{name:"23",value:"23"},{name:"24",value:"24"},{name:"25",value:"25"},{name:"26",value:"26"},{name:"27",value:"27"},{name:"28",value:"28"},{name:"29",value:"29"},{name:"30",value:"30"},{name:"31",value:"31"},{name:"32",value:"32"},{name:"33",value:"33"},{name:"34",value:"34"},{name:"35",value:"35"},{name:"36",value:"36"},{name:"37",value:"37"},{name:"38",value:"38"},{name:"39",value:"39"},{name:"40",value:"40"},{name:"41",value:"41"},{name:"42",value:"42"},{name:"43",value:"43"},{name:"44",value:"44"},{name:"45",value:"45"},{name:"46",value:"46"},{name:"47",value:"47"},{name:"48",value:"48"},{name:"49",value:"49"},{name:"50",value:"50"},{name:"51",value:"51"},{name:"52",value:"52"},{name:"53",value:"53"},{name:"54",value:"54"},{name:"55",value:"55"},{name:"56",value:"56"},{name:"57",value:"57"},{name:"58",value:"58"},{name:"59",value:"59"}],			columns:10		});
		cronEditor["hours"]=new biz.select({
			id:"#when_hours",
			data:[{name:"每小时",value:"*"},{name:"每2小时",value:"*/2"},{name:"每3小时",value:"*/3"},{name:"每4小时",value:"*/4"},{name:"每6小时",value:"*/6"},{name:"每8小时",value:"*/8"},{name:"每12小时",value:"*/12"},{name:"0点",value:"0"},{name:"自定义",value:"specify"}]
		});
		cronEditor["hours_specify"]=new biz.comboboxlist({
			id:"#when_hours_specify",
			data:[{name:"0",value:"0"},{name:"1",value:"1"},{name:"2",value:"2"},{name:"3",value:"3"},{name:"4",value:"4"},{name:"5",value:"5"},{name:"6",value:"6"},{name:"7",value:"7"},{name:"8",value:"8"},{name:"9",value:"9"},{name:"10",value:"10"},{name:"11",value:"11"},{name:"12",value:"12"},{name:"13",value:"13"},{name:"14",value:"14"},{name:"15",value:"15"},{name:"16",value:"16"},{name:"17",value:"17"},{name:"18",value:"18"},{name:"19",value:"19"},{name:"20",value:"20"},{name:"21",value:"21"},{name:"22",value:"22"},{name:"23",value:"23"}],
			columns:6
		});
		cronEditor["dayofmonth"]=new biz.select({
			id:"#when_dayofmonth",
			data:[{name:"每天",value:"*"},{name:"1日",value:"1"},{name:"自定义",value:"specify"}]
		});
		cronEditor["dayofmonth_specify"]=new biz.comboboxlist({
			id:"#when_dayofmonth_specify",
			data:[{name:"1",value:"1"},{name:"2",value:"2"},{name:"3",value:"3"},{name:"4",value:"4"},{name:"5",value:"5"},{name:"6",value:"6"},{name:"7",value:"7"},{name:"8",value:"8"},{name:"9",value:"9"},{name:"10",value:"10"},{name:"11",value:"11"},{name:"12",value:"12"},{name:"13",value:"13"},{name:"14",value:"14"},{name:"15",value:"15"},{name:"16",value:"16"},{name:"17",value:"17"},{name:"18",value:"18"},{name:"19",value:"19"},{name:"20",value:"20"},{name:"21",value:"21"},{name:"22",value:"22"},{name:"23",value:"23"},{name:"24",value:"24"},{name:"25",value:"25"},{name:"26",value:"26"},{name:"27",value:"27"},{name:"28",value:"28"},{name:"29",value:"29"},{name:"30",value:"30"},{name:"31",value:"31"}],
			columns:7
		});
		cronEditor["month"]=new biz.select({
			id:"#when_month",
			data:[{name:"每月",value:"*"},{name:"自定义",value:"specify"}]
		});
		cronEditor["month_specify"]=new biz.comboboxlist({
			id:"#when_month_specify",
			data:[{name:"1",value:"1"},{name:"2",value:"2"},{name:"3",value:"3"},{name:"4",value:"4"},{name:"5",value:"5"},{name:"6",value:"6"},{name:"7",value:"7"},{name:"8",value:"8"},{name:"9",value:"9"},{name:"10",value:"10"},{name:"11",value:"11"},{name:"12",value:"12"}],
			columns:6
		});
		cronEditor["dayofweek"]=new biz.select({
			id:"#when_dayofweek",
			data:[{name:"整周",value:"*"},{name:"工作日",value:"1-5"},{name:"周末",value:"6-7"},{name:"自定义",value:"specify"}]
		});
		cronEditor["dayofweek_specify"]=new biz.comboboxlist({
			id:"#when_dayofweek_specify",
			data:[{name:"周一",value:"1"},{name:"周二",value:"2"},{name:"周三",value:"3"},{name:"周四",value:"4"},{name:"周五",value:"5"},{name:"周六",value:"6"},{name:"周日",value:"7"}],
			columns:7
		});
	}

	function updateformdisplay() {
		console.log(["updateformdisplay",cronEditor["special"].getValue()]);
		if (cronEditor["special"].getValue() == 'specify') {
			$("#schedule_area").show();
			showSpecialTooltip(cronEditor["special"].getValue().replace("@", ""));
		} else {
			$("#schedule_area").hide();
			showSpecialTooltip(cronEditor["special"].getValue().replace("@", ""));
		}
		updateCron();
	}

	var specialvalues = [ 'hourly', 'daily', 'weekly', 'monthly', 'reboot' ];
	function showSpecialTooltip(special) {
		for ( var i = 0; i < specialvalues.length; i++) {
			var tooltip = specialvalues[i];
			if (tooltip == special) {
				$("#special_selected_" + tooltip).show();
			} else {
				$("#special_selected_" + tooltip).hide();
			}
		}

	}

	function changeField(field) {
		if(field=="dayofweek" && $("#when_dayofweek").val()!="*"){
			cronEditor["dayofmonth"].setValue("*");
			changeField("dayofmonth");
		}
		if(field=="dayofmonth" && $("#when_dayofmonth").val()!="*"){
			cronEditor["dayofweek"].setValue("*");
			changeField("dayofweek");
		}
		if (cronEditor[field].getValue() == 'specify') {
			$("#" + field + '_area').show();
		} else {
			$("#" + field + '_area').hide();
		}
		updateCron();
	}
	
	function appendCron(value){
		if(value==null){
			value="*";
		}
		if(Object.prototype.toString.call(value)=="[object Array]"){
			var i;
			var tmp=""
			if(value.length>0){
				tmp=value[0];
				for(i=1;i<value.length;i++){
					tmp+=","+value[i];
				}
			}
			value=tmp;
		}
		var val=$("#cron_result").val();
		$("#cron_result").val(val+" "+value);
	}
	
	function appendCronField(field){
		var value=cronEditor[field].getValue();
		if(field=="dayofweek" && value=="*"){
			value="?";
		}
		if(field=="dayofmonth" && value=="*" && cronEditor["dayofweek"].getValue()!="*"){
			value="?";
		}
		if(value=="specify"){
			appendCron(cronEditor[field+"_specify"].getValue())
		}else{
			appendCron(value);
		}
	}
	
	function updateCron(){
		var special=cronEditor["special"].getValue();
		if(special=="@@"){
			$("#cron_result").attr("readonly",false);
			return;
		}else{
			$("#cron_result").attr("readonly",true);
		}
		$("#cron_result").val("");
		switch(special){
		case "@@":
			return;
		case "@hourly":
			appendCron("0 0 */1 * * ?");
			return;
		case "@daily":
			appendCron("0 0 * */1 * ?");
			return;
		case "@weekly":
			appendCron("0 0 ? * * 1");
			return;
		case "@monthly":
			appendCron("0 0 0 1 */1 ?");
			return;
		case "specify":
			break;
		default:
			$("result").val("Illegal");
			return;
		}
		
		appendCron("0");
		
		//minute
		appendCronField("minutes");
		
		//hour
		appendCronField("hours");
		
		//day
		appendCronField("dayofmonth");
		
		//month
		appendCronField("month");
		
		//weekday
		appendCronField("dayofweek");
	}</script>

