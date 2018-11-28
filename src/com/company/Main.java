package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        StatesStruct oiginStates = new StatesStruct();
        StatesStruct endStates = new StatesStruct();
        StatesStruct curStatesMap = oiginStates;

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("states.txt")));

        String curStr;
        while ((curStr = reader.readLine()) != null) {
            if (curStr.trim().equals("-")) {
                curStatesMap = endStates;
                continue;
            }
            curStatesMap.addStateStr(curStr);
        }
        reader.close();


        ArrayList<RulesStruct> ruls = new ArrayList<RulesStruct>();

        reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("rules.txt")));

        String curStr2;
        RulesStruct tempRule = null;
        while ((curStr2 = reader.readLine()) != null) {
            if (curStr2.charAt(0) == '-') {
                if (tempRule != null) {
                    ruls.add(tempRule);
                }
                tempRule = new RulesStruct();
            }
            tempRule.addAttribute(curStr2);
        }
        ruls.add(tempRule);
        reader.close();


        System.out.println();


    }
}
