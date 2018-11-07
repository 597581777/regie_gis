package com.icss.regiegis.common.util;



/**
 * 
 * <p>Title: </p>
 * <p>Description: 定义证件办理中用到的常量</p>
 * <p>Copyright: Copyright ICSS(c) 2015</p>
 * @author <a href="mailTo:wangjunfu@chinasofti.com">wangjunfu</a>
 * @version 1.0
 * @history:
 * Created by wangjunfu 2015-6-4
 */
public class LicConstants{
    
    /**** 办理类型*/
    /*** 证件新办流程编码  licnew*/
    public final static Integer APPLY_TYPE_1001=1001;  //新办
    /*** 证件变更流程编码  licchange*/
    public final static Integer APPLY_TYPE_1002=1002;  //变更
    /*** 证件延续流程编码 licextend*/
    public final static Integer APPLY_TYPE_1003=1003;  //延续
    /*** 证件停业流程编码 licpause*/
    public final static Integer APPLY_TYPE_1004=1004;  //停业
    /*** 证件恢复营业流程编码 licresume*/
    public final static Integer APPLY_TYPE_1005=1005;  //恢复营业
    /*** 证件补办流程编码 licreissue*/
    public final static Integer APPLY_TYPE_1006=1006;  //补办
    /*** 证件歇业流程编码 licdestroy*/
    public final static Integer APPLY_TYPE_1007=1007;  //歇业
    /*** 办理延期流程编码 lic_postpone*/
    public final static Integer APPLY_TYPE_1008=1008;  //延期
    /*** 登记注销流程编码*/
    public final static Integer APPLY_TYPE_1009=1009;  //登记注销
    /*** 责令停业整顿流程编码 */
    public final static Integer APPLY_TYPE_1010=1010;  //责令停业整顿
    /*** 责令整顿恢复营业流程编码*/
    public final static Integer APPLY_TYPE_1011=1011;  //责令整顿恢复营业
    /***审批注销*/
    public final static Integer APPLY_TYPE_1012=1012;  //审批注销
    /*** 撤回*/
    public final static Integer APPLY_TYPE_1013=1013;  //撤回
    /*** 撤销*/
    public final static Integer APPLY_TYPE_1014=1014;  //撤销
    /*** 听证*/
    public final static Integer APPLY_TYPE_1015=1015;  //听证
    /*** 黑名单*/
    public final static Integer APPLY_TYPE_1016=1016;  //黑名单
    /*** 卷宗调阅 */
    public final static Integer APPLY_TYPE_1017=1017;  // 卷宗调阅
    /*** 基础信息维护,调用营销接口时传送的类型*/
    public final static Integer APPLY_TYPE_1018=1018;  // 基础信息维护,调用营销接口时传送的类型

    /**** 申请类型*/
    public final static String APPLY_TYPE="apply_type";
    
    /**
     * 页面业务逻辑状态
     */
    public final static String PAGE_NEWOFFICE = "XB";//新办
    public final static String PAGE_CHANGE = "BG";//变更
    public final static String PAGE_CONTINUE = "YX";//延续
    public final static String PAGE_PAUSE = "TY";//停业
    public final static String PAGE_RESUME = "HFYY";//恢复营业
    public final static String PAGE_REISSUE = "BB";//补办
    public final static String PAGE_dESTROY = "XY";//歇业
    //saiSQ 2014-06-13
    public final static String PAGE_CLJS = "CLJS"; //材料接收
    public final static String PAGE_SL = "SL"; //受理
    public final static String PAGE_SMSC = "SMSC"; //书面审查
    public final static String PAGE_XBHCRW = "XBHCRW"; //核查任务分配
    public final static String PAGE_SDHCYJ = "SDHCYJ"; //实地核查
    public final static String PAGE_DB = "DB";//待办
    public final static String PAGE_INITIALIZE = "initialize";//材料上传
    
