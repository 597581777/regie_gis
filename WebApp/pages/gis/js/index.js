//地图对象
var amap;
var roadLine;
var render;
var tempMarker;//临时marker对象
var placeSearch;
var myLngLat;
var myCity;
var location_from;//搜索地址栏 起点
var location_to;//搜索地址栏 终点
var licMarkers=[];

$(document).ready(function(){
 	//初始化地图容器
 	initMapContainer();
 	//绑定事件
 	bindEvent();
 	//初始化数据
 	initData();
// 	loadLicInfo();
});   	  	

////////////////////////////  地图相关     /////////////////////////////

//初始化地图容器
function initMapContainer(){
	var winWidth,winHeight;
	// 获取窗口宽度 
	if (window.innerWidth) 
		winWidth = window.innerWidth; 
	else if ((document.body) && (document.body.clientWidth)) 
		winWidth = document.body.clientWidth; 
		// 获取窗口高度 
	if (window.innerHeight) 
		winHeight = window.innerHeight; 
	else if ((document.body) && (document.body.clientHeight)) 
		winHeight = document.body.clientHeight; 
	// 通过深入 Document 内部对 body 进行检测，获取窗口大小 
	if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) 
	{ 
		winHeight = document.documentElement.clientHeight; 
		winWidth = document.documentElement.clientWidth; 
	} 
	$('#mapcontainer').width(winWidth).height(winHeight).load(mapSetting());
}

//初始化地图
function mapSetting(){
    amap = new AMap.Map('mapcontainer', {
    	resizeEnable: true,
    	showIndoorMap:false//隐藏室内地图
    });
//    amap.setFeatures(['road','building','bg']);//多个种类要素显示'bg','road','point','building'
    //地图创建是加载的插件
	var plugins = [ 'AMap.ToolBar','AMap.Scale','AMap.CitySearch','AMap.Autocomplete',
	                'AMap.DrivingRender','AMap.TransferRender','AMap.WalkingRender',
	                'AMap.PlaceSearch','AMap.Driving','AMap.Transfer','AMap.Walking','AMap.DragRoute' ];
    amap.plugin(plugins, function() {
    	var toolBar = new AMap.ToolBar({
    		autoPosition:true,//自动定位 只支持html5浏览器
    		position:'RB',
    		noIpLocate:false,//启用IP定位
    		locate:true,
    		liteStyle:true,
    		offset:new AMap.Pixel(40, 120)
//    		locationMarker:cusLocMarker//自定义marker图标
    	});
    	amap.addControl(toolBar);
//    	try{
    		//地图加载完成显示当前位置
        	toolBar.doLocation();
//    	}catch(e){
//    		msg(e);
//    	}
    	
        //比例尺
        amap.addControl(new AMap.Scale({
        	position:'RB',
        	offset:new AMap.Pixel(30,10)
        }));
        
        AMap.event.addListener(toolBar, 'location', locOnComplete);//返回定位信息
        AMap.event.addListener(toolBar, 'error', locError);      //返回定位出错信息
    });
    //添加autocomplete事件
    var autoOptions = {
    	city: myCity, //城市，默认全国
        input:"dir_from"//使用联想输入的input的id
    };
    var autocomplete_from= new AMap.Autocomplete(autoOptions);
    var autoOptions1 = {
    	city: myCity,
        input:"dir_to"
    };
    var autocomplete_to = new AMap.Autocomplete(autoOptions1);
    AMap.event.addListener(autocomplete_from, "select", placeAutoComplateSelect);
    AMap.event.addListener(autocomplete_to, "select", placeAutoComplateSelect);
    
    //获取我的城市
//    getMyCity();
}

//绑定事件
function bindEvent(){
	//导航栏按钮控制
	$('#licQuery').click(function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
		}else{
			$(this).addClass('active');
		}
//		$('#licLocationVerify').removeClass('active');
		$('#licQueryPanel').slideToggle(200);
	});
//	$('#licLocationVerify').click(function(){
//		$(this).addClass('active');
//		$('#licQuery').removeClass('active');
////		if($('#licQueryPanel').is(':visible')){
////			$('#licQueryPanel').slideToggle(200);
////		}
//		licLocationVerifyDialog();
//	});
	
//	$(".menubar>ul>li").each(function(i){
//		$(this).click(function(e){
//			var select=e.target.id;
//			//控制搜索框显示隐藏
//			$(".menubar>ul>li").each(function(j){
//				if($(this).attr("id")==select){
//					$(this).addClass('active');
//					$(this).showHideSearPanel(select,true);
//				}else{
//					$(this).removeClass('active');
//					$(this).showHideSearPanel($(this).attr("id"),false);
//				}
//			});
//		});
//	});
    //监听输入框内容更改
