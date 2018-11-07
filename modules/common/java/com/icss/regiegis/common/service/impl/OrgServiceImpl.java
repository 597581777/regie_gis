package com.icss.regiegis.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.libs.StringUtil;
import com.chinasofti.ro.bizframework.core.orm.IDAO;
import com.icss.regiegis.common.enums.PersonStationType;
import com.icss.regiegis.common.model.OrgModel;
import com.icss.regiegis.common.model.TreeVO;
import com.icss.regiegis.common.service.OrgService;
import com.icss.regiegis.common.service.UserService;
import com.icss.regiegis.common.util.LogUtil;

/**
 * <p>Service Impl</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class OrgServiceImpl implements OrgService {
	Logger log = LoggerFactory.getLogger(this.getClass());
	private IDAO dao;
	private UserService userService;
	
	@Override
	public List<TreeVO> getParentOrg(OrgModel orgModel) {
		try {
			String sql = orgModel.getSql("findParentOrg", dao.dbMeta());
			@SuppressWarnings("unchecked")
			List<TreeVO> list = dao.createNamedQuery(TreeVO.class, sql, orgModel).list();
			return list;
		} catch (Exception e) {
			LogUtil.error(e);
			log.error("============getOrgRoot====获取组织机构树失败==============");
			return null;
		}
	}
	
	@Override
	public List<TreeVO> getOrgList(OrgModel orgModel) {
		try {
			String sql = orgModel.getSql("findOrg", dao.dbMeta());
			@SuppressWarnings("unchecked")
			List<TreeVO> list = dao.createNamedQuery(TreeVO.class, sql, orgModel).list();
			return list;
		} catch (Exception e) {
			LogUtil.error(e);
			log.error("============getOrgList====获取组织机构列表失败==============");
			return null;
		}
	}
	
	@Override
	public List<TreeVO> findGridList(String personUuid, String deptCode, String gridCode, String roleCode) {
		List<TreeVO> list = null;
		// 获取人员角色市管员返回：sgy,管理所所长返回：zmglssz,如果都不是，则返回空
        if(roleCode!=null && roleCode.equals(PersonStationType.SGY.getValue())){
        	//findUserGridList
        	if(!StringUtil.isEmpty(gridCode)){
        		//获取五级网格
        		list = findChildGridList(gridCode);
        	}else if(!StringUtil.isEmpty(personUuid)){
    			//获取网格树根节点
        		TreeVO root = findRootGrid(personUuid, deptCode);
        		if(null!=root){
        			List<TreeVO> childs = findUserGridList(personUuid);
        			root.setChildren(childs);
        			list = new ArrayList<TreeVO>();
        			list.add(root);
        		}
    		}
        }else if(roleCode!=null && roleCode.equals(PersonStationType.ZMGLSSZ.getValue())){
        	// 专卖所所长 先取市管员 再去取市管员对应的网格
        	
        }
        return list;
	}
	
	public TreeVO findRootGrid(String personUuid, String deptCode){
		OrgModel orgModel = new OrgModel();
		orgModel.setPersonUuid(personUuid);
		orgModel.setDeptCode(deptCode);
		String sql = orgModel.getSql("findParentGrid", dao.dbMeta());
		TreeVO root = (TreeVO)dao.createNamedQuery(TreeVO.class, sql, orgModel).first();
		return root;
	}
	
	public List<TreeVO> findUserGridList(String personUuid){
		OrgModel orgModel = new OrgModel();
		orgModel.setPersonUuid(personUuid);
		String sql = orgModel.getSql("findUserGridList", dao.dbMeta());
		List<TreeVO> list = dao.createNamedQuery(TreeVO.class, sql, orgModel).list();
		return list;
	}
	
	public List<TreeVO> findChildGridList(String gridCode){
		OrgModel orgModel = new OrgModel();
		orgModel.setGridCode(gridCode);
		String sql = orgModel.getSql("findChildGridList", dao.dbMeta());
		List<TreeVO> list = dao.createNamedQuery(TreeVO.class, sql, orgModel).list();
		return list;
	} 
	/**
	 * 
	 * <p>Description: 获取人员所属网格列表<p>
	 * @return
	 * @author haoyajie 2017-7-25
	 */
	public List<TreeVO> findRootGridList(String personUuid, String deptCode){
		OrgModel orgModel = new OrgModel();
		orgModel.setPersonUuid(personUuid);
		orgModel.setDeptCode(deptCode);
		String sql = orgModel.getSql("findUserGridList", dao.dbMeta());
		List<TreeVO> list = dao.createNamedQuery(TreeVO.class, sql, orgModel).list();
		return list;
	}
	
	public void setDao(IDAO dao) {
		this.dao = dao;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}


}