    /**
     * 行政许可文书doc中命名
     */
    public final static String RETAIL_LIC_YES_DECISION_LICENSE="retail_lic_yes_decision_license";  
    public final static String RETAIL_LIC_NO_DECISION_LICENSE="retail_lic_no_decision_license";  
    /**
     * 注销通知书
     */
    public final static String RETAIL_LIC_LOGOUT_NOTICE="retail_lic_logout_notice";
    /**
     * 注销通知书中文
     */
    public final static String RETAIL_LIC_LOGOUT_NOTICE_CH="烟草专卖零售许可证注销通知书";
    /**
     * 注销决定书
     */
    public final static String RETAIL_LIC_LOGOUT_DECISION_LICENSE="retail_lic_logout_decision_license";
    /**
     * 注销决定书中文
     */
    public final static String RETAIL_LIC_LOGOUT_DECISION_LICENSE_CH="烟草专卖零售许可证注销决定书";
    /**
     * 撤销决定书
     */
    public final static String RETAIL_LIC_CANCEL_DECISION_LICENSE="retail_lic_cancel_decision_license";
    /**
     * 撤销决定书中文
     */
    public final static String RETAIL_LIC_CANCEL_DECISION_LICENSE_CH="烟草专卖零售许可证撤销决定书";  
    /**
     * 撤回决定书
     */
    public final static String RETAIL_LIC_WITHDRAW_DECISION_LICENSE="retail_lic_withdraw_decision_license";
    /**
     * 撤回决定书中文
     */
    public final static String RETAIL_LIC_WITHDRAW_DECISION_LICENSE_CH="烟草专卖零售许可证撤回决定书";  
    /**
     * 事先告知书
     */
    public final static String T_L_DOC_RLIC_PLEAD_NOTICE="t_l_doc_rlic_plead_notice";
    /**
     * 事先告知书中文
     */
    public final static String T_L_DOC_RLIC_PLEAD_NOTICE_CH="陈述申辩权利事先告知书";
    /**
     * 行政许可文书中文
     */
    public final static String RETAIL_LIC_DECISION_LICENSE_CH ="烟草专卖零售许可证准（不）予行政许可决定书";
    public final static String RETAIL_LIC_YES_DECISION_LICENSE_CH="烟草专卖零售许可证准予行政许可决定书";  
    public final static String RETAIL_LIC_NO_DECISION_LICENSE_CH="烟草专卖零售许可证不予行政许可决定书";  
    /**
     *受理不予受理决定书 
     */
    public final static String RETAIL_LIC_ACCEPT_YES_DECISION_LICENSE_CH="烟草专卖零售许可证申请受理决定书";  
    public final static String RETAIL_LIC_ACCEPT_NO_DECISION_LICENSE_CH="烟草专卖零售许可证申请不予受理决定书"; 
    /**
     * 行政许可文书doc中命名
     */
    public final static String RETAIL_LIC_ACCEPT_YES_DECISION_LICENSE="retail_lic_accept_yes_decision_license";  
    public final static String RETAIL_LIC_ACCEPT_NO_DECISION_LICENSE="retail_lic_accept_no_decision_license"; 
    /**
     *材料补正告知书
     */
    public final static String RETAIL_LIC_ACCEPT_NOTICE_LICENSE_CH="烟草专卖零售许可证申请材料补正告知书";
    /**
     * 行政许可文书doc中命名
     */
    public final static String RETAIL_LIC_ACCEPT_NOTICE_LICENSE="retail_lic_accept_notice_license";
    /**
     * 延期办理通知书doc英文名
     */
    public final static String RETAIL_LIC_EXTEND_YES_NOTICE = "retail_lic_extend_yes_notice"; 
    /**
     * 延期办理通知书中文名
     */
    public final static String RETAIL_LIC_EXTEND_YES_NOTICE_CH = "烟草专卖零售许可证延长审批期限通知书"; 
    /**
     * 烟草专卖零售许可证延长审批期限审批表
     */
    public final static String RETAIL_LIC_EXTEND_APPROVE_CH = "烟草专卖零售许可证延长审批期限审批表"; 
    /**
     * 烟草专卖零售许可证责令变更通知书  order_change
     */
    public final static String ORDER_CHANGE = "order_change"; 
    public final static String ORDER_CHANGE_CH = "烟草专卖零售许可证责令变更通知书"; 
    
