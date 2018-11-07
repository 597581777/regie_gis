<%@page import="com.chinasofti.ro.bizframework.modules.dsmonitor.Monitor"%><%
Monitor monitor=(Monitor)request.getAttribute("monitor");
%><%@page import="com.chinasofti.ro.bizframework.modules.dsmonitor.ds.DataSourceMonitor"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="c" prefix="c" %>
<%@ taglib uri="fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>DataSource Monitor</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta name="author" content="CSI"/>

<style type="text/css">
.dsm-st-hint{
	color: red;
	font-weight: bold;
}

.dsm-sql{
	table-layout: fixed;
}

.dsm-sql-begin th{
	text-align: center;
}

.dsm-sql-cell-op{
	width: 3em;
}

.dsm-sql-content a{
	overflow:hidden;
	white-space: nowrap;
	text-decoration: none;
	color: black;
	cursor: pointer;
}

.sqlDialog{
	word-break: break-all;
	word-wrap: break-word;
}
</style>
<%@ include file="/pages/inc/header.jsp" %>
</head>

<body>
	<%@ include file="/pages/inc/common.jsp"%>
<div class="main">
	<div class="barTitle">
		<div class="content">
			<div style="float:left;">
				<a href="#"></a>数据源监控
			</div>
			<div style="float:right;padding-left:10px;">
				<span class="dsm-time"></span>
			</div>
		</div>
	</div>
	<div class="ui-table ui-widget ui-corner-all ui-margin">
		<div class="nav" style="height:32px; text-align: left;">
			<c:choose>
				<c:when test="${monitor.enabled}">
					<button id="dsm_btn_disable">停用</button>
					<button id="dsm_btn_refresh">刷新</button>
					<span>
				数据源：<select id="dsm-datasource-name" onchange="fetchData();"><%
				{
					Iterator i=monitor.getDataSources().iterator();
					boolean first=true;
					while(i.hasNext()){
						Entry entry=(Entry)i.next();
						String uuid=(String)entry.getKey();
						DataSourceMonitor dsm=(DataSourceMonitor)entry.getValue();
						%><option value="<%=dsm.getName()%>" <%if(first){%>selected<%}%>><%=dsm.getName()%></option> <%
						first=false;
					}
				}%></select></span>
				<span style="width: 20px;"></span>
				</c:when>
				<c:otherwise>
					监控已关闭<button id="dsm_btn_enable">启用</button>
				</c:otherwise>
			</c:choose>
		</div>
		<c:choose>
			<c:when test="${monitor.enabled}">
				<script type="text/javascript">
				//<!--
					new biz.button({
						id:"#dsm_btn_refresh",
						icons: {primary: "ui-icon-refresh" }
					}).click(function(){
						fetchData();
					});
					new biz.button({
						id:"#dsm_btn_disable",
						icons: {primary: "ui-icon-stop" }
					}).click(function(){
						self.location="<c:url value='/bizconsole/dsmonitor/disable' />";
					});
				//-->
				</script>
			</c:when>
			<c:otherwise>
				<script type="text/javascript">
				//<!--
					new biz.button({
						id:"#dsm_btn_enable",
						icons: {primary: "ui-icon-play" }
					}).click(function(){
						self.location="<c:url value='/bizconsole/dsmonitor/enable' />";
					});
				//-->
				</script>
			</c:otherwise>
		</c:choose>
		<%
			if(!monitor.isEnabled()){
				return;
			}
		%>
		<div id="tabs" class="ui-widget ui-widget-content ui-corner-all">
			<!--可以添加多对li，即多个tab标签页-->
			<ul>
				<!--此处href与后面id为tabs-tabs1的div对应 -->
				<li><a  href="#tab-dsm-status">连接池状态</a></li>
				<li class="dsm-detail"><a  href="#tab-dsm-conns">活动连接</a></li>
				<li class="dsm-detail"><a  href="#tab-dsm-sql">最近SQL语句</a></li>
			</ul>
			<!--tabs-tabs1面板内容-->
			<div id="tab-dsm-status">
				<div class="ui-table ui-widget ui-corner-all ui-margin" >
					<table class="table forview" width="100%">
						<thead class="dsm-status-sum-begin"></thead>
						<tfoot></tfoot>
					</table>
					<div class="dsm-status-cluster"></div>
					<table class="table forview" width="100%">
						<thead class="dsm-status-begin"></thead>
						<tfoot></tfoot>
					</table>
				</div>
			</div>
			<div id="tab-dsm-conns">
				<hr class="barTitleHr"></hr>
				<div class="ui-table ui-widget ui-corner-all ui-margin" >
					<table class="table forview" width="100%">
						<thead class="dsm-conn-begin">
							<tr class="ui-jqgrid-labels">
								<td class="ui-state-default ui-th-column ui-th-ltr">连接ID</td>
								<td class="ui-state-default ui-th-column ui-th-ltr">持续时间（秒）</td>
								<td class="ui-state-default ui-th-column ui-th-ltr">跟踪</td>
							</tr>
						</thead>
						<tfoot></tfoot>
					</table>
				</div>
			</div>
			<div id="tab-dsm-sql">
				<hr class="barTitleHr"></hr>
				<div class="ui-table ui-widget ui-corner-all ui-margin" >
					<table class="table forview" width="100%">
						<thead class="dsm-sql-begin"></thead>
						<tfoot></tfoot>
					</table>
				</div>
			</div>      
		</div>
	</div>