//    $('#searchipt').bind('input propertychange', function(e) {
//    	if(e.target.value){
//    		$('#clear').show(); 
//    	}else{
//    		$('#clear').hide(); 
//    	}
//    });
    //分页按钮
    $('#prev').prev(loadLicInfo);
    $('#next').next(loadLicInfo);
    //许可证查询按钮
    $('#queryBtn').click(function(){
    	page.pageNo=1;
    	loadLicInfo();
    });
    //绑定回车键
    document.onkeydown = function(e){
    	if(!e){
	    	e = window.event;
		}
	    if((e.keyCode || e.which) == 13){
//	    	if(validLicSearchForm()){
	    	page.pageNo=1;
	    	loadLicInfo();
//	    	}
	    }
    }
    //工具提示
    $('[data-toggle="keytooltip"]').tooltip();
    //隐藏按钮
    $('#btnLicQueryHid').unbind().click(function(){
		$('#licQueryPanel').hide(); 
	});
    //组织机构点击事件
    $("#orgSelectBtn,#orgSelect").each(function(){
    	$(this).bind("click", function() {
    		if($('#orgTreeComp').is(':hidden')){
        		$('#orgSelectBtn > span').removeClass('glyphicon-triangle-bottom').addClass('glyphicon-triangle-top');
        		$('#orgTreeComp').show();
        	}else{
        		$('#orgSelectBtn > span').removeClass('glyphicon-triangle-top').addClass('glyphicon-triangle-bottom');
        		$('#orgTreeComp').hide();
        	}      
    	});
    });
    $(document).bind('click', function(e) {
    	var e = e || window.event; //浏览器兼容性
    	var target = $(e.target) || $(e.srcElement);
    	if(target.closest("#orgTreeComp,#orgSelectBtn,#orgSelect").length == 0){  
    		$('#orgSelectBtn > span').removeClass('glyphicon-triangle-top').addClass('glyphicon-triangle-bottom');
    		$("#orgTreeComp").hide();
    	};  
    	e.stopPropagation();  
    });
    
    //增加途经点
    $('#addPoint').unbind().bind('click', addPoint);
    
    //我的位置
	$('#myLocal').mousedown(function(){
		var targetId=$('#myLocalTarget').val();
		if(targetId=='dir_from' || targetId=='dir_to'){
			location_from=myLngLat;
		}else{
			//途经点
			$('#lng_'+targetId).val(myLngLat.lng);
			$('#lat_'+targetId).val(myLngLat.lat);
		}
		$('#'+targetId).val($('#myLocalDiv>a').html());
	});
    
	//点击起点 终点输入框 显示我的位置
	$('#dir_from').showMyLocal();
	$('#dir_to').showMyLocal();

    //触发点 目的地互换按钮
    $('#exchange').click(function(){
    	if($.trim($('#dir_from').val())||$.trim($('#dir_to').val())){
    		var tempAddr=$('#dir_from').val();
    		$('#dir_from').val($('#dir_to').val());
        	$('#dir_to').val(tempAddr);
        	//位置互换 起始 结束位置对调
        	var tempLoc=location_from;
        	location_from=location_to;
        	location_to=tempLoc;
        	
        	searchTabWay();
    	}
    });
    //地址搜索按钮
    $('#btnTabway').click(searchTabWay);//默认驾车
    //隐藏地址搜索栏
    $('#btnTabwayFormHid').click(function(e){
    	var item = e.target;
    	if($('#searchInputDiv').is(':hidden')){
    		$('#exchange').show();
        	$('#searchInputDiv').show();
        	$(item).removeClass('glyphicon-menu-down')
        	.addClass('glyphicon-menu-up');
    	}else{
    		$('#myLocalDiv').hide();
    		$('#exchange').hide();
        	$('#searchInputDiv').hide();
        	$(item).removeClass('glyphicon-menu-up').addClass('glyphicon-menu-down');
    	}
    });
    //右上角路线显示按钮
    $('#right_PathBtn').click(function(e){
    	$('#tipbox').show();
//    	$('#myLocalDiv').show();
    	$('#right_PathBtn').hide();
    });
    //搜索栏出行方式按钮切换
    $('.byTabway>span>img').each(function(i){
    	$(this).click(function(e){
        	var id = e.target.id;
        	if(id=='byCartab'){
        		$('#addPoint').show();//出行为驾车时 可以增加途径点
        		$('#btnTabway').val('开车去');
        		$('#tabway_SelectId').val('cartab');
        		$('#pointDiv').show();//显示途经点
        	}
        	if(id=='byBustab'){
        		$('#addPoint').hide();
        		$('#btnTabway').val('坐公交');
        		$('#tabway_SelectId').val('bustab');
        		$('#pointDiv').hide();//隐藏途经点
        	}
        	if(id=='byWalktab'){
        		$('#addPoint').hide();
        		$('#btnTabway').val('步行去');
        		$('#tabway_SelectId').val('walktab');
        		$('#pointDiv').hide();//隐藏途经点
        	}
        	//设置触发结束点互换按钮位置
        	settingExchangeLocal();
        	//查询路线
        	searchTabWay();
        });
    });
    //清除路线
    $('#clearPath').click(function(e){
    	document.getElementById('tabway_form').reset();
    	//清除显示路线
    	if(roadLine||render){
    		roadLine.clear();
    		render.clear();
    	}
    	$('#traffic').html('').hide();//清空路线步骤列表
    	e.target.style.display='none';
    	showSign();
    });
}

