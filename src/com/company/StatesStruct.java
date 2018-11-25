package com.company;

import java.util.HashMap;
import java.util.Map;

public class StatesStruct {
    private Map states;

    StatesStruct(){
        states = new HashMap<String, String>();
    }
    public void addState(String state, String stateValue){
        states.put(state, stateValue);
    }
    public String getStateValue(String state){
        return states.get(state).toString();
    }

    public void addStateStr(String str) {
        String[] strMas = str.split(" = ");
        states.put(strMas[0], strMas[1]);
    }
}