</div>
	<%@ include file="/pages/inc/footer.jsp"%>
	<div id="connSTraceDialog"></div>
	<div id="errorDialog"></div>
	<div id="sqlDialog" class="sqlDialog"></div>
</body>
</html>
<script type="text/javascript">
var connSTraceDialog;
var connSTraceDialogOption = {
	id:"#connSTraceDialog",
	autoOpen:false,
	title:"连接调用栈",
	width:"80%",
	modal:true,
	buttons: [
		{
			text: I18N.close,
			click: function() { 
				connSTraceDialog.close();
				connSTraceDialog.destroy();
			}
		}
	]
};

function monitorEnable(){
	var dsname=$("#dsm-datasource-name").val();
	var dataUrl = "<c:url value='/bizconsole/dsmonitor/enable' />?dsname="+dsname+"&cnname=" + id;
	$.ajax({
		url: dataUrl,
		cache:false,
		success: function(data, textStatus, jqXHR){
			fetchData();
		},
		error:errorInfo
	});	
}

function connGridOpSTrace(id){
	var dsname=$("#dsm-datasource-name").val();
	var dataUrl = "<c:url value='/bizconsole/dsmonitor/conn/strace' />?dsname="+dsname+"&cnname=" + id;
	$.ajax({
		url: dataUrl,
		cache:false,
		success: function(data, textStatus, jqXHR){
			connSTraceDialog = new biz.dialog(connSTraceDialogOption);
			$(connSTraceDialog.id).html(data)
			connSTraceDialog.open();
		},
		error:errorInfo
	});		
}
function connGridOpClose(id,value){
	var dsname=$("#dsm-datasource-name").val();
	var dataUrl = "<c:url value='/bizconsole/dsmonitor/conn/close' />?dsname="+dsname+"&cnname=" + id;
	$.ajax({
		url: dataUrl,
		cache:false,
		success: function(data, textStatus, jqXHR){
			fetchData();
		},
		error:errorInfo
	});	
}

function errorInfo(request, textStatus, errorThrown){
	var errorDialog = new biz.dialog({
		id:"#errorDialog",
		modal:true,
		width:"80%",
		height:400,
		title:"<fmt:message key='error.title'/>",
		close: function(event, ui){
			errorDialog.destroy();
		}
	});
	$("#errorDialog").html(request.responseText);
	errorDialog.open();
}

function showSqlDialog(content){
	var sqlDialog = new biz.dialog({
		id:"#sqlDialog",
		modal:true,
		width:480,
		height:360,
		title:"详细",
		close: function(event, ui){
			sqlDialog.destroy();
		}
	});
	$("#sqlDialog").html(content);
	sqlDialog.open();
}

function updateDataSourceStatusContent(contentClass,anchorClass,dsstatus){
	var content=$("<tbody/>");
	content.addClass(contentClass);
	var first=true;
	var row;
	for(var idx in dsstatus){
		var stat=dsstatus[idx];
		if(first){
			row=$("<tr/>");
		}
		//row.addClass("dsm-status-row");
		//if(idx&1==1){
		//	row.addClass("dsm-table-odd");
		//}
		var label=$("<td/>");
		//label.addClass("dsm-status-cell-label");
		label.addClass("inputLabelTd");
		label.html(stat.label+":");
		row.append(label);
		
		var value=$("<td/>");
		//value.addClass("dsm-status-cell-value");
		value.addClass("inputTd");
		value.html(stat.value);
		row.append(value);
		if(!first){
			content.append(row);
		}
		first=!first;
	}
	$("."+contentClass).remove();
	$("."+anchorClass).after(content);
}

