package com.boykinchoi.star.reflect;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

//import me.jessyan.autosize.utils.LogUtils;

/**
 * 反射测试类
 *
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/12/25 9:47
 */
public class ReflectTest {

    /**
     * 通过反射获取类的所有变量
     */
    public void printFields() {
        // 1.获取类并输出类的名称
        Class sClass = Son.class;
        System.out.println("类的名称=" + sClass.getName());

        // 2.1 获取所有public 访问权限的变量
        // 输出 SonClass 类以及其所继承的父类( 包括 FatherClass 和 Object ) 的 public 方法
//        Field[] fields = sClass.getFields();

        // 2,2 获取所有本类声明的变量(不问访问权限), 输出 SonClass 类的所有成员变量
        Field[] fields = sClass.getDeclaredFields();

        // 3.遍历变量输出变量信息
        for (Field field : fields) {
            // 获取访问权限并输出
            int modifiers = field.getModifiers();
            System.out.print("访问权限=" + Modifier.toString(modifiers));

            // 输出变量的类型及变量名
            System.out.println(" 变量类型=" + field.getType().getName() + " 变量名=" + field.getName());
        }
    }

    /**
     * 通过反射获取类的所有方法
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void printMethods() {
        // 1.获取类并输出类的名称
        Class sClass = Son.class;
        System.out.println("类的名称=" + sClass.getName());

        // 2.1 获取所有public 权限的方法 包括自己声明和从父类继承的
//        Method[] methods = sClass.getMethods();

        // 2.2 获取所有本类的方法，不问访问权限
        Method[] methods = sClass.getDeclaredMethods();

        for (Method method : methods) {
            int modifiers = method.getModifiers();
            System.out.print(" 方法名:" + method.getName());
            System.out.print(" 访问权限=" + Modifier.toString(modifiers));

            // 获取返回类型
            Class returnType = method.getReturnType();
            System.out.print(" 返回类型=" + returnType.getName());

            // 获取方法所有参数
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                System.out.print(" 参数类型=" + parameter.getType().getName() + " 参数名=" + parameter.getName());
            }

            Class[] exceptionTypes = method.getExceptionTypes();
            if (exceptionTypes.length == 0) {
                System.out.println(" 方法没有抛出异常");
            } else {
                for (Class c : exceptionTypes) {
                    System.out.println(" 方法抛出 "
                            + c.getName() + "异常");
                }
            }
        }
    }

    /**
     * 对象是无法访问或操作类的私有变量和方法的，但是，通过反射，我们就可以做到
     */
    public void getPrivateMethods() {
        Bank bank = new Bank();
        Class bClass = bank.getClass();
        Method privateMethod;
        // 获取私有方法
        try {
            // 第一个方法为要获取方法的名称
            // 第二个方法为要获取方法的参数顾类型，参数为Class,没有参数就是null
            privateMethod = bClass.getDeclaredMethod("privateMethod", String.class, int.class);

            // 开始操作方法
            if (privateMethod != null) {
                // 获取私有方法的访问权
                // 只是获取访问权，并不是修改实际权限,
                // 获取私有方法的访问权限，如果不加 setAccessible(true)会报异常 IllegalAccessException
                privateMethod.setAccessible(true);
                // 使用invoke反射调用私有方法
                // 第一个方法传入privateMethod拥有者类的对象，后面两个参数传方法的实际参数
                privateMethod.invoke(bank, "USA Bank ", 38293232);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改对象私有变量的值
     * 为简洁代码，在方法上抛出总的异常
     */
    public void modifyPrivateFiled() throws Exception {
        Bank bank = new Bank();
        Class bClass = bank.getClass();

        // 获取私有成员变量
//        Field privateField = bClass.getField("MSG");
        Field privateField = bClass.getDeclaredField("MSG");
//        Field privateField = bClass.getDeclaredField("FINAL_MSG2");

        // 操作私有变量
        if (privateField != null) {
            // 获取私有变量的访问权
            privateField.setAccessible(true);
            System.out.println("Before Modify：MSG = " + bank.getMsg());

            // 修改私有变量的值
            privateField.set(bank, "China Big Bank");
            System.out.println("After Modify：MSG = " + bank.getMsg());

        }
    }

    /**
     * 测试方法，通过反射获取sn码
     *
     * @return
     */
    public static String getPhoneSerialByReflect() {
        Object a = null;
        try {
            Class<?> mClass = Class.forName("android.os.Build");
            Constructor con = mClass.getDeclaredConstructor();
            if (!con.isAccessible()) {
                con.setAccessible(true);
            }
            // newInstance无参构造方法
            Object store = con.newInstance();
            Method[] methods = mClass.getDeclaredMethods();
            Method method = null;
            for (Method m : methods) {
                if ("getSerial".equalsIgnoreCase(m.getName())) {
                    method = m;
                    break;
                }
            }
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            a = method.invoke(store);
            if (a != null) {
                System.out.println("serial=" + a.toString());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (a != null) {
            return a.toString();
        }
        return "unknow";
    }
}
