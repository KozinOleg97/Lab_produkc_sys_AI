package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RulesStruct {
    private String name;
    private Map conditions;
    private List options;

    RulesStruct() {
        this.name = "name not set";
        this.conditions = new HashMap<String, String>();
        this.options = new ArrayList<HashMap<String, String>>();
    }

    public void addAttribute(String rawAttr) {
        rawAttr = rawAttr.trim();
        if (rawAttr.length() < 2) {
            throw new IllegalArgumentException("Attribute is too short: " + rawAttr);
            //return;
        }

        switch (rawAttr.charAt(0)) {
            case '-':
                name = rawAttr.substring(1);
                break;
            case 'i':
                if (rawAttr.startsWith("if")) {
                    String[] strMas = rawAttr.substring(3).split(" = | & ");
                    conditions = conditionsAdd(strMas);

                } else {
                    throw new IllegalArgumentException("Wrong attribute: " + rawAttr);
                }
                break;
            case '+':
                String[] strMas = rawAttr.substring(1).split(" = | & ");
                options.add(conditionsAdd(strMas));
                break;
            default:
                throw new IllegalArgumentException("Wrong attribute: " + rawAttr);
        }

    }

    private HashMap conditionsAdd(String[] strMas){
        if ((strMas.length % 2) != 0) {
            throw new IllegalArgumentException("Wrong attribute: " + strMas.toString());
        }
        HashMap <String, String> tempMap = new HashMap<String, String>();
        for (int i = 0; i < strMas.length; i += 2) {

            tempMap.put(strMas[i], strMas[i + 1]);
        }
        return tempMap;
    }
}
