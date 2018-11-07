package com.icss.regiegis.common.service.impl;

import com.chinasofti.ro.bizframework.core.libs.StringUtil;
import com.chinasofti.ro.bizframework.core.orm.IDAO;
import com.icss.regiegis.common.model.Employee;
import com.icss.regiegis.common.model.PersonModel;
import com.icss.regiegis.common.service.UserService;

/**
 * <p>Service Impl</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
	private IDAO dao;
	
	public PersonModel getPersonModel(String personUuid){
		if(null!=personUuid && !StringUtil.isEmpty(personUuid)){
			PersonModel personModel = new PersonModel();
			personModel.setPersonuuid(personUuid);
			String sql = personModel.getSql("findPersonByUuid",dao.dbMeta());
			return (PersonModel)dao.createNamedQuery(PersonModel.class, sql, personModel).first();
		}
		return null;
	}
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	@Override
	public Employee getEmployeeByPersonUuid(String personUuid) {
		Employee employee = new Employee();
		employee.setEmployeeUuid(personUuid);
		String sql = employee.getSql("getUserInfoByUuid", dao.dbMeta());
		employee = (Employee) dao.createQuery(Employee.class, sql,
				new String[] {}).first();
		if (null == employee) {
			throw new RuntimeException("根据UUID未获取到人员信息");
		}
		return employee;
	}

}