package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
            boolean finishFlag = true;

            for (Map.Entry<String, String> pair : endStates.states.entrySet()) {
                String key = pair.getKey();
                String value = pair.getValue();

                String value2 = curStatesMap.states.get(key);

                if (!value.equals(value2)) {
                    finishFlag = false;
                    break;
                }
            }

            if (finishFlag) {
                System.out.println("Success");
                curStatesMap.show();
                return;
            }


            boolean oneOptionFlag = false;
            System.out.println("------------Available actions-----------");
            for (RulesStruct rule : rules) {
                if (rule.isSuitable(curStatesMap)) {

                    System.out.println(rule.name);
                }
            }
            System.out.println("----------------------------------------");

            System.out.println("Select action by name:");
            String selectedAction = in.nextLine();


            for (RulesStruct rule : rules) {
                if (rule.name.equals(selectedAction)) {
                    if (rule.options.size() == 1) {
                        oneOptionFlag = true;
                        break;
                    }
                }

            }

            if (!oneOptionFlag) {
                System.out.println("------------" + selectedAction + "-----------");
                for (RulesStruct rule : rules) {
                    if (rule.name.equals(selectedAction)) {

                        rule.printOptions();
                    }
                }
                System.out.println("----------------------------------------");


                System.out.println("Select option by index:");
                Integer selectedOption = in.nextInt();
                in.nextLine();

                applyOption(selectedAction, selectedOption, rules, curStatesMap);
            } else {
                applyOption(selectedAction, 1, rules, curStatesMap);
            }
        }

        //System.out.println();


    }

    private static void applyOption(String ruleName, Integer index, List<RulesStruct> listOfRules, StatesStruct statesMap) {
        Map<String, String> curOption = null;
        for (RulesStruct rule : listOfRules) {
            if (rule.name.equals(ruleName)) {
                curOption = rule.options.get(index - 1);
                break;
            }
        }

        statesMap.states.putAll(curOption);
    }


}
