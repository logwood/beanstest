package org.example.lib;

import java.lang.reflect.Field;
import java.util.List;

public class A_B {
    public String str;
    public String classname;
    public int num;
    public int type=0;
    public void make(List<A_B>handlers) throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        Class clazz=A.class;
        A a=(A)clazz.newInstance();
         for(int i=0;i<handlers.size();i++){
             if (handlers.get(i).type==0){
                 try {
                     Field declared=clazz.getDeclaredField("str");
                     declared.setAccessible(true);
                     declared.set(a,handlers.get(i).str);
                     Field declare=clazz.getDeclaredField("num");
                     declare.setAccessible(true);
                     declare.set(a,handlers.get(i).num);
                    System.out.println("A"+"{str="+a.str+",num="+a.num+"}");
                 }catch (Exception E){

                 }
             }else {
                 Class clas=B.class;
                 B b = new B();
                 Field declared=clas.getDeclaredField("refA");
                 declared.setAccessible(true);
                 declared.set(b,a);
                 System.out.println("B{refA=A{"+"str="+a.str+",num="+a.num+"}}");
             }
         }
    }
    public static class A {
    private String str;
    private int num;
}
    public class B {
        private A refA;
    }
}
