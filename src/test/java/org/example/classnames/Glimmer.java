package org.example.classnames;

import org.example.lib.Bean;
import org.example.lib.Component;

@Component
public class Glimmer {
    private String location;      //工作室地点
    private int memberNum;        //工作室人数

    public Glimmer(){

    }
    Glimmer(String locat,int member) {
        location=locat;
        memberNum=member;
    }
    @Bean("glimmer")
    public Glimmer getGlimmer() {
        return new Glimmer("三教401", 41);
    }
}
