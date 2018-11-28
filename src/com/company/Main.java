package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        StatesStruct originStates = new StatesStruct();
        StatesStruct endStates = new StatesStruct();
        StatesStruct curStatesMap = originStates;

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


        ArrayList<RulesStruct> rules = new ArrayList<RulesStruct>();

        reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream("rules.txt")));

        String curStr2;
        RulesStruct tempRule = null;
        while ((curStr2 = reader.readLine()) != null) {
            if (curStr2.charAt(0) == '-') {
                if (tempRule != null) {
                    rules.add(tempRule);
                }
                tempRule = new RulesStruct();
            }
            tempRule.addAttribute(curStr2);
        }
        rules.add(tempRule);
        reader.close();

        //--------------------------------------------------------------------------------

        curStatesMap = originStates;

        Scanner in = new Scanner(System.in);
        while (true) {
            if (curStatesMap.equals(endStates)) {
                System.out.println("Success");
                curStatesMap.show();
                return;
            }

            System.out.println("------------Available actions-----------");
            for (RulesStruct rule : rules) {
                if (rule.isSuitable(curStatesMap)) {
                    System.out.println(rule.name);
                }
            }
            System.out.println("----------------------------------------");

            System.out.println("Select action by name:");
            String selectedAction = in.nextLine();

            //TODO print & select options
        }

        //System.out.println();


    }


}
