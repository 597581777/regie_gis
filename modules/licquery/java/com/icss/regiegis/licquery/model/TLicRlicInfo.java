package com.icss.regiegis.licquery.model;

import com.chinasofti.ro.bizframework.core.data.validation.IValidator;
import com.chinasofti.ro.bizframework.core.orm.entity.Model;

import java.sql.Date;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class TLicRlicInfo extends Model implements IValidator {
	private static final long serialVersionUID = 1L;
	// Fields    
	private String licUuid; 
	private String licNo; 
	private String companyName; 
	private String induCommBusinessLicenceNumber; 
	private String managerName; 
	private String idcard; 
	private String businessAddr; 
	private String isDifferHomeAddr; 
	private String supplyCompany; 
	private Date accrediDate; 
	private Date validateStart; 
	private Date validateEnd; 
	private Date deliveryDate; 
	private Date issueDate; 
	private String nowEffectStatus; 
	private String column1; 
	private String column2; 
	private String column3; 
	private String licIssuingOrg; 
	private String resalePersonId; 
	private String managerScope; 
	private String groundOwnership; 
	private Date leaseTimelimitStart; 
	private Date leaseTimelimitEnd; 
	private Integer isValidate; 
	private String issueOrgCode; 
	private String retailCidType; 
	private String retailCidNo; 
	private String retailCidAddress; 
	private String retailAddress; 
	private String contractPerson; 
	private String retailTel; 
	private String specialPersonGroup; 
	private String busiMgrLicValidity; 
	private Integer bizFormat; 
	private Integer areaType; 
	private String applicantPhoto; 
	private Short ecoType; 
	private String ecoTypeOther; 
	private String rlicAdc; 
	private String orgUuid; 
	private Timestamp createTime; 
	private Double bizArea; 
	private String isChain; 
	private String isMain; 
	private String isBusiness; 
	private String specialGroupTypeOther; 
	private String oldLicNo; 
	private String supplyCompanyCode; 
	private String agentName; 
	private String agentIdcard; 
	private Date firstAccrediDate; 
	private BigDecimal registeredCapital; 
	private Short agentIdcardType; 
	private String syncStatus; 
	private Timestamp lastModifyTime; 
	private Timestamp lastApplyTime; 
	private Timestamp lastOperateCompleteTime; 
	private Integer lastOperateType; 
	private Integer lastOperateResult; 
	private String lastOperateName; 
	private String lastOperateLogname; 
	private String remoteOrgCode; 
	private String syncTime; 
	private String clientCode; 
	private String clientName; 
	private String longitude; 
	private String latitude; 
	private String staffMemberUuid; 
	private String staffMemberName; 
	private String bizPlacePostcode; 
	private String agentTel; 
	private String isMarketIn; 
	private String marketCode; 
	private String marketName; 
	private String gridding; 
	private String firstGridding; 
	private String twoGridding; 
	private Date applyCloseBusinessDateStart; 
	private Date applyCloseBusinessDateEnd; 
	private String familyMembersNames; 
	private Timestamp lstModiTime; 
		
	//default constructor
    public TLicRlicInfo() {
    	super();
    }
    
    // Property accessors
	public String getLicUuid() {
        return this.licUuid;
    }
    
    public void setLicUuid(String licUuid) {
    	this.licUuid = licUuid;
    }
    
	public String getLicNo() {
        return this.licNo;
    }
    
    public void setLicNo(String licNo) {
    	this.firePropertyChange("licNo", this.licNo, licNo);
    	this.licNo = licNo;
    }
    
	public String getCompanyName() {
        return this.companyName;
    }
    
    public void setCompanyName(String companyName) {
    	this.firePropertyChange("companyName", this.companyName, companyName);
    	this.companyName = companyName;
    }
    
	public String getInduCommBusinessLicenceNumber() {
        return this.induCommBusinessLicenceNumber;
    }
    
    public void setInduCommBusinessLicenceNumber(String induCommBusinessLicenceNumber) {
    	this.firePropertyChange("induCommBusinessLicenceNumber", this.induCommBusinessLicenceNumber, induCommBusinessLicenceNumber);
    	this.induCommBusinessLicenceNumber = induCommBusinessLicenceNumber;
    }
    
	public String getManagerName() {
        return this.managerName;
    }
    
    public void setManagerName(String managerName) {
    	this.firePropertyChange("managerName", this.managerName, managerName);
    	this.managerName = managerName;
    }
    
	public String getIdcard() {
        return this.idcard;
    }
    
    public void setIdcard(String idcard) {
    	this.firePropertyChange("idcard", this.idcard, idcard);
    	this.idcard = idcard;
    }
    
	public String getBusinessAddr() {
        return this.businessAddr;
    }
    
    public void setBusinessAddr(String businessAddr) {
    	this.firePropertyChange("businessAddr", this.businessAddr, businessAddr);
    	this.businessAddr = businessAddr;
    }
    
	public String getIsDifferHomeAddr() {
        return this.isDifferHomeAddr;
    }
    
    public void setIsDifferHomeAddr(String isDifferHomeAddr) {
    	this.firePropertyChange("isDifferHomeAddr", this.isDifferHomeAddr, isDifferHomeAddr);
    	this.isDifferHomeAddr = isDifferHomeAddr;
    }
    
	public String getSupplyCompany() {
        return this.supplyCompany;
    }
    
    public void setSupplyCompany(String supplyCompany) {
    	this.firePropertyChange("supplyCompany", this.supplyCompany, supplyCompany);
    	this.supplyCompany = supplyCompany;
    }
    
	public Date getAccrediDate() {
        return this.accrediDate;
    }
    
    public void setAccrediDate(Date accrediDate) {
    	this.firePropertyChange("accrediDate", this.accrediDate, accrediDate);
    	this.accrediDate = accrediDate;
    }
    
	public Date getValidateStart() {
        return this.validateStart;
    }
    
    public void setValidateStart(Date validateStart) {
    	this.firePropertyChange("validateStart", this.validateStart, validateStart);
    	this.validateStart = validateStart;
    }
    
	public Date getValidateEnd() {
        return this.validateEnd;
    }
    
    public void setValidateEnd(Date validateEnd) {
    	this.firePropertyChange("validateEnd", this.validateEnd, validateEnd);
    	this.validateEnd = validateEnd;
    }
    
	public Date getDeliveryDate() {
        return this.deliveryDate;
    }
    
    public void setDeliveryDate(Date deliveryDate) {
    	this.firePropertyChange("deliveryDate", this.deliveryDate, deliveryDate);
    	this.deliveryDate = deliveryDate;
    }
    
	public Date getIssueDate() {
        return this.issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
    	this.firePropertyChange("issueDate", this.issueDate, issueDate);
    	this.issueDate = issueDate;
    }
    
	public String getNowEffectStatus() {
        return this.nowEffectStatus;
    }
    
    public void setNowEffectStatus(String nowEffectStatus) {
    	this.firePropertyChange("nowEffectStatus", this.nowEffectStatus, nowEffectStatus);
    	this.nowEffectStatus = nowEffectStatus;
    }
    
	public String getColumn1() {
        return this.column1;
    }
    
    public void setColumn1(String column1) {
    	this.firePropertyChange("column1", this.column1, column1);
    	this.column1 = column1;
    }
    
	public String getColumn2() {
        return this.column2;
    }
    
    public void setColumn2(String column2) {
    	this.firePropertyChange("column2", this.column2, column2);
    	this.column2 = column2;
    }
    
	public String getColumn3() {
        return this.column3;
    }
    
    public void setColumn3(String column3) {
    	this.firePropertyChange("column3", this.column3, column3);
    	this.column3 = column3;
    }
    
	public String getLicIssuingOrg() {
        return this.licIssuingOrg;
    }
    
    public void setLicIssuingOrg(String licIssuingOrg) {
    	this.firePropertyChange("licIssuingOrg", this.licIssuingOrg, licIssuingOrg);
    	this.licIssuingOrg = licIssuingOrg;
    }
    
	public String getResalePersonId() {
        return this.resalePersonId;
    }
    
    public void setResalePersonId(String resalePersonId) {
    	this.firePropertyChange("resalePersonId", this.resalePersonId, resalePersonId);
    	this.resalePersonId = resalePersonId;
    }
    
	public String getManagerScope() {
        return this.managerScope;
    }
    
    public void setManagerScope(String managerScope) {
    	this.firePropertyChange("managerScope", this.managerScope, managerScope);
    	this.managerScope = managerScope;
    }
    
	public String getGroundOwnership() {
        return this.groundOwnership;
    }
    
    public void setGroundOwnership(String groundOwnership) {
    	this.firePropertyChange("groundOwnership", this.groundOwnership, groundOwnership);
    	this.groundOwnership = groundOwnership;
    }
    
	public Date getLeaseTimelimitStart() {
        return this.leaseTimelimitStart;
    }
    
    public void setLeaseTimelimitStart(Date leaseTimelimitStart) {
    	this.firePropertyChange("leaseTimelimitStart", this.leaseTimelimitStart, leaseTimelimitStart);
    	this.leaseTimelimitStart = leaseTimelimitStart;
    }
    
	public Date getLeaseTimelimitEnd() {
        return this.leaseTimelimitEnd;
    }
    
    public void setLeaseTimelimitEnd(Date leaseTimelimitEnd) {
    	this.firePropertyChange("leaseTimelimitEnd", this.leaseTimelimitEnd, leaseTimelimitEnd);
    	this.leaseTimelimitEnd = leaseTimelimitEnd;
    }
    
	public Integer getIsValidate() {
        return this.isValidate;
    }
    
    public void setIsValidate(Integer isValidate) {
    	this.firePropertyChange("isValidate", this.isValidate, isValidate);
    	this.isValidate = isValidate;
    }
    
	public String getIssueOrgCode() {
        return this.issueOrgCode;
    }
    
    public void setIssueOrgCode(String issueOrgCode) {
    	this.firePropertyChange("issueOrgCode", this.issueOrgCode, issueOrgCode);
    	this.issueOrgCode = issueOrgCode;
    }
    
	public String getRetailCidType() {
        return this.retailCidType;
    }
    
    public void setRetailCidType(String retailCidType) {
    	this.firePropertyChange("retailCidType", this.retailCidType, retailCidType);
    	this.retailCidType = retailCidType;
    }
    
	public String getRetailCidNo() {
        return this.retailCidNo;
    }
    
    public void setRetailCidNo(String retailCidNo) {
    	this.firePropertyChange("retailCidNo", this.retailCidNo, retailCidNo);
    	this.retailCidNo = retailCidNo;
    }
    
	public String getRetailCidAddress() {
        return this.retailCidAddress;
    }
    
    public void setRetailCidAddress(String retailCidAddress) {
    	this.firePropertyChange("retailCidAddress", this.retailCidAddress, retailCidAddress);
    	this.retailCidAddress = retailCidAddress;
    }
    
	public String getRetailAddress() {
        return this.retailAddress;
    }
    
    public void setRetailAddress(String retailAddress) {
    	this.firePropertyChange("retailAddress", this.retailAddress, retailAddress);
    	this.retailAddress = retailAddress;
    }
    
	public String getContractPerson() {
        return this.contractPerson;
    }
    
    public void setContractPerson(String contractPerson) {
    	this.firePropertyChange("contractPerson", this.contractPerson, contractPerson);
    	this.contractPerson = contractPerson;
    }
    
	public String getRetailTel() {
        return this.retailTel;
    }
    
    public void setRetailTel(String retailTel) {
    	this.firePropertyChange("retailTel", this.retailTel, retailTel);
    	this.retailTel = retailTel;
    }
    
	public String getSpecialPersonGroup() {
        return this.specialPersonGroup;
    }
    
    public void setSpecialPersonGroup(String specialPersonGroup) {
    	this.firePropertyChange("specialPersonGroup", this.specialPersonGroup, specialPersonGroup);
    	this.specialPersonGroup = specialPersonGroup;
    }
    
	public String getBusiMgrLicValidity() {
        return this.busiMgrLicValidity;
    }
    
    public void setBusiMgrLicValidity(String busiMgrLicValidity) {
    	this.firePropertyChange("busiMgrLicValidity", this.busiMgrLicValidity, busiMgrLicValidity);
    	this.busiMgrLicValidity = busiMgrLicValidity;
    }
    
	public Integer getBizFormat() {
        return this.bizFormat;
    }
    
    public void setBizFormat(Integer bizFormat) {
    	this.firePropertyChange("bizFormat", this.bizFormat, bizFormat);
    	this.bizFormat = bizFormat;
    }
    
	public Integer getAreaType() {
        return this.areaType;
    }
    
    public void setAreaType(Integer areaType) {
    	this.firePropertyChange("areaType", this.areaType, areaType);
    	this.areaType = areaType;
    }
    
	public String getApplicantPhoto() {
        return this.applicantPhoto;
    }
    
    public void setApplicantPhoto(String applicantPhoto) {
    	this.firePropertyChange("applicantPhoto", this.applicantPhoto, applicantPhoto);
    	this.applicantPhoto = applicantPhoto;
    }
    
	public Short getEcoType() {
        return this.ecoType;
    }
    
    public void setEcoType(Short ecoType) {
    	this.firePropertyChange("ecoType", this.ecoType, ecoType);
    	this.ecoType = ecoType;
    }
    
	public String getEcoTypeOther() {
        return this.ecoTypeOther;
    }
    
    public void setEcoTypeOther(String ecoTypeOther) {
    	this.firePropertyChange("ecoTypeOther", this.ecoTypeOther, ecoTypeOther);
    	this.ecoTypeOther = ecoTypeOther;
    }
    
	public String getRlicAdc() {
        return this.rlicAdc;
    }
    
    public void setRlicAdc(String rlicAdc) {
    	this.firePropertyChange("rlicAdc", this.rlicAdc, rlicAdc);
    	this.rlicAdc = rlicAdc;
    }
    
	public String getOrgUuid() {
        return this.orgUuid;
    }
    
    public void setOrgUuid(String orgUuid) {
    	this.firePropertyChange("orgUuid", this.orgUuid, orgUuid);
    	this.orgUuid = orgUuid;
    }
    
	public Timestamp getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Timestamp createTime) {
    	this.firePropertyChange("createTime", this.createTime, createTime);
    	this.createTime = createTime;
    }
    
	public Double getBizArea() {
        return this.bizArea;
    }
    
    public void setBizArea(Double bizArea) {
    	this.firePropertyChange("bizArea", this.bizArea, bizArea);
    	this.bizArea = bizArea;
    }
    
	public String getIsChain() {
        return this.isChain;
    }
    
    public void setIsChain(String isChain) {
    	this.firePropertyChange("isChain", this.isChain, isChain);
    	this.isChain = isChain;
    }
    
	public String getIsMain() {
        return this.isMain;
    }
    
    public void setIsMain(String isMain) {
    	this.firePropertyChange("isMain", this.isMain, isMain);
    	this.isMain = isMain;
    }
    
	public String getIsBusiness() {
        return this.isBusiness;
    }
    
    public void setIsBusiness(String isBusiness) {
    	this.firePropertyChange("isBusiness", this.isBusiness, isBusiness);
    	this.isBusiness = isBusiness;
    }
    
	public String getSpecialGroupTypeOther() {
        return this.specialGroupTypeOther;
    }
    
    public void setSpecialGroupTypeOther(String specialGroupTypeOther) {
    	this.firePropertyChange("specialGroupTypeOther", this.specialGroupTypeOther, specialGroupTypeOther);
    	this.specialGroupTypeOther = specialGroupTypeOther;
    }
    
	public String getOldLicNo() {
        return this.oldLicNo;
    }
    
    public void setOldLicNo(String oldLicNo) {
    	this.firePropertyChange("oldLicNo", this.oldLicNo, oldLicNo);
    	this.oldLicNo = oldLicNo;
    }
    
	public String getSupplyCompanyCode() {
        return this.supplyCompanyCode;
    }
    
    public void setSupplyCompanyCode(String supplyCompanyCode) {
    	this.firePropertyChange("supplyCompanyCode", this.supplyCompanyCode, supplyCompanyCode);
    	this.supplyCompanyCode = supplyCompanyCode;
    }
    
	public String getAgentName() {
        return this.agentName;
    }
    
    public void setAgentName(String agentName) {
    	this.firePropertyChange("agentName", this.agentName, agentName);
    	this.agentName = agentName;
    }
    
	public String getAgentIdcard() {
        return this.agentIdcard;
    }
    
    public void setAgentIdcard(String agentIdcard) {
    	this.firePropertyChange("agentIdcard", this.agentIdcard, agentIdcard);
    	this.agentIdcard = agentIdcard;
    }
    
	public Date getFirstAccrediDate() {
        return this.firstAccrediDate;
    }
    
    public void setFirstAccrediDate(Date firstAccrediDate) {
    	this.firePropertyChange("firstAccrediDate", this.firstAccrediDate, firstAccrediDate);
    	this.firstAccrediDate = firstAccrediDate;
    }
    
	public BigDecimal getRegisteredCapital() {
        return this.registeredCapital;
    }
    
    public void setRegisteredCapital(BigDecimal registeredCapital) {
    	this.firePropertyChange("registeredCapital", this.registeredCapital, registeredCapital);
    	this.registeredCapital = registeredCapital;
    }
    
	public Short getAgentIdcardType() {
        return this.agentIdcardType;
    }
    
    public void setAgentIdcardType(Short agentIdcardType) {
    	this.firePropertyChange("agentIdcardType", this.agentIdcardType, agentIdcardType);
    	this.agentIdcardType = agentIdcardType;
    }
    
	public String getSyncStatus() {
        return this.syncStatus;
    }
    
    public void setSyncStatus(String syncStatus) {
    	this.firePropertyChange("syncStatus", this.syncStatus, syncStatus);
    	this.syncStatus = syncStatus;
    }
    
	public Timestamp getLastModifyTime() {
        return this.lastModifyTime;
    }
    
    public void setLastModifyTime(Timestamp lastModifyTime) {
    	this.firePropertyChange("lastModifyTime", this.lastModifyTime, lastModifyTime);
    	this.lastModifyTime = lastModifyTime;
    }
    
	public Timestamp getLastApplyTime() {
        return this.lastApplyTime;
    }
    
    public void setLastApplyTime(Timestamp lastApplyTime) {
    	this.firePropertyChange("lastApplyTime", this.lastApplyTime, lastApplyTime);
    	this.lastApplyTime = lastApplyTime;
    }
    
	public Timestamp getLastOperateCompleteTime() {
        return this.lastOperateCompleteTime;
    }
    
    public void setLastOperateCompleteTime(Timestamp lastOperateCompleteTime) {
    	this.firePropertyChange("lastOperateCompleteTime", this.lastOperateCompleteTime, lastOperateCompleteTime);
    	this.lastOperateCompleteTime = lastOperateCompleteTime;
    }
    
	public Integer getLastOperateType() {
        return this.lastOperateType;
    }
    
    public void setLastOperateType(Integer lastOperateType) {
    	this.firePropertyChange("lastOperateType", this.lastOperateType, lastOperateType);
    	this.lastOperateType = lastOperateType;
    }
    
	public Integer getLastOperateResult() {
        return this.lastOperateResult;
    }
    
    public void setLastOperateResult(Integer lastOperateResult) {
    	this.firePropertyChange("lastOperateResult", this.lastOperateResult, lastOperateResult);
    	this.lastOperateResult = lastOperateResult;
    }
    
	public String getLastOperateName() {
        return this.lastOperateName;
    }
    
    public void setLastOperateName(String lastOperateName) {
    	this.firePropertyChange("lastOperateName", this.lastOperateName, lastOperateName);
    	this.lastOperateName = lastOperateName;
    }
    
	public String getLastOperateLogname() {
        return this.lastOperateLogname;
    }
    
    public void setLastOperateLogname(String lastOperateLogname) {
    	this.firePropertyChange("lastOperateLogname", this.lastOperateLogname, lastOperateLogname);
    	this.lastOperateLogname = lastOperateLogname;
    }
    
	public String getRemoteOrgCode() {
        return this.remoteOrgCode;
    }
    
    public void setRemoteOrgCode(String remoteOrgCode) {
    	this.firePropertyChange("remoteOrgCode", this.remoteOrgCode, remoteOrgCode);
    	this.remoteOrgCode = remoteOrgCode;
    }
    
	public String getSyncTime() {
        return this.syncTime;
    }
    
    public void setSyncTime(String syncTime) {
    	this.firePropertyChange("syncTime", this.syncTime, syncTime);
    	this.syncTime = syncTime;
    }
    
	public String getClientCode() {
        return this.clientCode;
    }
    
    public void setClientCode(String clientCode) {
    	this.firePropertyChange("clientCode", this.clientCode, clientCode);
    	this.clientCode = clientCode;
    }
    
	public String getClientName() {
        return this.clientName;
    }
    
    public void setClientName(String clientName) {
    	this.firePropertyChange("clientName", this.clientName, clientName);
    	this.clientName = clientName;
    }
    
	public String getLongitude() {
        return this.longitude;
    }
    
    public void setLongitude(String longitude) {
    	this.firePropertyChange("longitude", this.longitude, longitude);
    	this.longitude = longitude;
    }
    
	public String getLatitude() {
        return this.latitude;
    }
    
    public void setLatitude(String latitude) {
    	this.firePropertyChange("latitude", this.latitude, latitude);
    	this.latitude = latitude;
    }
    
	public String getStaffMemberUuid() {
        return this.staffMemberUuid;
    }
    
    public void setStaffMemberUuid(String staffMemberUuid) {
    	this.firePropertyChange("staffMemberUuid", this.staffMemberUuid, staffMemberUuid);
    	this.staffMemberUuid = staffMemberUuid;
    }
    
	public String getStaffMemberName() {
        return this.staffMemberName;
    }
    
    public void setStaffMemberName(String staffMemberName) {
    	this.firePropertyChange("staffMemberName", this.staffMemberName, staffMemberName);
    	this.staffMemberName = staffMemberName;
    }
    
	public String getBizPlacePostcode() {
        return this.bizPlacePostcode;
    }
    
    public void setBizPlacePostcode(String bizPlacePostcode) {
    	this.firePropertyChange("bizPlacePostcode", this.bizPlacePostcode, bizPlacePostcode);
    	this.bizPlacePostcode = bizPlacePostcode;
    }
    
	public String getAgentTel() {
        return this.agentTel;
    }
    
    public void setAgentTel(String agentTel) {
    	this.firePropertyChange("agentTel", this.agentTel, agentTel);
    	this.agentTel = agentTel;
    }
    
	public String getIsMarketIn() {
        return this.isMarketIn;
    }
    
    public void setIsMarketIn(String isMarketIn) {
    	this.firePropertyChange("isMarketIn", this.isMarketIn, isMarketIn);
    	this.isMarketIn = isMarketIn;
    }
    
	public String getMarketCode() {
        return this.marketCode;
    }
    
    public void setMarketCode(String marketCode) {
    	this.firePropertyChange("marketCode", this.marketCode, marketCode);
    	this.marketCode = marketCode;
    }
    
	public String getMarketName() {
        return this.marketName;
    }
    
    public void setMarketName(String marketName) {
    	this.firePropertyChange("marketName", this.marketName, marketName);
    	this.marketName = marketName;
    }
    
	public String getGridding() {
        return this.gridding;
    }
    
    public void setGridding(String gridding) {
    	this.firePropertyChange("gridding", this.gridding, gridding);
    	this.gridding = gridding;
    }
    
	public String getFirstGridding() {
        return this.firstGridding;
    }
    
    public void setFirstGridding(String firstGridding) {
    	this.firePropertyChange("firstGridding", this.firstGridding, firstGridding);
    	this.firstGridding = firstGridding;
    }
    
	public String getTwoGridding() {
        return this.twoGridding;
    }
    
    public void setTwoGridding(String twoGridding) {
    	this.firePropertyChange("twoGridding", this.twoGridding, twoGridding);
    	this.twoGridding = twoGridding;
    }
    
	public Date getApplyCloseBusinessDateStart() {
        return this.applyCloseBusinessDateStart;
    }
    
    public void setApplyCloseBusinessDateStart(Date applyCloseBusinessDateStart) {
    	this.firePropertyChange("applyCloseBusinessDateStart", this.applyCloseBusinessDateStart, applyCloseBusinessDateStart);
    	this.applyCloseBusinessDateStart = applyCloseBusinessDateStart;
    }
    
	public Date getApplyCloseBusinessDateEnd() {
        return this.applyCloseBusinessDateEnd;
    }
    
    public void setApplyCloseBusinessDateEnd(Date applyCloseBusinessDateEnd) {
    	this.firePropertyChange("applyCloseBusinessDateEnd", this.applyCloseBusinessDateEnd, applyCloseBusinessDateEnd);
    	this.applyCloseBusinessDateEnd = applyCloseBusinessDateEnd;
    }
    
	public String getFamilyMembersNames() {
        return this.familyMembersNames;
    }
    
    public void setFamilyMembersNames(String familyMembersNames) {
    	this.firePropertyChange("familyMembersNames", this.familyMembersNames, familyMembersNames);
    	this.familyMembersNames = familyMembersNames;
    }
    
	public Timestamp getLstModiTime() {
        return this.lstModiTime;
    }
    
    public void setLstModiTime(Timestamp lstModiTime) {
    	this.firePropertyChange("lstModiTime", this.lstModiTime, lstModiTime);
    	this.lstModiTime = lstModiTime;
    }
    
   	public boolean validate(Object arg0) {
		//TLicRlicInfo tLicRlicInfo = (TLicRlicInfo)arg0;
		//Validation.required("tLicRlicInfo.licUuid",tLicRlicInfo.getLicUuid());
		return true;
	}
}