package com.icss.regiegis.licquery.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.chinasofti.ro.bizframework.core.exceptions.GeneralException;
import com.chinasofti.ro.bizframework.core.mvc.Controller;
import com.chinasofti.ro.bizframework.core.orm.Page;
import com.icss.regiegis.common.util.UserUtil;
import com.icss.regiegis.licquery.model.LicQueryQm;
import com.icss.regiegis.licquery.model.TLicRlicInfo;
import com.icss.regiegis.licquery.service.LicQueryService;
import com.icss.regiegis.licquery.service.TLicRlicInfoService;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Organization;

/**
 * <p>controller</p>
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class LicQueryController extends Controller {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private LicQueryService licQueryService;
	private TLicRlicInfoService tLicRlicInfoService;
	
	/**
	 * <p>Description: 零售户信息查询<p>
	 * @author haoyajie 2017-6-12
	 */
	public void queryLicList(LicQueryQm licQueryQm ,Page page) {
		log.info("查询零售户信息 queryLicList，请求参数：orgSeqCode: "+licQueryQm.getOrgSeqCode()+",licNo:"+licQueryQm.getLicNo()+",companyName: "+licQueryQm.getCompanyName()+", managerName: "+licQueryQm.getManagerName());
		long start = new Date().getTime();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Page pager = licQueryService.findLicListPage(licQueryQm,page);
			map.put("result",1);
			map.put("pager",pager);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", 0);
			map.put("msg", "获取零售户列表失败");
		}
		long end = new Date().getTime();
		log.info("查询零售户信息  queryLicList 结束! "+(end-start)+"毫秒");
		renderJSON(JSONObject.toJSONString(map));
	}
	
	/**
	 * <p>Description: 根据条件查询许可证信息<p>
	 * @param licQueryQm
	 * @return
	 * @author haoyajie 2017-6-14
	 */
	public void queryLicInfo(String licUuid){
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//获取人员组织机构代码
			UserInfo user = UserUtil.getUserInfo();
			if(null==user){
				throw new GeneralException("获取登录用户失败");
			}
			Organization org = user.getCurrentOrg();
			if(null==org){
				throw new GeneralException("获取用户组织机构失败");
			}
			LicQueryQm licQueryQm = new LicQueryQm();
			licQueryQm.setUnitCode(org.getOrgcode());
//			licQueryQm.setUnitCode("01345157");
			licQueryQm.setLicUuid(licUuid);
			licQueryQm = licQueryService.findLicInfoQm(licQueryQm);
			map.put("result",1);
			map.put("licInfo",licQueryQm);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("result", 0);
			map.put("msg", "获取零售户信息失败");
		}
		renderJSON(JSONObject.toJSONString(map));
	}
	/**
	 * 更新零售户坐标
	 * <p>Description: <p>
	 * @param licQueryQm
	 * @author haoyajie 2017-6-21
	 */
	public void updateLicInfoLocal(LicQueryQm licQueryQm){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result = tLicRlicInfoService.updateLicInfoLocalById(licQueryQm);
			resultMap.put("result", result);
			renderJSON(JSONObject.toJSONString(resultMap));
		} catch (Exception e) {
		}
	}
	
	/**
	 * 
	 * <p>Description: 接口：根据许可证号获取许可证信息<p>
	 * @param licNos 许可证编号，多个之间用“,”隔开, 格式如 licNo1,licNo2 
	 * @author haoyajie 2017-10-19
	 */
	public void getLicInfos(String licNos){
		response().setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int result = 0;
		if(null!=licNos){
			try {
//				String [] licNoArr = licNos.split(",");
				List<TLicRlicInfo> licInfos = tLicRlicInfoService.findLicInfoList(licNos);
				resultMap.put("data", licInfos);
				result=1;
			} catch (Exception e) {
				resultMap.put("msg", e.getMessage());
			}
		}
		resultMap.put("result", result);
		renderJSON(JSONObject.toJSONString(resultMap));
	}
	
	public static void main(String[] args) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", 1);
		System.out.println("'"+JSONObject.toJSONString(resultMap)+"'");
	}
	
	public void setLicQueryService(LicQueryService licQueryService) {
		this.licQueryService = licQueryService;
	}

	public void settLicRlicInfoService(TLicRlicInfoService tLicRlicInfoService) {
		this.tLicRlicInfoService = tLicRlicInfoService;
	}

	
}