    //延期办理状态
    /**延期办理状态-待提交*/
    public final static String LIC_APPECT_EXTEND_STATUS_1801 = "1801";
    /**延期办理状态-待审批*/
    public final static String LIC_APPECT_EXTEND_STATUS_1802 = "1802";
    /**延期办理状态-准予延期*/
    public final static String LIC_APPECT_EXTEND_STATUS_1803 = "1803";
    /**延期办理状态-不予延期*/
    public final static String LIC_APPECT_EXTEND_STATUS_1804 = "1804";
    
    //延期办理通知书制作状态
    /**
     * 未制作
     */
    public final static String LIC_APPECP_EXTEND_NOTICE_PRINT_1901 = "1901";
    /**
     * 制作
     */
    public final static String LIC_APPECP_EXTEND_NOTICE_PRINT_1902 = "1902";
    /**
     * 已制作
     */
    public final static String LIC_APPECP_EXTEND_NOTICE_PRINT_1903 = "1903";
    
    /**否 */
    public final static Integer STATUS_NO = 0;
    /**是 */
    public final static Integer STATUS_YES = 1;
    
    //操作项内容,上一步,下一步,完成
    public final static Integer ACTION_NEXT=1;
    public final static Integer ACTION_PREV=0;
    public final static Integer ACTION_FINISH=2;
    
    /**申请主记录表的：申请编号*/
    public static final String KEY_CODE_TYPE_APPLY_MAIN="code_apply_main";
    /**卷宗编号：顺序号：第xxx号*/
    public static final String KEY_CODE_TYPE_LIC_SERIAL="doc_file_serial";
    /**许可证号：顺序号*/
    public static final String KEY_CODE_TYPE_LIC_NO_SERIAL="lic_no_serial";
    /**延期通知书编号：顺序号：第xxx号*/
    public static final String KEY_CODE_TYPE_EXTEND_NOTICE_SERIAL="doc_extend_notice_serial";
    /**网上预约顺序号*/
    public static final String KEY_CODE_TYPE_ONLINE_APPOINT="code_online_appoint";
    /**许可证决定书：顺序号*/
    public static final String KEY_CODE_TYPE_LICINFO_SERAIL_PASS="lic_num_pass";
    /**许可证决定书：顺序号,不通过*/
    public static final String KEY_CODE_TYPE_LICINFO_SERAIL_NOT_PASS="lic_num_no_pass";
    /**许可证受理决定书：顺序号*/
    public static final String KEY_CODE_TYPE_LICINFO_SERAIL_ACCEPT_DECIDE_PASS="lic_num_accept_decide_pass";
    /**许可证不予受理决定书：顺序号,不通过*/
    public static final String KEY_CODE_TYPE_LICINFO_SERAIL_NOT_ACCEPT_DECIDE_PASS="lic_num_no_accept_decide_pass";
    /**许可证材料补正告知书：顺序号*/
    public static final String KEY_CODE_TYPE_LICINFO_SERAIL_ACCEPT_NOTICE_PASS="lic_num_accept_notice_pass";
    /**注销通知书文书编号*/
    public static final String KEY_CODE_TYPE_CANCEL_NOTICE="lic_cancel_notice";
    
    public final static String BACK_LOG_WORK_URL="/backlogwork"; //待办路由地址
    /**流程启动标识**/
    public static final String KEY_START_FLOW = "1";
    
    /**流程，已完成*/
    public static final Integer VALID = 1; // 有效（是）

    /**流程进行中，未完成*/
    public static final Integer INVALID = 0; // 无效（否）
    
    /**流程终止 */
    public static final Integer UNVALID = -1; // 流程终止
    
    public static final String ROLE_INSPECTION = "inspection";//核查人员角色编号
    
