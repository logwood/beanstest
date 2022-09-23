package org.example;

import org.example.classnames.Glimmer;
import org.example.lib.A_B;
import org.example.lib.BeanFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.List;

public class beanfactory implements BeanFactory {
    @Override
    public Object getBean(String str) {
        IocContainer incode=new IocContainer("org.example.classnames",str);
        System.out.println(incode.what);
        Glimmer glimmer=(Glimmer)(incode.what);
        return glimmer;
    }
    public String[] xmlBean(String str) throws ParserConfigurationException, SAXException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        File xmltry=new File("hello.xml");
        SAXParserFactory sf=SAXParserFactory.newInstance();
        SAXParser sp=sf.newSAXParser();
        SAXParserHandler handler=new SAXParserHandler();
        try {
            sp.parse("hello.xml",handler);

        }catch (Exception e){
            System.out.println(e);
        }
        List<A_B> handlers=handler.getAB();
        System.out.println(handlers);
        A_B is=new A_B();
        is.make(handlers);
        return null;
    }
}
