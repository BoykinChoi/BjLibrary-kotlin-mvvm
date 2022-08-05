package com.boykin.lib_processor;

import com.boykin.lib_annotation.BeforeClick;
import com.boykin.lib_annotation.SuperField;
import com.google.auto.service.AutoService;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

/**
 * 自定义注解处理器：
 * - 步骤1，创建类型为Java or Kotlin Library的module
 * - 步骤2，继承AbstractProcessor
 * - 步骤3，添加注解：@AutoService(Process.class)，固定写法
 * created by 蔡宝
 */
@AutoService(Processor.class) //注意Processor不能导错
public class MyProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        String msg = "MyProcessor：Hello,Apt!";
        //java输出
        System.out.println(msg);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }

    /**
     * 扫描注解回调
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        // 拿到添加SuperField注解的成员变量
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(SuperField.class);
        for (Element element : elements) {
            //拿到成员变量名
            Name simpleName = element.getSimpleName();
            //输出成员变量名
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, simpleName);

        }
        return false;
    }

    /**
     * 要扫描扫描的注解，可以添加多个
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet set = new HashSet<>();
        set.add(BeforeClick.class.getCanonicalName());
        set.add(SuperField.class.getCanonicalName());
        return set;
    }

    /**
     * 编译版本，固定写法就可以
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }
}