    /***许可证管理类申请表：证件类型 身份证*/
    public static final String KEY_LIC_CARDTYPE_IDCARD = "2801";
    /***许可证管理类申请表：证件类型  户口本*/
    public static final String KEY_LIC_CARDTYPE_BOOKREGISTER = "2802";
    /***许可证管理类申请表：证件类型 驾照*/
    public static final String KEY_LIC_CARDTYPE_DRIVER = "2803";
    /***许可证管理类申请表：证件类型 护照*/
    public static final String KEY_LIC_CARDTYPE_PASSPORT = "2804";
    /***许可证管理类申请表：证件类型 其它*/
    public static final String KEY_LIC_CARDTYPE_OTHER = "2805";
    
    
    /**零售业态类别  食杂店*/
    public static final Integer LIC_BIZ_FORMAT_2901 = 2901;
    /**零售业态类别  便利店*/
    public static final Integer LIC_BIZ_FORMAT_2902 = 2902;
    /**零售业态类别  超市*/
    public static final Integer LIC_BIZ_FORMAT_2903 = 2903;
    /**零售业态类别  商场*/
    public static final Integer LIC_BIZ_FORMAT_2904 = 2904;
    /**零售业态类别  烟酒商店*/
    public static final Integer LIC_BIZ_FORMAT_2905 = 2905;
    /**零售业态类别  娱乐服务业*/
    public static final Integer LIC_BIZ_FORMAT_2906 = 2906;
    /**零售业态类别  其他*/
    public static final Integer LIC_BIZ_FORMAT_2907 = 2907;
    
    /**地区类别 城镇*/
    public static final Integer AREA_TYPE_3001 = 3001;
    /**地区类别 乡村*/
    public static final Integer AREA_TYPE_3002 = 3002;
    
    /** 受理结果  准予受理*/
    public static final Integer ACCEPT_RESULT_1704=1704;//受理
    /** 受理结果  材料补正*/
    public static final Integer ACCEPT_RESULT_1701=1701;//补正
    /** 受理结果  不予受理*/
    public static final Integer ACCEPT_RESULT_1703=1703;//不受理
    
    
    public static final String WARMING_TYPE_101="101";//责令停业整顿通知书
    public static final String WARMING_TYPE_102="102";//责令停业整顿终结通知书
    public static final String WARMING_TYPE_103="103";//责令变更通知书
    public static final String WARMING_TYPE_104="104";//取消经营资格
    public static final String WARMING_TYPE_105="105";//涉案处罚结果
    
    /*** 同步接口跟政务外网用的返回值的key*/
    public static final String KEY_ASYC_MSGUUID="msguuid";
    /*** 同步接口跟政务外网用的返回值的key*/
    public static final String KEY_ASYC_RESULT="result";
    /*** 同步接口跟政务外网用的返回值的key*/
    public static final String KEY_ASYC_DATATYPE="datatype";
    /*** 同步接口跟政务外网用的返回值的key*/
    public static final String KEY_ASYC_MESSAGE="message";
    /*** 同步接口跟政务外网用的返回值的key*/
    public static final String KEY_ASYC_TIME="time";
    /*** 同步接口跟政务外网用的返回值的key*/
    public static final String KEY_ASYC_DATAVO="dataVo";
    
    /***同步接口，政务外网用于同步到证件系统内网的申请类型字段*/
    public static final String KEY_ASYC_APPLYTYPE="applyType";
    
    /***同步接口，政务外网用于同步到证件系统内网的 同步结果返回的id集合*/
    public static final String KEY_ASYC_ID_RESULTS="asyc_results";
    
    public static final String KEY_ASYC_SUCCESS="success";
    
    public static final String KEY_ASYC_ERROR="error";
    
