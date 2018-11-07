package com.icss.regiegis.common.model;

import com.chinasofti.ro.bizframework.core.data.validation.IValidator;
import com.chinasofti.ro.bizframework.core.orm.entity.Model;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ICSS(c) 2016</p>
 * @author <a href="mailTo:haoyajie@chinasofti.com">haoyajie</a>
 * @version 1.0
 * @history:
 * Created by haoyajie 2017-7-18
 */
public class PersonModel extends Model implements IValidator {
	
	private static final long serialVersionUID = -6508955283623538044L;
	private String personuuid;
	private String userid;
	private String password;
	private String userName;
	private String unitCode;
	private String unitName;
	private String deptCode;
	private String deptName;
	private String orgLevel;
	private String orgLevelName;
	private String idnum;
	private String sex;
	private String birthdate;
	private String officetel;
	private String mobile;
	private String email;
	private String msnqq;
	
	private String orglevelcode;
	private String unitlevelcode;
	
	private String roleCode;
	
	
	public String getPersonuuid() {
		return personuuid;
	}
	public void setPersonuuid(String personuuid) {
		this.personuuid = personuuid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUnitCode() {
		return unitCode;
	}
	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getOrgLevel() {
		return orgLevel;
	}
	public void setOrgLevel(String orgLevel) {
		this.orgLevel = orgLevel;
	}
	public String getOrgLevelName() {
		return orgLevelName;
	}
	public void setOrgLevelName(String orgLevelName) {
		this.orgLevelName = orgLevelName;
	}
	public String getIdnum() {
		return idnum;
	}
	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getOfficetel() {
		return officetel;
	}
	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMsnqq() {
		return msnqq;
	}
	public void setMsnqq(String msnqq) {
		this.msnqq = msnqq;
	}
	@Override
	public boolean validate(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	public String getOrglevelcode() {
		return orglevelcode;
	}
	public void setOrglevelcode(String orglevelcode) {
		this.orglevelcode = orglevelcode;
	}
	public String getUnitlevelcode() {
		return unitlevelcode;
	}
	public void setUnitlevelcode(String unitlevelcode) {
		this.unitlevelcode = unitlevelcode;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}
