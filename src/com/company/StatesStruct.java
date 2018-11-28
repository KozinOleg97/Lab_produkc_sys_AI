package com.company;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class StatesStruct {
    public Map<String, String> states;

    StatesStruct() {
        states = new HashMap<String, String>();
    }

    public void addState(String state, String stateValue) {
        states.put(state, stateValue);
    }

    public String getStateValue(String state) {
        return states.get(state).toString();
    }

    public void addStateStr(String str) {
        String[] strMas = str.split(" = ");
        states.put(strMas[0], strMas[1]);
    }

    public void show() {
        for (Map.Entry<String, String> pair : states.entrySet()) {
            String key = pair.getKey();
            String value = pair.getValue();
            System.out.println(key + " = " + value);
        }
    }
}