//定位结束执行
function locOnComplete(result){
	var lnglat = result.lnglat;
    if(!lnglat){
    	alertMsg('获取位置失败');
    }else{
    	myLngLat=lnglat;
    }

}
//定位失败执行
function locError(result){
	if(result.status==0){
		if(new AMap.Geolocation().isSupported()){
			alertMsg('获取定位失败！请检查浏览器是否禁用位置');
		}else{
			alertMsg('浏览器版本过低不支持定位！');
		}
	}else{
		layer.msg('获取定位失败！',{
		    btn: ['确定']
		  });
	}
}

//获取用户所在城市信息
function showCityInfo() {
    //实例化城市查询类
    var citysearch = new AMap.CitySearch();
    //自动获取用户IP，返回当前城市
    citysearch.getLocalCity(function(status, result) {
        if (status === 'complete' && result.info === 'OK') {
            if (result && result.city && result.bounds) {
                var cityinfo = result.city;
                var citybounds = result.bounds;
                //document.getElementById('tip').innerHTML = '您当前所在城市：'+cityinfo;
                //地图显示当前城市
                alert(citybounds);
                amap.setBounds(citybounds);
                alertMsg(cityinfo);
            }
        } else {
            //document.getElementById('tip').innerHTML = result.info;
        }
    });
}

function getMyCity() {
    amap.getCity(function(data) {
        if (data['province'] && typeof data['province'] === 'string') {
            //document.getElementById('info').innerHTML = '城市：' + (data['city'] || data['province']);
        	myCity = (data['city'] || data['province']);
//        	msg(myCity);
        }
    });
}

//地图自适应缩放大小
function setFitView(){
	var fitView = amap.setFitView();
//    fitView.getCenter();
}

//清除地图上所有覆盖物 type=marker、circle、polyline、polygon
function clearMap(type){
	var overlays = amap.getAllOverlays(type);
	amap.remove(overlays);
	amap.clearInfoWindow();
	//清除显示路线
	if(roadLine||render){
		roadLine.clear();
		render.clear();
	}
	if(tempMarker){
		tempMarker=null;
	}
	showSign(null);
	licMarkers=null;
}

//关闭行驶路线
function closeLineSearch(){
	if(roadLine){
		roadLine.clear();
	}
	if(render){
		render.clear();
	}
	amap.clearInfoWindow();
	$('#tipbox').hide();
	$('#right_PathBtn').show();
	showSign(null);
	if(!licMarkers){//是否需要重新加载数据
		loadLicInfo();
	}
}

//根据id获取marker
function getMarker(id){
	var marker;
	var markers = amap.getAllOverlays('marker');
	for ( var i = 0; i < markers.length; i++) {
		if(markers[i].getExtData().id==id){
			return markers[i];
		}
	}
}
//移除marker
function removeMarker(id){
	var markers = amap.getAllOverlays('marker');
	for ( var i = 0; i < markers.length; i++) {
		if(markers[i].getExtData().id==id){
			amap.remove(markers[i]);
		}
	}
}


///////////////////////////////////  地图相关   结束    ///////////////////////



////////////////////////////////// 零售户信息查询相关   ////////////////////////

//显示隐藏搜索栏
//jQuery.fn.showHideSearPanel=function(action,showHide){
//	if(showHide){
//		$('#'+action+'Panel').slideToggle(200);
//		if(action=='licQuery'){
//			var markers = amap.getAllOverlays('marker');
//			if(markers.length==0){//判断是否需要刷新零售户列表
//				loadLicInfo();
//			}
//		}
//		if(action=='licLocationVerify'){
//			licLocationVerifyDialog();
//		}
//	}else{
//		$('#'+action+'Panel').hide();
//	}
//}
//地址输入框获取焦点
jQuery.fn.showMyLocal=function(){
	$(this).bind('input valuechange', function(e) {
    	if(!e.target.value && isShowMyLocalDiv()){
    		$('#myLocalDiv').show();
    	}else{
    		$('#myLocalDiv').hide();
    	}
    }).focus(function(e){
    	if(!e.target.value && isShowMyLocalDiv()){
    		$('#myLocalDiv').show();
    	}
    	$('#myLocalTarget').val(e.target.id);
    }).blur(function(){
        $('#myLocalDiv').hide();
    });
}
//是否显示我的位置选择框
var isShowMyLocalDiv=function(){
	var isShow=true;
	$('#dir_from,#dir_to,#pointDiv input[class="form-control"]').each(function(i,item){
		if(item.value==$('#myLocalDiv>a').html()){
			isShow=false;
			return;
		}
	});
	return isShow;
}