    /**营销系统的webserviceESBurl*/
    public static String KEY_ASYC_MARKET_ESBPROX_URL ="EsbMaketProxUrl";
    /**营销系统的webserviceESBurl*/
    public static String KEY_ASYC_MARKET_ESBPROX_METHOD ="EsbMaketMethod";
    /**营销系统的webserviceESBurl*/
    public static String KEY_ASYC_MARKET_NAME_SPACE ="nameSpaceLc";
    /**传给北京浪潮营销判断webservice*/
    public static String KEY_ASYC_MARKET_FLAG ="maketFlagXml";
    /**营销方法webservice*/
    public static String KEY_ASYC_MARKET_SEND_METHOD ="maketMethod";
    /**营销系统:是否向符合条件的所有接口传送数据*/
    public static String KEY_ASYC_MARKET_SEND_ALL_FLAG="sendAllFlag";
    /**政务外网的ESB webservice 进度查询的接口url*/
    public static String KEY_ASYC_GOV_ESB_PROGRESS_URL = "EsbGovProxUrl";
    /**政务外网的webservice方法*/
    public static String KEY_ASYC_GOV_PROGRESS_METHOD ="progreMthod"; 
    /**政务外网的webservice结果公示方法*/
    public static String KEY_ASYC_GOV_NOTICE_METHOD ="noticeMthod";
    /** 二维码路径  */
    public static final String QR_PATH="/pages/upLoadCache/QRCode/";
    /** word路径  */
    public static final String WORD_PATH="/pages/upLoadCache/doc/";
    /** 签名签章路径  */
    public static final String CA_PATH="/pages/upLoadCache/caTmp/";
    
    /** 证件管理员 */
    public static final String ROLE_CODE_1="lic_admin";
    /** 专卖证件负责人 */
    public static final String ROLE_CODE_2="lic_principals";
    /** 机关负责人 */
    public static final String ROLE_CODE_3="org_principals";
    /** 队所所长 */
    public static final String ROLE_CODE_4="team_director";
    /** 实地核查人员 */
    public static final String ROLE_CODE_5="inspection";
    
    
    /***有效期届满未延续*/
    public static final String APPROVE_FACT_REGISTER_3601 = "3601";
    /***被依法取消*/
    public static final String APPROVE_FACT_REGISTER_3602 = "3602";
    
    /***文书签章状态-未签章*/
    public static final Integer LIDD_SIGNATURE_STATUS_3701 = 3701;
    /***文书签章状态-申请签章中*/
    public static final Integer LIDD_SIGNATURE_STATUS_3702 = 3702;
    /***文书签章状态-已签章*/
    public static final Integer LIDD_SIGNATURE_STATUS_3703 = 3703;
    /**签章文书打印份数申请状态-未申请*/
    public static final String LIDD_SIGNATURE_DOC_PRINT_STATUS_0="0";
    /**签章文书打印份数申请状态-申请中*/
    public static final String LIDD_SIGNATURE_DOC_PRINT_STATUS_1="1";
    /**签章文书打印份数申请状态-已完成*/
    public static final String LIDD_SIGNATURE_DOC_PRINT_STATUS_2="2";
    /**签章文书打印份数申请类型-决定书*/
    public static final short LIDD_SIGNATURE_DOC_PRINT_Type_1=1;
    /**签章文书打印份数申请类型-正本*/
    public static final short LIDD_SIGNATURE_DOC_PRINT_Type_2=2;
    /**签章文书打印份数申请类型-副本*/
    public static final short LIDD_SIGNATURE_DOC_PRINT_Type_3=3;
    /**签章文书打印申请默认份数-2份*/
    public static final int LIDD_SIGNATURE_DOC_PRINT_DEFAULT_COPIES=2;
    
    /***部分成功*/
    public static final String SEND_FLAG_RESULT_0002 = "2";
    /***全部失败*/
    public static final String SEND_FLAG_RESULT_0003 = "3";
    /***全部成功*/
    public static final String SEND_FLAG_RESULT_0004 = "4";
    
    /***统计类型-预约情况统计*/
    public static final Integer LIC_STATICTICS_TYPE_3801 = 3801;
    /***统计类型-预约办理统计*/
    public static final Integer LIC_STATICTICS_TYPE_3802 = 3802;
    /***统计类型-在办统计*/
    public static final Integer LIC_STATICTICS_TYPE_3803 = 3803;
    /***统计类型-已办统计*/
    public static final Integer LIC_STATICTICS_TYPE_3804 = 3804;
    /***统计类型-许可证分类统计*/
    public static final Integer LIC_STATICTICS_TYPE_3805 = 3805;
    /***统计类型-经营状态统计*/
    public static final Integer LIC_STATICTICS_TYPE_3806 = 3806;
    /***统计类型-综合统计*/
    public static final Integer LIC_STATICTICS_TYPE_3807 = 3807;
    /***统计类型-办理时限统计*/
    public static final Integer LIC_STATICTICS_TYPE_3808 = 3808;
    /***统计类型-注销统计*/
    public static final Integer LIC_STATICTICS_TYPE_3809 = 3809;
    
