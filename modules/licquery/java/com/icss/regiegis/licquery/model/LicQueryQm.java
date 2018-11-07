package com.icss.regiegis.licquery.model;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

import com.chinasofti.ro.bizframework.core.orm.entity.SqlModel;

/**
 * <p>SqlModel</p>
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class LicQueryQm extends SqlModel {
    /**
     * 
     */
    private static final long serialVersionUID = 1386251556588195624L;
    /**
     * 许可证统计分析汇总使用字段
     */
    // 许可证预约情况统计
    private long inHandAppointmentCount;// 代办理预约数
    private long longTimeNotAcceptCount;// 长期未办理预约数
    private long totalAppointmentCount;// 总预约数
    // 许可证在办统计
    private long licNewAcceptCountAccept;// 新办
    private long licContinueCountAccept;// 延续
    private long licChangeCountAccept;// 变更
    private long licCloseCountAccept;// 停业
    private long licRecoverBusinessCountAccept;// 恢复营业
    private long licSupplementCountAccept;// 补办
    private long licRetiredCountAccept;// 歇业
    private long licRecoverLogoutCountAccept;// 登记注销
    private long licOrderCloseCountAccept;// 责令整顿停业
    private long licOrderRecoverCountAccept;// 责令整顿恢复
    private long licApproveLogoutCountAccept;// 审批注销
    private long licBackOutCountAccept;// 撤销
    private long licRecallCountAccept;// 撤回
    // 许可证办理统计
    private long newAcceptNoAcceptCount;// 新办不予受理
    private long newAcceptPassCount;// 新办准予
    private long newAcceptNoPassCount;// 新办不予受理
    private long continueNoAcceptCount;// 延续不予受理
    private long continuePassCount;// 延续准予
    private long continueNoPassCount;// 延续不予受理
    private long changeNoAcceptCount;// 变更不予受理
    private long changePassCount;// 变更准予
    private long changeNoPassCount;// 变更不予受理
    private long closeBusinessPassCount;// 停业准予办理
    private long closeBusinessNoPassCount;// 停业不予办理
    private long supplementPassCount;// 补办准予办理
    private long supplementNoPassCount;// 补办不予办理
    private long RecoverBusinessPassCount;// 恢复营业准予办理
    private long RecoverBusinessNoPassCount;// 恢复营业不予办理
    private long RetiredPassCount;// 歇业准予办理
    private long RetiredNoPassCount;// 歇业不予办理
    private long historyTotalCount;// 办理单行统计
    // 许可证分类统计
    private long stateOwnedCount;// 国有
    private long collectiveCount;// 集体
    private long unitCount;// 个体
    private long partnershipCount;// 合伙
    private long shareholdingCount;// 股份制（合作）
    private long investmentCount;// 个人独资
    private long limitedLiabilityCount;// 有限责任
    private long foreignInvestmentCount;// 外商投资
    private long limitedLiabilityCompanyCount;// 股份有限公司
    private long othersCount;// 其他
    private long clsssifyTotalCount;// 总数
    private long cityCount;// 城镇
    private long townCount;// 乡村
    private long freeCount;// 自有
    private long rentCount;// 租赁
    private long gratisCount;// 无偿经营
    private long groceryCount;// 食杂店
    private long dimeStoreCount; // 便利店
    private long supermarketCount; // 超市
    private long marketplaceCount; // 商场
    private long tobaccoAndWineCount; // 烟酒商店
    private long recreationCount; // 娱乐服务类
    private long bizOthersCount; // 业态——其他
    private Integer orgRank; // 行政级别编号
    // 许可证综合统计
    private long atFristValidLicCount;// 期初有效证件数
    private long atLastValidLicCount;// 期末有效证件数
    private long atLastValidLicCountCity;// 期末有效证件数 城市
    private long atLastValidLicCountTown;// 期末有效证件数 乡村
    private long currentPeriodNewCount;// 本期新办证件数
    private long currentPeriodLogoutCount;// 本期注销证件数
    private Timestamp currentDate;// 系统当前时间戳
    private Timestamp defaultDataStart;// 默认统计区间开始时间
    private Timestamp defaultDataEnd;// 默认统计区间结束时间
    // 许可证预约情况统计
    private long backlogOrderNum;// 待办预约数
    private long longTimeOrderNum;// 长期未办理预约数
    private long totalOrderNum;// 总预约数
    // 许可证预约办理统计
    private long newAddOrderNum;// 新增预约数
    private long acceptOrderNum;// 办理预约数
    // 非ORM字段
    private String nowEffectStatusStr;
    private Timestamp validateStart_start;
    private Timestamp validateStart_end;
    private Timestamp validateEnd_start;
    private Timestamp validateEnd_end;
    private Timestamp applyDateStart;
    private Timestamp applyDateEnd;
    private Timestamp approveTime;//审批日期
    private String approveTimeStr;
    private String orgName;
    private String retailCidTypeStr;
    private String applyTypeStr;
    private String currentNodeStr;	//当前阶段
    private String currentNodeFlag; //判断审批注销、撤销、撤回的 审批阶段以及审查用的(因为数据库中存的CURRENT_NODE与其他类型有差异)
	private Timestamp decideDateStart;
    private Timestamp decideDateEnd;
    private String decideDateStr;// 决定日期Str
    private Integer acceptFileNum;// 文书份数
    private String acceptFileNumStr;// 页面显示的 文书份数
    private String validateStartStr;// 许可证有效期限起STR
    private String validateEndStr;// 许可证有效期限止STR
    private String managerScopeStr;// 经营范围、许可范围STR
    private String resultStatusStr;// 办理结果STR
    private String applicantPhoto;// 负责人头像

    // 预约查询字段
    private String bookingId;// 预约号
    private Timestamp bookingTime;// 预约时间
    private String docTypeStr;
    private String linkApplyMainUuid;
    private String isUsed; // 处理状态
    private String bookingTimeStr;// 预约时间  导出/显示

    // 增加的ORM字段
    private String busiMgrLicValidity;
    private Integer resultStatus;// 办理结果
    private Integer isProcessComplete;// 流程状态
    private String specialGroupTypeStr;// 特殊群体类型STR
    private Double bizArea;// 经营面积
    private String drillDirection;
    // ORM字段
    private String applyInfoUuid;
    private String licUuid;
    private String companyName;
    private String induCommBusinessLicenceNumber;
    private String managerName;
    private String idcard;
    private String companyEconomyType;
    private String businessAddr;
    private String isDifferHomeAddr;
    private String supplyCompany;
    private Date accrediDate;
    private Date validateStart;
    private Date validateEnd;
    private Date deliveryDate;
    private Date issueDate;
    private String nowEffectStatus;
    private List<Integer> nowEffectStatus2;
    private String column3;
    private String licIssuingOrg;
    private String resalePersonId;
    private String managerScope;
    private String groundOwnership;
    private Timestamp leaseTimelimitStart; // 租赁开始期限
    private Timestamp leaseTimelimitEnd; // 租赁截止期限
    private String leaseTimelimitStartText; // 租赁开始期限页面显示
    private String leaseTimelimitEndText; // 租赁截止期限页面显示
    private String licNo;
    private String licNoTemp;//查询显示许可证号--用于排除排序引起的歧义
	private String currentNode;	//当前阶段
    private Short applyType;
    private String fileCode;
    private Short isOnlineBooking;
    private Timestamp applyDeadline;
    private Short replyDecide;
    private Short docReviewIsPass;
    private Timestamp fieldAduitDeadline;
    private Short fieldAduitIsPass;
    private Timestamp fieldAduitSubmitTime;
    private Timestamp fieldAduitResultSubmitTime;
    private Short isApprove;
    private String isApproveStr;//Excel导出用
    private Short licensingDecide;
    private Timestamp firstPrintLicDate;
    private Short isHearing;
    private Timestamp hearingBeginTime;
    private Timestamp hearingEndTime;
    private String fieldAduitPhoto;
    private Short isExtend;
    private Timestamp decideDate;
    private Short isBookbuild;
    private Timestamp bookbuildTime;
    private Timestamp acceptDate;
    private Timestamp applyDate;
    private String applyDateStr;
    private String isInspect;
    private String extendDays;
    private String noPassingReason;
    private Date licValidatyStart;
    private Date licValidatyEnd;
    private String fileSeq;
    private String acceptResult;
    private String liddId;
    private String lbidId;
    private String bdtId;
    private String liddCode;
    private Integer liddIsFinish;
    private Integer liddIsOverdue;
    private Integer bcbEnumLiddStatus;
    private Integer bcbEnumLbtiStatus;
    private Integer liddPrintTimes;
    private Date liddDocDate;
    private Integer liddIsSend;
    private String personuuidTransact;
    private Date liddFinishTime;
    private Integer bcbEnumLiddFlow;
    private Integer liddPicNumber;
    private Integer liddIsAlreadyPic;
    private Integer liddIsSaveWord;
    private Integer liddIsEntering;
    private Integer bcbEnumLiddSend;
    private String liddRemark;
    private String lbtmId;
    private String accountId;
    private String onlineAppointId;
    private Short docType;
    private String entName;
    private String bizRange;
    private String storageAddrProv;
    private String storageAddrCity;
    private String storageAddrCounty;
    private String storageAddrStreet;
    private String bizAddrAdc;
    private String carryOnAddrCity;
    private String carryOnAddrCounty;
    private String bizAddrStreet;
    private Short ecoType;
    private String ecoTypeStr;// 企业经济类型文本值
    private String ecoTypeOther;
    private Short placeOwnership;
    private Date tenancyBegin;
    private Date tenancyEnd;
    private String picName;
    private Short picCidType;
    private String picCidNo;
    private String picCidAddrStreet;
    private String picAddrStreet;
    private Short specialGroupType;
    private String specialGroupTypeOther;
    private String linkName;
    private String lineTel;
    private String agentName;
    private Short agentCidType;
    private String agentIdcard;// 代理人身份证件号码
    private String applyPersonPromise;
    private String applyPersonSign;
    private String applyPersonSignDate;
    private String retailLicNo;
    private Short retailLicLoseType;
    private String isLostLicenceCopy;
    private String retailLicLoseReason;
    private String isDamageLicenceOrigi;
    private String isDamageLicenceCopy;
    private String damageRemark;
    private Short entNameIsChange;
    private String oldEntName;
    private String newEntName;
    private String isChangeCompanyName;
    private String oldCompanyName;
    private String newCompanyName;
    private Short picIsChange;
    private String oldPic;
    private String newPic;
    private Short bizAddrIsChange;
    private String oldBizAddrAdc;
    private String newBizAddrAdc;
    private String applyCode;
    private String column1;
    private String column2;
    private Integer retailCidType;
    private String retailCidNo;
    private String retailCidAddress;
    private String retailAddress;
    private String bizFormat;// 零售业态
    private List<Integer> bizFormat2;
    private String bizFormatStr;
    private Integer areaType;// 地区类别
    private String contractPerson;// 联系人
    private String retailTel;// 联系电话
    private String specialPersonGroup;// 特殊群体类型
    private String unitCode;// 组织机构ORG_SEQ_CODE
    private String orgUuid;// 组织结构主键
    private String orgSeqCode;
    private Integer childrenOrgRank;// grid中的行政级别
    private String isChain;// 是否连锁
    private String isMain;// 是否总店
    private String isBusiness;// 总店是否经营
    private String oldLicNo;// 老许可证号
    private Date firstAccrediDate;// 首次制证日期
    private String registeredCapital;// 注册资本
    // 许可证经营状态统计
    private long applicationOperating;// 初始申请
    private long normalOperating;// 正常营业
    private long applicationAndNormal;// 初始申请和正常营业
    private long closedOperating;// 停业
    private long closureOperating;// 责令整顿停业
    private long closedAndClosure;// 停业和责令整顿停业
    private long cancellationOperating;// 注销
    private long totalOperating;// 总数
    /* 许可证注销统计 */
    private long total;// 总和
    private long gooutofbusiness;// 歇业
    private long withdraw;// 撤回
    private long revoke;// 撤销
    private long licenseexpires;// 许可证到期
    private long cancelqualification;// 取消经营资格
    private long orderchangesare;// 责令变更超期
    private long expired;// 停业到期
    private long longtermoperation;// 长期未经营
    private long newmanagement;// 新办未经营
    private long other;// 其他
    /* 许可证办理时限统计 */
    private Double xb1;// 新办平均办理时长
    private Double xb2;// 新办延期办理次数
    private Double xb3;// 新办平均延期时长
    private Double xb4;// 新办平均逾期次数
    private Double xb5;// 新办平均逾期时长
    private Double yx1;// 延续平均办理时长
    private Double yx2;// 延续延期办理次数
    private Double yx3;// 延续平均延期时长
    private Double yx4;// 延续逾期办理次数
    private Double yx5;// 延续平均逾期时长
    private Double bg1;// 变更平均办理时长
    private Double bg2;// 变更延期办理次数
    private Double bg3;// 变更平均延期时长
    private Double bg4;// 变更逾期办理次数
    private Double bg5;// 变更平均逾期时长
    private Timestamp extendDateStart;// 拓展字段-时间开始
    private Timestamp extendDateEnd;// 拓展字段-时间结束
    private String placeOwnershipStr;// 经营场所权属STR
    private String areaTypeStr;// 地区类别
    private String classifyDimensionality;

    // sql动态处理标志
    private String sqlFlag01;
    private String sqlFlag02;
    private String sqlFlag03;
    private Timestamp infTimestamp;
    private Timestamp createTime;
    private Timestamp bookbuildTimeStart;//立卷开始时间
    private Timestamp bookbuildTimeEnd;//立卷结束时间
    private Integer childCount; // 2016-05-17 子组织机构数 统计使用
    private String acceptDateStr;// 2016-06-27 受理日期导出显示
    private Integer isFlowStop;//2016-08-12 流程是否终止

