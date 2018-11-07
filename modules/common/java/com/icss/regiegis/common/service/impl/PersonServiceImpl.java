package com.icss.regiegis.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.orm.IDAO;
import com.chinasofti.ro.bizframework.core.orm.Page;
import com.icss.regiegis.common.enums.PersonStationType;
import com.icss.regiegis.common.model.PersonModel;
import com.icss.regiegis.common.service.PersonService;

/**
 * <p>Service Impl</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class PersonServiceImpl implements PersonService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private IDAO dao;
	
	@Override
	public Page getSgyPersonList(String deptCode, Page page) {
		PersonModel personModel = new PersonModel();
		personModel.setDeptCode(deptCode);
		personModel.setRoleCode(PersonStationType.SGY.getValue());
		String sql = personModel.getSql("findSGY", dao.dbMeta());
		page = dao.createNamedQuery(personModel.getClass(), sql, personModel).fetch(page);
		return page;
	}
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

}