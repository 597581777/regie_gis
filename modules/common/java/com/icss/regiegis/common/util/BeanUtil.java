package com.icss.regiegis.common.util;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

import com.chinasofti.ro.bizframework.core.utils.ResourceUtil;
import com.chinasofti.ro.bizframework.core.utils.XMLHelper;

/**
 * <p>Title: 对象处理工具类</p>
 * <p>Description: 对象处理工具类</p>
 * <p>Copyright: Copyright ICSS(c) 2014</p>
 * @author <a href="mailTo:hanlin@chinasofti.com">hanlin</a>
 * @version 1.0
 * @history:
 * Created by hanlin 2014-8-11
 */
public class BeanUtil
{

    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * <p>Description: 拷贝源对象的相同字段值(可以处理)复制到目标对象<p>
     * @param srcObj 源对象
     * @param destObj 目标对象
     * @author hanlin 2014-8-11
     */
    public static void copyProperties(Object srcObj, Object destObj)
    {
        copyPropertiesByDB(srcObj, destObj);
    }

    /**
     * <p>Description: 拷贝文书到办理记录方法<p>
     * @param srcObj 源对象
     * @param destObj 目标对象
     * @author hanlin 2014-9-16
     */
    public static void copyPropertiesByDB(Object srcObj, Object destObj)
    {
        copyPropertiesByDB(srcObj, destObj, null, null);
    }

    /**
     * <p>Description: 复制对象的值<p>
     * @param srcObj 源对象
     * @param destObj 目标对象
     * @param srcPrefix 源对象前缀
     * @param destPrefix 目标对象前缀
     * @author hanlin 2014-9-16
     */
    public static void copyPropertiesByDB(Object srcObj, Object destObj, String srcPrefix, String destPrefix)
    {
        if (null == srcObj)
        {
            return;
        }

        Class<?> srcClass = srcObj.getClass();
        Class<?> destClass = destObj.getClass();

        srcPrefix = null != srcPrefix ? srcPrefix : getTableProFixByClass(srcClass);
        destPrefix = null != destPrefix ? destPrefix : getTableProFixByClass(destClass);

        String srcPropertyPK = srcPrefix + "Id";
        Field[] fieldArray = srcClass.getDeclaredFields();
        for (Field field : fieldArray)
        {
            String srcPropertyName = field.getName();
            String destPropertyName = srcPropertyName;

            // 主键跳过，不拷贝 - 简单处理 modify by hanlin 2014-10-21
            if (srcPropertyPK.equals(srcPropertyName))
            {
                continue;
            }

            // 验证对象2是否包含此属性
            boolean isHas = true;
            try
            {
                // 验证该属性在受复制对象中是否存在
                destClass.getDeclaredField(destPropertyName);
            }
            catch (Exception e)
            {

                try
                {
                    if (0 != srcPropertyName.indexOf(srcPrefix))
                    {
                        continue;
                    }
                    // 替换掉对应的开头
                    destPropertyName = srcPropertyName.replaceFirst(srcPrefix, destPrefix);
                    destClass.getDeclaredField(destPropertyName);
                }
                catch (Exception e1)
                {
                    isHas = false;
                }
            }

            // 判断两个对象是否包含该属性
            if (!isHas)
            {
                continue;
            }

            try
            {
                // 获取源对象的get方法，获取参数
                String getMethodName = "get" + srcPropertyName.substring(0, 1).toUpperCase() + srcPropertyName.substring(1);

                // 获取目标对象set方法，设置参数
                String setMethodName = "set" + destPropertyName.substring(0, 1).toUpperCase() + destPropertyName.substring(1);

                Method methodGet = srcClass.getMethod(getMethodName, new Class[] {});
                Object srcObjValue = methodGet.invoke(srcObj, new Object[] {});
                if (null == srcObjValue)
                {
                    continue;
                }
                // copy对应的值(这个地方可以根据需求而改变)
                Method methodSet = destClass.getMethod(setMethodName, new Class[] {field.getType() });
                methodSet.invoke(destObj, new Object[] {srcObjValue });
            }
            catch (Exception e)
            {
                logger.error("propertyName: " + srcPropertyName);
                logger.error(e.getMessage(), e);
                // throw new RuntimeException("copy bean value error.");
            }
        }
    }

    /**
     * <p>Description: 数据库表字段命名规则前缀（根据类名获取）<p>
     * @param cls 类型
     * @return 字段前缀
     * @author hanlin 2014-9-16
     */
    private static String getTableProFixByClass(Class<?> cls)
    {
        String clsName = cls.getName();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < clsName.length(); i++)
        {
            char c = clsName.charAt(i);
            if (Character.isUpperCase(c))
            {
                sbf.append(c);
            }
        }
        return sbf.toString().toLowerCase();
    }

    /**
     * <p>Description: 获取HBM文件里面，对应字段的中文数值<p>
     * @param cls 类
     * @param propertyName 字段名
     * @return 注释
     * @author hanlin 2014-11-17
     */
    @SuppressWarnings({"rawtypes", "unused" })
    private static String getPropertyComment(Class<?> cls, String propertyName)
    {
        String xmlClasspath = cls.getName().replace(".", File.separator) + ".hbm.xml";
        Resource[] resources = ResourceUtil.loadResourceFromClassPath(xmlClasspath);
        XMLHelper xmlHelper = new XMLHelper();
        List errors = new ArrayList();
        EntityResolver entityResolver = XMLHelper.DEFAULT_DTD_RESOLVER;

        Document doc = null;
        try
        {
            doc = xmlHelper.createSAXReader("XML InputStream", errors, entityResolver).read(new InputSource(resources[0].getInputStream()));
        }
        catch (Exception e)
        {
            logger.error("持久化实体的映射文件不存在. xmlClasspath : " + xmlClasspath);
        }

        String comment = null;
        if (null != doc)
        {
            Element hbmNode = doc.getRootElement();
            Node commentNode = hbmNode.selectSingleNode("class/property[@name='" + propertyName + "']/column/comment");
            comment = commentNode.getText();
        }
        return comment;
    }
}
