//文根
function getContextPath(){
	var pathName = document.location.pathname;
	var index = pathName.substr(1).indexOf("/");
	var result = pathName.substr(0,index+1);
	return result;
}
var contextPath = getContextPath();

var index;
function alertMsg(msg){
	layer.alert(msg, {
//		  skin: 'layui-layer-molv', //样式类名
		  closeBtn: 1
		});
}

function msg(msg){
	index=layer.msg(msg);
}
function closeMsg(){
	if(index){
		layer.close(index);
	}
}
function tip(msg,el){
	layer.tips(msg, el, {
		  tips: 3
		});
}

var common = {
	//模态对话框
	modelDialog : function(title, html, callback, width, height) {
		if(!title){
			title = '消息对话框';
		}
		if (!width) {
			width = window.document.body.clientWidth * 0.6;
		}
		if (!height) {
			height = window.document.body.clientHeight * 0.8;
		}
		layer.open({
			title : title,
			type : 1,
			skin : 'layui-layer-demo', // 样式类名
			closeBtn : 1, // 不显示关闭按钮
			area : [ width + 'px', height + 'px' ],
			shadeClose : false, // 开启遮罩关闭
			btn : [ '确定', '取消' ],
			content : html,
			btn1 : function(index, layer) {
				// 确定按钮
				callback();
			},
			btn2 : function(index, layer) {
			},
			cancel : function() {
				// 右上角关闭
			}
		});
	},
	//iframe对话框
	iframeDialog:function(title,url,callback, width, height){
		if(!title){
			title = '消息对话框';
		}
		if (!width) {
			width = window.document.body.clientWidth * 0.6;
		}
		if (!height) {
			height = window.document.body.clientHeight * 0.8;
		}
		layer.open({
			type : 2, 
			title : title,
			content: url,
			area : [ width + 'px', height + 'px' ],
			maxmin: true,
			skin : 'layui-layer-demo', // 样式类名
			closeBtn : 1, // 不显示关闭按钮
			btn : ['确定','取消'],
			btn1 : function(index, layer) {
				// 确定按钮
				callback();
			},
			btn2 : function(index, layer) {
			},
			cancel : function() {
				// 右上角关闭
			}
		});
	},
	iframeDialog1:function(title,url,callback, width, height){
		if(!title){
			title = '消息对话框';
		}
		if (!width) {
			width = window.document.body.clientWidth * 0.6;
		}
		if (!height) {
			height = window.document.body.clientHeight * 0.8;
		}
		layer.open({
			type : 2, 
			title : title,
			content: url,
			area : [ width + 'px', height + 'px' ],
			maxmin: true,
			skin : 'layui-layer-demo', // 样式类名
			closeBtn : 1, // 不显示关闭按钮
			cancel : function() {
				// 右上角关闭
			}
		});
	}
	
}

//分页对象
var page={
	pageNo:1,
	pageSize:10,
	start:0,
	totalCount:0,
	totalPage:0
}
//上一页
jQuery.fn.prev=function(callback){
	$(this).click(function(){
		page.pageNo-=1;
		if(page.pageNo<1){
			page.pageNo=1;
		}
		callback();
	});
}
//下一页
jQuery.fn.next=function(callback){
	$(this).click(function(){
		page.pageNo+=1;
		var total=Math.ceil(page.totalCount/page.pageSize);
		if(page.pageNo>total){
			page.pageNo=total;
		}
		callback();
	});
}
