////////////////////////////  市管员监控  /////////////////////////////////////

function loadOrgList(){
	var orgSeqCode=$('#orgSeqCode').val();
	if(!orgSeqCode){
		alertMsg('无法获取登录人信息，查询零售户列表失败！');
		return;
	}
	orgSeqCode='013451';
	var setting = {
			view: {
				selectedMulti: false,
				showLine :false
			},
	        async: {
	            enable: true, //开启异步加载
	            showLine: true ,//设置是否显示连接线
				type: 'post',//post方式请求数据
	            url: contextPath + '/common/getOrgInfoList?orgSeqCode='+orgSeqCode,
	            autoParam: ['id'],
	            dataFilter: filter
	        },
	        callback:{
	        	onClick :treeOnClick
	        }
	    };
	function filter(treeId, parentNode, childNodes) {
		if (!childNodes) return null;
		for (var i=0, l=childNodes.length; i<l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	$.fn.zTree.init($("#orgTree"), setting);
	
	function treeOnClick(event, treeId, treeNode, clickFlag) {
		$('#orgSelect').val(treeNode.name);
		$('#orgSeqCode_').val(treeNode.id);
		$('#orgSelectBtn > span').removeClass('glyphicon-triangle-top').addClass('glyphicon-triangle-bottom');
		$('#orgTreeComp').hide();
		//查询组织机构人员列表
		searchEmployees(treeNode.id);
	}
	
	function searchEmployees(orgSeqCode){
		$.ajax({
			url : contextPath + '/common/getOrgInfoList',
			type : 'POST',
			cache : false,
			dataType : 'json',
			success : function(data){
				if(data){
					var nodes=[];
					$(data).each(function(i,org){
						var node={
							id:org.orgSeqCode,
							name:org.orgName,
							pId:org.pId
						}
						nodes.push(node);
					});
					initTree(nodes);
				}else{
					alertMsg('获取组织机构信息失败');
				}
			},
			error : function(e){
				alertMsg('请求获取组织机构信息失败');
			}
		});	
	}
}