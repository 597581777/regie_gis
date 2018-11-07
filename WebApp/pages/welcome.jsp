<%
/**
	敬告：本页面是开发辅助页面，只能用于开发时，由于没有权限控制，所以严禁在生产环境中使用，否则后果自负。
	注意：BizFoundation默认提供的war包build任务不会包含此页面。
*/
%>
<%@page import="com.chinasofti.ro.bizframework.core.libs.BeanFactory"%>
<%@page import="com.chinasofti.ro.bizframework.core.mvc.ActionMapper"%>
<%@page import="com.chinasofti.ro.bizframework.core.mvc.Action"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.TreeMap"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.io.*" %>
<%@page import="org.dom4j.*" %>
<%@page import="org.dom4j.io.*" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ include file="/pages/inc/taglibs.jsp" %>
<%
String root = application.getRealPath("");
root = root.substring(0,(root.length() - 6)) + "modules/";

try{
	File modules = new File(root);
	String[] names = modules.list();
	for(int i=0;i<names.length;i++){
		String mName = root + names[i] +"/.module";
		File mFile = new File(mName);
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(mFile);
		List cNode = document.selectNodes("//code");
		List mNode = document.selectNodes("//Mark");
		for(int j = 0;j<cNode.size();j++){
			Element cEle = (Element)cNode.get(j);
			Element mEle = (Element)mNode.get(j);
			moduleMapping.put(cEle.getData(),mEle.getData());
		}
	}
}catch(Exception e){
	e.printStackTrace();
	out.println("<H1>此页面只能使用Studio提供的启动Tomcat的功能启动应用访问!</H1>");
	return;
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>BizFoundation基础开发平台辅助开发页面</title>
<%@ include file="/pages/inc/header.jsp" %>
</head>
<style>
body{	
	padding: 0 20px;
	overflow-x: hidden;
	background-color: white;
}
#main {
	margin: 5px;
	margin-right:30px;
}
#contain{
	margin-left:2px;
}
#displayDiv{
	margin-top:10px;
}
.header-left{
	padding:5px 0px;
	font-size: 18px;
	color:#336699;
}
.header-right{
	position:absolute;
	right:10px;
	top:10px;
	width:200px;
}
.jMenu {
    margin: 0;
    padding: 0;
}
/* First level */
.jMenu li {
    display: table-cell;
    background-color: #336699;
    border-right: 1px solid white;
}
.jMenu li a {
    padding: 8px 12px;
    display: block;
    background-color: transparent;
    text-decoration: none;
    color: white;
    cursor: pointer;
    font-size: 12px;
    white-space: nowrap
}
/* Lower levels */
.jMenu li ul {
    display: none;
    position: absolute;
	z-index:9999;
    padding: 0;
    margin: 0;
}
.jMenu li ul li {
    background-color: #A9C251;
    display: block;
    border-bottom: 1px solid #484548;
    padding: 0;
}
.jMenu li ul li.arrow {
    background-color: #322f32;
    background-repeat: no-repeat;
    background-position: center center;
    height: 5px;
    padding: 0;
    border-bottom: none;
    padding-bottom: 10px
}
.jMenu li ul li a {
    font-size: 11px;
    text-transform: none;
    padding: 7px;
    display: block;
    border-top: 1px solid transparent;
    border-bottom: 1px solid transparent;
}
.jMenu li ul li a.isParent {
    background-color: #336699;
    background-repeat: no-repeat;
    background-position: right center;
}
.jMenu li ul li a:hover {
    background-color: #514c52;
    border-top: 1px solid #322f32;
    border-bottom: 1px solid #322f32;
}