function updateDataSourceStatus(resp){
	var $resp=resp;
	var dsstatus=resp.dsstatus;
	var count=0;
	var defaultKey;
	for(var key in dsstatus){
		if(key!="sum"){
			count++;
			if(count>1){
				break;
			}else{
				defaultKey=key;
			}
		}
	}
	if(count>1){
		var currentSelected=null;
		var selectNode=$(".dsm-status-cluster select");
		if(selectNode!=null && selectNode.length>0 && dsstatus[selectNode.val()]){
			currentSelected=selectNode.val();
		}
		selectNode.remove();
		selectNode=$("<select />");
		for(var key in dsstatus){
			if(key!="sum"){
				optionNode=$("<option />");
				optionNode.attr("value",key);
				if(key==currentSelected){
					optionNode.attr("selected",true);
				}
				optionNode.html(key);
				selectNode.append(optionNode);
				if(currentSelected==null){
					currentSelected=key;
				}					
			}
		}
		$(".dsm-status-cluster").append(selectNode);
		selectNode.change(updateDataSourceStatus.bind(this,$resp));
		updateDataSourceStatusContent("dsm-status-sum-content","dsm-status-sum-begin",dsstatus["sum"]);
		updateDataSourceStatusContent("dsm-status-content","dsm-status-begin",dsstatus[currentSelected]);
	}else{
		$(".dsm-status-cluster select").remove();
		updateDataSourceStatusContent("dsm-status-content","dsm-status-begin",dsstatus[defaultKey]);
	}
}

function updateConnections(conns){
	var content=$("<tbody/>");
	content.addClass("dsm-conn-content");
	for(var idx in conns){
		var stat=conns[idx];
		var row=$("<tr/>");
		row.addClass("dsm-conn-row");
		row.addClass("ui-widget-content");
		row.addClass("jqgrow");
		row.addClass("ui-row-ltr");
		if(idx&1==1){
			row.addClass("dsm-table-odd");
		}
		var col;
		var id=stat.uuid;

		col=$("<td/>");
		col.addClass("inputLabelTd");
		//label.addClass("ui-widget-content");
		col.html(stat.uuid);
		row.append(col);
		
		col=$("<td/>");
		col.addClass("inputTd");
		//value.addClass("ui-widget-content");
		col.html(stat.lifeTime);
		row.append(col);

		col=$("<td/>");
		col.addClass("inputTd");
		var traceLink=$("<a/>");
		traceLink.html("跟踪");
		traceLink.attr("href","javascript:;");
		//闭包
		(function(tmp){
			traceLink.click(function(){
				connGridOpSTrace(tmp);
			});
		})(id);
		col.append(traceLink);
		row.append(col);

		/* 集群的话不支持关闭的功能
		col=$("<td/>");
		col.addClass("inputTd");
		var closeLink=$("<a/>");
		closeLink.html("关闭");
		closeLink.attr("href","javascript:;");
		//闭包
		(function(tmp){
			closeLink.click(function(){
				connGridOpClose(tmp);
			});
		})(id);
		col.append(closeLink);
		row.append(col);
		*/
		
		content.append(row);
	}
	$(".dsm-conn-content").remove();
	$(".dsm-conn-begin").after(content);
}

function updateSqls(sqls){
	var content=$("<tbody/>");
	content.addClass("dsm-sql-content");
	for(var idx in sqls){
		var stat=sqls[idx];
		var row=$("<tr/>");
//		row.addClass("dsm-sql-row");
//		if(idx&1==1){
//			row.addClass("dsm-table-odd");
//		}
		var col;
		var id=stat.uuid;
		
		col=$("<td/>");
		col.addClass("inputTd");
		//value.addClass("ui-widget-content");
		var sqlContent=$("<a/>");
		sqlContent.addClass("dsm-sql-content");
		sqlContent.attr("title",stat.sql);
		sqlContent.text(stat.sql);
		//闭包
		(function(tmp){
			sqlContent.click(function(){
				showSqlDialog(tmp);
			});
		})(stat.sql);
		col.append(sqlContent);
		row.append(col);
		
		content.append(row);
	}
	$(".dsm-sql-content").remove();
	$(".dsm-sql-begin").after(content);
}

function fetchData(){
	var name=$("#dsm-datasource-name").val();
	$.ajax("<c:url value='/bizconsole/dsmonitor/stat' />?dsname="+name,{
		async : true,
		cache : false,
		success : function(resp){
			$(".dsm-time").html(resp.time);
			
			updateDataSourceStatus(resp);

			updateConnections(resp.connections.active);

			updateSqls(resp.lastSqls);
		}
	});
}

var connGrid;
var sqlGrid;
$(document).ready(function($){
	fetchData();
	//setInterval(fetchData,4000);
});
	new biz.tabs({
		id:"#tabs"
	});
</script>