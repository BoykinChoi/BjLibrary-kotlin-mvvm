package com.boykinchoi.star.annotation;
import com.boykin.lib_annotation.SuperField;
//import com.boykinchoi.star.annotation.SuperUtil;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2022/8/9 17:48
 */
public class AnnotationTest2 {
    @SuperField
    private int unionId;
    @SuperField
    private String userName;
    @SuperField
    private String info;

    public AnnotationTest2(){
//        SuperUtil.printunionId();
//        SuperUtil.printuserName();
    }
}