#select-skin a {
    border: 1px solid #666666;
    float: left;
    height: 9px;
    margin: 0 5px 10px 0;
    overflow: hidden;
    width: 9px;
}
</style>
<script type="text/javascript">
;(function(b,c) {
    b('*').hover(function() {b(this).data(c, 1)},
    function() {b(this).data(c, 0)}).data(c, 0);
    b[c] = function(a) { return b(a)[c]()};
    b.fn[c] = function(a) { a = 0;
        b(this).each(function() {
            a += b(this).data(c)
        });
        return a > 0;
    }
})(jQuery, 'isHovered');
(function($) {
    $.jMenu = {
        defaults: {
            ulWidth:           'auto',
            absoluteTop:       30,
            absoluteLeft:      0,
            TimeBeforeOpening: 100,
            TimeBeforeClosing: 100,
            animatedText:      false,
            paddingLeft:       7,
            openClick:         false,
            effects: {
                effectSpeedOpen:  150,
                effectSpeedClose: 150,
                effectTypeOpen:   'fade',
                effectTypeClose:  'hide',
                effectOpen:       'linear',
                effectClose:      'linear'
            }
        },
        init: function(options) {
            opts = $.extend({}, $.jMenu.defaults, options);
            $(".jMenu a:not(.fNiv)").each(function() {
                var $thisChild = $(this);
                if($.jMenu._IsParent($thisChild)) {
                    $thisChild.addClass('isParent');
                }
                if(opts.animatedText) {
                    $.jMenu._animateText($thisChild);
                }
                if(!opts.openClick) {
                    $thisChild.bind({
                        mouseover:function() {
                            $.jMenu._hide($thisChild);
                            $.jMenu._showNextChild($thisChild);
                        }
                    });
                } else {
                    $thisChild.bind({
                        click:function() {
                            $.jMenu._hide($thisChild);
                            $.jMenu._showNextChild($thisChild);
                        }
                    });
                }
            });
            if(!opts.openClick) {
                $('.jMenu li a.fNiv').bind({
                    mouseover: function() {
                        var $this = $(this);
                        var $child = $this.next();
                        if (($child.length > 0) && ($child.is(':hidden') == false)) {
                            return;
                        }
                        ULWidth = $.jMenu._returnUlWidth($this);
                        $.jMenu._closeList($(".jMenu ul"));
                        if ($child.is(':hidden')) {
                            $.jMenu._showFirstChild($this);
                        }
                    }
                });
            } else {
                $('.jMenu li a.fNiv').bind({
                    click: function(e) {
                        e.preventDefault();
                        var $this = $(this);
                        var $child = $this.next();
                        ULWidth = $.jMenu._returnUlWidth($this);
                        $.jMenu._closeList($(".jMenu ul"));
                        if($child.is(':hidden')) {
                            $.jMenu._showFirstChild($this);
                        }
                    }
                });
            }
            $('.jMenu').bind({
                mouseleave: function() {
                    setTimeout(function(){$.jMenu._closeAll();},opts.TimeBeforeClosing);
                }
            });
        },
        _showFirstChild: function(el) {
            if($.jMenu._IsParent(el)) {
                var SecondList = el.next();
                if(SecondList.is(":hidden")) {
                    var position = el.position();
                    SecondList.
                            css({
                                top:   position.top + opts.absoluteTop,
                                left:  position.left + opts.absoluteLeft
                               // width: ULWidth
                            })
                          //  .children().css({ width: ULWidth })
                        ;

                    $.jMenu._show(SecondList);
                }
            } else {
                return false;
            }
        },
        _showNextChild: function(el) {
            if($.jMenu._IsParent(el)) {
                var ChildList = el.next();
                if(ChildList.is(":hidden")) {
                    var position = el.position();
                    ChildList.css({
                                top:   position.top,
                                left:  position.left + ULWidth
                                //width: ULWidth
                            })
                            //.children().css({ width:ULWidth })
                        ;
                    $.jMenu._show(ChildList);
                }
            } else {
                return false;
            }
        },
        _hide: function(el) {
            if($.jMenu._IsParent(el) && !el.next().is(':hidden')) {
                $.jMenu._closeList(el.next());
            } else if (($.jMenu._IsParent(el) && el.next().is(':hidden')) || !$.jMenu._IsParent(el)) {
                $.jMenu._closeList(el.parent().parent().find('ul'));
            } else {
                return false;
            }
        },
        _show: function(el) {
            switch(opts.effects.effectTypeOpen) {
                case 'slide':
                    el.stop(true, true).delay(opts.TimeBeforeOpening).slideDown(opts.effects.effectSpeedOpen, opts.effects.effectOpen);
                    break;
                case 'fade':
                    el.stop(true, true).delay(opts.TimeBeforeOpening).fadeIn(opts.effects.effectSpeedOpen, opts.effects.effectOpen);
                    break;
                default:
                    el.stop(true, true).delay(opts.TimeBeforeOpening).show();
            }
        },
        _closeList: function(el) {
            switch(opts.effects.effectTypeClose) {
                case 'slide':
                    el.stop(true,true).slideUp(opts.effects.effectSpeedClose, opts.effects.effectClose);
                    break;
                case 'fade':
                    el.stop(true,true).fadeOut(opts.effects.effectSpeedClose, opts.effects.effectClose);
                    break;
                default:
                    el.hide();
            }
        },
        _closeAll: function() {
            if (!$('.jMenu').isHovered()) {
                $('.jMenu ul').each(function() {
                    $.jMenu._closeList($(this));
                });
            }
        },
        _IsParent: function(el) {
            if (el.next().is('ul')) {
                return true;
            } else {
                return false;
            }
        },
        _returnUlWidth: function(el) {
            switch(opts.ulWidth) {
                case "auto" :
                    ULWidth = parseInt(el.outerWidth(true));
                    break;
                default:
                    ULWidth = parseInt(opts.ulWidth);
            }
            return ULWidth;
        },
        _animateText: function(el) {
            var paddingInit = parseInt(el.css('padding-left'));
            el.hover(
                function() {
                    $(this).stop(true,true).animate({
                        paddingLeft: paddingInit + opts.paddingLeft}, 100);
                },
                function() {
                    $(this).stop(true,true).animate({
                                    paddingLeft:paddingInit}, 100);
                }
            );
        },
        _isReadable: function() {
            if ($("a.fNiv").length > 0) {
                return true;
            } else {
                return false;
            }
        },
        _error: function() {
            alert('Please, check you have the \'.fNiv\' class on your first level links.');
        }
    };
    jQuery.fn.jMenu = function(options){
        $(this).addClass('jMenu');
        $(this).children('li').children('a').addClass('fNiv');
        if($.jMenu._isReadable()) {
            $.jMenu.init(options);
        } else {
            $.jMenu._error();
        }
    };
})(jQuery);

