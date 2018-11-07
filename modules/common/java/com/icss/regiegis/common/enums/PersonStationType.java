package com.icss.regiegis.common.enums;

import java.util.ArrayList;
import java.util.List;

public enum PersonStationType
{
    /**
     * 专管员
     */
    SGY("sgy"),
    /**
    * 系统角色 中队长
    */
    ZMGLSSZ("zmglssz"),
    /**
    * 系统角色 省局综合管理员
    */
    SJZHGLY("sjzhgly"),
    /**
    * 系统角色 市局综合管理员
    */
    SHIJZHGLY("shijzhgly"),
    /**
    * 系统角色 稽查队队长
    */
    JCDDZ("jcddz"),
    /**
    * 系统角色 县局专卖办
    */
    XJZMB("xjzmb"),
    /**
    * 系统角色 市局专卖科
    */
    SHZMK("shzmk"),
    /**
    * 系统角色 分县局领导
    */
    FXJLD("fxjld"),
    /**
    * 系统角色 市局领导
    */
    SHIJLD("sjld"),
    /**
    * 系统角色 证件协同信息处理人
    */
    XXXTCLR("xxxtclr"),
    
    /**
    * 系统角色 省局领导
    */
    SHENGJLD("SJLD"),
    
    /**
    * 模型上报审核员 
    */
    MXSBSHY("SMP"),

    /**
     *  空
     */
    NULL(" ");
    PersonStationType(String value)
    {
        this.value = value;
    }

    private String value;

    public String getValue()
    {
        return value;
    }

    public static List<PersonStationType> checkStationType(String roles)
    {
        List<PersonStationType> types = new ArrayList<PersonStationType>();
        for (PersonStationType type : PersonStationType.values())
        {
            if (roles.indexOf(type.getValue()) != -1)
            {
                types.add(type);
            }
        }
        return types;
    }
}
