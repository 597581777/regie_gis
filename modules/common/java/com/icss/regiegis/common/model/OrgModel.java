package com.icss.regiegis.common.model;

import com.chinasofti.ro.bizframework.core.data.validation.IValidator;
import com.chinasofti.ro.bizframework.core.orm.entity.Model;

/**
 * <p>Title: </p>
 * <p>Description: 组织机构</p>
 * <p>Copyright: Copyright ICSS(c) 2016</p>
 * @author <a href="mailTo:haoyajie@chinasofti.com">haoyajie</a>
 * @version 1.0
 * @history:
 * Created by haoyajie 2017-7-19
 */
public class OrgModel  extends Model implements IValidator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6033417076802834455L;

	private String orglevelcode;
	private String orgCode;
	private String orgName;
	private String aliasName;
	private String parentOrgCode;
	private String orgType;
	private String orgTypeName;
	private String orgLevel;
	private String orgLevelName;
	private String isRegieDept;
	private String isRegieDeptName;
	private Integer seq;
	private String gridCode;
	
	//查询网格需要
	private String personUuid;
	private String deptCode;
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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgTypeName() {
		return orgTypeName;
	}

	public void setOrgTypeName(String orgTypeName) {
		this.orgTypeName = orgTypeName;
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

	public String getIsRegieDept() {
		return isRegieDept;
	}

	public void setIsRegieDept(String isRegieDept) {
		this.isRegieDept = isRegieDept;
	}

	public String getIsRegieDeptName() {
		return isRegieDeptName;
	}

	public void setIsRegieDeptName(String isRegieDeptName) {
		this.isRegieDeptName = isRegieDeptName;
	}
	public String getGridCode() {
		return gridCode;
	}
	public void setGridCode(String gridCode) {
		this.gridCode = gridCode;
	}

	public String getPersonUuid() {
		return personUuid;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public void setPersonUuid(String personUuid) {
		this.personUuid = personUuid;
	}
}