function selectSkin(skinName){
	biz.utils.switchTheme(skinName);
	window.frames['displayUI'].location.reload();
}

$(document).ready(function() {
    $("#jMenu").jMenu();
});
</script>
<body>
<div id="main">
	<div id="header">
		<div class="header-left"><strong>BizFoundation</strong>开发辅助页</div>
		<div class="header-right">
			<div id="select-skin" style="position:absolute;right:120px;top:10px">
				<a style="background:#5791C3;" title="默认蓝色皮肤" href="#" onclick="selectSkin('default');"></a>
				<a style="background:#F3AE20;" title="桔黄色皮肤" href="#" onclick="selectSkin('orange');"></a>
				<a style="background:#EEEEEE;" title="灰色皮肤" href="#" onclick="selectSkin('gray');" ></a>
				<!-- <a style="background:#91ebff;" title="类Ext皮肤" href="#" onclick="selectSkin('extblue');"></a> -->
			</div>
			<div style="position:absolute;right:10px;top:6px">
				|<a href="<c:url value="/about.jsp"/>" target="displayUI">关于BizFoundation</a>
			</div>
		</div>
	</div>
	<div id="contain">
		<ul id="jMenu">		
<%
	Map newMap = new TreeMap();
	Map actions = ActionMapper.getAccessActions();
	newMap.putAll(actions);
			
	Iterator modules = actions.entrySet().iterator();
	int size = actions.size();
	StringBuffer more = new StringBuffer("");
	if(size>=ALLOW_DISPLAY_MENU_COUNT){
		more.append("<li><a class=\"arrow\" href=\"#\">更多>></a>");
		more.append("<ul>");
		int len = size - ALLOW_DISPLAY_MENU_COUNT;
		for(;modules.hasNext()&&len>0;len--){
			Map.Entry module = (Map.Entry)modules.next();
			buildMenu(module,more,"arrow",request.getContextPath());
			newMap.remove(module.getKey());
		}
	}
			
	modules = newMap.entrySet().iterator();
	StringBuffer buffer = new StringBuffer();
	while(modules.hasNext()){
		Map.Entry module = (Map.Entry)modules.next();
		buildMenu(module,buffer,"fNiv",request.getContextPath());
	}
	
	if(more.length()>0){
		more.append(buildInnMenu(request.getContextPath()));
		more.append("</ul>");
		more.append("</li>");
	}else{
		more.append(buildInnMenu(request.getContextPath()));
	}
	
	out.print(buffer.toString());
	out.print(more.toString());