//    private String unitCode;   //查询条件“所属单位”
    private String orgCode;         //完善功能增加属性，当前登录人组织机构冗余
//    private String orgSeqCode;      //完善功能增加属性，组织机构排序编码  
    
    private String onlyCheckUnit;    //是否只查本单位
    
    private Timestamp lastOperateCompleteTime;    //最后办理业务完成时间
    private String lastOperateCompleteTimeStr;     //注销时间导出显示

    //网格字段
    private String gridDing;
    private String gridName;
    private String showGirdFlag;
    
    private String longitude; //经度
    private String latitude; //纬度

	public String getLicNoTemp() {
		return licNoTemp;
	}


	public void setLicNoTemp(String licNoTemp) {
		this.licNoTemp = licNoTemp;
	}


	public List<Integer> getNowEffectStatus2() {
		return nowEffectStatus2;
	}


	public void setNowEffectStatus2(List<Integer> nowEffectStatus2) {
		this.nowEffectStatus2 = nowEffectStatus2;
	}


	public List<Integer> getBizFormat2() {
		return bizFormat2;
	}


	public void setBizFormat2(List<Integer> bizFormat2) {
		this.bizFormat2 = bizFormat2;
	}


	public Timestamp getLastOperateCompleteTime() {
		return lastOperateCompleteTime;
	}


	public void setLastOperateCompleteTime(Timestamp lastOperateCompleteTime) {
		this.lastOperateCompleteTime = lastOperateCompleteTime;
	}


	public String getLastOperateCompleteTimeStr() {
		return lastOperateCompleteTimeStr;
	}


	public void setLastOperateCompleteTimeStr(String lastOperateCompleteTimeStr) {
		this.lastOperateCompleteTimeStr = lastOperateCompleteTimeStr;
	}


	public String getOnlyCheckUnit() {
		return onlyCheckUnit;
	}


	public void setOnlyCheckUnit(String onlyCheckUnit) {
		this.onlyCheckUnit = onlyCheckUnit;
	}


	public LicQueryQm() {

    }

    
    public String getOrgCode() {
		return orgCode;
	}


	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}


	public long getInHandAppointmentCount() {
        return inHandAppointmentCount;
    }

    public void setInHandAppointmentCount(long inHandAppointmentCount) {
        this.inHandAppointmentCount = inHandAppointmentCount;
    }

    public long getLongTimeNotAcceptCount() {
        return longTimeNotAcceptCount;
    }

    public void setLongTimeNotAcceptCount(long longTimeNotAcceptCount) {
        this.longTimeNotAcceptCount = longTimeNotAcceptCount;
    }

    public long getTotalAppointmentCount() {
        return totalAppointmentCount;
    }

    public void setTotalAppointmentCount(long totalAppointmentCount) {
        this.totalAppointmentCount = totalAppointmentCount;
    }

    public long getLicNewAcceptCountAccept() {
        return licNewAcceptCountAccept;
    }

    public void setLicNewAcceptCountAccept(long licNewAcceptCountAccept) {
        this.licNewAcceptCountAccept = licNewAcceptCountAccept;
    }

    public long getLicContinueCountAccept() {
        return licContinueCountAccept;
    }

    public void setLicContinueCountAccept(long licContinueCountAccept) {
        this.licContinueCountAccept = licContinueCountAccept;
    }

    public long getLicChangeCountAccept() {
        return licChangeCountAccept;
    }

    public void setLicChangeCountAccept(long licChangeCountAccept) {
        this.licChangeCountAccept = licChangeCountAccept;
    }

    public long getLicCloseCountAccept() {
        return licCloseCountAccept;
    }

    public void setLicCloseCountAccept(long licCloseCountAccept) {
        this.licCloseCountAccept = licCloseCountAccept;
    }

    public long getLicRecoverBusinessCountAccept() {
        return licRecoverBusinessCountAccept;
    }

    public void setLicRecoverBusinessCountAccept(long licRecoverBusinessCountAccept) {
        this.licRecoverBusinessCountAccept = licRecoverBusinessCountAccept;
    }

    public long getLicSupplementCountAccept() {
        return licSupplementCountAccept;
    }

    public void setLicSupplementCountAccept(long licSupplementCountAccept) {
        this.licSupplementCountAccept = licSupplementCountAccept;
    }

    public long getLicRetiredCountAccept() {
        return licRetiredCountAccept;
    }

    public void setLicRetiredCountAccept(long licRetiredCountAccept) {
        this.licRetiredCountAccept = licRetiredCountAccept;
    }

    public long getLicRecoverLogoutCountAccept() {
        return licRecoverLogoutCountAccept;
    }

    public void setLicRecoverLogoutCountAccept(long licRecoverLogoutCountAccept) {
        this.licRecoverLogoutCountAccept = licRecoverLogoutCountAccept;
    }

    public long getLicOrderCloseCountAccept() {
        return licOrderCloseCountAccept;
    }

    public void setLicOrderCloseCountAccept(long licOrderCloseCountAccept) {
        this.licOrderCloseCountAccept = licOrderCloseCountAccept;
    }

    public long getLicOrderRecoverCountAccept() {
        return licOrderRecoverCountAccept;
    }

    public void setLicOrderRecoverCountAccept(long licOrderRecoverCountAccept) {
        this.licOrderRecoverCountAccept = licOrderRecoverCountAccept;
    }

    public long getLicApproveLogoutCountAccept() {
        return licApproveLogoutCountAccept;
    }

    public void setLicApproveLogoutCountAccept(long licApproveLogoutCountAccept) {
        this.licApproveLogoutCountAccept = licApproveLogoutCountAccept;
    }

    public long getLicBackOutCountAccept() {
        return licBackOutCountAccept;
    }

    public void setLicBackOutCountAccept(long licBackOutCountAccept) {
        this.licBackOutCountAccept = licBackOutCountAccept;
    }

    public long getLicRecallCountAccept() {
        return licRecallCountAccept;
    }

    public void setLicRecallCountAccept(long licRecallCountAccept) {
        this.licRecallCountAccept = licRecallCountAccept;
    }

    public long getNewAcceptNoAcceptCount() {
        return newAcceptNoAcceptCount;
    }

    public void setNewAcceptNoAcceptCount(long newAcceptNoAcceptCount) {
        this.newAcceptNoAcceptCount = newAcceptNoAcceptCount;
    }

    public long getNewAcceptPassCount() {
        return newAcceptPassCount;
    }

    public void setNewAcceptPassCount(long newAcceptPassCount) {
        this.newAcceptPassCount = newAcceptPassCount;
    }

    public long getNewAcceptNoPassCount() {
        return newAcceptNoPassCount;
    }

    public void setNewAcceptNoPassCount(long newAcceptNoPassCount) {
        this.newAcceptNoPassCount = newAcceptNoPassCount;
    }

    public long getContinueNoAcceptCount() {
        return continueNoAcceptCount;
    }

    public void setContinueNoAcceptCount(long continueNoAcceptCount) {
        this.continueNoAcceptCount = continueNoAcceptCount;
    }

    public long getContinuePassCount() {
        return continuePassCount;
    }

    public void setContinuePassCount(long continuePassCount) {
        this.continuePassCount = continuePassCount;
    }

    public long getContinueNoPassCount() {
        return continueNoPassCount;
    }

    public void setContinueNoPassCount(long continueNoPassCount) {
        this.continueNoPassCount = continueNoPassCount;
    }

    public long getChangeNoAcceptCount() {
        return changeNoAcceptCount;
    }

    public void setChangeNoAcceptCount(long changeNoAcceptCount) {
        this.changeNoAcceptCount = changeNoAcceptCount;
    }

    public long getChangePassCount() {
        return changePassCount;
    }

    public void setChangePassCount(long changePassCount) {
        this.changePassCount = changePassCount;
    }

    public long getChangeNoPassCount() {
        return changeNoPassCount;
    }

    public void setChangeNoPassCount(long changeNoPassCount) {
        this.changeNoPassCount = changeNoPassCount;
    }

    public long getCloseBusinessPassCount() {
        return closeBusinessPassCount;
    }

    public void setCloseBusinessPassCount(long closeBusinessPassCount) {
        this.closeBusinessPassCount = closeBusinessPassCount;
    }

    public long getCloseBusinessNoPassCount() {
        return closeBusinessNoPassCount;
    }

    public void setCloseBusinessNoPassCount(long closeBusinessNoPassCount) {
        this.closeBusinessNoPassCount = closeBusinessNoPassCount;
    }

    public long getSupplementPassCount() {
        return supplementPassCount;
    }

    public void setSupplementPassCount(long supplementPassCount) {
        this.supplementPassCount = supplementPassCount;
    }

    public long getSupplementNoPassCount() {
        return supplementNoPassCount;
    }

    public void setSupplementNoPassCount(long supplementNoPassCount) {
        this.supplementNoPassCount = supplementNoPassCount;
    }

    public long getRecoverBusinessPassCount() {
        return RecoverBusinessPassCount;
    }

    public void setRecoverBusinessPassCount(long recoverBusinessPassCount) {
        RecoverBusinessPassCount = recoverBusinessPassCount;
    }

    public long getRecoverBusinessNoPassCount() {
        return RecoverBusinessNoPassCount;
    }

    public void setRecoverBusinessNoPassCount(long recoverBusinessNoPassCount) {
        RecoverBusinessNoPassCount = recoverBusinessNoPassCount;
    }

    public long getRetiredPassCount() {
        return RetiredPassCount;
    }

    public void setRetiredPassCount(long retiredPassCount) {
        RetiredPassCount = retiredPassCount;
    }

    public long getRetiredNoPassCount() {
        return RetiredNoPassCount;
    }

    public void setRetiredNoPassCount(long retiredNoPassCount) {
        RetiredNoPassCount = retiredNoPassCount;
    }

    public long getHistoryTotalCount() {
        return historyTotalCount;
    }

    public void setHistoryTotalCount(long historyTotalCount) {
        this.historyTotalCount = historyTotalCount;
    }

    public long getStateOwnedCount() {
        return stateOwnedCount;
    }

    public void setStateOwnedCount(long stateOwnedCount) {
        this.stateOwnedCount = stateOwnedCount;
    }

    public long getCollectiveCount() {
        return collectiveCount;
    }

    public void setCollectiveCount(long collectiveCount) {
        this.collectiveCount = collectiveCount;
    }

    public long getUnitCount() {
        return unitCount;
    }

    public void setUnitCount(long unitCount) {
        this.unitCount = unitCount;
    }

    public long getPartnershipCount() {
        return partnershipCount;
    }

    public void setPartnershipCount(long partnershipCount) {
        this.partnershipCount = partnershipCount;
    }

    public long getShareholdingCount() {
        return shareholdingCount;
    }

    public void setShareholdingCount(long shareholdingCount) {
        this.shareholdingCount = shareholdingCount;
    }

    public long getInvestmentCount() {
        return investmentCount;
    }

    public void setInvestmentCount(long investmentCount) {
        this.investmentCount = investmentCount;
    }

    public long getLimitedLiabilityCount() {
        return limitedLiabilityCount;
    }

    public void setLimitedLiabilityCount(long limitedLiabilityCount) {
        this.limitedLiabilityCount = limitedLiabilityCount;
    }

    public long getForeignInvestmentCount() {
        return foreignInvestmentCount;
    }

    public void setForeignInvestmentCount(long foreignInvestmentCount) {
        this.foreignInvestmentCount = foreignInvestmentCount;
    }

    public long getLimitedLiabilityCompanyCount() {
        return limitedLiabilityCompanyCount;
    }

    public void setLimitedLiabilityCompanyCount(long limitedLiabilityCompanyCount) {
        this.limitedLiabilityCompanyCount = limitedLiabilityCompanyCount;
    }

    public long getOthersCount() {
        return othersCount;
    }

    public void setOthersCount(long othersCount) {
        this.othersCount = othersCount;
    }

    public long getClsssifyTotalCount() {
        return clsssifyTotalCount;
    }

    public void setClsssifyTotalCount(long clsssifyTotalCount) {
        this.clsssifyTotalCount = clsssifyTotalCount;
    }

    public long getCityCount() {
        return cityCount;
    }

    public void setCityCount(long cityCount) {
        this.cityCount = cityCount;
    }

    public long getTownCount() {
        return townCount;
    }

    public void setTownCount(long townCount) {
        this.townCount = townCount;
    }

    public long getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(long freeCount) {
        this.freeCount = freeCount;
    }

    public long getRentCount() {
        return rentCount;
    }

    public void setRentCount(long rentCount) {
        this.rentCount = rentCount;
    }

    public long getGratisCount() {
        return gratisCount;
    }

    public void setGratisCount(long gratisCount) {
        this.gratisCount = gratisCount;
    }

    public long getGroceryCount() {
        return groceryCount;
    }

    public void setGroceryCount(long groceryCount) {
        this.groceryCount = groceryCount;
    }

    public long getDimeStoreCount() {
        return dimeStoreCount;
    }

    public void setDimeStoreCount(long dimeStoreCount) {
        this.dimeStoreCount = dimeStoreCount;
    }

    public long getSupermarketCount() {
        return supermarketCount;
    }

    public void setSupermarketCount(long supermarketCount) {
        this.supermarketCount = supermarketCount;
    }

    public long getMarketplaceCount() {
        return marketplaceCount;
    }

    public void setMarketplaceCount(long marketplaceCount) {
        this.marketplaceCount = marketplaceCount;
    }

    public long getTobaccoAndWineCount() {
        return tobaccoAndWineCount;
    }

    public void setTobaccoAndWineCount(long tobaccoAndWineCount) {
        this.tobaccoAndWineCount = tobaccoAndWineCount;
    }

    public long getRecreationCount() {
        return recreationCount;
    }

    public void setRecreationCount(long recreationCount) {
        this.recreationCount = recreationCount;
    }

    public long getBizOthersCount() {
        return bizOthersCount;
    }

    public void setBizOthersCount(long bizOthersCount) {
        this.bizOthersCount = bizOthersCount;
    }

    public Integer getOrgRank() {
        return orgRank;
    }

    public void setOrgRank(Integer orgRank) {
        this.orgRank = orgRank;
    }

    public long getAtFristValidLicCount() {
        return atFristValidLicCount;
    }

    public void setAtFristValidLicCount(long atFristValidLicCount) {
        this.atFristValidLicCount = atFristValidLicCount;
    }

    public long getAtLastValidLicCount() {
        return atLastValidLicCount;
    }

    public void setAtLastValidLicCount(long atLastValidLicCount) {
        this.atLastValidLicCount = atLastValidLicCount;
    }

    public long getCurrentPeriodNewCount() {
        return currentPeriodNewCount;
    }

    public void setCurrentPeriodNewCount(long currentPeriodNewCount) {
        this.currentPeriodNewCount = currentPeriodNewCount;
    }

    public long getCurrentPeriodLogoutCount() {
        return currentPeriodLogoutCount;
    }

    public void setCurrentPeriodLogoutCount(long currentPeriodLogoutCount) {
        this.currentPeriodLogoutCount = currentPeriodLogoutCount;
    }

    public Timestamp getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Timestamp currentDate) {
        this.currentDate = currentDate;
    }

    public Timestamp getDefaultDataStart() {
        return defaultDataStart;
    }

    public void setDefaultDataStart(Timestamp defaultDataStart) {
        this.defaultDataStart = defaultDataStart;
    }

    public Timestamp getDefaultDataEnd() {
        return defaultDataEnd;
    }

    public void setDefaultDataEnd(Timestamp defaultDataEnd) {
        this.defaultDataEnd = defaultDataEnd;
    }

    public long getBacklogOrderNum() {
        return backlogOrderNum;
    }

    public void setBacklogOrderNum(long backlogOrderNum) {
        this.backlogOrderNum = backlogOrderNum;
    }

    public long getLongTimeOrderNum() {
        return longTimeOrderNum;
    }

    public void setLongTimeOrderNum(long longTimeOrderNum) {
        this.longTimeOrderNum = longTimeOrderNum;
    }

    public long getTotalOrderNum() {
        return totalOrderNum;
    }

    public void setTotalOrderNum(long totalOrderNum) {
        this.totalOrderNum = totalOrderNum;
    }

    public long getNewAddOrderNum() {
        return newAddOrderNum;
    }

    public void setNewAddOrderNum(long newAddOrderNum) {
        this.newAddOrderNum = newAddOrderNum;
    }

    public long getAcceptOrderNum() {
        return acceptOrderNum;
    }

    public void setAcceptOrderNum(long acceptOrderNum) {
        this.acceptOrderNum = acceptOrderNum;
    }

    public String getNowEffectStatusStr() {
        return nowEffectStatusStr;
    }

    public void setNowEffectStatusStr(String nowEffectStatusStr) {
        this.nowEffectStatusStr = nowEffectStatusStr;
    }

    public Timestamp getValidateStart_start() {
        return validateStart_start;
    }

    public void setValidateStart_start(Timestamp validateStart_start) {
        this.validateStart_start = validateStart_start;
    }

    public Timestamp getValidateStart_end() {
        return validateStart_end;
    }

    public void setValidateStart_end(Timestamp validateStart_end) {
        this.validateStart_end = validateStart_end;
    }

    public Timestamp getValidateEnd_start() {
        return validateEnd_start;
    }

    public void setValidateEnd_start(Timestamp validateEnd_start) {
        this.validateEnd_start = validateEnd_start;
    }

    public Timestamp getValidateEnd_end() {
        return validateEnd_end;
    }

    public void setValidateEnd_end(Timestamp validateEnd_end) {
        this.validateEnd_end = validateEnd_end;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRetailCidTypeStr() {
        return retailCidTypeStr;
    }

    public void setRetailCidTypeStr(String retailCidTypeStr) {
        this.retailCidTypeStr = retailCidTypeStr;
    }

    public String getApplyTypeStr() {
        return applyTypeStr;
    }

    public void setApplyTypeStr(String applyTypeStr) {
        this.applyTypeStr = applyTypeStr;
    }

    public String getCurrentNodeStr() {
        return currentNodeStr;
    }

    public void setCurrentNodeStr(String currentNodeStr) {
        this.currentNodeStr = currentNodeStr;
    }

    public Timestamp getDecideDateStart() {
        return decideDateStart;
    }

    public void setDecideDateStart(Timestamp decideDateStart) {
        this.decideDateStart = decideDateStart;
    }

    public Timestamp getDecideDateEnd() {
        return decideDateEnd;
    }

    public void setDecideDateEnd(Timestamp decideDateEnd) {
        this.decideDateEnd = decideDateEnd;
    }

    public String getDecideDateStr() {
        return decideDateStr;
    }

    public void setDecideDateStr(String decideDateStr) {
        this.decideDateStr = decideDateStr;
    }

    public Integer getAcceptFileNum() {
        return acceptFileNum;
    }

    public void setAcceptFileNum(Integer acceptFileNum) {
        this.acceptFileNum = acceptFileNum;
    }

    public String getAcceptFileNumStr() {
        return acceptFileNumStr;
    }

    public void setAcceptFileNumStr(String acceptFileNumStr) {
        this.acceptFileNumStr = acceptFileNumStr;
    }

    public String getValidateStartStr() {
        return validateStartStr;
    }

    public void setValidateStartStr(String validateStartStr) {
        this.validateStartStr = validateStartStr;
    }

    public String getValidateEndStr() {
        return validateEndStr;
    }

    public void setValidateEndStr(String validateEndStr) {
        this.validateEndStr = validateEndStr;
    }

    public String getManagerScopeStr() {
        return managerScopeStr;
    }

    public void setManagerScopeStr(String managerScopeStr) {
        this.managerScopeStr = managerScopeStr;
    }

    public String getResultStatusStr() {
        return resultStatusStr;
    }

    public void setResultStatusStr(String resultStatusStr) {
        this.resultStatusStr = resultStatusStr;
    }

    public String getApplicantPhoto() {
        return applicantPhoto;
    }

    public void setApplicantPhoto(String applicantPhoto) {
        this.applicantPhoto = applicantPhoto;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getDocTypeStr() {
        return docTypeStr;
    }

    public void setDocTypeStr(String docTypeStr) {
        this.docTypeStr = docTypeStr;
    }

    public String getLinkApplyMainUuid() {
        return linkApplyMainUuid;
    }

    public void setLinkApplyMainUuid(String linkApplyMainUuid) {
        this.linkApplyMainUuid = linkApplyMainUuid;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    public String getBusiMgrLicValidity() {
        return busiMgrLicValidity;
    }

    public void setBusiMgrLicValidity(String busiMgrLicValidity) {
        this.busiMgrLicValidity = busiMgrLicValidity;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Integer getIsProcessComplete() {
        return isProcessComplete;
    }

    public void setIsProcessComplete(Integer isProcessComplete) {
        this.isProcessComplete = isProcessComplete;
    }

    public String getSpecialGroupTypeStr() {
        return specialGroupTypeStr;
    }

    public void setSpecialGroupTypeStr(String specialGroupTypeStr) {
        this.specialGroupTypeStr = specialGroupTypeStr;
    }

    public Double getBizArea() {
        return bizArea;
    }

    public void setBizArea(Double bizArea) {
        this.bizArea = bizArea;
    }

    public String getDrillDirection() {
        return drillDirection;
    }

    public void setDrillDirection(String drillDirection) {
        this.drillDirection = drillDirection;
    }

    public String getApplyInfoUuid() {
        return applyInfoUuid;
    }

    public void setApplyInfoUuid(String applyInfoUuid) {
        this.applyInfoUuid = applyInfoUuid;
    }

    public String getLicUuid() {
        return licUuid;
    }

    public void setLicUuid(String licUuid) {
        this.licUuid = licUuid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInduCommBusinessLicenceNumber() {
        return induCommBusinessLicenceNumber;
    }

    public void setInduCommBusinessLicenceNumber(String induCommBusinessLicenceNumber) {
        this.induCommBusinessLicenceNumber = induCommBusinessLicenceNumber;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCompanyEconomyType() {
        return companyEconomyType;
    }

    public void setCompanyEconomyType(String companyEconomyType) {
        this.companyEconomyType = companyEconomyType;
    }

    public String getBusinessAddr() {
        return businessAddr;
    }

    public void setBusinessAddr(String businessAddr) {
        this.businessAddr = businessAddr;
    }

    public String getIsDifferHomeAddr() {
        return isDifferHomeAddr;
    }

    public void setIsDifferHomeAddr(String isDifferHomeAddr) {
        this.isDifferHomeAddr = isDifferHomeAddr;
    }

    public String getSupplyCompany() {
        return supplyCompany;
    }

    public void setSupplyCompany(String supplyCompany) {
        this.supplyCompany = supplyCompany;
    }

    public Date getAccrediDate() {
        return accrediDate;
    }

    public void setAccrediDate(Date accrediDate) {
        this.accrediDate = accrediDate;
    }

    public Date getValidateStart() {
        return validateStart;
    }

    public void setValidateStart(Date validateStart) {
        this.validateStart = validateStart;
    }

    public Date getValidateEnd() {
        return validateEnd;
    }

    public void setValidateEnd(Date validateEnd) {
        this.validateEnd = validateEnd;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getNowEffectStatus() {
        return nowEffectStatus;
    }

    public void setNowEffectStatus(String nowEffectStatus) {
        this.nowEffectStatus = nowEffectStatus;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    public String getLicIssuingOrg() {
        return licIssuingOrg;
    }

    public void setLicIssuingOrg(String licIssuingOrg) {
        this.licIssuingOrg = licIssuingOrg;
    }

    public String getResalePersonId() {
        return resalePersonId;
    }

    public void setResalePersonId(String resalePersonId) {
        this.resalePersonId = resalePersonId;
    }

    public String getManagerScope() {
        return managerScope;
    }

    public void setManagerScope(String managerScope) {
        this.managerScope = managerScope;
    }

    public String getGroundOwnership() {
        return groundOwnership;
    }

    public void setGroundOwnership(String groundOwnership) {
        this.groundOwnership = groundOwnership;
    }

    public Timestamp getLeaseTimelimitStart() {
        return leaseTimelimitStart;
    }

    public void setLeaseTimelimitStart(Timestamp leaseTimelimitStart) {
        this.leaseTimelimitStart = leaseTimelimitStart;
    }

    public Timestamp getLeaseTimelimitEnd() {
        return leaseTimelimitEnd;
    }

    public void setLeaseTimelimitEnd(Timestamp leaseTimelimitEnd) {
        this.leaseTimelimitEnd = leaseTimelimitEnd;
    }

    public String getLicNo() {
        return licNo;
    }

    public void setLicNo(String licNo) {
        this.licNo = licNo;
    }

    public String getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(String currentNode) {
        this.currentNode = currentNode;
    }

    public Short getApplyType() {
        return applyType;
    }

    public void setApplyType(Short applyType) {
        this.applyType = applyType;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public Short getIsOnlineBooking() {
        return isOnlineBooking;
    }

    public void setIsOnlineBooking(Short isOnlineBooking) {
        this.isOnlineBooking = isOnlineBooking;
    }

    public Timestamp getApplyDeadline() {
        return applyDeadline;
    }

    public void setApplyDeadline(Timestamp applyDeadline) {
        this.applyDeadline = applyDeadline;
    }

    public Short getReplyDecide() {
        return replyDecide;
    }

    public void setReplyDecide(Short replyDecide) {
        this.replyDecide = replyDecide;
    }

    public Short getDocReviewIsPass() {
        return docReviewIsPass;
    }

    public void setDocReviewIsPass(Short docReviewIsPass) {
        this.docReviewIsPass = docReviewIsPass;
    }

    public Timestamp getFieldAduitDeadline() {
        return fieldAduitDeadline;
    }

    public void setFieldAduitDeadline(Timestamp fieldAduitDeadline) {
        this.fieldAduitDeadline = fieldAduitDeadline;
    }

    public Short getFieldAduitIsPass() {
        return fieldAduitIsPass;
    }

    public void setFieldAduitIsPass(Short fieldAduitIsPass) {
        this.fieldAduitIsPass = fieldAduitIsPass;
    }

    public Timestamp getFieldAduitSubmitTime() {
        return fieldAduitSubmitTime;
    }

    public void setFieldAduitSubmitTime(Timestamp fieldAduitSubmitTime) {
        this.fieldAduitSubmitTime = fieldAduitSubmitTime;
    }

    public Timestamp getFieldAduitResultSubmitTime() {
        return fieldAduitResultSubmitTime;
    }

    public void setFieldAduitResultSubmitTime(Timestamp fieldAduitResultSubmitTime) {
        this.fieldAduitResultSubmitTime = fieldAduitResultSubmitTime;
    }

    public Short getIsApprove() {
        return isApprove;
    }

    public void setIsApprove(Short isApprove) {
        this.isApprove = isApprove;
    }

    public Short getLicensingDecide() {
        return licensingDecide;
    }

    public void setLicensingDecide(Short licensingDecide) {
        this.licensingDecide = licensingDecide;
    }

    public Timestamp getFirstPrintLicDate() {
        return firstPrintLicDate;
    }

    public void setFirstPrintLicDate(Timestamp firstPrintLicDate) {
        this.firstPrintLicDate = firstPrintLicDate;
    }

    public Short getIsHearing() {
        return isHearing;
    }

    public void setIsHearing(Short isHearing) {
        this.isHearing = isHearing;
    }

    public Timestamp getHearingBeginTime() {
        return hearingBeginTime;
    }

    public void setHearingBeginTime(Timestamp hearingBeginTime) {
        this.hearingBeginTime = hearingBeginTime;
    }

    public Timestamp getHearingEndTime() {
        return hearingEndTime;
    }

    public void setHearingEndTime(Timestamp hearingEndTime) {
        this.hearingEndTime = hearingEndTime;
    }

    public String getFieldAduitPhoto() {
        return fieldAduitPhoto;
    }

    public void setFieldAduitPhoto(String fieldAduitPhoto) {
        this.fieldAduitPhoto = fieldAduitPhoto;
    }

    public Short getIsExtend() {
        return isExtend;
    }

    public void setIsExtend(Short isExtend) {
        this.isExtend = isExtend;
    }

    public Timestamp getDecideDate() {
        return decideDate;
    }

    public void setDecideDate(Timestamp decideDate) {
        this.decideDate = decideDate;
    }

    public Short getIsBookbuild() {
        return isBookbuild;
    }

    public void setIsBookbuild(Short isBookbuild) {
        this.isBookbuild = isBookbuild;
    }

    public Timestamp getBookbuildTime() {
        return bookbuildTime;
    }

    public void setBookbuildTime(Timestamp bookbuildTime) {
        this.bookbuildTime = bookbuildTime;
    }

    public Timestamp getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Timestamp acceptDate) {
        this.acceptDate = acceptDate;
    }

    public Timestamp getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Timestamp applyDate) {
        this.applyDate = applyDate;
    }

    public String getIsInspect() {
        return isInspect;
    }

    public void setIsInspect(String isInspect) {
        this.isInspect = isInspect;
    }

    public String getExtendDays() {
        return extendDays;
    }

    public void setExtendDays(String extendDays) {
        this.extendDays = extendDays;
    }

    public String getNoPassingReason() {
        return noPassingReason;
    }

    public void setNoPassingReason(String noPassingReason) {
        this.noPassingReason = noPassingReason;
    }

    public Date getLicValidatyStart() {
        return licValidatyStart;
    }

    public void setLicValidatyStart(Date licValidatyStart) {
        this.licValidatyStart = licValidatyStart;
    }

    public Date getLicValidatyEnd() {
        return licValidatyEnd;
    }

    public void setLicValidatyEnd(Date licValidatyEnd) {
        this.licValidatyEnd = licValidatyEnd;
    }

    public String getFileSeq() {
        return fileSeq;
    }

    public void setFileSeq(String fileSeq) {
        this.fileSeq = fileSeq;
    }

    public String getAcceptResult() {
        return acceptResult;
    }

    public void setAcceptResult(String acceptResult) {
        this.acceptResult = acceptResult;
    }

    public String getLiddId() {
        return liddId;
    }

    public void setLiddId(String liddId) {
        this.liddId = liddId;
    }

    public String getLbidId() {
        return lbidId;
    }

    public void setLbidId(String lbidId) {
        this.lbidId = lbidId;
    }

    public String getBdtId() {
        return bdtId;
    }

    public void setBdtId(String bdtId) {
        this.bdtId = bdtId;
    }

    public String getLiddCode() {
        return liddCode;
    }

    public void setLiddCode(String liddCode) {
        this.liddCode = liddCode;
    }

    public Integer getLiddIsFinish() {
        return liddIsFinish;
    }

    public void setLiddIsFinish(Integer liddIsFinish) {
        this.liddIsFinish = liddIsFinish;
    }

    public Integer getLiddIsOverdue() {
        return liddIsOverdue;
    }

    public void setLiddIsOverdue(Integer liddIsOverdue) {
        this.liddIsOverdue = liddIsOverdue;
    }

    public Integer getBcbEnumLiddStatus() {
        return bcbEnumLiddStatus;
    }

    public void setBcbEnumLiddStatus(Integer bcbEnumLiddStatus) {
        this.bcbEnumLiddStatus = bcbEnumLiddStatus;
    }

    public Integer getBcbEnumLbtiStatus() {
        return bcbEnumLbtiStatus;
    }

    public void setBcbEnumLbtiStatus(Integer bcbEnumLbtiStatus) {
        this.bcbEnumLbtiStatus = bcbEnumLbtiStatus;
    }

    public Integer getLiddPrintTimes() {
        return liddPrintTimes;
    }

    public void setLiddPrintTimes(Integer liddPrintTimes) {
        this.liddPrintTimes = liddPrintTimes;
    }

    public Date getLiddDocDate() {
        return liddDocDate;
    }

    public void setLiddDocDate(Date liddDocDate) {
        this.liddDocDate = liddDocDate;
    }

    public Integer getLiddIsSend() {
        return liddIsSend;
    }

    public void setLiddIsSend(Integer liddIsSend) {
        this.liddIsSend = liddIsSend;
    }

    public String getPersonuuidTransact() {
        return personuuidTransact;
    }

    public void setPersonuuidTransact(String personuuidTransact) {
        this.personuuidTransact = personuuidTransact;
    }

    public Date getLiddFinishTime() {
        return liddFinishTime;
    }

    public void setLiddFinishTime(Date liddFinishTime) {
        this.liddFinishTime = liddFinishTime;
    }

    public Integer getBcbEnumLiddFlow() {
        return bcbEnumLiddFlow;
    }

    public void setBcbEnumLiddFlow(Integer bcbEnumLiddFlow) {
        this.bcbEnumLiddFlow = bcbEnumLiddFlow;
    }

    public Integer getLiddPicNumber() {
        return liddPicNumber;
    }

    public void setLiddPicNumber(Integer liddPicNumber) {
        this.liddPicNumber = liddPicNumber;
    }

    public Integer getLiddIsAlreadyPic() {
        return liddIsAlreadyPic;
    }

    public void setLiddIsAlreadyPic(Integer liddIsAlreadyPic) {
        this.liddIsAlreadyPic = liddIsAlreadyPic;
    }

    public Integer getLiddIsSaveWord() {
        return liddIsSaveWord;
    }

    public void setLiddIsSaveWord(Integer liddIsSaveWord) {
        this.liddIsSaveWord = liddIsSaveWord;
    }

    public Integer getLiddIsEntering() {
        return liddIsEntering;
    }

    public void setLiddIsEntering(Integer liddIsEntering) {
        this.liddIsEntering = liddIsEntering;
    }

    public Integer getBcbEnumLiddSend() {
        return bcbEnumLiddSend;
    }

    public void setBcbEnumLiddSend(Integer bcbEnumLiddSend) {
        this.bcbEnumLiddSend = bcbEnumLiddSend;
    }

    public String getLiddRemark() {
        return liddRemark;
    }

    public void setLiddRemark(String liddRemark) {
        this.liddRemark = liddRemark;
    }

    public String getLbtmId() {
        return lbtmId;
    }

    public void setLbtmId(String lbtmId) {
        this.lbtmId = lbtmId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getOnlineAppointId() {
        return onlineAppointId;
    }

    public void setOnlineAppointId(String onlineAppointId) {
        this.onlineAppointId = onlineAppointId;
    }

    public Short getDocType() {
        return docType;
    }

    public void setDocType(Short docType) {
        this.docType = docType;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getBizRange() {
        return bizRange;
    }

    public void setBizRange(String bizRange) {
        this.bizRange = bizRange;
    }

    public String getStorageAddrProv() {
        return storageAddrProv;
    }

    public void setStorageAddrProv(String storageAddrProv) {
        this.storageAddrProv = storageAddrProv;
    }

    public String getStorageAddrCity() {
        return storageAddrCity;
    }

    public void setStorageAddrCity(String storageAddrCity) {
        this.storageAddrCity = storageAddrCity;
    }

    public String getStorageAddrCounty() {
        return storageAddrCounty;
    }

    public void setStorageAddrCounty(String storageAddrCounty) {
        this.storageAddrCounty = storageAddrCounty;
    }

    public String getStorageAddrStreet() {
        return storageAddrStreet;
    }

    public void setStorageAddrStreet(String storageAddrStreet) {
        this.storageAddrStreet = storageAddrStreet;
    }

    public String getBizAddrAdc() {
        return bizAddrAdc;
    }

    public void setBizAddrAdc(String bizAddrAdc) {
        this.bizAddrAdc = bizAddrAdc;
    }

    public String getCarryOnAddrCity() {
        return carryOnAddrCity;
    }

    public void setCarryOnAddrCity(String carryOnAddrCity) {
        this.carryOnAddrCity = carryOnAddrCity;
    }

    public String getCarryOnAddrCounty() {
        return carryOnAddrCounty;
    }

    public void setCarryOnAddrCounty(String carryOnAddrCounty) {
        this.carryOnAddrCounty = carryOnAddrCounty;
    }

    public String getBizAddrStreet() {
        return bizAddrStreet;
    }

    public void setBizAddrStreet(String bizAddrStreet) {
        this.bizAddrStreet = bizAddrStreet;
    }

    public Short getEcoType() {
        return ecoType;
    }

    public void setEcoType(Short ecoType) {
        this.ecoType = ecoType;
    }

    public String getEcoTypeOther() {
        return ecoTypeOther;
    }

    public void setEcoTypeOther(String ecoTypeOther) {
        this.ecoTypeOther = ecoTypeOther;
    }

    public Short getPlaceOwnership() {
        return placeOwnership;
    }

    public void setPlaceOwnership(Short placeOwnership) {
        this.placeOwnership = placeOwnership;
    }

    public Date getTenancyBegin() {
        return tenancyBegin;
    }

    public void setTenancyBegin(Date tenancyBegin) {
        this.tenancyBegin = tenancyBegin;
    }

    public Date getTenancyEnd() {
        return tenancyEnd;
    }

    public void setTenancyEnd(Date tenancyEnd) {
        this.tenancyEnd = tenancyEnd;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }

    public Short getPicCidType() {
        return picCidType;
    }

    public void setPicCidType(Short picCidType) {
        this.picCidType = picCidType;
    }

    public String getPicCidNo() {
        return picCidNo;
    }

    public void setPicCidNo(String picCidNo) {
        this.picCidNo = picCidNo;
    }

    public String getPicCidAddrStreet() {
        return picCidAddrStreet;
    }

    public void setPicCidAddrStreet(String picCidAddrStreet) {
        this.picCidAddrStreet = picCidAddrStreet;
    }

    public String getPicAddrStreet() {
        return picAddrStreet;
    }

    public void setPicAddrStreet(String picAddrStreet) {
        this.picAddrStreet = picAddrStreet;
    }

    public Short getSpecialGroupType() {
        return specialGroupType;
    }

    public void setSpecialGroupType(Short specialGroupType) {
        this.specialGroupType = specialGroupType;
    }

    public String getSpecialGroupTypeOther() {
        return specialGroupTypeOther;
    }

    public void setSpecialGroupTypeOther(String specialGroupTypeOther) {
        this.specialGroupTypeOther = specialGroupTypeOther;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLineTel() {
        return lineTel;
    }

    public void setLineTel(String lineTel) {
        this.lineTel = lineTel;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Short getAgentCidType() {
        return agentCidType;
    }

    public void setAgentCidType(Short agentCidType) {
        this.agentCidType = agentCidType;
    }

    public String getApplyPersonPromise() {
        return applyPersonPromise;
    }

    public void setApplyPersonPromise(String applyPersonPromise) {
        this.applyPersonPromise = applyPersonPromise;
    }

    public String getApplyPersonSign() {
        return applyPersonSign;
    }

    public void setApplyPersonSign(String applyPersonSign) {
        this.applyPersonSign = applyPersonSign;
    }

    public String getApplyPersonSignDate() {
        return applyPersonSignDate;
    }

    public void setApplyPersonSignDate(String applyPersonSignDate) {
        this.applyPersonSignDate = applyPersonSignDate;
    }

    public String getRetailLicNo() {
        return retailLicNo;
    }

    public void setRetailLicNo(String retailLicNo) {
        this.retailLicNo = retailLicNo;
    }

    public Short getRetailLicLoseType() {
        return retailLicLoseType;
    }

    public void setRetailLicLoseType(Short retailLicLoseType) {
        this.retailLicLoseType = retailLicLoseType;
    }

    public String getIsLostLicenceCopy() {
        return isLostLicenceCopy;
    }

    public void setIsLostLicenceCopy(String isLostLicenceCopy) {
        this.isLostLicenceCopy = isLostLicenceCopy;
    }

    public String getRetailLicLoseReason() {
        return retailLicLoseReason;
    }

    public void setRetailLicLoseReason(String retailLicLoseReason) {
        this.retailLicLoseReason = retailLicLoseReason;
    }

    public String getIsDamageLicenceOrigi() {
        return isDamageLicenceOrigi;
    }

    public void setIsDamageLicenceOrigi(String isDamageLicenceOrigi) {
        this.isDamageLicenceOrigi = isDamageLicenceOrigi;
    }

    public String getIsDamageLicenceCopy() {
        return isDamageLicenceCopy;
    }

    public void setIsDamageLicenceCopy(String isDamageLicenceCopy) {
        this.isDamageLicenceCopy = isDamageLicenceCopy;
    }

    public String getDamageRemark() {
        return damageRemark;
    }

    public void setDamageRemark(String damageRemark) {
        this.damageRemark = damageRemark;
    }

    public Short getEntNameIsChange() {
        return entNameIsChange;
    }

    public void setEntNameIsChange(Short entNameIsChange) {
        this.entNameIsChange = entNameIsChange;
    }

    public String getOldEntName() {
        return oldEntName;
    }

    public void setOldEntName(String oldEntName) {
        this.oldEntName = oldEntName;
    }

    public String getNewEntName() {
        return newEntName;
    }

    public void setNewEntName(String newEntName) {
        this.newEntName = newEntName;
    }

    public String getIsChangeCompanyName() {
        return isChangeCompanyName;
    }

    public void setIsChangeCompanyName(String isChangeCompanyName) {
        this.isChangeCompanyName = isChangeCompanyName;
    }

    public String getOldCompanyName() {
        return oldCompanyName;
    }

    public void setOldCompanyName(String oldCompanyName) {
        this.oldCompanyName = oldCompanyName;
    }

    public String getNewCompanyName() {
        return newCompanyName;
    }

    public void setNewCompanyName(String newCompanyName) {
        this.newCompanyName = newCompanyName;
    }

    public Short getPicIsChange() {
        return picIsChange;
    }

    public void setPicIsChange(Short picIsChange) {
        this.picIsChange = picIsChange;
    }

    public String getOldPic() {
        return oldPic;
    }

    public void setOldPic(String oldPic) {
        this.oldPic = oldPic;
    }

    public String getNewPic() {
        return newPic;
    }

    public void setNewPic(String newPic) {
        this.newPic = newPic;
    }

    public Short getBizAddrIsChange() {
        return bizAddrIsChange;
    }

    public void setBizAddrIsChange(Short bizAddrIsChange) {
        this.bizAddrIsChange = bizAddrIsChange;
    }

    public String getOldBizAddrAdc() {
        return oldBizAddrAdc;
    }

    public void setOldBizAddrAdc(String oldBizAddrAdc) {
        this.oldBizAddrAdc = oldBizAddrAdc;
    }

    public String getNewBizAddrAdc() {
        return newBizAddrAdc;
    }

    public void setNewBizAddrAdc(String newBizAddrAdc) {
        this.newBizAddrAdc = newBizAddrAdc;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public Integer getRetailCidType() {
        return retailCidType;
    }

    public void setRetailCidType(Integer retailCidType) {
        this.retailCidType = retailCidType;
    }

    public String getRetailCidNo() {
        return retailCidNo;
    }

    public void setRetailCidNo(String retailCidNo) {
        this.retailCidNo = retailCidNo;
    }

    public String getRetailCidAddress() {
        return retailCidAddress;
    }

    public void setRetailCidAddress(String retailCidAddress) {
        this.retailCidAddress = retailCidAddress;
    }

    public String getRetailAddress() {
        return retailAddress;
    }

    public void setRetailAddress(String retailAddress) {
        this.retailAddress = retailAddress;
    }

    public String getBizFormat() {
        return bizFormat;
    }

    public void setBizFormat(String bizFormat) {
        this.bizFormat = bizFormat;
    }

    public Integer getAreaType() {
        return areaType;
    }

    public void setAreaType(Integer areaType) {
        this.areaType = areaType;
    }

    public String getRetailTel() {
        return retailTel;
    }

    public void setRetailTel(String retailTel) {
        this.retailTel = retailTel;
    }

    public String getSpecialPersonGroup() {
        return specialPersonGroup;
    }

    public void setSpecialPersonGroup(String specialPersonGroup) {
        this.specialPersonGroup = specialPersonGroup;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getOrgUuid() {
        return orgUuid;
    }

    public void setOrgUuid(String orgUuid) {
        this.orgUuid = orgUuid;
    }

    public String getOrgSeqCode() {
        return orgSeqCode;
    }

    public void setOrgSeqCode(String orgSeqCode) {
        this.orgSeqCode = orgSeqCode;
    }

    public Integer getChildrenOrgRank() {
        return childrenOrgRank;
    }

    public void setChildrenOrgRank(Integer childrenOrgRank) {
        this.childrenOrgRank = childrenOrgRank;
    }

    public long getApplicationOperating() {
        return applicationOperating;
    }

    public void setApplicationOperating(long applicationOperating) {
        this.applicationOperating = applicationOperating;
    }

    public long getNormalOperating() {
        return normalOperating;
    }

    public void setNormalOperating(long normalOperating) {
        this.normalOperating = normalOperating;
    }

    public long getApplicationAndNormal() {
        return applicationAndNormal;
    }

    public void setApplicationAndNormal(long applicationAndNormal) {
        this.applicationAndNormal = applicationAndNormal;
    }

    public long getClosedOperating() {
        return closedOperating;
    }

    public void setClosedOperating(long closedOperating) {
        this.closedOperating = closedOperating;
    }

    public long getClosureOperating() {
        return closureOperating;
    }

    public void setClosureOperating(long closureOperating) {
        this.closureOperating = closureOperating;
    }

    public long getClosedAndClosure() {
        return closedAndClosure;
    }

    public void setClosedAndClosure(long closedAndClosure) {
        this.closedAndClosure = closedAndClosure;
    }

    public long getCancellationOperating() {
        return cancellationOperating;
    }

    public void setCancellationOperating(long cancellationOperating) {
        this.cancellationOperating = cancellationOperating;
    }

    public long getTotalOperating() {
        return totalOperating;
    }

    public void setTotalOperating(long totalOperating) {
        this.totalOperating = totalOperating;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getGooutofbusiness() {
        return gooutofbusiness;
    }

    public void setGooutofbusiness(long gooutofbusiness) {
        this.gooutofbusiness = gooutofbusiness;
    }

    public long getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(long withdraw) {
        this.withdraw = withdraw;
    }

    public long getRevoke() {
        return revoke;
    }

    public void setRevoke(long revoke) {
        this.revoke = revoke;
    }

    public long getLicenseexpires() {
        return licenseexpires;
    }

    public void setLicenseexpires(long licenseexpires) {
        this.licenseexpires = licenseexpires;
    }

    public long getCancelqualification() {
        return cancelqualification;
    }

    public void setCancelqualification(long cancelqualification) {
        this.cancelqualification = cancelqualification;
    }

    public long getOrderchangesare() {
        return orderchangesare;
    }

    public void setOrderchangesare(long orderchangesare) {
        this.orderchangesare = orderchangesare;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    public long getLongtermoperation() {
        return longtermoperation;
    }

    public void setLongtermoperation(long longtermoperation) {
        this.longtermoperation = longtermoperation;
    }

    public long getNewmanagement() {
        return newmanagement;
    }

    public void setNewmanagement(long newmanagement) {
        this.newmanagement = newmanagement;
    }

    public long getOther() {
        return other;
    }

    public void setOther(long other) {
        this.other = other;
    }

    public Double getXb1() {
        return xb1;
    }

    public void setXb1(Double xb1) {
        this.xb1 = xb1;
    }

    public Double getXb2() {
        return xb2;
    }

    public void setXb2(Double xb2) {
        this.xb2 = xb2;
    }

    public Double getXb3() {
        return xb3;
    }

    public void setXb3(Double xb3) {
        this.xb3 = xb3;
    }

    public Double getXb4() {
        return xb4;
    }

    public void setXb4(Double xb4) {
        this.xb4 = xb4;
    }

    public Double getXb5() {
        return xb5;
    }

    public void setXb5(Double xb5) {
        this.xb5 = xb5;
    }

    public Double getYx1() {
        return yx1;
    }

    public void setYx1(Double yx1) {
        this.yx1 = yx1;
    }

    public Double getYx2() {
        return yx2;
    }

    public void setYx2(Double yx2) {
        this.yx2 = yx2;
    }

    public Double getYx3() {
        return yx3;
    }

    public void setYx3(Double yx3) {
        this.yx3 = yx3;
    }

    public Double getYx4() {
        return yx4;
    }

    public void setYx4(Double yx4) {
        this.yx4 = yx4;
    }

    public Double getYx5() {
        return yx5;
    }

    public void setYx5(Double yx5) {
        this.yx5 = yx5;
    }

    public Double getBg1() {
        return bg1;
    }

    public void setBg1(Double bg1) {
        this.bg1 = bg1;
    }

    public Double getBg2() {
        return bg2;
    }

    public void setBg2(Double bg2) {
        this.bg2 = bg2;
    }

    public Double getBg3() {
        return bg3;
    }

    public void setBg3(Double bg3) {
        this.bg3 = bg3;
    }

    public Double getBg4() {
        return bg4;
    }

    public void setBg4(Double bg4) {
        this.bg4 = bg4;
    }

    public Double getBg5() {
        return bg5;
    }

    public void setBg5(Double bg5) {
        this.bg5 = bg5;
    }

    public Timestamp getExtendDateStart() {
        return extendDateStart;
    }

    public void setExtendDateStart(Timestamp extendDateStart) {
        this.extendDateStart = extendDateStart;
    }

    public Timestamp getExtendDateEnd() {
        return extendDateEnd;
    }

    public void setExtendDateEnd(Timestamp extendDateEnd) {
        this.extendDateEnd = extendDateEnd;
    }

    public String getPlaceOwnershipStr() {
        return placeOwnershipStr;
    }

    public void setPlaceOwnershipStr(String placeOwnershipStr) {
        this.placeOwnershipStr = placeOwnershipStr;
    }

    public String getAreaTypeStr() {
        return areaTypeStr;
    }

    public void setAreaTypeStr(String areaTypeStr) {
        this.areaTypeStr = areaTypeStr;
    }

    public String getSqlFlag01() {
        return sqlFlag01;
    }

    public void setSqlFlag01(String sqlFlag01) {
        this.sqlFlag01 = sqlFlag01;
    }

    public String getSqlFlag02() {
        return sqlFlag02;
    }

    public void setSqlFlag02(String sqlFlag02) {
        this.sqlFlag02 = sqlFlag02;
    }

    public String getSqlFlag03() {
        return sqlFlag03;
    }

    public void setSqlFlag03(String sqlFlag03) {
        this.sqlFlag03 = sqlFlag03;
    }

    public Timestamp getInfTimestamp() {
        return infTimestamp;
    }

    public void setInfTimestamp(Timestamp infTimestamp) {
        this.infTimestamp = infTimestamp;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getClassifyDimensionality() {
        return classifyDimensionality;
    }

    public void setClassifyDimensionality(String classifyDimensionality) {
        this.classifyDimensionality = classifyDimensionality;
    }

    public String getContractPerson() {
        return contractPerson;
    }

    public void setContractPerson(String contractPerson) {
        this.contractPerson = contractPerson;
    }

    public String getIsChain() {
        return isChain;
    }

    public void setIsChain(String isChain) {
        this.isChain = isChain;
    }

    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    public String getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(String isBusiness) {
        this.isBusiness = isBusiness;
    }

    public String getOldLicNo() {
        return oldLicNo;
    }

    public void setOldLicNo(String oldLicNo) {
        this.oldLicNo = oldLicNo;
    }

    public String getAgentIdcard() {
        return agentIdcard;
    }

    public void setAgentIdcard(String agentIdcard) {
        this.agentIdcard = agentIdcard;
    }

    public Date getFirstAccrediDate() {
        return firstAccrediDate;
    }

    public void setFirstAccrediDate(Date firstAccrediDate) {
        this.firstAccrediDate = firstAccrediDate;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public String getBizFormatStr() {
        return bizFormatStr;
    }

    public void setBizFormatStr(String bizFormatStr) {
        this.bizFormatStr = bizFormatStr;
    }

    public String getEcoTypeStr() {
        return ecoTypeStr;
    }

    public void setEcoTypeStr(String ecoTypeStr) {
        this.ecoTypeStr = ecoTypeStr;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public String getLeaseTimelimitStartText() {
        return leaseTimelimitStartText;
    }

    public void setLeaseTimelimitStartText(String leaseTimelimitStartText) {
        this.leaseTimelimitStartText = leaseTimelimitStartText;
    }

    public String getLeaseTimelimitEndText() {
        return leaseTimelimitEndText;
    }

    public void setLeaseTimelimitEndText(String leaseTimelimitEndText) {
        this.leaseTimelimitEndText = leaseTimelimitEndText;
    }

    public String getAcceptDateStr() {
        return acceptDateStr;
    }
    
    public void setAcceptDateStr(String acceptDateStr) {
        this.acceptDateStr = acceptDateStr;
    }

    public String getBookingTimeStr() {
        return bookingTimeStr;
    }

    public void setBookingTimeStr(String bookingTimeStr) {
        this.bookingTimeStr = bookingTimeStr;
    }

    public Integer getIsFlowStop() {
        return isFlowStop;
    }

    public void setIsFlowStop(Integer isFlowStop) {
        this.isFlowStop = isFlowStop;
    }


	public long getAtLastValidLicCountCity() {
		return atLastValidLicCountCity;
	}


	public void setAtLastValidLicCountCity(long atLastValidLicCountCity) {
		this.atLastValidLicCountCity = atLastValidLicCountCity;
	}


	public long getAtLastValidLicCountTown() {
		return atLastValidLicCountTown;
	}


	public void setAtLastValidLicCountTown(long atLastValidLicCountTown) {
		this.atLastValidLicCountTown = atLastValidLicCountTown;
	}


	public String getGridDing() {
		return gridDing;
	}


	public void setGridDing(String gridDing) {
		this.gridDing = gridDing;
	}


    public Timestamp getBookbuildTimeStart()
    {
        return bookbuildTimeStart;
    }


    public void setBookbuildTimeStart(Timestamp bookbuildTimeStart)
    {
        this.bookbuildTimeStart = bookbuildTimeStart;
    }


    public Timestamp getBookbuildTimeEnd()
    {
        return bookbuildTimeEnd;
    }


    public void setBookbuildTimeEnd(Timestamp bookbuildTimeEnd)
    {
        this.bookbuildTimeEnd = bookbuildTimeEnd;
    }


	public String getCurrentNodeFlag() {
		return currentNodeFlag;
	}


	public void setCurrentNodeFlag(String currentNodeFlag) {
		this.currentNodeFlag = currentNodeFlag;
	}


	public Timestamp getApplyDateStart() {
		return applyDateStart;
	}


	public void setApplyDateStart(Timestamp applyDateStart) {
		this.applyDateStart = applyDateStart;
	}


	public Timestamp getApplyDateEnd() {
		return applyDateEnd;
	}


	public void setApplyDateEnd(Timestamp applyDateEnd) {
		this.applyDateEnd = applyDateEnd;
	}


	public Timestamp getApproveTime() {
		return approveTime;
	}


	public void setApproveTime(Timestamp approveTime) {
		this.approveTime = approveTime;
	}


	public String getApplyDateStr() {
		return applyDateStr;
	}


	public void setApplyDateStr(String applyDateStr) {
		this.applyDateStr = applyDateStr;
	}


	public String getApproveTimeStr() {
		return approveTimeStr;
	}


	public void setApproveTimeStr(String approveTimeStr) {
		this.approveTimeStr = approveTimeStr;
	}


	public String getIsApproveStr() {
		return isApproveStr;
	}


	public void setIsApproveStr(String isApproveStr) {
		this.isApproveStr = isApproveStr;
	}


    public String getGridName()
    {
        return gridName;
    }


    public void setGridName(String gridName)
    {
        this.gridName = gridName;
    }


    public String getShowGirdFlag()
    {
        return showGirdFlag;
    }


    public void setShowGirdFlag(String showGirdFlag)
    {
        this.showGirdFlag = showGirdFlag;
    }


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	
    
}