//验证表单输入
function validLicSearchForm(){
	var licNo=$('#licNo').val();
	var companyName=$('#companyName').val();
	var managerName=$('#managerName').val();
	if($.trim(licNo)||$.trim(companyName)||$.trim(managerName)){
		return true;
	}
	return false;
}

//加载零售户信息
function loadLicInfo(){
	if(!$('#orgSeqCode').val()){
		alertMsg('无法获取登录人信息，查询零售户列表失败！');
		return;
	}
	msg('正在获取零售户列表...');
	$('#resultPanel').html('').append('正在加载零售户信息...');
//	$('#loading').show();
	//清除标注点
	clearMap('marker');
	//设置分页信息
	$('#pageSize').val(page.pageSize);
	$('#pageNo').val(page.pageNo);
	$.ajax({
		url : contextPath + '/licquery/queryLicList',
		data:$('#licQueryForm').serialize(),
		type : 'POST',
		cache : false,
		dataType : 'json',
		success : function(data){
			if(data && data.result==1){
				if(data.pager.totalCount==0){
					$('#resultPanel').html('').append('未查找到零售户信息');
					return;
				}
				$('#resultPanel').html('');
				$(data.pager.result).each(function(i,item){
//					if(item.longitude && item.latitude){
						//加载零售户标注点
						addLicMarker(item);
						//加载零售户查询列表
						fullLicResultPanel(i,item);
//					}
				});
				//设置分页信息
				page.pageNo=data.pager.pageNo;
				page.pageSize=data.pager.pageSize;
				page.totalCount=data.pager.totalCount;
				//自动缩放地图视图大小
				setFitView();
				closeMsg();
			}else{
				$('#resultPanel').html('').append('未查找到零售户信息');
				alertMsg('获取零售户列表失败');
			}
		},
		error : function(e){
			alertMsg('请求失败');
		}
	});
}

//填充左侧零售户查询列表
function fullLicResultPanel(i,licInfo){
	var showLoc="";
	if(!(licInfo.longitude&&licInfo.latitude)){
		showLoc+='<span id="loc_'+licInfo.licUuid+'" class="label label-default" style="margin-left:30px;" data-toggle="tooltip" data-placement="top" title="标定零售户位置" onclick="addLocMarker(\''+licInfo.licUuid+'\')"><img src="../pages/gis/img/map-marker-ball_1.png"/>标定位置</span>';
	}
	
	var content;
	if(i%2==0){
		content='<div class="info-content1 single">';
	}else{
		content='<div class="info-content1 double">';
	}
	content+= '<b><div onclick="showMarker(\''+licInfo.licUuid+'\')" class="licInfoLine">'+licInfo.licNo+'<span id="sign_'+licInfo.licUuid+'" class="glyphicon glyphicon-pushpin"></span></b><br/><b>' +
	licInfo.managerName+'</b>&nbsp;&nbsp;'+
	licInfo.companyName+'</div>'+
	'<input id="lng_'+licInfo.licUuid+'" value="'+licInfo.longitude+'" type="hidden">'+
	'<input id="lat_'+licInfo.licUuid+'" value="'+licInfo.latitude+'" type="hidden">'+
	'<div class="tabway"><span class="label label-warning" style="margin-right:10px;">获取路线</span>'+
	'<span class="label label-default" onclick="showTabWay(\''+licInfo.licUuid+'\',\'cartab\')">'+
	'<img id=cartab_'+licInfo.licUuid+' class="cartab" data-toggle="tooltip" data-placement="top" title="驾车" src="../pages/gis/img/icon_car.png"/></span>'+
	'<span class="label label-default" onclick="showTabWay(\''+licInfo.licUuid+'\',\'bustab\')">'+
	'<img id="bustab_'+licInfo.licUuid+'" class="bustab" src="../pages/gis/img/icon_bus.png" data-toggle="tooltip" data-placement="top" title="公交"/></span>'+
	'<span class="label label-default" onclick="showTabWay(\''+licInfo.licUuid+'\',\'walktab\')">'+
	'<img id="walktab_'+licInfo.licUuid+'" class="walktab" src="../pages/gis/img/icon_walk.png" data-toggle="tooltip" data-placement="top" title="步行"/></span>'+showLoc+'</div></div>';
	$('[data-toggle="tooltip"]').tooltip();//添加tip提示信息
	$('#resultPanel').append(content);
}
function showTabWay(id,tabway){
	//清除地图
	clearMap();
	//显示图钉
	showSign(id);
	//设置开始位置 默认为 我的当前位置
	location_from=myLngLat;
	$('#dir_from').val('我的位置');
	//设置目标位置
	location_to=new AMap.LngLat($('#lng_'+id).val(),$('#lat_'+id).val());
	//根据目标位置搜索地址信息
	var batchloc=[location_to];
	AMap.service('AMap.Geocoder',function(){
	    var geocoder = new AMap.Geocoder({
	        city: myCity,//城市，默认：“全国”
	    	batch:true//批量查询
	    });
	    geocoder.getAddress(batchloc, function(status, result) {
            if (status === 'complete' && result.info === 'OK') {
            	//出行线路搜索 输入框赋值
        		$('#dir_to').val(result.regeocodes[0].formattedAddress);
        		showRoadLine(tabway,location_from,location_to,{waypoints:[]});
            }
        });
	});
}

