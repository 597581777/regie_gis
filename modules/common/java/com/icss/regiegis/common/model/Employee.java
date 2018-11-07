package com.icss.regiegis.common.model;

import java.sql.Date;

import com.chinasofti.ro.bizframework.core.data.validation.IValidator;
import com.chinasofti.ro.bizframework.core.orm.entity.Model;

/**
 * <p>Title：员工基础信息 </p>
 * <p>Description： 员工基础信息</p>
 * <p>Copyright： Copyright ICSS(c) 2015</p>
 * @author <a href="mailTo:sunquan@chinasofti.com">sunquan</a>
 * @version 1.0
 * @history：
 * modify by sunquan 2015-7-4
 */
public class Employee extends Model implements IValidator {
	// Fields    
	private String employeeUuid; //人员uuid
	private String employeeMainJob; 
	private String employeeSecondJob; 
	private Short mainJobType; 
	private String employeeUnit; //单位编码  对应组织机构T_TM_ORG_BASE_INFO表中orgUuid   代表专卖局
	private String employeeDept; //部门编码 对应组织机构T_TM_ORG_BASE_INFO表中orgUuid   代表专卖局下某部门
	private String employeeName; //姓名
	private Short employeeSex; 
	private Date employeeBirthday; 
	private Short employeeNation; 
	private String nativePlace; 
	private Short employeeDegree; 
	private String idCardNo; 
	private String mobilePhone; 
	/**回显电话**/
	private String phoneNo; //联系电话
	private String emailAdress; 
	private Short staffingType; 
	private Date workBeginDate; 
	private Short effectiveStatus; 
	private Date inspectionCertPeriodS; 
	/***检查证号*/
	private String inspectionCertNo;
	/***执法证号*/
	private String leCertNo;  //执法证号
	private Date leCertPeriodS; 
	private Date leCertPeriodE; 
	private Date monopolyBeginDate; 
	private String inspectionRegion; 		//检查地区
	private String issuingAuthorityName;  //执法人员执法证的发证机关名称
	private Short curSkillLevel; 
	private Date curSkillDate; 
	private Short curEngageLevel; 
	private Date curEngageDate; 
	private String employeePhoto; 
	private String employeePhotoFileName; 
	private Long infTimestamp; 
	private String infLastModifier; 
	private Date inspectionCertPeriodE; 
    private String orgAddrAdrPro;//省编码
    private String orgAddrAdrCity;//市级编码
	
	// QueryOnlyFields
	private String employeeSexName;
	private String employeeMainJobName;
	private String unitCode;	//单位排序编码
	private String deptCode;	//部门排序编码
	/**回显单位名称**/
	private String unitName;	//单位名称  即所属专卖局
	private String deptName;	//部门名称
	private String effectiveStatusName;
	private String onlyUnit;
	private String deptOrgCode; //部门组织机构编码
	private String unitOrgCode; //单位组织机构编码
	private String countrycode;  //县行政区划编码
	private String countryname;  //县行政区划名称
	private String admdivcode; 	 //市行政区划编码
	private String admdivname; 	 //市行政区划名称
	private String procode; 	 //省行政区划编码
	private String proname;		 //省行政区划名称
	private String skillLevelName; 	 //当前技能等级
	private String engageLevelName;		 //当前聘任等级
	private Integer unitRank;//单位级别   对应T_TM_ORG_BASE_INFO中的orgRank
	private String roleCode;//角色代码
	
	
	//default constructor
    public Employee() {
    	super();
    }
    
    // Property accessors
	public String getEmployeeUuid() {
        return this.employeeUuid;
    }
    
    public void setEmployeeUuid(String employeeUuid) {
    	this.employeeUuid = employeeUuid;
    }
    
	public String getEmployeeMainJob() {
        return this.employeeMainJob;
    }
    
    public void setEmployeeMainJob(String employeeMainJob) {
    	this.firePropertyChange("employeeMainJob", this.employeeMainJob, employeeMainJob);
    	this.employeeMainJob = employeeMainJob;
    }
    
	public String getEmployeeSecondJob() {
        return this.employeeSecondJob;
    }
    
    public void setEmployeeSecondJob(String employeeSecondJob) {
    	this.firePropertyChange("employeeSecondJob", this.employeeSecondJob, employeeSecondJob);
    	this.employeeSecondJob = employeeSecondJob;
    }
    
