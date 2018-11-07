package com.icss.regiegis.licquery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.exceptions.GeneralException;
import com.chinasofti.ro.bizframework.core.libs.StringUtil;
import com.chinasofti.ro.bizframework.core.orm.IDAO;
import com.icss.regiegis.licquery.model.LicQueryQm;
import com.icss.regiegis.licquery.model.TLicRlicInfo;
import com.icss.regiegis.licquery.service.TLicRlicInfoService;

/**
 * <p>Service Impl</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class TLicRlicInfoServiceImpl implements TLicRlicInfoService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private IDAO dao;
	
	@Override
	public TLicRlicInfo findLicInfoById(String licUuid) {
		// TODO Auto-generated method stub
		if(!StringUtil.isEmpty(licUuid)){
			try {
				TLicRlicInfo tLicRlicInfo = new TLicRlicInfo();
				tLicRlicInfo.setLicUuid(licUuid);
				return (TLicRlicInfo)dao.createQuery(tLicRlicInfo).first();
			} catch (Exception e) {
				log.error("========查询零售户信息异常=========findLicInfoById，licUuid: "+licUuid);
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public int updateLicInfoLocalById(LicQueryQm licQueryQm) {
		int result = 0;
		try {
			if(StringUtil.isNotEmpty(licQueryQm.getLicUuid())){
				//查询许可证信息是否存在
				TLicRlicInfo tlicRlicInfo=findLicInfoById(licQueryQm.getLicUuid());
				if(null==tlicRlicInfo){
					throw new GeneralException("查询零售户信息为null，licUuid: "+licQueryQm.getLicUuid());
				}
				tlicRlicInfo.setLongitude(licQueryQm.getLongitude());
				tlicRlicInfo.setLatitude(licQueryQm.getLatitude());
				dao.update(tlicRlicInfo);
				result=1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("=============更新零售户位置失败======updateLicInfoLocal=======");
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public TLicRlicInfo findLicInfoByLicNo(String licNo) {
		if(StringUtil.isEmpty(licNo)){
			return null;
		}
		try {
			TLicRlicInfo tLicRlicInfo = new TLicRlicInfo();
			tLicRlicInfo.setLicNo(licNo);
			String sql = tLicRlicInfo.getSql("queryByLicNo",dao.dbMeta());
			tLicRlicInfo = (TLicRlicInfo)dao.createNamedQuery(TLicRlicInfo.class, sql, tLicRlicInfo).first();
			return tLicRlicInfo;
		} catch (Exception e) {
			return null;
		}
	}
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<TLicRlicInfo> findLicInfoList(String licNos) {
		Map<String, Object> args = new HashMap<String, Object>();
		String sql = new TLicRlicInfo().getSql("queryLicInfos", dao.dbMeta());
		String lics[] = licNos.split(",");
		args.put("licNos", lics);
		@SuppressWarnings("unchecked")
		List<TLicRlicInfo> list = dao.createNamedQuery(TLicRlicInfo.class, sql, args).list();
		return list;
	}

}