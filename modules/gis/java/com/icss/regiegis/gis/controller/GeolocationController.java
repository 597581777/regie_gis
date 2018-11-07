package com.icss.regiegis.gis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chinasofti.ro.bizframework.core.mvc.Controller;
import com.chinasofti.ro.bizframework.core.mvc.View;

/**
 * <p>controller</p>
 * 
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class GeolocationController extends Controller {
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * <p>显示列表</p>
	 * 
	 */
	public void index() {
		View view = new View("success");
		view.bind("contextPath", request().getContextPath());
		this.render(view);
	}

}