//显示路线
/*
 * origin:起点lnglat
 * destination:终点lnglat
 * opts:途经点Array
 * */
function showRoadLine(tabway,origin,destination,opts){
	if(!origin){
		alertMsg('无法获取起点坐标，路线规划失败！');
		return;
	}
	if(!destination){
		alertMsg('无法获取目的地坐标，线路规划失败！');
	}
	
	if(roadLine||render){
		roadLine.clear();
		render.clear();
	}
	msg('正在规划路线');
	$('#traffic').html('正在规划路线...');
	//构造路线导航类
	if(tabway=='cartab'){
		$('#btnTabway').val('开车去');
		//途经点添加按钮
		$('#addPoint').show();
		roadLine = new AMap.Driving();
		render = new AMap.DrivingRender();
	}
	if(tabway=='bustab'){
		$('#btnTabway').val('坐公交');
		$('#addPoint').hide();
		//公交线路需要设置城市
		roadLine = new AMap.Transfer({
			city:myCity
		});
		render = new AMap.TransferRender();
	}
	if(tabway=='walktab'){
		$('#btnTabway').val('步行去');
		$('#addPoint').hide();
		roadLine = new AMap.Walking();
		render = new AMap.WalkingRender();
	}
    // 根据起终点经纬度规划导航路线
	if(tabway=='cartab'){//驾驶路线可以有途经点
		roadLine.search(origin,destination,opts,callback);
	}else{
		roadLine.search(origin,destination,callback);
	}
    function callback(status,result){
    	if(status == 'complete'){
    		closeMsg();
    		$('#traffic').html('');
    		render.autoRender({
 				data: result,
                map: amap,
                autoFitView:true,
                extensions:'all',
                panel: "traffic"
            });
    		if($('#traffic').is(':hidden')){
    			$('#traffic').show();//线路详细步骤div
    		}
    		if($('#tipbox').is(':hidden')){
    			$('#tipbox').show();//右侧路线搜索栏
    		}
    		if($('#right_PathBtn').is(':hidden')){
    			$('#right_PathBtn').hide();//右上角路线窗口按钮
    		}
    		if($('#clearPath').is(':hidden')){
    			$('#clearPath').show();//清除路线
    		}
         }else{
             alertMsg(result);
         }
    }
}
//定位marker坐标
function showMarker(licUuid){
	if(licUuid){
		if((roadLine||render)){
			msg('当前正在路线导航，请关闭后再查看零售户信息');
			return;
		}
		//显示图钉
		showSign(licUuid);
		var marker = getMarker(licUuid);
		if(marker!=null){
			marker.setIcon('../pages/gis/img/mark_2.png');
			showLicInfoWindow(marker.getExtData());
		}
	}
}
//点击或显示导航是 标识对应零售户
function showSign(licUuid){
	$('#resultPanel .glyphicon-pushpin').each(function(i){
		if($(this).attr("id")==("sign_"+licUuid)){
			$(this).show();
		}else{
			$(this).hide();
		}
	});
}

//添加零售户标注点
function addLicMarker(licInfo) {
	var id=licInfo.licUuid;
	var title=licInfo.companyName;
	var lng=licInfo.longitude;
	var lat=licInfo.latitude;
	var ico="../pages/gis/img/mark_3.png";
	if(!(lng&&lat)){
		return;
	}
    var marker = new AMap.Marker({
    	map: amap,
        icon: ico,
        position: [lng, lat]
    });
    //鼠标悬停时显示
    marker.setTitle(title);
    //扩展信息 
    var extData={
    	id:id,
    	data:licInfo
    }
    marker.setExtData(extData);
    //设置marker标签	
    marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
        offset: new AMap.Pixel(-23, -24),//修改label相对于maker的位置
        content: ''
    });
    marker.on('click',function(e){
    	showLicInfoWindow(e.target.getExtData());
    });
    //右键菜单
