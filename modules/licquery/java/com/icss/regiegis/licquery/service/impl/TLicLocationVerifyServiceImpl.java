package com.icss.regiegis.licquery.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.chinasofti.ro.bizframework.core.libs.DateTimeUtil;
import com.chinasofti.ro.bizframework.core.libs.StringUtil;
import com.chinasofti.ro.bizframework.core.orm.IDAO;
import com.chinasofti.ro.bizframework.core.orm.Page;
import com.icss.regiegis.common.model.Employee;
import com.icss.regiegis.common.service.UserService;
import com.icss.regiegis.common.util.UserUtil;
import com.icss.regiegis.licquery.model.LicQueryQm;
import com.icss.regiegis.licquery.model.TLicLocationVerify;
import com.icss.regiegis.licquery.model.TLicRlicInfo;
import com.icss.regiegis.licquery.service.TLicLocationVerifyService;
import com.icss.regiegis.licquery.service.TLicRlicInfoService;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.util.UUIDUtil;

/**
 * <p>Service Impl</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class TLicLocationVerifyServiceImpl implements TLicLocationVerifyService {
	private IDAO dao;
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	private TLicRlicInfoService tLicRlicInfoService;
	private UserService userService;
	
	@Override
	public Page getLicVerifyList(TLicLocationVerify tLicLocationVerify, Page page) {
		try {
			if(StringUtil.isEmpty(tLicLocationVerify.getLicNo())){
				tLicLocationVerify.setLicNo(null);
			}
			if(StringUtil.isEmpty(tLicLocationVerify.getManagerName())){
				tLicLocationVerify.setManagerName(null);
			}
			if(StringUtil.isEmpty(tLicLocationVerify.getCompanyName())){
				tLicLocationVerify.setCompanyName(null);
			}
			String sql = tLicLocationVerify.getSql("queryList", dao.dbMeta());
			page = dao.createNamedQuery(TLicLocationVerify.class, sql, tLicLocationVerify).fetch(page);
			return page;
		} catch (Exception e) {
		}
		return null;
	}

	@Override
	public int saveLIcLocationApplyInfo(TLicLocationVerify licLocationVerify) {
		if(null==licLocationVerify){
			return 0;
		}
		try {
			//查询许可证信息
			String licUuid = licLocationVerify.getLicUuid();
			TLicRlicInfo tLicRlicInfo = tLicRlicInfoService.findLicInfoById(licUuid);
			//保存申请信息
			String uuid = UUIDUtil.getUUID();
			licLocationVerify.settId(uuid);
			licLocationVerify.setLicNo(tLicRlicInfo.getLicNo());
			//原始坐标
			licLocationVerify.setOldLongitude(tLicRlicInfo.getLongitude());
			licLocationVerify.setOldLatitude(tLicRlicInfo.getLatitude());
			//获取当前用户信息
			UserInfo user = UserUtil.getUserInfo();
			licLocationVerify.setApplyPersonUuid(user.getPersonUuid());
			licLocationVerify.setApplyPersonName(user.getName());
			licLocationVerify.setOrgCode(tLicRlicInfo.getIssueOrgCode());
			licLocationVerify.setApplyTime(DateTimeUtil.nowTimestamp());
			licLocationVerify.setApproveStatus(0);
			dao.save(licLocationVerify);
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	public TLicLocationVerify findLicLocationVerifyInfoById(String tId){
		if(null==tId){
			return null;
		}
		try {
			TLicLocationVerify tLicLocaltionVerify = new TLicLocationVerify();
			tLicLocaltionVerify.settId(tId);
			String sql = tLicLocaltionVerify.getSql("queryById",dao.dbMeta());
			tLicLocaltionVerify = (TLicLocationVerify)dao.createNamedQuery(TLicLocationVerify.class, sql, tLicLocaltionVerify).first();
			return tLicLocaltionVerify;
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public int approveLicLocal(String tid) {
		int result = 0;
		try {
			//查询零售户坐标修改申请记录
			TLicLocationVerify tLicLocaltionVerify = findLicLocationVerifyInfoById(tid);
			if(null!=tLicLocaltionVerify){
				tLicLocaltionVerify.setApproveStatus(1);
				UserInfo userInfo = UserUtil.getUserInfo();
				tLicLocaltionVerify.setApprovePersonName(userInfo.getName());
				tLicLocaltionVerify.setApprovePersonUuid(userInfo.getPersonUuid());
				tLicLocaltionVerify.setApproveTime(DateTimeUtil.nowTimestamp());
				dao.update(tLicLocaltionVerify);
				//更新零售户坐标
				LicQueryQm licQueryQm = new LicQueryQm();
				TLicRlicInfo tLicRlicInfo = tLicRlicInfoService.findLicInfoByLicNo(tLicLocaltionVerify.getLicNo());
				licQueryQm.setLicUuid(tLicRlicInfo.getLicUuid());
				licQueryQm.setLongitude(tLicLocaltionVerify.getLongitude());
				licQueryQm.setLatitude(tLicLocaltionVerify.getLatitude());
				tLicRlicInfoService.updateLicInfoLocalById(licQueryQm);
				result = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	@Override
	public Map<String, Object> licLocalModifyApply(String licNo,
			String personUuid, String oldLng, String oldLat, String newLng,
			String newLat) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(StringUtil.isEmpty(licNo)){
				throw new RuntimeException("licNo为空");
			}
			if(StringUtil.isEmpty(personUuid)){
				throw new RuntimeException("personUuid为空");
			}
			if(StringUtil.isEmpty(oldLng)){
				throw new RuntimeException("oldLng为空");
			}
			if(StringUtil.isEmpty(oldLat)){
				throw new RuntimeException("oldLat为空");
			}
			if(StringUtil.isEmpty(newLng)){
				throw new RuntimeException("newLng为空");
			}
			if(StringUtil.isEmpty(newLat)){
				throw new RuntimeException("newLat为空");
			}
			TLicLocationVerify tLicLocaltionVerify = new TLicLocationVerify();
			tLicLocaltionVerify.setLicNo(licNo);
			tLicLocaltionVerify.setOldLongitude(oldLng);
			tLicLocaltionVerify.setOldLatitude(oldLat);
			tLicLocaltionVerify.setLongitude(newLng);
			tLicLocaltionVerify.setLatitude(newLat);
			tLicLocaltionVerify.settId(UUIDUtil.getUUID());
			
			//获取用户信息
			Employee employee = userService.getEmployeeByPersonUuid(personUuid);
			if(null!=employee){
				tLicLocaltionVerify.setApplyPersonUuid(employee.getEmployeeUuid());
				tLicLocaltionVerify.setApplyPersonName(employee.getEmployeeName());
				tLicLocaltionVerify.setOrgCode(employee.getUnitOrgCode());
			}
			tLicLocaltionVerify.setApplyTime(DateTimeUtil.nowTimestamp());
			tLicLocaltionVerify.setApproveStatus(0);
			dao.save(tLicLocaltionVerify);
			map.put("result", 1);
		} catch (Exception e) {
			map.put("result", 0);
			map.put("msg", e.getMessage());
		}
		return map;
	}
	
	public void settLicRlicInfoService(TLicRlicInfoService tLicRlicInfoService) {
		this.tLicRlicInfoService = tLicRlicInfoService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}