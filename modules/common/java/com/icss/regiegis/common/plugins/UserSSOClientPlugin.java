package com.icss.regiegis.common.plugins;

import javax.servlet.FilterConfig;

import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sso.agent.SSOR1Agent;
import com.icss.resourceone.sso.token.Token;
import com.icss.ro.framework.plugin.PluginException;
import com.icss.ro.framework.plugin.sso.SSOClientPlugin;
import com.icss.ro.framework.plugin.sso.impl.BaseSSOClientPlugin;


/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright ICSS(c) 2016</p>
 * @author <a href="mailTo:haoyajie@chinasofti.com">haoyajie</a>
 * @version 1.0
 * @history:
 * Created by haoyajie 2017-6-13
 */
public class UserSSOClientPlugin extends BaseSSOClientPlugin implements
		SSOClientPlugin {

//	@Override
//	public void beforLogin(Object ssoAgent, FilterConfig fc)
//			throws PluginException {
//		// TODO Auto-generated method stub
//		super.beforLogin(ssoAgent, fc);
//        SSOR1Agent sso=(SSOR1Agent)ssoAgent;
//        Token token=sso.getToken();
////        String userid=token.getUserId();//映射前的账号
////        String mappedUserid=getMappedUserId(userid);
////        token.put(R1Token.USERID, mappedUserid);//使用映射后的账号更新token中的userid
//
//	}
	
	@Override
	public void login(Object ssoAgent, FilterConfig fc, UserInfo user)
			throws PluginException {
		// TODO Auto-generated method stub
		super.login(ssoAgent, fc, user);
	}
	
	
}
