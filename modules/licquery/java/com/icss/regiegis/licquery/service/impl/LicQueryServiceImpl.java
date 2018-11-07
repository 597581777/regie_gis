package com.icss.regiegis.licquery.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.libs.StringUtil;
import com.chinasofti.ro.bizframework.core.orm.IDAO;
import com.chinasofti.ro.bizframework.core.orm.Page;
import com.icss.regiegis.licquery.model.LicQueryQm;
import com.icss.regiegis.licquery.service.LicQueryService;

/**
 * <p>Service Impl</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class LicQueryServiceImpl implements LicQueryService {
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private IDAO dao;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page findLicListPage(LicQueryQm licQueryQm, Page page) {
		if(StringUtil.isEmpty(licQueryQm.getLicNo())){
			licQueryQm.setLicNo(null);
		}
		if(StringUtil.isEmpty(licQueryQm.getCompanyName())){
			licQueryQm.setCompanyName(null);
		}
		if(StringUtil.isEmpty(licQueryQm.getManagerName())){
			licQueryQm.setManagerName(null);
		}
		if(StringUtil.isEmpty(licQueryQm.getOrgSeqCode())){
			licQueryQm.setOrgSeqCode(null);
		}
		String sql = licQueryQm.getSql("licQuery", dao.dbMeta());
		page = dao.createNamedQuery(licQueryQm.getClass(), sql, licQueryQm).fetch(page);
		fetchList(page.getResult());
		return page;
	}
	
	private void fetchList(List<LicQueryQm> list){
		for (LicQueryQm licQueryQm : list) {
			if(null!=licQueryQm){
				//经营范围
//                licQueryQm.setManagerScopeStr(BaseDataUtil.getBscCodeBaseNameByValues(licQueryQm.getManagerScope(), null));
                //有效日期
                if(null!=licQueryQm.getValidateEnd()){
                	String date = new SimpleDateFormat("yyyy-MM-dd").format(licQueryQm.getValidateEnd());
                	licQueryQm.setValidateEndStr(date);
                }
			}
		}
	}
	
	@Override
	public LicQueryQm findLicInfoQm(LicQueryQm licQueryQm) {
		try {
			String sql = licQueryQm.getSql("queryLicInfo", dao.dbMeta());
			licQueryQm = (LicQueryQm)dao.createNamedQuery(licQueryQm.getClass(), sql, licQueryQm).first();
			if(null!=licQueryQm){
				//经营范围
//                licQueryQm.setManagerScopeStr(BaseDataUtil.getBscCodeBaseNameByValues(licQueryQm.getManagerScope(), null));
                //有效日期
                if(null!=licQueryQm.getValidateEnd()){
                	String date = new SimpleDateFormat("yyyy-MM-dd").format(licQueryQm.getValidateEnd());
                	licQueryQm.setValidateEndStr(date);
                }
			}
			return licQueryQm;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("查询许可证信息失败");
			e.printStackTrace();
			return null;
		}
	}
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

}