    public static final Integer LIC_STATICTICS_TYPE_3810 = 3810;
    public static final Integer LIC_STATICTICS_TYPE_3811 = 3811;
    public static final Integer LIC_STATICTICS_TYPE_3812 = 3812;
    public static final String LIC_STATICTICS_ECOTYPE_NAME = "ecoType";
    public static final String LIC_STATICTICS_AREATYPE_NAME = "areaType";
    public static final String LIC_STATICTICS_OWNERSHIP_NAME = "ownership";
    public static final String LIC_STATICTICS_BIZFORMATTER_NAME = "bizFormatter";
    
    /***统计报表生成方式-手动*/
    public static final Integer LIC_STATICTICS_REPORT_TYPE_3901 = 3901;
    /***统计报表生成方式-自动*/
    public static final Integer LIC_STATICTICS_REPORT_TYPE_3902 = 3902;
    /***系统生成**/
    public static final String LIC_STATICTICS_REPORT_NAME = "系统生成";
    /***进度服务代码**/
    public static final String KEY_ASYC_GOV_ESB_CODE = "Esb_Gov_ServiceCode";
    /***进度版本号**/
    public static final String KEY_ASYC_GOV_ESB_VERSION = "Esb_Gov_Version";
    /***进度服务消费者系统代码**/
    public static final String KEY_ASYC_GOV_ESB_INBOUND = "Esb_Gov_Inbound";
    /***公示服务代码**/
    public static final String KEY_ASYC_GOV_ESB_NOTICE_CODE = "Esb_ServiceCode_Notc";
    /***公示版本号**/
    public static final String KEY_ASYC_GOV_ESB_NOTICE_VERSION = "Esb_Version_Notc";
    /***公示服务消费者系统代码**/
    public static final String KEY_ASYC_GOV_ESB_NOTICE_INBOUND = "Esb_Inbound_Notc";
    
    
    /***营销服务代码**/
    public static final String KEY_ASYC_ESB_MARKE_CODE = "Esb_ServiceCode_Mark";
    /***版本号**/
    public static final String KEY_ASYC_ESB_MARKE_VERSION = "Esb_Version_Mark";
    /***营销服务消费者系统代码**/
    public static final String KEY_ASYC_ESB_MARKE_INBOUND = "Esb_Inbound_Mark";

    /**
     * 文书处理模式 - 0普通1直属分局
     */
    public static final String BDT_HANDLE_MODE_0 = "0";
    public static final String BDT_HANDLE_MODE_1 = "1";
    
    /**
     * 长期
     */
    public static final String LONG_TERM = "1111-11-11";
    
    /**
     * 审批阶段审核是否显示true 不显示，false显示。
     */
    public static final String IS_AUDIT_SHOW = "isAuditShow";
    public static final String IS_AUDIT_SHOW_TRUE = "true";
    public static final String IS_AUDIT_SHOW_FALSE = "false";
    /**
     * 实地核查完成后，当前阶段为审查阶段，证件管理员是否可以编辑实地核查信息。true为可以，false为不可以。
     */
    public static final String IS_ALOW_EDIT_AUDIT = "isAlowEditAudit";
    /**
     * 实地核查完成后，当前阶段为审查阶段，证件管理员是否可以编辑实地核查信息。true为可以，false为不可以。
     */
    public static final String LIC_START_CHANGE = "LicStartChange";
    
    /*** 同步接口省市管理系统的返回值的key*/
    public static final String KEY_SSGLZT_DATAVO="dataVo";
    /**
     * 同步省市管理系统中是否接受实地核查--否
     */
    public static final int IS_INSPECT_0 = 0;
    /**
     * 同步省市管理系统中是否接受实地核查--是
     */
    public static final int IS_INSPECT_1 = 1;

    /**
     * 许可证收回状态2808 
     */
    public static final int LIC_STATE_TAKE_BACK_2808 = 2808;

    
}
