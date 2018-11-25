package com.company;

import java.io.*;

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

        System.out.println();



    }
}