%>
		</ul>
	</div>
	<div class="clear"></div>	
	<hr>
	<div id="displayDiv">
		<iframe name="displayUI" frameborder="0" src="<%=defUrl %>" scrolling="auto" style="width:100%;height:560px"/>
	</div>
	<div class="clear"></div>
</div>
</body>
</html>
<%!
// 允许显示的菜单的最大个数，当超过最大数后会自动隐藏
int ALLOW_DISPLAY_MENU_COUNT = 10;
Map moduleMapping = new HashMap();
String defUrl = "/";

private boolean schedulerExists(){
	try{
		return BeanFactory.getBean("quartzScheduler")!=null;
	}catch(org.springframework.beans.factory.NoSuchBeanDefinitionException e){
	} 
	return false;
}

private void buildMenu(Map.Entry module,StringBuffer buf,String css,String contextPath){
	if(module == null) return;
	String moduleName = (String)module.getKey();
	if(moduleMapping.get(moduleName)!=null){
		moduleName = (String)moduleMapping.get(moduleName);
	}
	buf.append("<li><a class=\""+css+"\" href=\"#\">" + moduleName + "</a>");
	buf.append("<ul>");
	Iterator actions = ((List)module.getValue()).iterator();
	while(actions.hasNext()){
		buf.append("<li>");
		Action action = (Action)actions.next();
		String displayName = action.getPath();
		if(action.getDisplayName() != null){
			displayName += " [" + action.getDisplayName() + "]";
		}
		String url = contextPath + action.getPath();
		defUrl = url;
		buf.append("<a href="+url+" target=\"displayUI\">"+displayName+"</a>");
		buf.append("</li>");
	}
	buf.append("</ul>");
	buf.append("</li>");
}
	
private String buildInnMenu(String contextPath){
	StringBuffer buffer = new StringBuffer();
	buffer.append("<li><a href=\"#\">内置组件</a>");
	buffer.append("<ul>");
	buffer.append("<li>");
	if(schedulerExists()){
		buffer.append("<a href=\""+contextPath+"/bizconsole/schedule\" target=\"displayUI\">计划任务</a>");
	}else{
		buffer.append("<a href=\""+contextPath+"/pages/schedule-help.jsp\" target=\"displayUI\">计划任务</a>");
	}
	buffer.append("</li>");
	buffer.append("<li>");
	buffer.append("<a href=\""+contextPath+"/bizconsole/dsmonitor\" target=\"displayUI\">数据源监控</a>");
	buffer.append("</li>");
	buffer.append("</ul>");
	buffer.append("</li>");
	return buffer.toString();
}
%>