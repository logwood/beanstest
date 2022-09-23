package org.example;

import org.example.lib.A_B;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SAXParserHandler extends DefaultHandler {
    int SAXindex=0;
    String str=null;
    int num;
    A_B b=null;
    String classname;
    public List<A_B> AB=new ArrayList<A_B>();
    public List<A_B> getAB(){
        return AB;
    }
    public void startElement(String uri, String localName, String qName, Attributes attributes)throws SAXException{
        super.startElement(uri,localName,qName,attributes);
        if(qName.equals(("bean"))){
            SAXindex++;
            b=new A_B();
            int num=attributes.getLength();
            for(int i=0;i<num;i++){
            }
        } else if (!qName.equals("bean")&&!qName.equals("beans")) {
            String name=attributes.getValue("property");
            int num=attributes.getLength();
            for(int i=0;i<num;i++){
                if(attributes.getValue(i).equals("str")){
                    b.str=attributes.getValue(++i);
                }else if(attributes.getValue(i).equals("num")){
                    System.out.println("--"+attributes.getValue(i));
                    b.num=Integer.parseInt(attributes.getValue(++i));

                }else if(attributes.getValue(i).equals("A")){
                    b.type=1;
                }
                System.out.println("--"+attributes.getValue(i).equals("ref"));
                System.out.println(i+1+attributes.getValue(i));
            }

        }
    }
    public void endElement(String uri, String localName, String qName)throws SAXException{
        super.endElement(uri, localName, qName);
        if(qName.equals("bean")){
            AB.add(b);
            System.out.println("结束一个");
            b=null;
        }

    }
    public void startDocument()throws SAXException{
        super.startDocument();
        System.out.println("Begin");
    }
    public void endDocument()throws SAXException{
        super.endDocument();
    }
    public void characters(char[] ch,int start,int length)throws SAXException{
        super.characters(ch,start,length);
        str=new String(ch,start,length);
        if(!str.trim().equals("")){
            System.out.println("节点值"+str);
        }
    }
}