//	var contextMenu = new AMap.ContextMenu();  //创建右键菜单
//    contextMenu.addItem("<span class='glyphicon glyphicon-hand-right finger'></span>设为起点", function() {
//    }, 0);
//    contextMenu.addItem("<span class='glyphicon glyphicon-hand-right finger'></span>设为途经点", function() {
//    	
//    }, 1);
//    contextMenu.addItem("<span class='glyphicon glyphicon-hand-right finger'></span>设为终点", function() {
//    }, 2);
//	//绑定鼠标右击事件
//    marker.on('rightclick', function(e) {
//        contextMenu.open(amap, e.lnglat);
//    });
    if(null==licMarkers){
    	licMarkers=[];
    }
    licMarkers.push(id);
}

//显示零售户信息窗口
function showLicInfoWindow(extData){
	var id=extData.id;
	if(!id){
		return;
	}
	showSign(id);
	var licInfo=extData.data;
	var content='<div></br></div><div class="info-content"><dl class="dl-horizontal">' +
	'<b><dt>负责人(经营者)姓名：</b></dt><dd>'+licInfo.managerName+'</dd>' +
    '<b><dt>企业(字号)名称：</b></dt><dd>'+licInfo.companyName+'</dd>' +
    '<b><dt>零售许可证编号：</b></dt><dd>'+licInfo.licNo+'</dd>' +
    '<b><dt>经营地址：</b></dt><dd>'+licInfo.businessAddr+'</dd>'+
    '<b><dt>身份证号：</b></dt><dd>'+licInfo.idcard+'</dd>'+
    /*'<b><dt>经营范围：</b></dt><dd>'+(licInfo.managerScopeStr==undefined?'':licInfo.managerScopeStr)+'</dd>'+*/
	'<b><dt>有效期：</b></dt><dd>'+(licInfo.validateEndStr==undefined?'':licInfo.validateEndStr)+'</dd></dl>'+
	'<div style="text-align:right;padding-right:10px;"><img src="../pages/gis/img/map-marker-ball_1.png" class="img1"/>'+
	'<a href="javascript:void(0);" onclick="modifyMarker(\''+id+'\')">修改零售户坐标</a>'+
	'</div></div>';
	//打开信息窗体
	var infoWindow = new AMap.InfoWindow({
		content: content,
	    offset: new AMap.Pixel(0, -30)
	});
	var lnglat=[licInfo.longitude,licInfo.latitude];
	infoWindow.open(amap,lnglat);
	amap.setCenter(lnglat);
	//改变marker图标
	var marker = getMarker(id);
	marker.setIcon('../pages/gis/img/mark_2.png');
	infoWindow.on('close',function(){
		$('#sign_'+id).hide();
		marker.setIcon('../pages/gis/img/mark_3.png');
	});
}

function addMarker(point) {
	if(!point.lnglat){
		return;
	}
    var marker = new AMap.Marker({
    	map: amap,
        icon: "../pages/gis/img/mark_3.png",
        position: [point.lnglat.lng, point.lnglat.lat]
    });
    //设置可拖拽
	marker.setDraggable(true);
    //鼠标悬停时显示
//    marker.setTitle(point.name);
    //设置marker标签	
    marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
        offset: new AMap.Pixel(-23, -24),//修改label相对于maker的位置
        content: point.name
    });
}

//标定零售户位置
function addLocMarker(licUuid){
	if(!licUuid){return};
	//清空地图
//	clearMap('marker');
	//关闭信息窗口
	amap.clearInfoWindow();
	//添加新marker
	if(tempMarker){
		alertMsg('目标已存在！</br>请在标注点上点击鼠标右键取消，再重新添加');
		amap.setZoomAndCenter(15,tempMarker.getPosition());
		return;
	}
	$('#loc_'+licUuid).removeClass('label-default').addClass('label-warning');
	tempMarker = new AMap.Marker({
    	map: amap,
        position: amap.getCenter()
    });
	//设置marker图标
	tempMarker.setIcon('../pages/gis/img/mark_1.png');
	tempMarker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
        offset: new AMap.Pixel(-10, -23),//修改label相对于maker的位置
        content: "请用鼠标拖动气泡改变位置"
    });
	//设置可拖拽
	tempMarker.setDraggable(true);
	//设置图标样式
	tempMarker.setCursor('move');
	amap.setZoomAndCenter(15,tempMarker.getPosition());
	
	//右键菜单
	var contextMenu = new AMap.ContextMenu();  //创建右键菜单
    contextMenu.addItem("保存", function() {
    	savePosition(tempMarker,licUuid);
    }, 0);
    contextMenu.addItem("取消", function() {
    	tempMarker=null;
    	loadLicInfo();
    }, 1);
	//绑定鼠标右击事件
	tempMarker.on('rightclick', function(e) {
        contextMenu.open(amap, e.lnglat);
    });
