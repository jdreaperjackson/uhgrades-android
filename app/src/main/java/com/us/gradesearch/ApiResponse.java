package com.us.gradesearch;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApiResponse {
    public int id;
    public String term;
    public String subject;
    public String catalogNbr;
    public String courseDescription;
    public String instructorLast;
    public String instructorFirst;
    public String aCount;
    public String bCount;
    public String cCount;
    public String dCount;
    public String fCount;
    public String satisfactory;
    public String dropCount;
    public String percentA;

    private static Map map = new HashMap<String,String>(){{
        put("id","Id");
        put("term","Term");
        put("subject","Subject");
        put("catalogNbr","Catalog Number");
        put("courseDescription","Class Name");
        put("instructorLast","Instructor Last Name");
        put("instructorFirst","Instructor First Name");
        put("aCount","A");
        put("bCount","B");
        put("cCount","C");
        put("dCount","D");
        put("fCount","F");
        put("satisfactory","S");
        put("dropCount","Dropped");
        put("percentA","A Percentage");

    }};


    String getField(String fieldName)
    {
        try {
            Field field = getClass().getDeclaredField(fieldName);
            return (String) map.get(fieldName)+": " +field.get(this);
        }
        catch (Exception ex){
            return "";
        }
    }
}
