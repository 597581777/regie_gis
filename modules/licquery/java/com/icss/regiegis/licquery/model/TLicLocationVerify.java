package com.icss.regiegis.licquery.model;

import com.chinasofti.ro.bizframework.core.data.validation.IValidator;
import com.chinasofti.ro.bizframework.core.orm.entity.Model;

import java.sql.Timestamp;

/**
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class TLicLocationVerify extends Model implements IValidator {
	private static final long serialVersionUID = 6694475575346546561L;
	// Fields    
	private String tId; 
	private String licNo; 
	private String orgCode; 
	private String oldLongitude; 
	private String oldLatitude; 
	private String longitude; 
	private String latitude; 
	private String applyPersonUuid; 
	private String applyPersonName; 
	private Timestamp applyTime; 
	private String approvePersonUuid; 
	private String approvePersonName; 
	private Timestamp approveTime; 
	private Integer approveStatus; 
		
	private String gridding;//所属网格
	private String licUuid;//许可证编码
	private String managerName;//零售户经营者姓名
	private String companyName;//企业字号
	private String address;//零售户经营地址
	private String orgName;//所属组织机构
	private String staffMemberuuid;//市管员编号
	private String staffMembername;//市管员姓名
	private String baiCode;//所属网格code
	private String baiName;//所属网格名称
	private String applyTimeStr;
	private String approveTimeStr;
	
	
	public String getApplyTimeStr() {
		return applyTimeStr;
	}

	public void setApplyTimeStr(String applyTimeStr) {
		this.applyTimeStr = applyTimeStr;
	}

	public String getApproveTimeStr() {
		return approveTimeStr;
	}

	public void setApproveTimeStr(String approveTimeStr) {
		this.approveTimeStr = approveTimeStr;
	}

	//default constructor
    public TLicLocationVerify() {
    	super();
    }
    
    // Property accessors
	public String gettId() {
        return this.tId;
    }
    
    public void settId(String tId) {
    	this.tId = tId;
    }
    
	public String getLicNo() {
        return this.licNo;
    }
    
    public void setLicNo(String licNo) {
    	this.firePropertyChange("licNo", this.licNo, licNo);
    	this.licNo = licNo;
    }
    
	public String getOrgCode() {
        return this.orgCode;
    }
    
    public void setOrgCode(String orgCode) {
    	this.firePropertyChange("orgCode", this.orgCode, orgCode);
    	this.orgCode = orgCode;
    }
    
	public String getOldLongitude() {
        return this.oldLongitude;
    }
    
    public void setOldLongitude(String oldLongitude) {
    	this.firePropertyChange("oldLongitude", this.oldLongitude, oldLongitude);
    	this.oldLongitude = oldLongitude;
    }
    
	public String getOldLatitude() {
        return this.oldLatitude;
    }
    
    public void setOldLatitude(String oldLatitude) {
    	this.firePropertyChange("oldLatitude", this.oldLatitude, oldLatitude);
    	this.oldLatitude = oldLatitude;
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
    
	public String getApplyPersonUuid() {
        return this.applyPersonUuid;
    }
    
    public void setApplyPersonUuid(String applyPersonUuid) {
    	this.firePropertyChange("applyPersonUuid", this.applyPersonUuid, applyPersonUuid);
    	this.applyPersonUuid = applyPersonUuid;
    }
    
	public String getApplyPersonName() {
        return this.applyPersonName;
    }
    
    public void setApplyPersonName(String applyPersonName) {
    	this.firePropertyChange("applyPersonName", this.applyPersonName, applyPersonName);
    	this.applyPersonName = applyPersonName;
    }
    
	public Timestamp getApplyTime() {
        return this.applyTime;
    }
    
    public void setApplyTime(Timestamp applyTime) {
    	this.firePropertyChange("applyTime", this.applyTime, applyTime);
    	this.applyTime = applyTime;
    }
    
	public String getApprovePersonUuid() {
        return this.approvePersonUuid;
    }
    
    public void setApprovePersonUuid(String approvePersonUuid) {
    	this.firePropertyChange("approvePersonUuid", this.approvePersonUuid, approvePersonUuid);
    	this.approvePersonUuid = approvePersonUuid;
    }
    
	public String getApprovePersonName() {
        return this.approvePersonName;
    }
    
    public void setApprovePersonName(String approvePersonName) {
    	this.firePropertyChange("approvePersonName", this.approvePersonName, approvePersonName);
    	this.approvePersonName = approvePersonName;
    }
    
	public Timestamp getApproveTime() {
        return this.approveTime;
    }
    
    public void setApproveTime(Timestamp approveTime) {
    	this.firePropertyChange("approveTime", this.approveTime, approveTime);
    	this.approveTime = approveTime;
    }
    
	public Integer getApproveStatus() {
        return this.approveStatus;
    }
    
    public void setApproveStatus(Integer approveStatus) {
    	this.firePropertyChange("approveStatus", this.approveStatus, approveStatus);
    	this.approveStatus = approveStatus;
    }
    
   	public String getGridding() {
		return gridding;
	}

	public void setGridding(String gridding) {
		this.gridding = gridding;
	}

	public String getLicUuid() {
		return licUuid;
	}

	public void setLicUuid(String licUuid) {
		this.licUuid = licUuid;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStaffMemberuuid() {
		return staffMemberuuid;
	}

	public void setStaffMemberuuid(String staffMemberuuid) {
		this.staffMemberuuid = staffMemberuuid;
	}

	public String getStaffMembername() {
		return staffMembername;
	}

	public void setStaffMembername(String staffMembername) {
		this.staffMembername = staffMembername;
	}

	public String getBaiCode() {
		return baiCode;
	}

	public void setBaiCode(String baiCode) {
		this.baiCode = baiCode;
	}

	public String getBaiName() {
		return baiName;
	}

	public void setBaiName(String baiName) {
		this.baiName = baiName;
	}

	public boolean validate(Object arg0) {
		//TLicLocationVerify tLicLocationVerify = (TLicLocationVerify)arg0;
		//Validation.required("tLicLocationVerify.tId",tLicLocationVerify.gettId());
		return true;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}