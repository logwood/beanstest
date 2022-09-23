package org.example;

public class Main {
    /**
     * @param args 前面用于进行普通的beans解析，后面用于XMLbeans解析。
     */
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        beanfactory bins=new beanfactory();//try {
//            String bool= "hello.xml";
//            System.out.println(bool.isEmpty());
//            bins.xmlBean("src/hello.xml");
//
//        }catch (Exception e){
//          System.out.println(e);
//        }

        Object glimmer=bins.getBean("glimmer");
        System.out.println(glimmer);

    }
}