	public Short getMainJobType() {
        return this.mainJobType;
    }
    
    public void setMainJobType(Short mainJobType) {
    	this.firePropertyChange("mainJobType", this.mainJobType, mainJobType);
    	this.mainJobType = mainJobType;
    }
    
	public String getEmployeeUnit() {
        return this.employeeUnit;
    }
    
    public void setEmployeeUnit(String employeeUnit) {
    	this.firePropertyChange("employeeUnit", this.employeeUnit, employeeUnit);
    	this.employeeUnit = employeeUnit;
    }
    
	public String getEmployeeDept() {
        return this.employeeDept;
    }
    
    public void setEmployeeDept(String employeeDept) {
    	this.firePropertyChange("employeeDept", this.employeeDept, employeeDept);
    	this.employeeDept = employeeDept;
    }
    
	public String getEmployeeName() {
        return this.employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
    	this.firePropertyChange("employeeName", this.employeeName, employeeName);
    	this.employeeName = employeeName;
    }
    
	public Short getEmployeeSex() {
        return this.employeeSex;
    }
    
    public void setEmployeeSex(Short employeeSex) {
    	this.firePropertyChange("employeeSex", this.employeeSex, employeeSex);
    	this.employeeSex = employeeSex;
    	if(employeeSex != null){
    		if(employeeSex == 1){
    			this.employeeSexName = "男";
    		}else if(employeeSex == 2){
    			this.employeeSexName = "女";
    		}else{
    			this.employeeSexName = "";
    		}
		}
    }
    
	public Date getEmployeeBirthday() {
        return this.employeeBirthday;
    }
    
    public void setEmployeeBirthday(Date employeeBirthday) {
    	this.firePropertyChange("employeeBirthday", this.employeeBirthday, employeeBirthday);
    	this.employeeBirthday = employeeBirthday;
    }
    
	public Short getEmployeeNation() {
        return this.employeeNation;
    }
    
    public void setEmployeeNation(Short employeeNation) {
    	this.firePropertyChange("employeeNation", this.employeeNation, employeeNation);
    	this.employeeNation = employeeNation;
    }
    
	public String getNativePlace() {
        return this.nativePlace;
    }
    
    public void setNativePlace(String nativePlace) {
    	this.firePropertyChange("nativePlace", this.nativePlace, nativePlace);
    	this.nativePlace = nativePlace;
    }
    
	public Short getEmployeeDegree() {
        return this.employeeDegree;
    }
    
    public void setEmployeeDegree(Short employeeDegree) {
    	this.firePropertyChange("employeeDegree", this.employeeDegree, employeeDegree);
    	this.employeeDegree = employeeDegree;
    }
    
	public String getIdCardNo() {
        return this.idCardNo;
    }
    
    public void setIdCardNo(String idCardNo) {
    	this.firePropertyChange("idCardNo", this.idCardNo, idCardNo);
    	this.idCardNo = idCardNo;
    }
    
	public String getMobilePhone() {
        return this.mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone) {
    	this.firePropertyChange("mobilePhone", this.mobilePhone, mobilePhone);
    	this.mobilePhone = mobilePhone;
    }
    /**回显电话**/
	public String getPhoneNo() {
        return this.phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
    	this.firePropertyChange("phoneNo", this.phoneNo, phoneNo);
    	this.phoneNo = phoneNo;
    }
    
	public String getEmailAdress() {
        return this.emailAdress;
    }
    
    public void setEmailAdress(String emailAdress) {
    	this.firePropertyChange("emailAdress", this.emailAdress, emailAdress);
    	this.emailAdress = emailAdress;
    }
    
	public Short getStaffingType() {
        return this.staffingType;
    }
    
    public void setStaffingType(Short staffingType) {
    	this.firePropertyChange("staffingType", this.staffingType, staffingType);
    	this.staffingType = staffingType;
    }
    
	public Date getWorkBeginDate() {
        return this.workBeginDate;
    }
    
    public void setWorkBeginDate(Date workBeginDate) {
    	this.firePropertyChange("workBeginDate", this.workBeginDate, workBeginDate);
    	this.workBeginDate = workBeginDate;
    }
    
	public Short getEffectiveStatus() {
        return this.effectiveStatus;
    }
    