//	AMap.event.addListener(tempMarker,'dragend',function(e){
//		
//	});
}
//更改marker坐标
function modifyMarker(markerId){
	var marker = getMarker(markerId);
	var position = marker.getPosition();
	//移除marker
	amap.remove(marker);
	//关闭信息窗口
	amap.clearInfoWindow();
	//添加新marker
	marker = new AMap.Marker({
    	map: amap,
        position: position
    });
	//设置marker图标
	marker.setIcon('../pages/gis/img/mark_1.png');
	//设置可拖拽
	marker.setDraggable(true);
	//设置图标样式
	marker.setCursor('move');
	marker.setLabel({//label默认蓝框白底左上角显示，样式className为：amap-marker-label
	        offset: new AMap.Pixel(-10, -23),//修改label相对于maker的位置
	        content: "请用鼠标拖动气泡改变位置"
	    });
    //右键菜单
	var contextMenu = new AMap.ContextMenu();  //创建右键菜单
    contextMenu.addItem("确定", function() {
//    	savePosition(marker,markerId);
    	saveLocalApply(markerId,marker.getPosition());
    }, 0);
    contextMenu.addItem("取消", function() {
    	tempMarker=null;
    	loadLicInfo();
    }, 1);
	//绑定鼠标右击事件
    marker.on('rightclick', function(e) {
        contextMenu.open(amap, e.lnglat);
    });
//	AMap.event.addListener(marker,'dragend',function(e){
//		savePosition(marker,markerId);
//	});
}

//保存
function savePosition(marker,markerId){
	var lnglat = marker.getPosition();
	layer.confirm('是否保存当前位置？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			//更新零售户位置
			$.ajax({
				url : contextPath + '/licquery/updateLicInfoLocal',
				type : 'POST',
				data : {
					'licUuid':markerId,
					'longitude':lnglat.lng,
					'latitude':lnglat.lat
				},
				cache : false,
				dataType : 'json',
				success : function(data){
					if(data && data.result=='1'){
						alertMsg('保存成功');
					}else{
						alertMsg('保存失败');
					}
					loadLicInfo();
				},
				error : function(e){
					alertMsg('保存失败');
				}
			});	
		}, function(){
			tempMarker=null;
			marker=null;
			loadLicInfo();
		});	
}

//保存坐标修改申请
function saveLocalApply(markerId,lnglat){
	if(!markerId){
		return;
	}
	layer.confirm('是否发送修改零售户坐标请求？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			//更新零售户位置
			$.ajax({
				url : contextPath + '/licLocationVerify/saveLicLocationApply',
				type : 'POST',
				data : {
					'licUuid':markerId,
					'longitude':lnglat.lng,
					'latitude':lnglat.lat
				},
				cache : false,
				dataType : 'json',
				success : function(data){
					if(data && data.result==1){
						alertMsg('申请成功');
					}else{
						alertMsg('申请失败');
					}
					loadLicInfo();
				},
				error : function(e){
					alertMsg('请求失败');
				}
			});	
		}, function(){
			tempMarker=null;
			marker=null;
		});	
}

