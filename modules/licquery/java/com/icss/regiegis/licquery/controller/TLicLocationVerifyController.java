package com.icss.regiegis.licquery.controller;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.chinasofti.ro.bizframework.core.mvc.Controller;
import com.chinasofti.ro.bizframework.core.mvc.View;
import com.chinasofti.ro.bizframework.core.orm.Page;
import com.icss.regiegis.licquery.model.TLicLocationVerify;
import com.icss.regiegis.licquery.service.TLicLocationVerifyService;

/**
 * <p>controller</p>
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class TLicLocationVerifyController extends Controller {
	
	private TLicLocationVerifyService tLicLocationVerifyService;
	
	public void index(){
		View view = new View("success");
		this.render(view);
	}
	
	/**
	 * <p>Description: 获取零售户坐标修改申请列表<p>
	 * @param orgCode
	 * @author haoyajie 2017-10-17
	 */
	public void getLicVerifyList(TLicLocationVerify tLicLocationVerify, Page page){
		Page pager = tLicLocationVerifyService.getLicVerifyList(tLicLocationVerify, page);
		renderJSON(JSONObject.toJSONString(pager));
	}
	
	/**
	 * <p>Description: 保存坐标修改申请<p>
	 * @param licLocationVerify
	 * @author haoyajie 2017-10-17
	 */
	public void saveLicLocationApply(TLicLocationVerify licLocationVerify){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = tLicLocationVerifyService.saveLIcLocationApplyInfo(licLocationVerify);
		map.put("reuslt", result);
		renderJSON(JSONObject.toJSONString(map));
	}

	public void settLicLocationVerifyService(
			TLicLocationVerifyService tLicLocationVerifyService) {
		this.tLicLocationVerifyService = tLicLocationVerifyService;
	}
	
	/**
	 * 零售户坐标审核
	 * <p>Description: <p>
	 * @author haoyajie 2017-10-19
	 */
	public void approveLicLocal(String tid){
		Map<String, Object> map = new HashMap<String, Object>();
		int result = tLicLocationVerifyService.approveLicLocal(tid);
		map.put("result", result);
		renderJSON(JSONObject.toJSONString(map));
	}
	
	/**
	 * <p>Description: 零售户坐标修改申请接口<p>
	 * @param licNo 零售户坐标
	 * @param oldLngLats 原始坐标 (lng,lat)
	 * @param newLngLats 新坐标(lng,lat)
	 * @param personUuid 修改人id
	 * @author haoyajie 2017-10-18
	 */
	public void licLocalModifyApply(String licNo, String personUuid, String oldLng, String oldLat, String newLng,String newLat){
		response().setHeader("Access-Control-Allow-Origin", "*");
		Map<String, Object> map = tLicLocationVerifyService.licLocalModifyApply(licNo,personUuid,oldLng,oldLat,newLng,newLat);
		renderJSON(JSONObject.toJSONString(map));
	}
	
}