package com.icss.regiegis.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chinasofti.ro.bizframework.core.libs.StringUtil;
import com.chinasofti.ro.bizframework.core.mvc.Controller;
import com.chinasofti.ro.bizframework.core.orm.Page;
import com.icss.regiegis.common.model.Employee;
import com.icss.regiegis.common.model.OrgModel;
import com.icss.regiegis.common.model.TreeVO;
import com.icss.regiegis.common.service.OrgService;
import com.icss.regiegis.common.service.PersonService;
import com.icss.regiegis.common.service.UserService;
import com.icss.regiegis.common.util.LogUtil;
import com.icss.regiegis.common.util.UserUtil;
import com.icss.resourceone.common.logininfo.UserInfo;

/**
 * <p>controller</p>
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class CommonController extends Controller {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private UserService userService;
	private OrgService orgService;
	private PersonService personService;
	
	/**
	 * <p>Description: 获取登录用户信息<p>
	 * @author haoyajie 2017-7-18
	 */
	public void getUserInfo(){
		UserInfo user = UserUtil.getUserInfo();
		Employee employee = userService.getEmployeeByPersonUuid(user.getPersonUuid());
		this.renderJSON(JSON.toJSONString(employee));
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 获取组织机构列表
	 * <p>Description: <p>
	 * @param orgBaseInfo
	 * @author haoyajie 2017-6-20
	 */
	public void getOrgTreeList(String orgCode, String id){
		try {
			List<TreeVO> orgList=null;
			OrgModel orgModel = new OrgModel();
			//点击树节点 根据节点id查询下级组织机构列表
			if(!StringUtil.isEmpty(id)){
				orgModel.setOrgCode(id);
				orgList = orgService.getOrgList(orgModel);
			}else if(!StringUtil.isEmpty(orgCode)){
				//orgSeqCode不为空时 查询树根节点
				orgModel.setOrgCode(orgCode);
				orgList = orgService.getParentOrg(orgModel);
			}
			this.renderJSON(JSONArray.fromObject(orgList).toString());
		} catch (Exception e) {
			LogUtil.error(e);
			log.error("获取组织机构失败");
		}
	}
	
	/**
	 * <p>Description: 获取市管员<p>
	 * @param deptCode
	 * @author haoyajie 2017-7-20
	 */
	public void getSgyPersonList(String deptCode, Page page){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Page pager = personService.getSgyPersonList(deptCode, page);
			map.put("result",1);
			map.put("pager",pager);
		} catch (Exception e) {
			log.error("获取市管员列表失败");
			map.put("result",0);
		}
		renderJSON(JSONObject.toJSONString(map));
	}

	/**
	 * 获取人员网格
	 * <p>Description: <p>
	 * @param personUuid
	 * @author haoyajie 2017-7-25
	 */
	public void getGridList(String personUuid, String deptCode, String id, String roleCode){
		try {
			List<TreeVO> gridList = orgService.findGridList(personUuid, deptCode, id, roleCode);
			renderJSON(JSONObject.toJSONString(gridList));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setOrgService(OrgService orgService) {
		this.orgService = orgService;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}
	
	
}