//增加途经点
function addPoint(){
	//途经点大于5个 返回
	if($('#pointDiv div[class="input-group"]').length==5){
		return;
	}
	var index=$('#pointDiv div').length;
	var inputId='pathway_'+index;
	var pointHtml='<div class="input-group"><span class="input-group-addon addon">经</span>'+
				'<input id="'+inputId+'" class="form-control" placeholder="请输入途经点" type="text" style="width:210px;height:26px;"/>'+
				'<input id="lng_'+inputId+'" type="hidden" />'+
				'<input id="lat_'+inputId+'" type="hidden" />'+
				'<span class="glyphicon glyphicon-minus dir_point" onclick="$(this).parent().remove();settingExchangeLocal();searchTabWay();"></span></div>';
	$('#pointDiv').append(pointHtml);
	
	///添加地址搜索自动填充
	var autoOptions = {
		city: myCity,
		input:inputId
	};
	var pathwayauto= new AMap.Autocomplete(autoOptions);
	AMap.event.addListener(pathwayauto, "select", function(e){
		$('#'+inputId).val(e.poi.name);
		$('#lng_'+inputId).val(e.poi.location.lng);
		$('#lat_'+inputId).val(e.poi.location.lat);
	});
	//添加输入框选择事件
	$('#'+inputId).showMyLocal();
	
	//设置触发结束点互换按钮位置
	settingExchangeLocal();
}
//设置触发结束点互换按钮位置
function settingExchangeLocal(){
	var pdiv=document.getElementById('searchInputDiv');
	var btnDiv=document.getElementById('tabwaybtnDiv');
	var cHeight=(pdiv.clientHeight-btnDiv.clientHeight)/2;
	document.getElementById('exchange').style.top=cHeight+60+'px';
}
//搜索路线
function searchTabWay(){
	//验证输入
	if(!$.trim($('#dir_from').val())){
		$('#dir_from').focus().val('');
		return;
	}
	if(!$.trim($('#dir_to').val())){
		$('#dir_to').focus().val('');
		return;
	}
	if($.trim($('#dir_from').val())==$.trim($('#dir_to').val())){
		msg('起点与终点不能相同');
		return;
	}
	//判断途经点输入
	var isValid=true;
	$('#pointDiv input[class="form-control"]').each(function(i,item){
		if($.trim(item.value)==$.trim($('#dir_from').val()) || $.trim(item.value)==$.trim($('#dir_to').val())){
			msg('途经点不能与起点或终点相同');
			isValid=false;
			return;
		}
	});
	if(!isValid){
		return;
	}
//	if(!location_from || !location_to){
//		msg('请选择正确的地址');
//		return;
//	}
	//清空地图
	clearMap('marker');
	var opts={};//途经点
	var waypointsArr=[];
	//如果是驾车路线 则获取途经点
	if($('#tabway_SelectId').val()=='cartab'){
		var pathwayLen = $('#pointDiv input[class="form-control"]').length;
		if(pathwayLen>0){
			for(var i=0;i<pathwayLen;i++){
				var lng = $('#lng_pathway_'+i).val();
				var lat = $('#lat_pathway_'+i).val();
				var lnglat = new AMap.LngLat(lng, lat);
				waypointsArr.push(lnglat);
			}
			opts.waypoints=waypointsArr;
		}
	}
	//显示路线
	showRoadLine($('#tabway_SelectId').val(),location_from,location_to,opts);
}
//地址搜索栏 自动填充列表选择事件
function placeAutoComplateSelect(e){
//	var point={};
//	point.name=e.poi.name;
//	point.address=e.poi.district+e.poi.address;
//	point.id=e.poi.id;
//	point.lnglat=e.poi.location;
    $('#'+$('#myLocalTarget').val()).val(e.poi.name);
    if($('#myLocalTarget').val()=='dir_from'){
    	location_from=e.poi.location;
    }
    if($('#myLocalTarget').val()=='dir_to'){
    	location_to=e.poi.location;
    }
    
	//placeSearch.search(e.poi.name);
}






//function licQueryDialog(){
//	var callback=function(){
//		alert('许可证查询');
//	}
//	var content='查询';
//	common.modelDialog('零售户查询',content,callback);
//}
//


//初始化页面数据
function initData(){
	msg('正在获取登录人信息...');
	//清空表单
	document.getElementById('orgQueryForm').reset();
	document.getElementById('tabway_form').reset();
	
	//初始化登录人信息
	$.ajax({
		url : contextPath + '/common/getUserInfo',
		type : 'POST',
		cache : false,
		dataType : 'json',
		async: false,
		success : function(data){
			if(data){
				$('#orgSeqCode').val(data.unitCode);
				$('#orgCode').val(data.unitOrgCode);
				$('#unitRank').val(data.unitRank);
				$('#orgSeqCode_').val(data.unitCode);
				$('#unitRank_').val(data.unitRank);
				
			}else{
				alertMsg('无法获取到登录人信息！');
			}
		}
	});
	//加载零售户信息
 	loadLicInfo();
 	//加载组织机构
// 	loadOrgList();
}

////零售户坐标修改审核弹出层
//function licLocationVerifyDialog(){
//	var width = 970+'px';
//	var height = 600+'px';
//	layer.open({
//		type : 2, 
//		title : '零售户坐标修改审核',
//		content: [contextPath+'/licLocationVerify/index', 'no'],
//		area : [ width, height],
//		maxmin: true,
//		skin : 'layui-layer-demo', 
//		closeBtn : 1, 
//		cancel : function() {
//			// 右上角关闭
//		}
//	});
//}

//设置搜索panel大小
var offsetTop = document.getElementById('licQueryPanel').offsetTop;
var domClientHeight = document.body.clientHeight;
$('#licQueryPanel').css({
	height: (domClientHeight-offsetTop-5)+'px',
	maxHeight: (domClientHeight-offsetTop-5)+'px',
	minHeight: (domClientHeight-offsetTop)*0.5+'px'
});
$('#resultPanel').css({
	height: (domClientHeight-offsetTop-178)+'px'
});

$('#managerMonitorPanel').css({
	maxHeight: (domClientHeight-offsetTop-200)+'px',
	minHeight: (domClientHeight-offsetTop-200)*0.5+'px'
});
//设置导航panel大小
//offsetTop = document.getElementById('tipbox').offsetTop;
//$('#tipbox').css({
//	maxHeight: (domClientHeight-offsetTop-10)+'px'
//});


