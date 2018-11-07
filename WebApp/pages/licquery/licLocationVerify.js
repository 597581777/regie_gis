
$(document).ready(function(){
	initData();
	//初始化表格数据
	findLicLocalApproveList();
	
});   	  	


///查询坐标修改申请列表
function findLicLocalApproveList(){
	$('#gridDateBody').html('<tr><td colspan="16">正在加载数据</td></tr>');
	//设置分页参数
	$('#pageNo').val(page.pageNo);
	$('#pageSize').val(page.pageSize);
	
	$.ajax({
		url : contextPath + '/licLocationVerify/getLicVerifyList',
		data:$('#verifyForm').serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){
			if(data && data.result.length>0){
				fillGridDate(data);
				page.pageNo=data.pageNo;
				page.pageSize=data.pageSize;
				page.totalCount=data.totalCount;
			}else{
				$('#gridDateBody').html('').append("<tr><td colspan='16'>未查找到数据</td></tr>");
			}
		},
		error : function(e){
			alertMsg('请求失败');
		}
	});
}

function search(){
	page.pageNo=1;
	findLicLocalApproveList();
}
//分页按钮
$('#prev').prev(findLicLocalApproveList);
$('#next').next(findLicLocalApproveList);

function fillGridDate(data){
	$('#gridDateBody').html('');
	$(data.result).each(function(i,it){
		var row = '';
		if(i%2==0){
			row+='<tr class="warning">';
		}else{
			row+='<tr class="success">';
		}
		row+='<td>'+(i+1)+'</td>'+
		'<td>'+it.licNo+'</td>'+
		'<td>'+(it.managerName?it.managerName:'')+'</td>'+
		'<td>'+(it.companyName?it.companyName:'')+'</td>'+
		'<td>'+(it.address?it.address:'')+'</td>'+
		'<td><a href="javascript:showShortMap('+it.licNo+','+it.oldLongitude+','+it.oldLatitude+','+it.longitude+','+it.latitude+')">'+it.oldLongitude+','+it.oldLatitude+'</a></td>'+
		'<td><a href="javascript:showShortMap('+it.licNo+','+it.oldLongitude+','+it.oldLatitude+','+it.longitude+','+it.latitude+')">'+it.longitude+','+it.latitude+'</a></td>'+
//		'<td>'+(it.staffMembername?it.staffMembername:'')+'</td>'+
		'<td></td>'+
//		'<td>'+(it.gridding?it.gridding:'')+'</td>'+
		'<td></td>'+
		'<td>'+(it.orgName?it.orgName:'')+'</td>'+
		'<td>'+(it.applyPersonName?it.applyPersonName:'')+'</td>'+
		'<td>'+(it.applyTimeStr?it.applyTimeStr.substring(0,it.applyTimeStr.indexOf('.')):'')+'</td>'+
		'<td>'+(it.approveStatus==1?'已审批':'未审批')+'</td>'+
		'<td>'+(it.approvePersonName?it.approvePersonName:'')+'</td>'+
		'<td>'+(it.approveTimeStr?it.approveTimeStr.substring(0,it.approveTimeStr.indexOf('.')):'')+'</td>'+
		'<td>'+(it.approveStatus==1?'':'<a href="javascript:doApprove(\''+it.tId+'\')">同意</a>')+'</td>'+
		'</tr>';
		$('#gridDateBody').append(row);
	});
}

//执行审核
function doApprove(tid){
	if(!tid){
		return;
	}
	
	layer.confirm('是否同意申请?', {
		btn: ['确定','取消']},
		function(index){
			layer.close(index);
			$.ajax({
				url : contextPath + '/licLocationVerify/approveLicLocal',
				data:{
					tid: tid
				},
				type : 'POST',
				dataType : 'json',
				success : function(data){
					if(data&&data.result==1){
						findLicLocalApproveList();
					}else{
						alertMsg('审核失败');
					}
				},
				error : function(e){
					alertMsg('请求失败');
				}
			});
		},
		function(index){
			layer.close(index);
		}
	);       
	
}

//坐标审核
function showShortMap(licNo,oldLongitude,oldLatitude,longitude,latitude){
	var mapcontent='<div id="mapShortView" style="width:100%;height:100%;overflow:hidden;"></div>';
	layer.open({
		type: 1,
		content: mapcontent,
		title: '零售户坐标',
		area : [ 600 + 'px', 380 + 'px' ],
		maxmin: true,
		shadeClose: true,
		skin : 'layui-layer-demo', // 样式类名
		scrollbar: false,
		btn: 0,
		closeBtn : 1, // 不显示关闭按钮
		full: function(layero){
			$('#mapShortView').css({
				height: '100%'
			});
			window.amap.setFitView();
		},
		restore: function(layero){
			$('#mapShortView').css({
				height: '100%'
			});
			window.amap.setFitView();
		},
		success: function(){
			initMap();
			showLicMarkers(licNo,oldLongitude,oldLatitude,longitude,latitude);
		},
		cancel: function(index, layero){ 
			window.amap = null;
		}
	});
}

var initMap = function(){
	var amap = new AMap.Map('mapShortView', {
   	 	resizeEnable: true,
    	showIndoorMap:false,//隐藏室内地图
   	    zoom:11,
   	    center: [116.397428, 39.90923]        
   	});
	amap.setFitView();
	window.amap = amap;
}

var showLicMarkers = function(licNo,oldLongitude,oldLatitude,longitude,latitude){
	var amap = window.amap;
	var marker_old = new AMap.Marker({
    	map: amap,
        icon: '../pages/gis/img/mark_3.png',
        position: [oldLongitude,oldLatitude]
    });
    //鼠标悬停时显示
	marker_old.setTitle(licNo);
    //设置marker标签	
	marker_old.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
        offset: new AMap.Pixel(-23, -24),//修改label相对于maker的位置
        content: '原始坐标'
    });
	
	var marker_new = new AMap.Marker({
    	map: amap,
        icon: '../pages/gis/img/mark_1.png',
        position: [longitude,latitude]
    });
    //鼠标悬停时显示
	marker_new.setTitle(licNo);
    //设置marker标签	
	marker_new.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
        offset: new AMap.Pixel(-23, -24),//修改label相对于maker的位置
        content: '修改坐标'
    });
	
	amap.setFitView();
}

function initData(){
	msg('正在获取登录人信息...');
	//清空表单
	document.getElementById('verifyForm').reset();

	//初始化登录人信息
	$.ajax({
		url : contextPath + '/common/getUserInfo',
		type : 'POST',
		cache : false,
		dataType : 'json',
		async: false,
		success : function(data){
			if(data){
				$('#orgCode').val(data.unitOrgCode);
				closeMsg();
			}else{
				alertMsg('无法获取到登录人信息！');
			}
		}
	});
}