    public void setEffectiveStatus(Short effectiveStatus) {
    	this.firePropertyChange("effectiveStatus", this.effectiveStatus, effectiveStatus);
    	this.effectiveStatus = effectiveStatus;
    	if(effectiveStatus != null){
    		if(effectiveStatus.intValue() == 1){
    			this.effectiveStatusName = "启用";
    		}else{
    			this.effectiveStatusName = "停用";
    		}
		}
    }
    
	public Date getInspectionCertPeriodS() {
        return this.inspectionCertPeriodS;
    }
    
    public void setInspectionCertPeriodS(Date inspectionCertPeriodS) {
    	this.firePropertyChange("inspectionCertPeriodS", this.inspectionCertPeriodS, inspectionCertPeriodS);
    	this.inspectionCertPeriodS = inspectionCertPeriodS;
    }
    
	public String getInspectionCertNo() {
        return this.inspectionCertNo;
    }
    
    public void setInspectionCertNo(String inspectionCertNo) {
    	this.firePropertyChange("inspectionCertNo", this.inspectionCertNo, inspectionCertNo);
    	this.inspectionCertNo = inspectionCertNo;
    }
    /***执法证号*/
	public String getLeCertNo() {
        return this.leCertNo;
    }
    
    public void setLeCertNo(String leCertNo) {
    	this.firePropertyChange("leCertNo", this.leCertNo, leCertNo);
    	this.leCertNo = leCertNo;
    }
    
	public Date getLeCertPeriodS() {
        return this.leCertPeriodS;
    }
    
    public void setLeCertPeriodS(Date leCertPeriodS) {
    	this.firePropertyChange("leCertPeriodS", this.leCertPeriodS, leCertPeriodS);
    	this.leCertPeriodS = leCertPeriodS;
    }
    
	public Date getLeCertPeriodE() {
        return this.leCertPeriodE;
    }
    
    public void setLeCertPeriodE(Date leCertPeriodE) {
    	this.firePropertyChange("leCertPeriodE", this.leCertPeriodE, leCertPeriodE);
    	this.leCertPeriodE = leCertPeriodE;
    }
    
	public Date getMonopolyBeginDate() {
        return this.monopolyBeginDate;
    }
    
    public void setMonopolyBeginDate(Date monopolyBeginDate) {
    	this.firePropertyChange("monopolyBeginDate", this.monopolyBeginDate, monopolyBeginDate);
    	this.monopolyBeginDate = monopolyBeginDate;
    }
    
	public String getInspectionRegion() {
        return this.inspectionRegion;
    }
    
    public void setInspectionRegion(String inspectionRegion) {
    	this.firePropertyChange("inspectionRegion", this.inspectionRegion, inspectionRegion);
    	this.inspectionRegion = inspectionRegion;
    }
    
	public String getIssuingAuthorityName() {
        return this.issuingAuthorityName;
    }
    
    public void setIssuingAuthorityName(String issuingAuthorityName) {
    	this.firePropertyChange("issuingAuthorityName", this.issuingAuthorityName, issuingAuthorityName);
    	this.issuingAuthorityName = issuingAuthorityName;
    }
    
	public Short getCurSkillLevel() {
        return this.curSkillLevel;
    }
    
    public void setCurSkillLevel(Short curSkillLevel) {
    	this.firePropertyChange("curSkillLevel", this.curSkillLevel, curSkillLevel);
    	this.curSkillLevel = curSkillLevel;
    }
    
	public Date getCurSkillDate() {
        return this.curSkillDate;
    }
    
    public void setCurSkillDate(Date curSkillDate) {
    	this.firePropertyChange("curSkillDate", this.curSkillDate, curSkillDate);
    	this.curSkillDate = curSkillDate;
    }
    
	public Short getCurEngageLevel() {
        return this.curEngageLevel;
    }
    
    public void setCurEngageLevel(Short curEngageLevel) {
    	this.firePropertyChange("curEngageLevel", this.curEngageLevel, curEngageLevel);
    	this.curEngageLevel = curEngageLevel;
    }
    
	public Date getCurEngageDate() {
        return this.curEngageDate;
    }
    
    public void setCurEngageDate(Date curEngageDate) {
    	this.firePropertyChange("curEngageDate", this.curEngageDate, curEngageDate);
    	this.curEngageDate = curEngageDate;
    }
    
	public String getEmployeePhoto() {
        return this.employeePhoto;
    }
    
    public void setEmployeePhoto(String employeePhoto) {
    	this.firePropertyChange("employeePhoto", this.employeePhoto, employeePhoto);
    	this.employeePhoto = employeePhoto;
    }
    
