package com.icss.regiegis.common.util;

import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class UserUtil {
	/**
	 * title:当前登录人员uuid
	 * @return
	 */
	public static String getPersonUuid(){
		String uuid ="";
		try{
			if(Context.getInstance()!=null){
				uuid = Context.getInstance().getCurrentPersonUuid();
			}
		}catch(Exception ex){
			LogUtil.error(ex);
		}
		return uuid;
	}
	
	/**
	 * title:当前登录人员信息，如uuid,姓名等。
	 * @return
	 */
	public static UserInfo getUserInfo(){
		UserInfo userInfo =null;
		try{
			if(Context.getInstance()!=null){
				userInfo = Context.getInstance().getCurrentLoginInfo();
			}
		}catch(Exception ex){
			LogUtil.error(ex);
		}
		return userInfo;
	}
}
