package org.example;

import org.example.lib.Bean;
import org.example.lib.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class IocContainer {
    //Ioc容器 存储的键值对为 <类的完全限定名称,该类的一个对象>
    private Map<String, Object> container = new HashMap<String, Object>();

    Object what=null;
    //Ioc容器可扫描到该包及其子包下的所有类
    private String packageName;
    private String stringname;
    public IocContainer(String packageName, String str) {
        this.packageName = packageName;
        this.stringname=str;
        try{
            //添加组件到容器
            loadComponent();

            what=assemble();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadComponent() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        //用于获取包对应的URL,而URL中的分隔符为“/”, 所以将包路径的分隔符“.” 用“/”代替
        String packagePath = packageName.replace(".","/");

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource(packagePath);

        //通过获取此资源的协议名称,判断包名对应的资源类型
        String protocol = resource.getProtocol();

        if(!"file".equals(protocol)){
            //只测试protocol为file的情况，其他情况还有jar等
            return;
        }

        //获取了指定包及其子包下所对应的所有以suffix(.class)结尾的文件路径
        List<String> filePathList = listFilePath(resource.getPath());

        //获取类的完全限定名称
        List<String> fullClassNameList = listFullClassName(filePathList);

        //加载component到容器中
        for (String fullClassName : fullClassNameList) {
            addToContainer(fullClassName);
        }
    }

    private List<String> listFilePath(String directoryPath) throws IOException {
        List<String> filePathList = new ArrayList<String>();

        //参数校验
        if(null==directoryPath){
            return filePathList;
        }

        File directoryFile = new File(directoryPath);
        if(!directoryFile.isDirectory()){
            return filePathList;
        }

        String filePath = null;
        File[] files = directoryFile.listFiles();
        for (File file : files) {
            if(!file.isDirectory()){
                filePath = file.getCanonicalPath();
                if(filePath.endsWith(".class")){
                    filePathList.add(filePath);
                }
            }
            else{
                //递归调用
                filePathList.addAll(listFilePath(file.getCanonicalPath()));
            }
        }

        return filePathList;
    }
     private List<String> listFullClassName(List<String> filePathList){
        List<String> fullClassNameList = new ArrayList<String>();
        if(null==packageName||null==filePathList){
            return fullClassNameList;
        }

        String packagePath = packageName.replace(".","\\");

        for (String filePath : filePathList) {
            fullClassNameList.add(filePath.substring(filePath.indexOf(packagePath),filePath.indexOf(".class")).replace("\\","."));
        }
        return fullClassNameList;
    }

    private void addToContainer(String fullClassName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class classObject = Class.forName(fullClassName);
        if(!classObject.isInterface()
                &&null!=classObject.getAnnotation(Component.class)){
            //如果扫描的Class对象不是接口类型且有@Component注解，就将对应的component装配到容器中
            try{
                container.put(fullClassName,classObject.getConstructor().newInstance());
            }
            catch (Exception e){

            }
        }
    }
    private Object assemble() throws IllegalAccessException, ClassNotFoundException  {
        Set<Map.Entry<String, Object>> entrySet = container.entrySet();
        Object ohb=null;
        for (Map.Entry<String, Object> entry : entrySet) {
            String[] s = new String[]{};
            Class classObj = Class.forName(entry.getKey());
            try {
               ohb=classObj.newInstance();
            }
            catch (Exception e){
            }
            Method[] declaredFieldMethod = classObj.getDeclaredMethods();
            for(Method met : declaredFieldMethod){
                if(met.getAnnotation(Bean.class)!=null){
                    try {
                        String value=stringname;
                        String val=met.getAnnotation(Bean.class).value()[0];
                       if(val.equals(value)){
                           Object IS=met.invoke(ohb);
                           IS.getClass().getDeclaredField("location").setAccessible(true);
                           IS.getClass().getDeclaredField("memberNum").setAccessible(true);
                           return IS;
                       }

                    }
                    catch (Exception e){
                    }
                }

            }
        }
        return null;
    }
}