	public String getEmployeePhotoFileName() {
        return this.employeePhotoFileName;
    }
    
    public void setEmployeePhotoFileName(String employeePhotoFileName) {
    	this.firePropertyChange("employeePhotoFileName", this.employeePhotoFileName, employeePhotoFileName);
    	this.employeePhotoFileName = employeePhotoFileName;
    }
    
	public Long getInfTimestamp() {
        return this.infTimestamp;
    }
    
    public void setInfTimestamp(Long infTimestamp) {
    	this.firePropertyChange("infTimestamp", this.infTimestamp, infTimestamp);
    	this.infTimestamp = infTimestamp;
    }
    
	public String getInfLastModifier() {
        return this.infLastModifier;
    }
    
    public void setInfLastModifier(String infLastModifier) {
    	this.firePropertyChange("infLastModifier", this.infLastModifier, infLastModifier);
    	this.infLastModifier = infLastModifier;
    }
    
	public Date getInspectionCertPeriodE() {
        return this.inspectionCertPeriodE;
    }
    
    public void setInspectionCertPeriodE(Date inspectionCertPeriodE) {
    	this.firePropertyChange("inspectionCertPeriodE", this.inspectionCertPeriodE, inspectionCertPeriodE);
    	this.inspectionCertPeriodE = inspectionCertPeriodE;
    }
    
	
	// QueryOnlyProperty accessors
    
    
   	public String getOrgAddrAdrPro() {
		return orgAddrAdrPro;
	}

	public void setOrgAddrAdrPro(String orgAddrAdrPro) {
		this.orgAddrAdrPro = orgAddrAdrPro;
	}

	public String getOrgAddrAdrCity() {
		return orgAddrAdrCity;
	}

	public void setOrgAddrAdrCity(String orgAddrAdrCity) {
		this.orgAddrAdrCity = orgAddrAdrCity;
	}

	public boolean validate(Object arg0) {
		//Employee employee = (Employee)arg0;
		//Validation.required("employee.employeeUuid",employee.getEmployeeUuid());
		return true;
	}

	public String getEmployeeSexName() {
		return employeeSexName;
	}

	public void setEmployeeSexName(String employeeSexName) {
		this.employeeSexName = employeeSexName;
	}

	public String getEmployeeMainJobName() {
		return employeeMainJobName;
	}

	public void setEmployeeMainJobName(String employeeMainJobName) {
		this.employeeMainJobName = employeeMainJobName;
	}

	/**回显单位名称**/
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getEffectiveStatusName() {
		return effectiveStatusName;
	}

	public void setEffectiveStatusName(String effectiveStatusName) {
		this.effectiveStatusName = effectiveStatusName;
	}

	public String getOnlyUnit() {
		return onlyUnit;
	}

	public void setOnlyUnit(String onlyUnit) {
		this.onlyUnit = onlyUnit;
	}
	
	public String getDeptOrgCode() {
		return deptOrgCode;
	}

	public void setDeptOrgCode(String deptOrgCode) {
		this.deptOrgCode = deptOrgCode;
	}

	public String getUnitOrgCode() {
		return unitOrgCode;
	}

	public void setUnitOrgCode(String unitOrgCode) {
		this.unitOrgCode = unitOrgCode;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
	}

	public String getCountryname() {
		return countryname;
	}

	public void setCountryname(String countryname) {
		this.countryname = countryname;
	}

	public String getAdmdivcode() {
		return admdivcode;
	}

	public void setAdmdivcode(String admdivcode) {
		this.admdivcode = admdivcode;
	}

	public String getAdmdivname() {
		return admdivname;
	}

	public void setAdmdivname(String admdivname) {
		this.admdivname = admdivname;
	}

	public String getProcode() {
		return procode;
	}

	public void setProcode(String procode) {
		this.procode = procode;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public String getSkillLevelName() {
		return skillLevelName;
	}

	public void setSkillLevelName(String skillLevelName) {
		this.skillLevelName = skillLevelName;
	}

	public String getEngageLevelName() {
		return engageLevelName;
	}

	public void setEngageLevelName(String engageLevelName) {
		this.engageLevelName = engageLevelName;
	}

	public Integer getUnitRank() {
		return unitRank;
	}

	public void setUnitRank(Integer unitRank) {
		this.unitRank = unitRank;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
}