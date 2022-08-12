package com.boykin.lib_processor;

import com.boykin.lib_annotation.BeforeClick;
import com.boykin.lib_annotation.SuperField;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
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
import javax.tools.JavaFileObject;

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
        printLog("init");
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
        printLog("process");
        // 拿到添加SuperField注解的成员变量
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(SuperField.class);
        try {
            // 创建类源文件
            JavaFileObject javaFileObject = processingEnv.getFiler().createSourceFile("SuperUtil");
            Writer writer = javaFileObject.openWriter();
            // 包路径，写app模块的
            writer.write("package com.boykinchoi.star.annotation;\n");
            writer.write("\n");
            writer.write("public class SuperUtil{\n");
            for (Element element : elements) {
                // 拿到成员变量名
                Name simpleName = element.getSimpleName();
                writer.write(" //打印" + simpleName + " \n");
                //拼接方法
                writer.write("public static void print" + simpleName + "(){\n");
                writer.write("System.out.println(\"I am super " + simpleName + "\"); \n}\n");
            }
            writer.write("}\n");
            writer.flush();
            writer.close();
            // TODO 除了拼接生成外，也可使用JavaPoet生成
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Element element : elements) {
//            // 拿到成员变量名
//            Name simpleName = element.getSimpleName();
//            // 输出成员变量名
//            printLog("simpleName:" + simpleName.toString());
//        }
        // 该方法返回true表示该注解已经被处理, 后续不会再有其他处理器处理; 返回false表示仍可被其他处理器处理
        return false;
    }

    /**
     * 要扫描的注解，可以添加多个,必须实现此方法,process()方法才会执行
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> annotations = new HashSet<>();
        annotations.add(BeforeClick.class.getCanonicalName());
        annotations.add(SuperField.class.getCanonicalName());
        return annotations;
    }

    /**
     * 编译版本，固定写法就可以
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return processingEnv.getSourceVersion();
    }

    private void printLog(String msg) {
        //java输出
        //System.out.println(msg);
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "MyProcessor：" + msg);
    }
}